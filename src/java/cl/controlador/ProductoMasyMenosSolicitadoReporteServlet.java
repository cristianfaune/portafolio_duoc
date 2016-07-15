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


@WebServlet(name = "ProductoMasyMenosSolicitadoReporteServlet", urlPatterns = {"/ProductoMasyMenosSolicitadoReporteServlet"})
public class ProductoMasyMenosSolicitadoReporteServlet extends HttpServlet {

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

        try(Connection con = ds.getConnection()){
            Servicio servicio = new Servicio(con);
            
            
            if(servicio.productoMenosSolicitado().isEmpty() && servicio.productoMasSolicitado().isEmpty() ){
               
                String MensajeVacio = "No existen Registros de Solicitudes";
                
                request.setAttribute("MensajeVacio", MensajeVacio);
                
                request.getRequestDispatcher("ReportesMasyMenosSolicitados").forward(request, response);
                
            }else{
            
            ArrayList<Producto> menosSolicitado = servicio.productoMenosSolicitado();
            ArrayList<Producto> masSolicitado = servicio.productoMasSolicitado();
            
            request.setAttribute("menosSolicitado", menosSolicitado);
            request.setAttribute("masSolicitado", masSolicitado);
            
            request.getRequestDispatcher("ReportesMasyMenosSolicitados.jsp").forward(request, response);
            
            }
        }catch(SQLException e){
            throw new RuntimeException("Error en la busqueda", e);
        }
        
    }

}
