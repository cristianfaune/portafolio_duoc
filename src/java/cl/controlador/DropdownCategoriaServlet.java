/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Marca;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import javax.sql.DataSource;

/**
 *
 * @author cristian
 */
@WebServlet(name = "DropdownCategoriaServlet", urlPatterns = {"/DropdownCategoriaServlet"})
public class DropdownCategoriaServlet extends HttpServlet {
    
    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try (Connection con = ds.getConnection()) {

            int idCategoria = Integer.parseInt(request.getParameter("seleccionCategoria"));

            Servicio servicio = new Servicio(con);
            
            ArrayList<Marca> lstMarcas = servicio.marcaPorId(idCategoria);           
            
            out.print("<select class='form-control' id='seleccionMarca' name='seleccionMarca'>");
            out.print("<option value = '0'>--Seleccione una marca--</option>");
            for (Marca temp : lstMarcas) {
                out.print(" <option value ='" + temp.getIdMarca() + "'>" + temp.getDescripcion() + "</option>");
            }
            out.print("</select>");
           

        } catch (SQLException e) {
            throw new RuntimeException("Error en dropdown ajax", e);
        }

    }

}
