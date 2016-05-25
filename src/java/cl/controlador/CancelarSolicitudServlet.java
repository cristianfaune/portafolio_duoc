package cl.controlador;

import cl.dominio.Usuario;
import cl.dto.ProductoMarcaDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author cristian
 */
@WebServlet(name = "CancelarSolicitudServlet", urlPatterns = {"/CancelarSolicitudServlet"})
public class CancelarSolicitudServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (Connection con = ds.getConnection()){
            
            HttpSession session = request.getSession();

            UsuarioPerfilCarreraDTO usuario = (UsuarioPerfilCarreraDTO) session.getAttribute("usuarioSolicitud");
            
            usuario = null;
            
            request.getRequestDispatcher("AdminSolicitudes.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a bd",e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ds.getConnection()) {
            Servicio servicio = new Servicio(con);

            ArrayList<ProductoMarcaDTO> listaProductos = servicio.productosMarcaCursor();

            cl.controlador.RegistrarSolicitudServlet.listaDtSol.clear();
            cl.controlador.RegistrarSolicitudServlet.listaProductosSolicitud.clear();

            request.setAttribute("lstProductos", listaProductos);
            request.getRequestDispatcher("RegistroSolicitud.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión bd",e);
        }

    }

}
