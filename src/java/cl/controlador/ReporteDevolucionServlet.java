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
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import cl.dto.DevolucionPrestamoSolicitudDetalleSolicitudUsuarioDTO;
import java.util.ArrayList;

@WebServlet(name = "ReporteDevolucionServlet", urlPatterns = {"/ReporteDevolucionServlet"})
public class ReporteDevolucionServlet extends HttpServlet {

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
        
                
        try(Connection con = ds.getConnection()){
            Servicio servicio = new Servicio(con);
            if(servicio.ReporteDevoluciones().isEmpty()){
                String MensajeVacio = "No existen Devoluciones Atrasadas";
                request.setAttribute("MensajeVacio", MensajeVacio);
                request.getRequestDispatcher("DevolucionesFueradePlazo.jsp").forward(request, response);
            }else{
                ArrayList<DevolucionPrestamoSolicitudDetalleSolicitudUsuarioDTO> ReporteDevolucion = servicio.ReporteDevoluciones();
                
                request.setAttribute("ReporteDevolucion", ReporteDevolucion);
                request.getRequestDispatcher("DevolucionesFueradePlazo.jsp").forward(request, response);
            }
            
            
        }catch(SQLException e){
            throw new RuntimeException("Error Creando el Reporte de devoluciones", e);
        }
    }

}
