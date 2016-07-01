package cl.controlador;

import cl.dominio.DetalleDevolucion;
import cl.dominio.Devolucion;
import cl.dominio.HistorialClienteDevolucion;
import cl.dominio.Prestamo;
import cl.dominio.Usuario;
import cl.dto.DetallePrestamoDTO;
import cl.persistencia.DevolucionDAO;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
@WebServlet(name = "ConfirmarDevolucionServlet", urlPatterns = {"/ConfirmarDevolucionServlet"})
public class ConfirmarDevolucionServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idPrestamo = request.getParameter("idPrestamoDev");
        Devolucion devolucion = new Devolucion();
        ArrayList<DetallePrestamoDTO> lista = new ArrayList();
        Prestamo prestamo = new Prestamo();
        ArrayList<DetalleDevolucion> detDev = cl.controlador.AdminDevolucionServlet.listaDev;
        ArrayList<HistorialClienteDevolucion> detHcd = cl.controlador.AdminDevolucionServlet.listaHcd;
        Usuario us = new Usuario();
        String rutCliente = "";
        int idDevolucion = 0;
        int atraso = 0;

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            DevolucionDAO devDAO = new DevolucionDAO(con);

            lista = servicio.buscarDetallePrestamoPorId(Integer.parseInt(idPrestamo));
            
            ArrayList<Usuario> listaUs = new ArrayList<>();

            for (int i = 0; i <= 0; i++) {

                prestamo = lista.get(i).getPrestamo();
                
                rutCliente = detHcd.get(i).getRut();
                
                listaUs = servicio.buscarUsuarioRut(rutCliente);
                
                us = listaUs.get(i);
                
                idDevolucion = detDev.get(i).getIdDevolucion();
            }

            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
            Timestamp fechaEstimadaEntrega = prestamo.getFechaEstimadaEntrega();

            devolucion.setIdPrestamo(Integer.parseInt(idPrestamo));
            devolucion.setFechaDevolucion(fechaActual);
            devolucion.setIdDevolucion(idDevolucion);

            atraso = devDAO.determinarAtraso(fechaEstimadaEntrega, fechaActual);

            devolucion.setAtraso((byte) atraso);

            servicio.registroDevolucion(devolucion);

            for (DetalleDevolucion x : detDev) {

                servicio.registroDetalleDevolucion(x);
            }

            for (HistorialClienteDevolucion y : detHcd) {

                servicio.registroHistorialCliente(y);

                if (y.getDescripcion().equals("dañado") || y.getDescripcion().equals("no devuelto")) {
                    servicio.modificarEstadoItem(y.getNroSerie(), (byte) 0);
                    servicio.modificarEstadoPrestamoItem(y.getNroSerie(), (byte) 0);
                } else {
                    servicio.modificarEstadoPrestamoItem(y.getNroSerie(), (byte) 0);
                }
            }

            servicio.ModificarEstadoPrestamo(Integer.parseInt(idPrestamo), (byte) 0);

            request.setAttribute("idDev", idDevolucion);
            request.setAttribute("usuarioCliente", us);
            request.setAttribute("detalleCliente", detHcd);
            request.getRequestDispatcher("ConfirmacionDevolucionFinalizada.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a bd", e);
        }

    }

}
