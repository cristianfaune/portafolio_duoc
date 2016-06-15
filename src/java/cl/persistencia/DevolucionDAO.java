/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.DetalleDevolucion;
import cl.dominio.Devolucion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author cristian
 */
public class DevolucionDAO {

    Connection con;

    public DevolucionDAO(Connection con) {
        this.con = con;
    }

    public void registroDevolucion(Devolucion devolucion) {

        String sql = "{call registrar_devolucion(?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setTimestamp(1, devolucion.getFechaDevolucion());
            cs.setString(2, devolucion.getObservacion());
            cs.setInt(3, devolucion.getIdPrestamo());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar devolucion", e);
        }
    }

    public void registroDetalleDevolucion(DetalleDevolucion detalleDevolucion) {

        String sql = "{call registrar_detalle_devolucion(?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, detalleDevolucion.getRut());
            cs.setInt(2, detalleDevolucion.getIdDevolucion());
            cs.setString(3, detalleDevolucion.getNroSerie());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar detalle devolucion", e);
        }
    }

    
}
