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

        String sql = "SELECT p.IDPRODUCTO as idproducto,\n" +
                     "p.NOMBRE as nombre,\n" +
                     "p.modelo as modelo,\n" +
                     "p.descripcion as productodescripcion,\n" +
                     "p.stock as stock,\n" +
                     "p.RUTAIMAGEN as rutaimagen,\n" +
                     "p.IDCATEGORIA as productoidcategoria,\n" +
                     "p.IDMARCA as productoidmarca,\n" +
                     "m.DESCRIPCION as marcadescripcion\n" +
                     "FROM producto p join marca m on p.IDMARCA = m.IDMARCA;";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt("idproducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setModelo(rs.getString("modelo"));
                producto.setDescripcion(rs.getString("productodescripcion"));
                producto.setStock(rs.getInt("stock"));
                producto.setRutaImagen(rs.getString("rutaImagen"));
                producto.setIdCategoria(rs.getInt("productoidcategoria"));
                producto.setIdMarca(rs.getInt("productoidmarca"));
                
                marca = new Marca();
                
                marca.setIdMarca(rs.getInt("productoidmarca"));
                marca.setDescripcion(rs.getString("marcadescripcion"));
                marca.setIdCategoria(rs.getInt("productoidcategoria"));

                lista.add(new ProductoMarcaDTO(producto,marca));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de todos los productos con marca", e);
        }

        return lista;
    }
    
}
