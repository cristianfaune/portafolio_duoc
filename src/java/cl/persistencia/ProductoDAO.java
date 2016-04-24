/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author cristian
 */
public class ProductoDAO {
    
    Connection con;
    
        public ProductoDAO(Connection con) {
        this.con = con;
    }
        
    public void registroProducto(Producto producto) {

        String sql = "{call registrar_producto(?,?,?,?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, producto.getNombre());
            cs.setString(2, producto.getDescripcion());
            cs.setString(3, producto.getModelo());
            cs.setInt(4, producto.getStock());
            cs.setString(5, producto.getRutaImagen());
            cs.setInt(6, producto.getIdCategoria());
            cs.setInt(7, producto.getIdMarca());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en ingresar un nuevo producto", e);
        }
    }
}
