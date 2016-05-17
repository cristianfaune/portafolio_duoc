/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author cristian
 */
public class SolicitudDAO {
    
    Connection con;

    public SolicitudDAO(Connection con) {
        this.con = con;
    }
    
    public void ModificarEstadoSolicitud(int idSolicitud, byte activa) {

        String sql = "{call modificar_estado_solicitud(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idSolicitud);
            cs.setByte(2, activa);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en modificar estado solicitud", e);
        }
    }
}
