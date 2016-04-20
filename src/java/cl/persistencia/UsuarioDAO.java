/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Marca;
import cl.dominio.Producto;
import cl.dominio.Usuario;
import cl.dto.ProductoMarcaDTO;
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
                usuario.setTelefono(rs.getInt("telefono"));
                usuario.setCelular(rs.getInt("celular"));
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

    public ArrayList<Usuario> UsuariosProcedimiento() {
        ArrayList<Usuario> lista = new ArrayList<>();
        Usuario usuario;

        String sql = "{call listar_usuarios(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1,OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                usuario = new Usuario();

                usuario.setRut(rs.getString("rut"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setTelefono(rs.getInt("telefono"));
                usuario.setCelular(rs.getInt("celular"));
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
}
