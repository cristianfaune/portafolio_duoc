/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Carrera;
import cl.dominio.Marca;
import cl.dominio.Perfil;
import cl.dominio.Producto;
import cl.dominio.Usuario;
import cl.dto.ProductoMarcaDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
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
public class ConsultaDAO {

    Connection con;

    public ConsultaDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList<ProductoMarcaDTO> ProductosMarcaCursor() {
        ArrayList<ProductoMarcaDTO> lista = new ArrayList<>();
        Producto producto;
        Marca marca;

        String sql = "{call producto_marca(?)}";

        CallableStatement cs = null;      
        
        try {
            
            cs = con.prepareCall(sql);
            
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            
            cs.executeQuery();
            
            ResultSet rs = (ResultSet)cs.getObject(1);
            
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

                marca = new Marca();

                marca.setIdMarca(rs.getInt(9));
                marca.setDescripcion(rs.getString(10));
                marca.setIdCategoria(rs.getInt(11));

                lista.add(new ProductoMarcaDTO(producto, marca));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento producto marca",e);
        }
                return lista;
    }
    
    public ArrayList<ProductoMarcaDTO> productosPorId(int idProducto) {
        ArrayList<ProductoMarcaDTO> lista = new ArrayList<>();
        Producto producto;
        Marca marca;

        String sql = "{call producto_por_id(?,?)}";

        CallableStatement cs = null;      
        
        try {
            
            cs = con.prepareCall(sql);
            
            cs.setInt(1, idProducto);
            
            cs.registerOutParameter(2,OracleTypes.CURSOR);
            
            cs.executeQuery();
            
            ResultSet rs = (ResultSet)cs.getObject(2);
            
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

                marca = new Marca();

                marca.setIdMarca(rs.getInt(9));
                marca.setDescripcion(rs.getString(10));
                marca.setIdCategoria(rs.getInt(11));

                lista.add(new ProductoMarcaDTO(producto, marca));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento producto por ID",e);
        }
                return lista;
    }
    
    public ArrayList<UsuarioPerfilCarreraDTO> usuarioPerfilCarrera(){
        ArrayList<UsuarioPerfilCarreraDTO> lista = new ArrayList<UsuarioPerfilCarreraDTO>();
        Usuario usuario;
        Perfil perfil;
        Carrera carrera;
        
        String sql = "{call usuario_perfil_carrera(?)}";

        CallableStatement cs = null;      
        
        try {
            
            cs = con.prepareCall(sql);
            
            cs.registerOutParameter(1,OracleTypes.CURSOR);
            
            cs.executeQuery();
            
            ResultSet rs = (ResultSet)cs.getObject(1);
            
            while (rs.next()) {
                usuario = new Usuario();

                usuario.setRut(rs.getString(1));
                usuario.setNombres(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setTelefono(rs.getInt(4));
                usuario.setDireccion(rs.getString(5));
                usuario.setEmail(rs.getString(6));
                usuario.setPassword(rs.getString(7));
                usuario.setActivo(rs.getByte(8));
                usuario.setIdPerfil(rs.getInt(9));
                usuario.setIdCarrera(rs.getInt(10));

                perfil = new Perfil();
                
                perfil.setIdPerfil(rs.getInt(11));
                perfil.setDescripcion(rs.getString(12));
                
                carrera = new Carrera();
                
                carrera.setIdCarrera(rs.getInt(13));
                carrera.setDescripcion(rs.getString(14));

                lista.add(new UsuarioPerfilCarreraDTO(usuario,perfil,carrera));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento almacenado usuario perfil carrera",e);
        }
                return lista;
    }
    
    public ArrayList<ProductoMarcaDTO> existeProducto(int idMarca, String modelo) {
        ArrayList<ProductoMarcaDTO> lista = new ArrayList<ProductoMarcaDTO>();
        Producto producto;
        Marca marca;

        String sql = "{call existe_producto(?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idMarca);
            cs.setString(2, modelo);

            cs.registerOutParameter(3, OracleTypes.CURSOR);

            cs.executeQuery();
            
            ResultSet rs = (ResultSet)cs.getObject(3);
            
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

                marca = new Marca();

                marca.setIdMarca(rs.getInt(9));
                marca.setDescripcion(rs.getString(10));
                marca.setIdCategoria(rs.getInt(11));

                lista.add(new ProductoMarcaDTO(producto, marca));
                
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento item por id", e);
        }
        
        return lista;
    }

}
