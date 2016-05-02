/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
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
        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        int celular = Integer.parseInt(request.getParameter("celular"));
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        Map<String, String> mapMensaje = new HashMap<>();
        
        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);
        
                    if (rut.isEmpty()) {
                mapMensaje.put("errorRut", "Debe ingresar rut");
            } else if (!rut.isEmpty() && rut.length() > 10) {
                mapMensaje.put("errorRut", "El valor es demasiado largo");
            }
            if (nombre.isEmpty()) {
                mapMensaje.put("errorNombre", "Debe ingresar su nombre");
            } else if (!nombre.isEmpty() && nombre.length() > 50) {
                mapMensaje.put("errorNombre", "El valor es demasiado largo");
            } else {

            }

            if (apellido.isEmpty()) {
                mapMensaje.put("errorApellidos", "Debe ingresar su apellido");
            } else if (!apellido.isEmpty() && apellido.length() > 50) {
                mapMensaje.put("errorApellidos", "El valor es demasiado largo");
            } else {
            }

            if (!Integer.toString(telefono).isEmpty()) {
                if (Integer.toString(telefono).length() > 10) {
                    mapMensaje.put("errorTelefono", "el número es demasiado largo");
                } else{
                telefono = -1;
            }
            }

            if (Integer.toString(celular).isEmpty()) {
                mapMensaje.put("errorCelular", "Debe ingresar su celular");
            } else if (!Integer.toString(celular).isEmpty() && Integer.toString(celular).length() > 10) {
                mapMensaje.put("errorCelular", "El valor es demasiado largo");
            }

            if (direccion.isEmpty()) {
                mapMensaje.put("errorDireccion", "Debe ingresar su direccion");
            } else if (!direccion.isEmpty() && direccion.length() > 200) {
                mapMensaje.put("errorDireccion", "El valor es demasiado largo");
            } else {

            }
            if (email.isEmpty()) {
                mapMensaje.put("errorEmail", "Debe ingresar su email");
            } else if (!email.isEmpty() && email.length() > 50) {
                mapMensaje.put("errorEmail", "El valor es demasiado largo");
            } 
            
             if (mapMensaje.isEmpty()) {

                servicio.modificarUsuario(rut, nombre, apellido, telefono, celular, direccion, email);
                mapMensaje.put("mensaje", "El usuario fue modificado con éxito");
                request.getRequestDispatcher("/MostrarUsuario.jsp").forward(request, response);
            }
            
            
        }catch(SQLException e ){
            throw new RuntimeException("Error en la conexión a bd", e);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
