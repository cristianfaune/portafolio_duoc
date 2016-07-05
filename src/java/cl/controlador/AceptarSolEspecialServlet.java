/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Usuario;
import cl.dto.DetalleSolicitudPrUsCaDTO;
import cl.dto.SolicitudUsuarioDTO;
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
@WebServlet(name = "AceptarSolEspecialServlet", urlPatterns = {"/AceptarSolEspecialServlet"})
public class AceptarSolEspecialServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idSolicitud = request.getParameter("nroSolicitud");
        String email = "";
        Usuario us = new Usuario();
        Map<String, String> mapMensaje = new HashMap<>();

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                servicio.activarSolicitudEspecial(Integer.parseInt(idSolicitud));

                ArrayList<DetalleSolicitudPrUsCaDTO> lista = servicio.buscarSolicitudId(Integer.parseInt(idSolicitud));

                for (int i = 0; i == 0; i++) {

                    us = lista.get(i).getUsuario();
                    email = us.getEmail();

                }

                ArrayList<SolicitudUsuarioDTO> listaSol = servicio.listarSolicitudesEspeciales();

                mapMensaje.put("mensajeAutorizado", "**La solicitud Nº " + idSolicitud + " fue autorizada y un correo se ha "
                        + "enviado a " + email + "**");

                request.setAttribute("mensajeA", mapMensaje);
                request.setAttribute("lstSolEspeciales", listaSol);
                request.getRequestDispatcher("AdminSolicitudesEspeciales.jsp").forward(request, response);

                servicio.avisoAutorizacionSolicitud(Integer.parseInt(idSolicitud), us);

            } catch (SQLException e) {
                throw new RuntimeException("error en la conexión a bd", e);
            }

        }

    }

}
