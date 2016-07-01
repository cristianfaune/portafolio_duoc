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
import java.util.ArrayList;
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

    public void registroItem(Item item) {

        String sql = "{call registrar_item(?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, item.getNroSerie());
            cs.setByte(2, item.getActivo());
            cs.setInt(3, item.getIdProducto());
            cs.setByte(4, item.getPrestamo());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en ingresar un nuevo item", e);
        }
    }

    public ArrayList<Item> itemPorId(String nroSerie) {
        Item item;
        ArrayList<Item> lista = new ArrayList<>();

        String sql = "{call item_por_id(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, nroSerie);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                item = new Item();

                item.setNroSerie(rs.getString(1));
                item.setActivo(rs.getByte(2));
                item.setIdProducto(rs.getInt(3));
                item.setPrestamo(rs.getByte(4));

                lista.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento item por id", e);
        }
        return lista;
    }
    
    public ArrayList<Item> itemPorIdProducto(int idProducto) {
        Item item;
        ArrayList<Item> lista = new ArrayList<>();

        String sql = "{call items_por_idproducto(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idProducto);

            cs.registerOutParameter(2, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(2);

            while (rs.next()) {
                item = new Item();

                item.setNroSerie(rs.getString(1));
                item.setActivo(rs.getByte(2));
                item.setIdProducto(rs.getInt(3));
                item.setPrestamo(rs.getByte(4));

                lista.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento item por id producto", e);
        }
        return lista;
    }
    
        public ArrayList<Item> itemsDisponibles(int idProducto, int cantidad) {
        Item item;
        ArrayList<Item> lista = new ArrayList<>();

        String sql = "{call items_disponibles(?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idProducto);
            cs.setInt(2, cantidad);

            cs.registerOutParameter(3, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(3);

            while (rs.next()) {
                item = new Item();

                item.setNroSerie(rs.getString(1));
                item.setActivo(rs.getByte(2));
                item.setIdProducto(rs.getInt(3));
                item.setPrestamo(rs.getByte(4));

                lista.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento item disponibles", e);
        }
        return lista;
    }
    
    public void ModificarEstadoItem(String nroSerie, byte activo) {

        String sql = "{call modificar_estado_item(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, nroSerie);
            cs.setByte(2, activo);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en modificar estado item", e);
        }
    }
    
    public void modificarEstadoPrestamoItem(String nroSerie, byte prestamo) {

        String sql = "{call modificar_prestamo_item(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, nroSerie);
            cs.setByte(2, prestamo);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en modificar estado prestamo", e);
        }
    }

}
