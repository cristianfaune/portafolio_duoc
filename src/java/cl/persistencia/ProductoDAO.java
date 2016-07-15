/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Item;
import cl.dominio.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cristian
 */
public class ProductoDAO {

    Connection con;

    public ProductoDAO(Connection con) {
        this.con = con;
    }

    public void registroProducto(Producto producto) {

        String sql = "{call registrar_producto(?,?,?,?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setString(1, producto.getNombre());
            cs.setString(2, producto.getDescripcion());
            cs.setString(3, producto.getModelo());
            cs.setInt(4, producto.getStock());
            cs.setString(5, producto.getRutaImagen());
            cs.setInt(6, producto.getIdCategoria());
            cs.setInt(7, producto.getIdMarca());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en ingresar un nuevo producto", e);
        }
    }

    public ArrayList<Producto> listarProductos() {
        Producto producto;
        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "{call listar_productos(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setStock(rs.getInt(5));
                producto.setRutaImagen(rs.getString(6));
                producto.setIdCategoria(rs.getInt(7));
                producto.setIdMarca(rs.getInt(8));

                lista.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento listar productos", e);
        }
        return lista;
    }

    public void modificarProducto(Producto producto) {

        String sql = "{call modificar_producto(?,?,?,?,?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, producto.getIdProducto());
            cs.setInt(2, producto.getIdCategoria());
            cs.setInt(3, producto.getIdMarca());
            cs.setString(4, producto.getNombre());
            cs.setString(5, producto.getModelo());
            cs.setString(6, producto.getDescripcion());

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en modificar producto", e);
        }
    }

    public int stockProducto(int idProducto) {

        String sql = "{? = call stock_producto(?)}";
        int stock;

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1, OracleTypes.NUMBER);

            cs.setInt(2, idProducto);

            cs.executeQuery();

            stock = cs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la funcion stock de producto", e);
        }
        return stock;
    }

    public ArrayList<Integer> revisarStockPrestamo(ArrayList<Item> lista) {
        ArrayList<Integer> productosSinStock = new ArrayList<>();
        int idProducto = 0;
        int stock = 0;

        for (Item x : lista) {

            idProducto = x.getIdProducto();

            stock = this.stockProducto(idProducto);

            if (stock <= 0) {
                productosSinStock.add(idProducto);
            }
        }

        for (int i = 0; i < productosSinStock.size(); i++) {
            for (int j = i + 1; j < productosSinStock.size(); j++) {
                if (productosSinStock.get(i) == productosSinStock.get(j)) {
                    productosSinStock.remove(j);
                    j--;
                }
            }
        }

        return productosSinStock;
    }

    public void alertaStockPrestamo(ArrayList<Integer> lista) {

        String cadena = "";

        for (Integer x : lista) {

            cadena += x + ", ";
        }

        final String username = "sistemapanol@gmail.com";
        final String password = "panolsis";
        String texto = "AVISO FALTA DE STOCK."
                + "Los productos con ID " + cadena + "se encuentran sin recursos"
                + " disponibles para préstamo. Por favor tomar las medidas del caso.";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sistemapanol@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(username));

            message.setSubject("Aviso de falta de stock");
            message.setText(texto);

            Transport.send(message);

            //System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Producto> menosSolicitado() {
        Producto producto;
        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "{call producto_menos_solicitado(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setStock(rs.getInt(5));
                producto.setRutaImagen(rs.getString(6));
                producto.setIdCategoria(rs.getInt(7));
                producto.setIdMarca(rs.getInt(8));

                lista.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento menos solicitado", e);
        }
        return lista;
    }

    public ArrayList<Producto> masSolicitado() {
        Producto producto;
        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "{call producto_mas_solicitado(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setStock(rs.getInt(5));
                producto.setRutaImagen(rs.getString(6));
                producto.setIdCategoria(rs.getInt(7));
                producto.setIdMarca(rs.getInt(8));

                lista.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento mas solicitado", e);
        }
        return lista;
    }

    public ArrayList<Producto> ReporteDeBaja() {
        Producto producto;

        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "";

        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {

                producto = new Producto();

                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setStock(rs.getInt(5));
                producto.setRutaImagen(rs.getString(6));
                producto.setIdCategoria(rs.getInt(7));
                producto.setIdMarca(rs.getInt(8));

                lista.add(producto);

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error Generando el reporte", e);
        }
        return lista;
    }

    public ArrayList<Producto> stockDisponible() {
        Producto producto;
        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "{call producto_stock_disponible(?)}";

        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setStock(rs.getInt(5));
                producto.setRutaImagen(rs.getString(6));
                producto.setIdCategoria(rs.getInt(7));
                producto.setIdMarca(rs.getInt(8));

                lista.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento", e);
        }
        return lista;
    }

    public ArrayList<Producto> sinStockDisponible() {
        Producto producto;
        ArrayList<Producto> lista = new ArrayList<>();

        String sql = "{call producto_stock_no_disponible(?)}";

        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.executeQuery();
            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {
                producto = new Producto();

                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setModelo(rs.getString(3));
                producto.setDescripcion(rs.getString(4));
                producto.setStock(rs.getInt(5));
                producto.setRutaImagen(rs.getString(6));
                producto.setIdCategoria(rs.getInt(7));
                producto.setIdMarca(rs.getInt(8));

                lista.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento", e);
        }
        return lista;
    }

}
