/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Prestamo;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.sql.DataSource;
/**
 *
 * @author Doterzer
 */
@WebServlet(name = "ReportesActivosInactivosServlet", urlPatterns = {"/ReportesActivosInactivosServlet"})
public class ReportesActivosInactivosServlet extends HttpServlet {

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

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
          
          try(Connection con = ds.getConnection()){
              
              Servicio servicio = new Servicio(con);
              
              ArrayList<Prestamo> Inactivos = servicio.ReportesInactivos();
              ArrayList<Prestamo> Activos = servicio.ReportesActivos();
              
              request.setAttribute("Inactivos", Inactivos);
              request.setAttribute("Activos", Activos);
  
              request.getRequestDispatcher("reportesPrestamos.jsp").forward(request, response);
              
          }catch(SQLException e){
              throw new RuntimeException("Error Buscando Reportes", e);
          }
        
    }
}


