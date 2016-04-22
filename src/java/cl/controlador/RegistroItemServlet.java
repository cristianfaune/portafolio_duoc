/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dto.ProductoMarcaDTO;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
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
 * @author cristian
 */
@WebServlet(name = "RegistroItemServlet", urlPatterns = {"/RegistroItemServlet"})
public class RegistroItemServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         try (Connection con = ds.getConnection()){
            
            int idProducto =  parseInt(request.getParameter("idProducto"));
            
            Servicio servicio = new Servicio(con);
            
            ArrayList<ProductoMarcaDTO> listaProductos = servicio.productosPorId(idProducto);
            
            request.setAttribute("lstProductos", listaProductos);
            request.getRequestDispatcher("RegistroItem.jsp").forward(request, response);
                    
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }


}
