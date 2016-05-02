/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Carrera;
import cl.dominio.Perfil;
import cl.dominio.Usuario;
import cl.persistencia.UsuarioDAO;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
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
 * @author Doterzer
 */
@WebServlet(name = "RegistroUsuarioServlet", urlPatterns = {"/RegistroUsuarioServlet"})
public class RegistroUsuarioServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            ArrayList<Carrera> lstCarreras = servicio.listarCarreras();
            ArrayList<Perfil> lstPerfiles = servicio.listarPerfiles();

            request.setAttribute("lstCarreras", lstCarreras);
            request.setAttribute("lstPerfiles", lstPerfiles);
            request.getRequestDispatcher("RegistroUsuario.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String celular = request.getParameter("celular");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        byte activo = 1;
        String perfil = request.getParameter("seleccionPerfil");
        String carrera = request.getParameter("seleccionCarrera");
        Map<String, String> mapMensaje = new HashMap<>();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);
            ArrayList<Perfil> lstPerfiles = servicio.listarPerfiles();
            ArrayList<Carrera> lstCarreras = servicio.listarCarreras();

            Usuario usuario = new Usuario();

            if (rut.isEmpty()) {
                mapMensaje.put("errorRut", "Debe ingresar rut");
            } else if (!rut.isEmpty() && rut.length() > 10) {
                mapMensaje.put("errorRut", "El valor es demasiado largo");
            } else {
                usuario.setRut(rut);
            }

            if (nombre.isEmpty()) {
                mapMensaje.put("errorNombre", "Debe ingresar su nombre");
            } else if (!nombre.isEmpty() && nombre.length() > 50) {
                mapMensaje.put("errorNombre", "El valor es demasiado largo");
            } else {
                usuario.setNombres(nombre);
            }

            if (apellido.isEmpty()) {
                mapMensaje.put("errorApellidos", "Debe ingresar su apellido");
            } else if (!apellido.isEmpty() && apellido.length() > 50) {
                mapMensaje.put("errorApellidos", "El valor es demasiado largo");
            } else {
                usuario.setApellidos(apellido);
            }

            if (!telefono.isEmpty()) {
                if (telefono.length() > 10) {
                    mapMensaje.put("errorTelefono", "el número es demasiado largo");
                } else {
                    int num = Integer.parseInt(telefono);
                    usuario.setTelefono(num);
                }
            }else{
                usuario.setTelefono(-1);
            }

            if (celular.isEmpty()) {
                mapMensaje.put("errorCelular", "Debe ingresar su celular");
            } else if (!celular.isEmpty() && celular.length() > 10) {
                mapMensaje.put("errorCelular", "El valor es demasiado largo");
            } else {
                int num = Integer.parseInt(celular);
                usuario.setCelular(num);
            }

            if (direccion.isEmpty()) {
                mapMensaje.put("errorDireccion", "Debe ingresar su direccion");
            } else if (!direccion.isEmpty() && direccion.length() > 200) {
                mapMensaje.put("errorDireccion", "El valor es demasiado largo");
            } else {
                usuario.setDireccion(direccion);
            }
            if (email.isEmpty()) {
                mapMensaje.put("errorEmail", "Debe ingresar su email");
            } else if (!email.isEmpty() && email.length() > 50) {
                mapMensaje.put("errorEmail", "El valor es demasiado largo");
            } else {
                usuario.setEmail(email);
            }

            if (password.isEmpty()) {
                mapMensaje.put("errorPassword", "Debe ingresar password");
            } else if (!password.isEmpty() && password.length() > 10) {
                mapMensaje.put("errorPassword", "Contraseña demasiado larga");
            } else {
                usuario.setPassword(password);
            }
            
            usuario.setActivo(activo);

            
            if (perfil.equals("0")) {
                mapMensaje.put("mensaje", "Todos los campos son obligatorios");
            } else {
                int idPerfil = Integer.parseInt(perfil);
                usuario.setIdPerfil(idPerfil);
            }

            if (carrera.equals("0")) {
                mapMensaje.put("mensaje", "Todos los campos son obligatorios");
            } else {
                int idCarrera = Integer.parseInt(carrera);
                usuario.setIdCarrera(idCarrera);
            }

            /**
             * if (!rut.isEmpty()) { Usuario user =
             * servicio.buscarUsuarioRut(rut); request.setAttribute("user",
             * user);
             *
             * if (rut.equals(user.getRut())) { mapMensaje.put("mensaje", "El
             * usuario esta registrados"); } else { } }
*
             */
            if (mapMensaje.isEmpty()) {

                servicio.registrarUsuario(usuario);
                mapMensaje.put("mensaje", "El usuario fue registrado con éxito");
                request.getRequestDispatcher("/AdminUsuarios.jsp").forward(request, response);
            }

            request.setAttribute("mapMensaje", mapMensaje);
            request.setAttribute("lstPerfiles", lstPerfiles);
            request.setAttribute("lstCarreras", lstCarreras);
            request.getRequestDispatcher("RegistroUsuario.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a bd", e);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
