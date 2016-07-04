package cl.controlador;

import cl.dto.DetalleSolicitudPrUsCaDTO;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
@WebServlet(name = "DetalleSolicitudModalServlet", urlPatterns = {"/DetalleSolicitudModalServlet"})
public class DetalleSolicitudModalServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try (Connection con = ds.getConnection()) {

            int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));

            Servicio servicio = new Servicio(con);
            
            ArrayList<DetalleSolicitudPrUsCaDTO> lista = new ArrayList<>();
            
            lista = servicio.buscarSolicitudId(idSolicitud);
            
            //out.print("<select class='form-control' id='seleccionMarca' name='seleccionMarca'>");
            //out.print("<option value = '0'>--Seleccione una marca--</option>");
            for (DetalleSolicitudPrUsCaDTO x : lista) {
                out.print(" <p id='informacion'>'" + x.getProducto().getNombre()+ "</p>");
            }
            //out.print("</select>");
           

        } catch (SQLException e) {
            throw new RuntimeException("Error en dropdown ajax", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try (Connection con = ds.getConnection()) {

            int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));

            Servicio servicio = new Servicio(con);
            
            ArrayList<DetalleSolicitudPrUsCaDTO> lista;
            
            lista = servicio.totalSolicitudId(idSolicitud);
            
            out.print("<div id='informacion'>");
            out.print("<table class='table table-striped'>");
            out.print("<tr>");
            out.print("<th class='text-center'>Id Producto</th>");
            out.print("<th class='text-center'>Nombre</th>");
            out.print("<th class='text-center'>Marca</th>");
            out.print("<th class='text-center'>Modelo</th>");
            out.print("<th class='text-center'>Cantidad</th>");
            out.print("</tr>");
            for (DetalleSolicitudPrUsCaDTO temp : lista) {
                out.print("<tr>");
                out.print("<td><p class='text-center'>"+ temp.getProducto().getIdProducto()+"</p></td>");
                out.print("<td><p class='text-center'>"+ temp.getProducto().getNombre()+"</p></td>");
                out.print("<td><p class='text-center'>"+ temp.getMarca().getDescripcion()+"</p></td>");
                out.print("<td><p class='text-center'>"+ temp.getProducto().getModelo()+"</p></td>");
                out.print("<td><p class='text-center'>"+ temp.getDetalleSolicitud().getCantidad()+"</p></td>");
                out.print("</tr>");
            }   
            out.print("</table");
            out.print("</div>");
           

        } catch (SQLException e) {
            throw new RuntimeException("Error en dropdown ajax", e);
        }

    }

}
