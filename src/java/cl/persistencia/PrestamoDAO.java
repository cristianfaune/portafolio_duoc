/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.DetallePrestamo;
import cl.dominio.Item;
import cl.dominio.Prestamo;
import cl.dominio.Usuario;
import java.io.ByteArrayOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
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
    
    public Prestamo buscarPrestamoPorId(int idPrestamo) {
        Prestamo prestamo=null;

        String sql = "{call buscar_prestamo_por_id(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idPrestamo);

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
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento buscar usuario rut", e);
        }
        return prestamo;
    }
    
    public void ModificarEstadoPrestamo(int idPrestamo, byte activa) {

        String sql = "{call modificar_estado_prestamo(?,?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idPrestamo);
            cs.setByte(2, activa);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en modificar estado prestamo", e);
        }
    }

    
    public void enviarEmailPrestamo (String nombre, int idPrestamo, String email, ByteArrayOutputStream doc){
        
        final String username = "sistemapanol@gmail.com";
        final String password = "panolsis";
        String texto ="Hola " + nombre + ", tu préstamo ha sido confirmado con el número "+idPrestamo+". "
                        + "El detalle de tu pedido está adjunto a este correo. "
                        + "Porfavor conserva tu ticket de préstamo, te lo solicitarán en el "
                        + "proceso de devolución. Atte. Encargado de Pañol.";

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
                    InternetAddress.parse(email));

           
                message.setSubject("Ticket de préstamo Nº "+idPrestamo);
                MimeBodyPart textBodyPart = new MimeBodyPart();
                BodyPart messageBodyPart = new MimeBodyPart(); 
                messageBodyPart.setText(texto);
                Multipart multipart = new MimeMultipart();
                
                String applicationType = "application/pdf";
                String fileName = "Detalle préstamo pañol";
                textBodyPart = new MimeBodyPart();
                textBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(doc.toByteArray(), applicationType)));
                textBodyPart.setFileName(fileName);
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(textBodyPart);
                
                
                message.setContent(multipart);
            
            Transport.send(message);

            //System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
