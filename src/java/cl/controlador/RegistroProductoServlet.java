package cl.controlador;

import cl.dominio.Categoria;
import cl.dominio.Marca;
import cl.servicio.Servicio;
import java.io.IOException;
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
@WebServlet(name = "RegistroProductoServlet", urlPatterns = {"/RegistroProductoServlet"})
public class RegistroProductoServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ds.getConnection()){
            
            Servicio servicio = new Servicio(con);
            
            ArrayList<Categoria> lstCategorias = servicio.listarCategorias();
            ArrayList<Marca> lstMarcas = servicio.listarMarcas();
            
            request.setAttribute("lstMarcas", lstMarcas);
            request.setAttribute("lstCategorias", lstCategorias);
            request.getRequestDispatcher("RegistroProducto.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
