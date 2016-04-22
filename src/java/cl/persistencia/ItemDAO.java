/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Item;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cristian
 */
public class ItemDAO {
    
    Connection con;

    public ItemDAO(Connection con) {
        this.con = con;
    }
    
    public void registroItem (Item item){
        
        String sql = "{call registrar_item(?,?,?)}";
        
        CallableStatement cs = null;      
        
        try {
            
            cs = con.prepareCall(sql);
            
            cs.setString(1, item.getNroSerie());
            cs.setByte(2, item.getActivo());
            cs.setInt(3, item.getIdProducto());
            
            cs.executeQuery();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en ingresar un nuevo item",e);
        }
    }
}
