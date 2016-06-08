/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.DetalleSolicitud;
import cl.dominio.Carrera;
import cl.dominio.DetallePrestamo;
import cl.dominio.Item;
import cl.dominio.Marca;
import cl.dominio.Perfil;
import cl.dominio.Prestamo;
import cl.dominio.Producto;
import cl.dominio.Solicitud;
import cl.dominio.Usuario;
import cl.dto.DetallePrestamoDTO;
import cl.dto.DetalleSolicitudPrUsCaDTO;
import cl.dto.ProductoMarcaDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
import cl.dto.UsuarioPrestamoDTO;
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

                marca = new Marca();

                marca.setIdMarca(rs.getInt(9));
                marca.setDescripcion(rs.getString(10));
                marca.setIdCategoria(rs.getInt(11));

                lista.add(new ProductoMarcaDTO(producto, marca));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento producto marca", e);
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

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

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
            throw new RuntimeException("Error en el procedimiento producto por ID", e);
        }
        return lista;
    }

    public ArrayList<UsuarioPerfilCarreraDTO> usuarioPerfilCarrera(int idPerfil) {
        ArrayList<UsuarioPerfilCarreraDTO> lista = new ArrayList<UsuarioPerfilCarreraDTO>();
        Usuario usuario;
        Perfil perfil;
        Carrera carrera;

        String sql = "{call usuario_perfil_carrera(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idPerfil);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setRut(rs.getString(1));
                usuario.setNombres(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setTelefono(rs.getString(4));
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

                lista.add(new UsuarioPerfilCarreraDTO(usuario, perfil, carrera));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento almacenado usuario perfil carrera", e);
        }
        return lista;
    }

    public ArrayList<UsuarioPerfilCarreraDTO> usuarioPerfilCarreraPorRut(String rut) {
        ArrayList<UsuarioPerfilCarreraDTO> lista = new ArrayList<>();
        Usuario usuario;
        Perfil perfil;
        Carrera carrera;

        String sql = "{call usuario_perfil_carrera_por_rut(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, rut);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setRut(rs.getString(1));
                usuario.setNombres(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setTelefono(rs.getString(4));
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

                lista.add(new UsuarioPerfilCarreraDTO(usuario, perfil, carrera));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento almacenado usuario perfil carrera por rut", e);
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

            ResultSet rs = (ResultSet) cs.getObject(3);

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

    public ArrayList<DetalleSolicitudPrUsCaDTO> buscarSolicitudId(int idSolicitud) {
        ArrayList<DetalleSolicitudPrUsCaDTO> lista = new ArrayList<>();
        Producto producto;
        Marca marca;
        Usuario usuario;
        DetalleSolicitud detalleSolicitud;
        Carrera carrera;
        Solicitud solicitud;

        String sql = "{call solicitud_por_id(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idSolicitud);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                solicitud = new Solicitud();

                solicitud.setIdSolicitud(rs.getInt(1));
                solicitud.setFechaSolicitud(rs.getTimestamp(2));
                solicitud.setActiva(rs.getByte(3));
                solicitud.setSolicitudEspecial(rs.getByte(4));
                solicitud.setDiasPrestamo(rs.getInt(5));

                detalleSolicitud = new DetalleSolicitud();

                detalleSolicitud.setRut(rs.getString(6));
                detalleSolicitud.setIdSolicitud(rs.getInt(7));
                detalleSolicitud.setIdProducto(rs.getInt(8));
                detalleSolicitud.setCantidad(rs.getInt(9));

                usuario = new Usuario();

                usuario.setRut(rs.getString(10));
                usuario.setNombres(rs.getString(11));
                usuario.setApellidos(rs.getString(12));
                usuario.setTelefono(rs.getString(13));
                usuario.setDireccion(rs.getString(14));
                usuario.setEmail(rs.getString(15));
                usuario.setPassword(rs.getString(16));
                usuario.setActivo(rs.getByte(17));
                usuario.setIdPerfil(rs.getInt(18));
                usuario.setIdCarrera(rs.getInt(19));

                carrera = new Carrera();

                carrera.setIdCarrera(rs.getInt(20));
                carrera.setDescripcion(rs.getString(21));

                producto = new Producto();

                producto.setIdProducto(rs.getInt(22));
                producto.setNombre(rs.getString(23));
                producto.setModelo(rs.getString(24));
                producto.setDescripcion(rs.getString(25));
                producto.setStock(rs.getInt(26));
                producto.setRutaImagen(rs.getString(27));
                producto.setIdCategoria(rs.getInt(28));
                producto.setIdMarca(rs.getInt(29));

                marca = new Marca();

                marca.setIdMarca(rs.getInt(30));
                marca.setDescripcion(rs.getString(31));
                marca.setIdCategoria(rs.getInt(32));

                lista.add(new DetalleSolicitudPrUsCaDTO(detalleSolicitud,
                        solicitud, producto, carrera, usuario, marca));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento solicitud por id", e);
        }
        return lista;
    }

    public ArrayList<DetalleSolicitudPrUsCaDTO> totalSolicitudId(int idSolicitud) {
        ArrayList<DetalleSolicitudPrUsCaDTO> lista = new ArrayList<>();
        Producto producto;
        Marca marca;
        Usuario usuario;
        DetalleSolicitud detalleSolicitud;
        Carrera carrera;
        Solicitud solicitud;

        String sql = "{call total_solicitud_por_id(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idSolicitud);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                solicitud = new Solicitud();

                solicitud.setIdSolicitud(rs.getInt(1));
                solicitud.setFechaSolicitud(rs.getTimestamp(2));
                solicitud.setActiva(rs.getByte(3));
                solicitud.setSolicitudEspecial(rs.getByte(4));
                solicitud.setDiasPrestamo(rs.getInt(5));

                detalleSolicitud = new DetalleSolicitud();

                detalleSolicitud.setRut(rs.getString(6));
                detalleSolicitud.setIdSolicitud(rs.getInt(7));
                detalleSolicitud.setIdProducto(rs.getInt(8));
                detalleSolicitud.setCantidad(rs.getInt(9));

                usuario = new Usuario();

                usuario.setRut(rs.getString(10));
                usuario.setNombres(rs.getString(11));
                usuario.setApellidos(rs.getString(12));
                usuario.setTelefono(rs.getString(13));
                usuario.setDireccion(rs.getString(14));
                usuario.setEmail(rs.getString(15));
                usuario.setPassword(rs.getString(16));
                usuario.setActivo(rs.getByte(17));
                usuario.setIdPerfil(rs.getInt(18));
                usuario.setIdCarrera(rs.getInt(19));

                carrera = new Carrera();

                carrera.setIdCarrera(rs.getInt(20));
                carrera.setDescripcion(rs.getString(21));

                producto = new Producto();

                producto.setIdProducto(rs.getInt(22));
                producto.setNombre(rs.getString(23));
                producto.setModelo(rs.getString(24));
                producto.setDescripcion(rs.getString(25));
                producto.setStock(rs.getInt(26));
                producto.setRutaImagen(rs.getString(27));
                producto.setIdCategoria(rs.getInt(28));
                producto.setIdMarca(rs.getInt(29));

                marca = new Marca();

                marca.setIdMarca(rs.getInt(30));
                marca.setDescripcion(rs.getString(31));
                marca.setIdCategoria(rs.getInt(32));

                lista.add(new DetalleSolicitudPrUsCaDTO(detalleSolicitud,
                        solicitud, producto, carrera, usuario, marca));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento total solicitud por id", e);
        }
        return lista;
    }

    public ArrayList<DetallePrestamoDTO> buscarDetallePrestamoPorId(int idPrestamo) {
        ArrayList<DetallePrestamoDTO> lista = new ArrayList<>();
        Producto producto;
        Marca marca;
        Usuario usuario;
        DetallePrestamo detallePrestamo;
        Item item;
        Prestamo prestamo;

        String sql = "{call buscar_detalle_prestamo_por_id(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idPrestamo);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                prestamo = new Prestamo();

                prestamo.setIdPrestamo(rs.getInt(1));
                prestamo.setActiva(rs.getByte(2));
                prestamo.setFechaRetiro(rs.getTimestamp(3));
                prestamo.setFechaEstimadaEntrega(rs.getTimestamp(4));
                prestamo.setIdSolicitud(rs.getInt(5));
                prestamo.setPrestamoEspecial(rs.getByte(6));

                detallePrestamo = new DetallePrestamo();

                detallePrestamo.setRut(rs.getString(7));
                detallePrestamo.setIdPrestamo(rs.getInt(8));
                detallePrestamo.setNroSerie(rs.getString(9));

                usuario = new Usuario();

                usuario.setRut(rs.getString(10));
                usuario.setNombres(rs.getString(11));
                usuario.setApellidos(rs.getString(12));
                usuario.setTelefono(rs.getString(13));
                usuario.setDireccion(rs.getString(14));
                usuario.setEmail(rs.getString(15));
                usuario.setPassword(rs.getString(16));
                usuario.setActivo(rs.getByte(17));
                usuario.setIdPerfil(rs.getInt(18));
                usuario.setIdCarrera(rs.getInt(19));

                item = new Item();
                
                item.setNroSerie(rs.getString(20));
                item.setActivo(rs.getByte(21));
                item.setIdProducto(rs.getInt(22));
                item.setPrestamo(rs.getByte(23));

                producto = new Producto();

                producto.setIdProducto(rs.getInt(24));
                producto.setNombre(rs.getString(25));
                producto.setModelo(rs.getString(26));
                producto.setDescripcion(rs.getString(27));
                producto.setStock(rs.getInt(28));
                producto.setRutaImagen(rs.getString(29));
                producto.setIdCategoria(rs.getInt(30));
                producto.setIdMarca(rs.getInt(31));

                marca = new Marca();

                marca.setIdMarca(rs.getInt(32));
                marca.setDescripcion(rs.getString(33));
                marca.setIdCategoria(rs.getInt(34));

                lista.add(new DetallePrestamoDTO(usuario,
                        prestamo, detallePrestamo, item, producto, marca));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento detalle prestamo por id", e);
        }
        return lista;
    }
    
    public ArrayList<UsuarioPrestamoDTO> buscarUsuarioPrestamoPorId(int idPrestamo) {
        ArrayList<UsuarioPrestamoDTO> lista = new ArrayList<>();
        Usuario usuario;
        Perfil perfil;
        Carrera carrera;
        Prestamo prestamo;
        Solicitud solicitud;
        DetalleSolicitud detalleSolicitud;

        String sql = "{call buscar_usuario_prestamo_por_id(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idPrestamo);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                
                prestamo = new Prestamo();

                prestamo.setIdPrestamo(rs.getInt(1));
                prestamo.setActiva(rs.getByte(2));
                prestamo.setFechaRetiro(rs.getTimestamp(3));
                prestamo.setFechaEstimadaEntrega(rs.getTimestamp(4));
                prestamo.setIdSolicitud(rs.getInt(5));
                prestamo.setPrestamoEspecial(rs.getByte(6));
                
                solicitud = new Solicitud();

                solicitud.setIdSolicitud(rs.getInt(7));
                solicitud.setFechaSolicitud(rs.getTimestamp(8));
                solicitud.setActiva(rs.getByte(9));
                solicitud.setSolicitudEspecial(rs.getByte(10));
                solicitud.setDiasPrestamo(rs.getInt(11));

                detalleSolicitud = new DetalleSolicitud();

                detalleSolicitud.setRut(rs.getString(12));
                detalleSolicitud.setIdSolicitud(rs.getInt(13));
                detalleSolicitud.setIdProducto(rs.getInt(14));
                detalleSolicitud.setCantidad(rs.getInt(15));
                
                usuario = new Usuario();

                usuario.setRut(rs.getString(16));
                usuario.setNombres(rs.getString(17));
                usuario.setApellidos(rs.getString(18));
                usuario.setTelefono(rs.getString(19));
                usuario.setDireccion(rs.getString(20));
                usuario.setEmail(rs.getString(21));
                usuario.setPassword(rs.getString(22));
                usuario.setActivo(rs.getByte(23));
                usuario.setIdPerfil(rs.getInt(24));
                usuario.setIdCarrera(rs.getInt(25));
                
                carrera = new Carrera();

                carrera.setIdCarrera(rs.getInt(26));
                carrera.setDescripcion(rs.getString(27));

                perfil = new Perfil();

                perfil.setIdPerfil(rs.getInt(28));
                perfil.setDescripcion(rs.getString(29));

                

                lista.add(new UsuarioPrestamoDTO(prestamo, usuario, solicitud, detalleSolicitud, carrera, perfil));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento usuario prestamo por id", e);
        }
        return lista;
    }

}
