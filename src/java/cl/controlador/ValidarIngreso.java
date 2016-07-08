package cl.controlador;

import cl.dominio.Usuario;
import cl.servicio.Servicio;
import java.io.IOException;
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
        
        HttpSession session = request.getSession();

        //Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");
        
        session.removeAttribute("usuarioSesion");
        session.invalidate();

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
        Usuario usuarioSesion = null;

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            ArrayList<Usuario> lista = servicio.buscarUsuarioRut(rut.toUpperCase());

            for (Usuario usuario : lista) {
                usuarioSesion = new Usuario();
                usuarioSesion.setRut(usuario.getRut());
                usuarioSesion.setNombres(usuario.getNombres());
                usuarioSesion.setApellidos(usuario.getApellidos());
                usuarioSesion.setTelefono(usuario.getTelefono());
                usuarioSesion.setDireccion(usuario.getDireccion());
                usuarioSesion.setEmail(usuario.getEmail());
                usuarioSesion.setPassword(usuario.getPassword());
                usuarioSesion.setActivo(usuario.getActivo());
                usuarioSesion.setIdPerfil(usuario.getIdPerfil());
                usuarioSesion.setIdCarrera(usuario.getIdCarrera());
            }

            if (rut.isEmpty() || rut == null) {
                mapMensajeRut.put("errorRut", "**Ingrese su rut**");
            } else if (usuarioSesion == null) {
                mapMensajeRut.put("errorRut", "**Rut " + rut + " no existe**");
            }

            if (password.isEmpty() || password == null) {
                mapMensajePass.put("errorPass", "**Ingrese su password**");
            } else if (usuarioSesion != null) {
                if (!usuarioSesion.getPassword().equals(password)) {
                    mapMensajePass.put("errorPass", "**Su password no coincide**");
                }
            }
            

            if (mapMensajePass.isEmpty() && mapMensajeRut.isEmpty()) {

                session.setAttribute("usuarioSesion", usuarioSesion);               

                if (usuarioSesion.getIdPerfil() == 100 && usuarioSesion.getActivo() == 1) {
                    request.getRequestDispatcher("HomeJefeCarrera.jsp").forward(request, response);
                } else if (usuarioSesion.getIdPerfil() == 120 && usuarioSesion.getActivo() == 1) {
                    request.getRequestDispatcher("HomePanolero.jsp").forward(request, response);
                } else if (usuarioSesion.getIdPerfil() == 110 && usuarioSesion.getActivo() == 1) {
                    request.getRequestDispatcher("HomeCoordinador.jsp").forward(request, response);
                } else {
                    session.removeAttribute("usuarioSesion");
                    request.getRequestDispatcher("ErrorUsuarioInactivo.jsp").forward(request, response);
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
