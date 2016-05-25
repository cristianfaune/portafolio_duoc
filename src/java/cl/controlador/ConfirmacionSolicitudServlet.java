package cl.controlador;

import cl.dominio.DetalleSolicitud;
import cl.dominio.Solicitud;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author cristian
 */
@WebServlet(name = "ConfirmacionSolicitudServlet", urlPatterns = {"/ConfirmacionSolicitudServlet"})
public class ConfirmacionSolicitudServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ds.getConnection()) {

            request.setAttribute("listaProductos", cl.controlador.RegistrarSolicitudServlet.listaProductosSolicitud);
            request.setAttribute("listaDetalle", cl.controlador.RegistrarSolicitudServlet.listaDtSol);
            request.getRequestDispatcher("ConfirmacionSolicitud.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a bd", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String diasPrestamo = request.getParameter("diasPrestamo");
        String especial = request.getParameter("especial");
        Solicitud solicitud = new Solicitud();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            //int idSolicitud = servicio.idSolicitudDisponible();
            solicitud.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));

            if (Integer.parseInt(especial) == 1) {
                solicitud.setActiva((byte) 0);
            } else {
                solicitud.setActiva((byte) 1);
            }

            solicitud.setSolicitudEspecial((byte) Integer.parseInt(especial));

            if (Integer.parseInt(especial) == 1) {
                solicitud.setDiasPrestamo(Integer.parseInt(diasPrestamo));
            } else {
                solicitud.setDiasPrestamo(1);
            }

            servicio.registrarSolicitud(solicitud);
            
            for (DetalleSolicitud detSol : cl.controlador.RegistrarSolicitudServlet.listaDtSol) {
                DetalleSolicitud detalleS = new DetalleSolicitud();

                detalleS.setIdSolicitud(detSol.getIdSolicitud());
                detalleS.setIdProducto(detSol.getIdProducto());
                detalleS.setRut(detSol.getRut());
                detalleS.setCantidad(detSol.getCantidad());

                servicio.registrarDetalleSolicitud(detalleS);
            }
            
            cl.controlador.RegistrarSolicitudServlet.listaDtSol.clear();
            cl.controlador.RegistrarSolicitudServlet.listaProductosSolicitud.clear();
            
            int idUltimaSolicitud = servicio.idUltimaSolicitud();
            
            request.setAttribute("ultimoIdSolicitud", idUltimaSolicitud);
            request.getRequestDispatcher("RegistroSolicitudExitosa.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion a bd", e);
        }

    }

}
