/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.HistorialClienteDevolucion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author cristian
 */
public class HistorialClienteDevolucionDAO {
    
    Connection con;

    public HistorialClienteDevolucionDAO(Connection con) {
        this.con = con;
    }
     
    public void registroHistorialCliente(HistorialClienteDevolucion historialCliente) {

        String sql = "{call registrar_historial_cliente(?,?,?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, historialCliente.getRut());
            cs.setInt(2, historialCliente.getIdDevolucion());
            cs.setString(3, historialCliente.getNroSerie());
            cs.setTimestamp(4, historialCliente.getFecha());
            cs.setString(5, historialCliente.getDescripcion());
            cs.setString(6, historialCliente.getObservacion());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar historial cliente", e);
        }
    }
}
