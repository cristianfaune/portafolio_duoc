/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Carrera;
import cl.dominio.Perfil;
import cl.dominio.Usuario;
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
 * @author Doterzer
 */
@WebServlet(name = "ModificarUsuarioServlet", urlPatterns = {"/ModificarUsuarioServlet"})
public class ModificarUsuarioServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        request.setCharacterEncoding("UTF-8");

        String rut = request.getParameter("rut");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String perfil = request.getParameter("seleccionPerfil");
        Map<String, String> mapMensaje = new HashMap<>();
        Map<String, String> mapMensajeExito = new HashMap<>();

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (usuarioS.getIdPerfil() == 120) {
            request.getRequestDispatcher("HomePanolero.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);
                Usuario usuarioMod = new Usuario();

                ArrayList<Carrera> lstCarreras = servicio.listarCarreras();
                ArrayList<Perfil> lstPerfiles = servicio.listarPerfilesFiltro(usuarioS.getIdPerfil());

                usuarioMod.setRut(rut);

                if (nombres.isEmpty()) {
                    mapMensaje.put("errorNombre", "**Debe ingresar nombre**");
                } else if (!nombres.isEmpty() && nombres.length() > 50) {
                    mapMensaje.put("errorNombre", "**El valor es demasiado largo**");
                } else {
                    usuarioMod.setNombres(nombres);
                }

                if (apellidos.isEmpty()) {
                    mapMensaje.put("errorApellidos", "**Debe ingresar apellido**");
                } else if (!apellidos.isEmpty() && apellidos.length() > 50) {
                    mapMensaje.put("errorApellidos", "**El valor es demasiado largo**");
                } else {
                    usuarioMod.setApellidos(apellidos);
                }

                if (telefono.isEmpty()) {
                    mapMensaje.put("errorTelefono", "**Ingrese un teléfono**");
                } else if (telefono.length() > 10) {
                    mapMensaje.put("errorTelefono", "**El número es demasiado largo**");
                } else {
                    usuarioMod.setTelefono(telefono);
                }

                if (direccion.isEmpty()) {
                    mapMensaje.put("errorDireccion", "**Debe ingresar una dirección**");
                } else if (!direccion.isEmpty() && direccion.length() > 200) {
                    mapMensaje.put("errorDireccion", "**El valor es demasiado largo**");
                } else {
                    usuarioMod.setDireccion(direccion);
                }

                if (email.isEmpty()) {
                    mapMensaje.put("errorEmail", "**Debe ingresar un email**");
                } else if (!email.isEmpty() && email.length() > 50) {
                    mapMensaje.put("errorEmail", "**El valor es demasiado largo**");
                } else {
                    usuarioMod.setEmail(email);
                }

                if (perfil.equals("0")) {
                    mapMensaje.put("errorPerfil", "**Debe ingresar un perfil**");
                } else {
                    int idPerfil = Integer.parseInt(perfil);
                    usuarioMod.setIdPerfil(idPerfil);
                }

                if (request.getParameter("carrera") != null) {
                    int idCarrera = Integer.parseInt(request.getParameter("carrera"));
                    usuarioMod.setIdCarrera(idCarrera);
                } else {
                    usuarioMod.setIdCarrera(0);
                }

                if (mapMensaje.isEmpty()) {

                    servicio.modificarUsuario(usuarioMod);
                    mapMensajeExito.put("mensajeExito", "El usuario fue modificado con éxito");
                    request.setAttribute("mapMensajeExito", mapMensajeExito);
                    request.getRequestDispatcher("/MostrarUsuario.jsp").forward(request, response);
                } else {
                    request.setAttribute("mapMensaje", mapMensaje);
                    request.setAttribute("lstCarreras", lstCarreras);
                    request.setAttribute("lstPerfiles", lstPerfiles);
                    request.getRequestDispatcher("/MostrarUsuario.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                throw new RuntimeException("Error en la conexión a bd", e);
            }

        }

    }

}
