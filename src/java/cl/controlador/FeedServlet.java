/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Marca;
import cl.dto.ProductoMarcaDTO;
import cl.recursos.DataTable;
import cl.servicio.Servicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
@WebServlet(name = "FeedServlet", urlPatterns = {"/FeedServlet"})
public class FeedServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try (Connection con = ds.getConnection()) {

            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "portafolio", "portafolio");
            Servicio servicio = new Servicio(con);

            ArrayList<ProductoMarcaDTO> listaProductos = servicio.productosMarcaCursor();

            request.setAttribute("lstProductos", listaProductos);
            request.getRequestDispatcher("DataTablesTest.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

        /*response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try (Connection con = ds.getConnection()) {

            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "portafolio", "portafolio");
            Servicio servicio = new Servicio(con); 
            
            response.setContentType("application/json");
                // Call business service class to get list of company
                ArrayList<ProductoMarcaDTO> listaProductos = servicio.productosMarcaCursor();

                DataTable dataTableParam = new DataTable();
                // Set the list fetched in aaData
                dataTableParam.setAaData(listaProductos);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                // Convert Java Object to Json
                String json = gson.toJson(dataTableParam);

                response.getWriter().print(json);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }
*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
