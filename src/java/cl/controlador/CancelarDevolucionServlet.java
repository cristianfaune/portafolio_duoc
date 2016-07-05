package cl.controlador;

import cl.dominio.Usuario;
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
@WebServlet(name = "CancelarDevolucionServlet", urlPatterns = {"/CancelarDevolucionServlet"})
public class CancelarDevolucionServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (usuarioS.getIdPerfil() == 100) {
            request.getRequestDispatcher("HomeJefeCarrera.jsp").forward(request, response);
        } else if (usuarioS.getIdPerfil() == 110) {
            request.getRequestDispatcher("HomeCoordinador.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                cl.controlador.AdminDevolucionServlet.listaDev.clear();
                cl.controlador.AdminDevolucionServlet.listaHcd.clear();

                request.getRequestDispatcher("AdminDevolucion.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error en la conexión bd", e);
            }

        }

    }

}
