/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Producto;
import cl.dto.ProductoMarcaDTO;
import cl.recursos.ConexionOracle;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@WebServlet(name = "PrincipalProductos", urlPatterns = {"/PrincipalProductos"})
public class PrincipalProductos extends HttpServlet {
   
    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        

                try (Connection con = ds.getConnection()){
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "portafolio", "portafolio");
     
            Servicio servicio = new Servicio(con);
            
            ArrayList<Producto> listaProductos = servicio.TodosLosProductos();
            
            request.setAttribute("lstProductos", listaProductos);
            request.getRequestDispatcher("index.jsp").forward(request, response);

            }catch (Exception e) {
                    throw new RuntimeException("Error en la conexion bd", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
       
    }
