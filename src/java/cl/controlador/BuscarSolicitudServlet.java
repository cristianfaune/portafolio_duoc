package cl.controlador;

import cl.dominio.Item;
import cl.dto.DetalleSolicitudPrUsCaDTO;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "BuscarSolicitudServlet", urlPatterns = {"/BuscarSolicitudServlet"})
public class BuscarSolicitudServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idSolicitud = request.getParameter("idSolicitud");
        Map<String, String> mapMensajeSolicitud = new HashMap<>();
        ArrayList<DetalleSolicitudPrUsCaDTO> lista = new ArrayList<>();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            if (idSolicitud.isEmpty()) {
                mapMensajeSolicitud.put("errorSolicitud", "**Debe ingresar un id de solicitud**");
            } else {
                lista = servicio.buscarSolicitudId(Integer.parseInt(idSolicitud));
                if (lista.size() == 0) {
                    mapMensajeSolicitud.put("errorSolicitud", "**No se registra solicitud con el ID " + idSolicitud + ""
                            + " o no se encuentra activa**");
                }
            }

            if (mapMensajeSolicitud.isEmpty()) {
                request.setAttribute("lstSolicitud", lista);
                request.getRequestDispatcher("AdminPrestamos.jsp").forward(request, response);
            } else {
                request.setAttribute("mensajeError", mapMensajeSolicitud);
                request.getRequestDispatcher("AdminPrestamos.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idSolicitud = request.getParameter("varIdSol");
        ArrayList<Item> listaItems = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        int idProducto;
        int cantidad;

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            ArrayList<DetalleSolicitudPrUsCaDTO> lista = servicio.buscarSolicitudId(Integer.parseInt(idSolicitud));

            for (DetalleSolicitudPrUsCaDTO var : lista) {

                idProducto = var.getDetalleSolicitud().getIdProducto();
                cantidad = var.getDetalleSolicitud().getCantidad();

                listaItems = servicio.itemsDisponibles(idProducto, cantidad);

                for (Item varItem : listaItems) {
                    items.add(varItem);
                }
            }

            request.setAttribute("lstItems", items);
            request.getRequestDispatcher("AdminPrestamos.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }

}
