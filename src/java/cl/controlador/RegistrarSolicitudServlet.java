
package cl.controlador;

import cl.dto.ProductoMarcaDTO;
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
 * @author cristian
 */
@WebServlet(name = "RegistrarSolicitudServlet", urlPatterns = {"/RegistrarSolicitudServlet"})
public class RegistrarSolicitudServlet extends HttpServlet {
    
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
            request.getRequestDispatcher("AdminProductos.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
