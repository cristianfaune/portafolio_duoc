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
import java.sql.Timestamp;
import oracle.jdbc.OracleTypes;

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

        String sql = "{call registrar_devolucion(?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, devolucion.getIdDevolucion());
            cs.setTimestamp(2, devolucion.getFechaDevolucion());
            cs.setByte(3, devolucion.getAtraso());
            cs.setInt(4, devolucion.getIdPrestamo());

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

    public int idDevolucionDisponible() {

        String sql = "{? = call iddevolucion_disponible()}";
        int idDevolucion;

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);
            
            cs.registerOutParameter(1, OracleTypes.NUMBER);

            cs.executeQuery();

            idDevolucion = cs.getInt(1);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la funcion id devolucion disponible", e);
        }
        return idDevolucion;
    }
    
    public int determinarAtraso (Timestamp fechaEstimadaEntrega, Timestamp fechaDevolucion){
        
        long f1 = fechaEstimadaEntrega.getTime();
        long f2 = fechaDevolucion.getTime();
        int atraso = 0;
    
        if (f2 > f1) {
            atraso = 1;
        }
        
        return atraso;
    }
    
}
