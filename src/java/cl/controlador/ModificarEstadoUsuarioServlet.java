/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Item;
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
 * @author Doterzer
 */
@WebServlet(name = "ModificarEstadoUsuarioServlet", urlPatterns = {"/ModificarEstadoUsuarioServlet"})
public class ModificarEstadoUsuarioServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        Byte activo = Byte.parseByte(request.getParameter("activo"));
        String rut = request.getParameter("rut");
        byte activar = 0;

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (usuarioS.getIdPerfil() == 120) {
            request.getRequestDispatcher("HomePanolero.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                if (activo == activar) {
                    activar = 1;

                } else {
                    activar = 0;
                }

                servicio.modificarEstadoUsuario(rut, activar);

                ArrayList<UsuarioPerfilCarreraDTO> listaUsuarios = servicio.usuarioPerfilCarrera(usuarioS.getIdPerfil());

                request.setAttribute("lstUsuarios", listaUsuarios);
                request.getRequestDispatcher("/ListarUsuario.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error en la conexión a la bd", e);
            }

        }

    }

}
