/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Marca;
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
public class MarcaDAO {
    
    Connection con;

    public MarcaDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList<Marca> listarMarcas() {
        ArrayList<Marca> lista = new ArrayList<>();
        Marca marca;

        String sql = "{call listar_marcas(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1,OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                marca = new Marca();
                
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setDescripcion(rs.getString("descripcion"));
                marca.setIdCategoria(rs.getInt("idCategoria"));

                lista.add(marca);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento listar marcas", e);
        }
        return lista;
    }
}
