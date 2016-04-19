/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Marca;
import cl.dominio.Producto;
import cl.dto.ProductoMarcaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class ConsultaDAO {

    Connection con;

    public ConsultaDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<ProductoMarcaDTO> ProductosConMarca() {
        ArrayList<ProductoMarcaDTO> lista = new ArrayList<>();
        Producto producto;
        Marca marca;

        String sql = "SELECT * FROM PRODUCTO p JOIN MARCA m ON p.IDMARCA = m.IDMARCA";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt("p.idProducto"));
                producto.setNombre(rs.getString("p.nombre"));
                producto.setModelo(rs.getString("p.modelo"));
                producto.setDescripcion(rs.getString("p.descripcion"));
                producto.setStock(rs.getInt("p.stock"));
                producto.setRutaImagen(rs.getString("p.rutaImagen"));
                producto.setIdCategoria(rs.getInt("p.idCategoria"));
                producto.setIdMarca(rs.getInt("p.idMarca"));

                marca = new Marca();

                marca.setIdMarca(rs.getInt("m.idMarca"));
                marca.setDescripcion(rs.getString("m.descripcion"));
                marca.setIdCategoria(rs.getInt("m.idCategoria"));

                lista.add(new ProductoMarcaDTO(producto, marca));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de todos los productos con marca", e);
        }

        return lista;
    }

}
