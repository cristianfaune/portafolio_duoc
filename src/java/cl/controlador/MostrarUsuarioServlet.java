/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        
        try (Connection con = ds.getConnection()){
            
            request.getRequestDispatcher("MostrarUsuario.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a la bd",e);
        }
    }
    


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.setCharacterEncoding("UTF-8");
       
       String rut = request.getParameter("rut");
       Map<String, String> mapMensajeRut = new HashMap<>();
       
       
       
               try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            Usuario usuario = servicio.buscarUsuarioRut(rut);

            if (rut.isEmpty() || rut == null) {
                mapMensajeRut.put("errorRut", "Rut Vacio");
            }else
            if (usuario == null) {
                mapMensajeRut.put("errorRut", "El rut ingresado no existe");
            }
            
            if (mapMensajeRut.isEmpty()) {

                request.setAttribute("usuario", usuario);
                    String estado="";
                    String nombre = usuario.getNombres();
                    String apellido = usuario.getApellidos();
                    String telefo = Integer.toString(usuario.getTelefono());
                    String celular =Integer.toString(usuario.getCelular());
                    String direccion = usuario.getDireccion();
                    String email = usuario.getEmail();
                    if(usuario.getActivo() == 1){
                    estado = "Activo";    
                    }else{
                    estado = "Inactivo";
                    }
                    
                    request.setAttribute("nombre", nombre);
                    request.setAttribute("apellido", apellido);
                    request.setAttribute("telefo", telefo);
                    request.setAttribute("celular", celular);
                    request.setAttribute("direccion", direccion);
                    request.setAttribute("email", email);
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("MostrarUsuario.jsp").forward(request, response);
                }else {
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
