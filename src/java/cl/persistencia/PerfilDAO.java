/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Perfil;
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
public class PerfilDAO {

    Connection con;

    public PerfilDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Perfil> listarPerfiles() {
        ArrayList<Perfil> list = new ArrayList<>();
        Perfil perfil;

        String sql = "{call listar_perfiles(?)}";

        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {

                perfil = new Perfil();

                perfil.setIdPerfil(rs.getInt("idPerfil"));
                perfil.setDescripcion(rs.getString("descripcion"));

                list.add(perfil);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar los perfiles", e);
        }
        return list;
    }

    public ArrayList<Perfil> listarPerfilesFiltro(int idPerfil) {
        ArrayList<Perfil> list = new ArrayList<>();
        Perfil perfil;

        String sql = "{call listar_perfiles_filtro(?,?)}";

        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            
            cs.setInt(1, idPerfil);
            
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {

                perfil = new Perfil();

                perfil.setIdPerfil(rs.getInt("idPerfil"));
                perfil.setDescripcion(rs.getString("descripcion"));

                list.add(perfil);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar los perfiles con filtro de perfil", e);
        }
        return list;
    }
}
