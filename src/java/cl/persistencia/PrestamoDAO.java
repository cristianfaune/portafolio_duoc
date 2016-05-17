/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.DetallePrestamo;
import cl.dominio.Prestamo;
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
public class PrestamoDAO {
    
    Connection con;

    public PrestamoDAO(Connection con) {
        this.con = con;
    }
    
    public void registroPrestamo(Prestamo prestamo, int cantidadDias) {

        String sql = "{call registrar_prestamo(?,?,?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setByte(1, prestamo.getActiva());
            cs.setTimestamp(2, prestamo.getFechaRetiro());
            cs.setTimestamp(3, prestamo.getFechaEstimadaEntrega());
            cs.setInt(4, prestamo.getIdSolicitud());
            cs.setByte(5, prestamo.getPrestamoEspecial());
            cs.setInt(6, cantidadDias);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar prestamo", e);
        }
    }
    
    public ArrayList<Prestamo> prestamoPorIdSolicitud(int idSolicitud) {
        Prestamo prestamo;
        ArrayList<Prestamo> lista = new ArrayList<>();

        String sql = "{call listar_prestamo_idsolicitud(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idSolicitud);

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

                lista.add(prestamo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento prestamo por id solicitud", e);
        }
        return lista;
    }
    
    public void registroDetallePrestamo(DetallePrestamo detallePrestamo) {

        String sql = "{call registrar_detalle_prestamo(?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, detallePrestamo.getRut());
            cs.setInt(2, detallePrestamo.getIdPrestamo());
            cs.setString(3, detallePrestamo.getNroSerie());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar detalle prestamo", e);
        }
    }
    
    public int idUltimoPrestamo() {

        String sql = "{? = call id_ultimo_prestamo()}";
        int idPrestamo;

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);
            
            cs.registerOutParameter(1, OracleTypes.NUMBER);

            cs.executeQuery();

            idPrestamo = cs.getInt(1);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la funcion id ultimo prestamo", e);
        }
        return idPrestamo;
    }
    
}
