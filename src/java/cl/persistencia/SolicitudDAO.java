/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.DetalleSolicitud;
import cl.dominio.Solicitud;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cristian
 */
public class SolicitudDAO {

    Connection con;

    public SolicitudDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método que modifica el estado de activo a inactivo o viceversa de una
     * solicitud según su Id
     *
     * @param idSolicitud
     * @param activa
     */
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

    /**
     * Método que llama a una función. Esta devuelve un valor numérico
     * correspondiente al idSolicitud disponible para insertar, o sea el máximo
     * valor de la tabla + 1
     *
     * @return
     */
    public int idSolicitudDisponible() {

        String sql = "{? = call idsolicitud_disponible()}";
        int idSolicitud;

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1, OracleTypes.NUMBER);

            cs.executeQuery();

            idSolicitud = cs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la funcion id solicitud disponible", e);
        }
        return idSolicitud;
    }

    /**
     * Método que llama a procedimiento para almacenar detalle solicitud
     *
     * @param detalleSolicitud
     */
    public void registroDetalleSolicitud(DetalleSolicitud detalleSolicitud) {

        String sql = "{call registrar_detalle_solicitud(?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, detalleSolicitud.getRut());
            cs.setInt(2, detalleSolicitud.getIdSolicitud());
            cs.setInt(3, detalleSolicitud.getIdProducto());
            cs.setInt(4, detalleSolicitud.getCantidad());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar detalle solicitud", e);
        }
    }

    /**
     * Método que llama a procedimiento para registrar una solicitud
     * @param solicitud 
     */
    public void registroSolicitud(Solicitud solicitud) {

        String sql = "{call registrar_solicitud(?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setTimestamp(1, solicitud.getFechaSolicitud());
            cs.setInt(2, solicitud.getActiva());
            cs.setInt(3, solicitud.getSolicitudEspecial());
            cs.setInt(4, solicitud.getDiasPrestamo());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar solicitud", e);
        }
    }

}
