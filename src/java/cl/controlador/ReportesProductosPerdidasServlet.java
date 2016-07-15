/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Producto;
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
import javax.sql.DataSource;

/**
 *
 * @author Doterzer
 */
@WebServlet(name = "ReportesProductosPerdidasServlet", urlPatterns = {"/ReportesProductosPerdidasServlet"})
public class ReportesProductosPerdidasServlet extends HttpServlet {

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

        
        try (Connection con = ds.getConnection()) {
            
            Servicio servicio = new Servicio(con);
            
            ArrayList<Producto> DeBaja = servicio.ReporteDeBaja();
            
            if(servicio.ReporteDeBaja().isEmpty()){
               String MensajeVacio = "No existen productos dado de baja";
               request.getRequestDispatcher("ReporteProductosDeBaja.jsp").forward(request, response);
            }else{
                request.setAttribute("DeBaja", DeBaja);
                request.getRequestDispatcher("ReporteProductosDeBaja.jsp").forward(request, response);
            }
            
        }catch(SQLException e){
            throw new RuntimeException("Error con la conexion", e);
        }
        
    }

}
