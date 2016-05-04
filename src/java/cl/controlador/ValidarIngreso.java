package cl.controlador;

import cl.dominio.Usuario;
import cl.servicio.Servicio;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
 * @author CristianFaune
 */
@WebServlet(name = "ValidarIngreso", urlPatterns = {"/ValidarIngreso"})
public class ValidarIngreso extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String rut = request.getParameter("rut");
        String password = request.getParameter("password");
        Map<String, String> mapMensajeRut = new HashMap<>();
        Map<String, String> mapMensajePass = new HashMap<>();
        HttpSession session = request.getSession();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            Usuario usuario = servicio.buscarUsuarioRut(rut);

            if (rut.isEmpty() || rut == null) {
                mapMensajeRut.put("errorRut", "Debe ingresar su RUT");
            } else if (usuario == null) {
                mapMensajeRut.put("errorRut", "El usuario rut " + rut + " no existe");
            }

            if (password.isEmpty() || password == null) {
                mapMensajePass.put("errorPass", "Debe ingresar su PASSWORD");
            } else if (usuario != null) {
                if (!usuario.getPassword().equals(password)) {
                    mapMensajePass.put("errorPass", "Su password no coincide con el registro");
                }
            }

            if (mapMensajePass.isEmpty() && mapMensajeRut.isEmpty()) {

                session.setAttribute("usuarioSesion", usuario);

                if (usuario.getIdPerfil() == 100 && usuario.getActivo() == 1) {
                    request.getRequestDispatcher("HomeJefeCarrera.jsp").forward(request, response);
                }

                if (usuario.getIdPerfil() == 120 && usuario.getActivo() == 1) {
                    request.getRequestDispatcher("HomePanolero.jsp").forward(request, response);
                }

                if (usuario.getIdPerfil() == 110 && usuario.getActivo() == 1) {
                    request.getRequestDispatcher("HomeCoordinador.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("mapMensajePass", mapMensajePass);
                request.setAttribute("mapMensajeRut", mapMensajeRut);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("error en la conexion", e);
        }

    }

}
