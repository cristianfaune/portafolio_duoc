/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

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

    public ArrayList<Producto> listarProductos() {
        Producto producto;
        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "{call listar_productos(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setStock(rs.getInt(5));
                producto.setRutaImagen(rs.getString(6));
                producto.setIdCategoria(rs.getInt(7));
                producto.setIdMarca(rs.getInt(8));

                lista.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento listar productos", e);
        }
        return lista;
    }
}
