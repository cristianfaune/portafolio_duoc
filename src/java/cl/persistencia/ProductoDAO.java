/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class ProductoDAO {
    
    Connection con;
    
        public ProductoDAO(Connection con) {
        this.con = con;
    }
        
        public ArrayList<Producto> buscarTodosLosProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        Producto producto;

        String sql = "select * from Producto";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setModelo(rs.getString("modelo"));
                producto.setStock(rs.getInt("stock"));
                producto.setIdCategoria(rs.getInt("idCategoria"));
                producto.setIdMarca(rs.getInt("idMarca"));

                lista.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de todos los procedimientos", e);
        }

        return lista;
    }
}
