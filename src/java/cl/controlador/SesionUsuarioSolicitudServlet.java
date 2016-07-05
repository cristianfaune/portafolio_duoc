package cl.controlador;

import cl.dominio.Carrera;
import cl.dominio.Perfil;
import cl.dominio.Usuario;
import cl.dto.UsuarioPerfilCarreraDTO;
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author cristian
 */
@WebServlet(name = "SesionUsuarioSolicitudServlet", urlPatterns = {"/SesionUsuarioSolicitudServlet"})
public class SesionUsuarioSolicitudServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String rut = request.getParameter("rut");
        HttpSession sessionSolicitud = request.getSession();
        Map<String, String> mapMensajeRut = new HashMap<>();

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                Usuario usuarioBusqueda = new Usuario();
                Carrera carrera = new Carrera();
                Perfil perfil = new Perfil();
                UsuarioPerfilCarreraDTO usuarioSolicitud = null;

                ArrayList<UsuarioPerfilCarreraDTO> lista = servicio.usuarioPerfilCarreraPorRut(rut);

                for (UsuarioPerfilCarreraDTO usuario : lista) {

                    usuarioBusqueda.setRut(usuario.getUsuario().getRut());
                    usuarioBusqueda.setNombres(usuario.getUsuario().getNombres());
                    usuarioBusqueda.setApellidos(usuario.getUsuario().getApellidos());
                    usuarioBusqueda.setTelefono(usuario.getUsuario().getTelefono());
                    usuarioBusqueda.setDireccion(usuario.getUsuario().getDireccion());
                    usuarioBusqueda.setEmail(usuario.getUsuario().getEmail());
                    usuarioBusqueda.setPassword(usuario.getUsuario().getPassword());
                    usuarioBusqueda.setActivo(usuario.getUsuario().getActivo());
                    usuarioBusqueda.setIdPerfil(usuario.getUsuario().getIdPerfil());
                    usuarioBusqueda.setIdCarrera(usuario.getUsuario().getIdCarrera());

                    carrera.setIdCarrera(usuario.getCarrera().getIdCarrera());
                    carrera.setDescripcion(usuario.getCarrera().getDescripcion());

                    perfil.setIdPerfil(usuario.getPerfil().getIdPerfil());
                    perfil.setDescripcion(usuario.getPerfil().getDescripcion());

                    usuarioSolicitud = new UsuarioPerfilCarreraDTO(usuarioBusqueda, perfil, carrera);
                }

                if (rut.isEmpty() || rut == null) {
                    mapMensajeRut.put("errorRut", "**Debe ingresar un rut**");
                } else if (lista.size() == 0) {
                    mapMensajeRut.put("errorRut", "**El usuario no está registrado o no poseee las credenciales"
                            + " para modificarlo**");
                }

                if (mapMensajeRut.isEmpty()) {

                    sessionSolicitud.setAttribute("usuarioSolicitud", usuarioSolicitud);
                    request.getRequestDispatcher("AdminSolicitudes.jsp").forward(request, response);
                } else {
                    request.setAttribute("mapMensajeRut", mapMensajeRut);
                    request.getRequestDispatcher("AdminSolicitudes.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                throw new RuntimeException("error en la conexion", e);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
