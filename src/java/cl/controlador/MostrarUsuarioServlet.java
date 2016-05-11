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
@WebServlet(name = "MostrarUsuarioServlet", urlPatterns = {"/MostrarUsuarioServlet"})
public class MostrarUsuarioServlet extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ds.getConnection()) {

            request.getRequestDispatcher("MostrarUsuario.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a la bd", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");
        
        request.setCharacterEncoding("UTF-8");

        String rut = request.getParameter("rut");
        Map<String, String> mapMensajeRut = new HashMap<>();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            ArrayList<Carrera> lstCarreras = servicio.listarCarreras();
            ArrayList<Perfil> lstPerfiles = servicio.listarPerfilesFiltro(usuarioS.getIdPerfil());

            Usuario usuarioBusqueda = new Usuario();
                    
            ArrayList<Usuario> lista = servicio.buscarUsuarioRutFiltro(rut, usuarioS.getIdPerfil());
            
            for (Usuario usuario : lista) {
                usuarioBusqueda.setRut(usuario.getRut());
                usuarioBusqueda.setNombres(usuario.getNombres());
                usuarioBusqueda.setApellidos(usuario.getApellidos());
                usuarioBusqueda.setTelefono(usuario.getTelefono());
                usuarioBusqueda.setDireccion(usuario.getDireccion());
                usuarioBusqueda.setEmail(usuario.getEmail());
                usuarioBusqueda.setPassword(usuario.getPassword());
                usuarioBusqueda.setActivo(usuario.getActivo());
                usuarioBusqueda.setIdPerfil(usuario.getIdPerfil());
                usuarioBusqueda.setIdCarrera(usuario.getIdCarrera());
            }

            if (rut.isEmpty() || rut == null) {
                mapMensajeRut.put("errorRut", "**Debe ingresar un rut**");
            } else if (lista.size()== 0) {
                mapMensajeRut.put("errorRut", "**El usuario no está registrado o no poseee las credenciales"
                        + " para modificarlo**");
            }

            if (mapMensajeRut.isEmpty()) {

                request.setAttribute("lstCarreras", lstCarreras);
                request.setAttribute("lstPerfiles", lstPerfiles);
                request.setAttribute("usuarioBusqueda", usuarioBusqueda);
                request.getRequestDispatcher("MostrarUsuario.jsp").forward(request, response);
            } else {
                request.setAttribute("mapMensajeRut", mapMensajeRut);
                request.getRequestDispatcher("MostrarUsuario.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("error en la conexion", e);
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
