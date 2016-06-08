package cl.controlador;

import cl.dto.DetallePrestamoDTO;
import cl.dto.UsuarioPrestamoDTO;
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
@WebServlet(name = "AdminDevolucionServlet", urlPatterns = {"/AdminDevolucionServlet"})
public class AdminDevolucionServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPrestamo = request.getParameter("idPrestamo");
        Map<String, String> mapMensaje = new HashMap<>();
        ArrayList<DetallePrestamoDTO> lista = new ArrayList();
        ArrayList<UsuarioPrestamoDTO> listaUsuario = new ArrayList();

        try (Connection con = ds.getConnection()) {
            
            Servicio servicio = new Servicio(con);
            

            if (idPrestamo.isEmpty()) {
                mapMensaje.put("errorPrestamo", "**Debe ingresar el número de ticket**");
            }else{
                lista = servicio.buscarDetallePrestamoPorId(Integer.parseInt(idPrestamo));
                listaUsuario = servicio.buscarUsuarioPrestamoPorId(Integer.parseInt(idPrestamo));
            }
            
            
            if (mapMensaje.isEmpty()) {
                
                request.setAttribute("lstUsuarioPrestamo", listaUsuario);
                request.setAttribute("lstDetallePrestamo", lista);
                request.getRequestDispatcher("AdminDevolucion.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a bd",e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
