/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Carrera;
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
public class CarreraDAO {

    Connection con;

    public CarreraDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Carrera> listarCarreras() {
        ArrayList<Carrera> list = new ArrayList<>();
        Carrera carrera;

        String sql = "{call listar_carreras(?)}";

        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {

                carrera = new Carrera();

                carrera.setIdCarrera(rs.getInt("idCarrera"));
                carrera.setDescripcion(rs.getString("descripcion"));

                list.add(carrera);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar las carreras", e);
        }
        return list;
    }

}
