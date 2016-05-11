/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cristian
 */
public class UsuarioDAO {

    Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Usuario> buscarTodosLosUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        Usuario usuario;

        String sql = "select * from usuario";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setRut(rs.getString("rut"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setActivo(rs.getByte("activo"));
                usuario.setIdPerfil(rs.getInt("idPerfil"));
                usuario.setIdCarrera(rs.getInt("idCarrera"));

                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de todos los usuarios", e);
        }

        return lista;
    }

    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        Usuario usuario;

        String sql = "{call listar_usuarios(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setRut(rs.getString("rut"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setActivo(rs.getByte("activo"));
                usuario.setIdPerfil(rs.getInt("idPerfil"));
                usuario.setIdCarrera(rs.getInt("idCarrera"));

                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento almacenado usuario", e);
        }
        return lista;
    }

    public ArrayList<Usuario> buscarUsuarioRut(String rut) {
        ArrayList<Usuario> lista = new ArrayList<>();
        Usuario usuario;

        String sql = "{call usuario_rut(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, rut);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setRut(rs.getString("rut"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setActivo(rs.getByte("activo"));
                usuario.setIdPerfil(rs.getInt("idPerfil"));
                usuario.setIdCarrera(rs.getInt("idCarrera"));

                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento buscar usuario rut", e);
        }
        return lista;
    }
    
    public ArrayList<Usuario> buscarUsuarioRutFiltro(String rut, int idPerfil) {
        ArrayList<Usuario> lista = new ArrayList<>();
        Usuario usuario;

        String sql = "{call buscar_usuario_rut_filtro(?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, rut);
            cs.setInt(2, idPerfil);

            cs.registerOutParameter(3, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(3);

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setRut(rs.getString("rut"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setActivo(rs.getByte("activo"));
                usuario.setIdPerfil(rs.getInt("idPerfil"));
                usuario.setIdCarrera(rs.getInt("idCarrera"));

                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento buscar usuario rut filtro", e);
        }
        return lista;
    }

    
    public void ModificarEstadoUsuario(String rut, byte activar) {

        String sql = "{call modificar_estado_usuario(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);
            cs.setString(1, rut);
            cs.setByte(2, activar);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en modificar estado usuario", e);
        }
    }

    public void ModificarUsuario(Usuario usuario) {

        String sql = "{call modificar_usuario(?,?,?,?,?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, usuario.getRut());
            cs.setString(2, usuario.getNombres());
            cs.setString(3, usuario.getApellidos());
            cs.setString(4, usuario.getTelefono());
            cs.setString(5, usuario.getDireccion());
            cs.setString(6, usuario.getEmail());
            cs.setInt(7, usuario.getIdPerfil());
            cs.setInt(8, usuario.getIdCarrera());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en modificar usuario", e);
        }
    }

    public void registroUsuario(Usuario usuario) {

        String sql = "{call registrar_usuario(?,?,?,?,?,?,?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, usuario.getRut());
            cs.setString(2, usuario.getNombres());
            cs.setString(3, usuario.getApellidos());
            cs.setString(4, usuario.getTelefono());
            cs.setString(5, usuario.getDireccion());
            cs.setString(6, usuario.getEmail());
            cs.setString(7, usuario.getPassword());
            cs.setByte(8, usuario.getActivo());
            cs.setInt(9, usuario.getIdPerfil());
            cs.setInt(10, usuario.getIdCarrera());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en ingresar un nuevo usuario", e);
        }
    }
}
