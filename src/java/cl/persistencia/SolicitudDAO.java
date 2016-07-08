/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.DetalleSolicitud;
import cl.dominio.Producto;
import cl.dominio.Solicitud;
import cl.dominio.Usuario;
import cl.dto.SolicitudUsuarioDTO;
import java.io.ByteArrayOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
     * El método devuelve el último id solicitud generado
     *
     * @return
     */
    public int idUltimaSolicitud() {

        String sql = "{? = call id_ultima_solicitud()}";
        int idSolicitud;

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1, OracleTypes.NUMBER);

            cs.executeQuery();

            idSolicitud = cs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la funcion id ultima solicitud", e);
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
     *
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

    public void enviarEmailSolicitud(String nombre, int idSolicitud, String email, ByteArrayOutputStream doc, String especial) {

        final String username = "sistemapanol@gmail.com";
        final String password = "panolsis";
        String texto1 = "Hola " + nombre + ", gracias por utilizar nuestro sistema de solicitudes. "
                + "Su pedido se realizó de forma exitosa, ahora debes pasar por pañol"
                + " y hacer efectivo tu préstamo con el número " + idSolicitud + ". "
                + " Una vez validado el stock de tu solicitud podrás retirar tus productos.";

        String texto2 = "Hola " + nombre + ", gracias por utilizar nuestro sistema de solicitudes. "
                + "Su pedido se realizó de forma exitosa con el ID " + idSolicitud + ", ahora debes"
                + " esperar la autorización y activación por parte"
                + " de tu jefe de carrera debido a que se trata de una SOLICITUD ESPECIAL."
                + " Una vez validado te llegará un correo con los pasos a seguir para finalizar el proceso.";

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

            message.setSubject("Solicitud Pañol Nº " + idSolicitud);
            MimeBodyPart textBodyPart = new MimeBodyPart();
            BodyPart messageBodyPart = new MimeBodyPart();
            
            if (especial.equals("0")) {
                messageBodyPart.setText(texto1);
            } else {
                messageBodyPart.setText(texto2);
            }

            Multipart multipart = new MimeMultipart();

            String applicationType = "application/pdf";
            String fileName = "solicitud pañol";
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

    public void avisoAutorizacionSolicitud(int idSolicitud, Usuario usuario) {

        final String username = "sistemapanol@gmail.com";
        final String password = "panolsis";
        String texto = "Hola " + usuario.getNombres() + " " + usuario.getApellidos() + ", "
                + "Su solicitud fue AUTORIZADA, ahora debes pasar por pañol"
                + " y hacer efectivo tu préstamo con el número " + idSolicitud + ". "
                + " Una vez validado el stock de tu solicitud podrás retirar tus productos.";

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
                    InternetAddress.parse(usuario.getEmail()));

            message.setSubject("Solicitud especial Nº " + idSolicitud + " aprobada");
            message.setText(texto);

            Transport.send(message);

            //System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void avisoNegacionSolicitud(int idSolicitud, Usuario usuario) {

        final String username = "sistemapanol@gmail.com";
        final String password = "panolsis";
        String texto = "Hola " + usuario.getNombres() + " " + usuario.getApellidos() + ", "
                + "Su solicitud nº " + idSolicitud + " fue NEGADA."
                + "Para mayor información dirigirse a dirección de su carrera, "
                + " o puede contactarse vía telefónica al 800 215001 "
                + "- desde Celulares : 229153439";

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
                    InternetAddress.parse(usuario.getEmail()));

            message.setSubject("Solicitud especial Nº " + idSolicitud + " negada");
            message.setText(texto);

            Transport.send(message);

            //System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<SolicitudUsuarioDTO> listarSolicitudesEspeciales() {
        Usuario usuario;
        Solicitud solicitud;
        ArrayList<SolicitudUsuarioDTO> lista = new ArrayList<>();

        String sql = "{call listar_solicitudes_especiales(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.registerOutParameter(1, OracleTypes.CURSOR);

            cs.executeQuery();

            ResultSet rs = (ResultSet) cs.getObject(1);

            while (rs.next()) {

                solicitud = new Solicitud();

                solicitud.setIdSolicitud(rs.getInt(1));
                solicitud.setFechaSolicitud(rs.getTimestamp(2));
                solicitud.setActiva(rs.getByte(3));
                solicitud.setSolicitudEspecial(rs.getByte(4));
                solicitud.setDiasPrestamo(rs.getInt(5));

                usuario = new Usuario();

                usuario.setRut(rs.getString(6));
                usuario.setNombres(rs.getString(7));
                usuario.setApellidos(rs.getString(8));
                usuario.setTelefono(rs.getString(9));
                usuario.setDireccion(rs.getString(10));
                usuario.setEmail(rs.getString(11));
                usuario.setPassword(rs.getString(12));
                usuario.setActivo(rs.getByte(13));
                usuario.setIdPerfil(rs.getInt(14));
                usuario.setIdCarrera(rs.getInt(15));

                lista.add(new SolicitudUsuarioDTO(usuario, solicitud));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en el procedimiento listar solicitudes especiales", e);
        }
        return lista;
    }

    public void activarSolicitudEspecial(int idSolicitud) {

        String sql = "{call activar_solicitud_especial(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idSolicitud);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en activar solicitud especial", e);
        }
    }

    public void NegarSolicitudEspecial(int idSolicitud) {

        String sql = "{call negar_solicitud_especial(?)}";

        CallableStatement cs = null;

        try {

            cs = con.prepareCall(sql);

            cs.setInt(1, idSolicitud);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException("Error en negar solicitud especial", e);
        }
    }
}
