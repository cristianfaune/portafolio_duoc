package cl.controlador;

import cl.dominio.DetalleSolicitud;
import cl.dominio.Marca;
import cl.dominio.Producto;
import cl.dominio.Usuario;
import cl.dto.ProductoMarcaDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "RegistrarSolicitudServlet", urlPatterns = {"/RegistrarSolicitudServlet"})
public class RegistrarSolicitudServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;
    static ArrayList<DetalleSolicitud> listaDtSol = new ArrayList<>();
    static ArrayList<ProductoMarcaDTO> listaProductosSolicitud = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ds.getConnection()) {

            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "portafolio", "portafolio");
            Servicio servicio = new Servicio(con);

            ArrayList<ProductoMarcaDTO> listaProductos = servicio.productosMarcaCursor();

            request.setAttribute("lstProductos", listaProductos);
            request.getRequestDispatcher("RegistroSolicitud.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        UsuarioPerfilCarreraDTO usuarioSlc = (UsuarioPerfilCarreraDTO) session.getAttribute("usuarioSolicitud");

        request.setCharacterEncoding("UTF-8");

        String cantidad = request.getParameter("cantidad");
        String idProducto = request.getParameter("idProducto");
        Map<String, String> mapMensaje = new HashMap<>();
        DetalleSolicitud dtSol = new DetalleSolicitud();
        int idSolicitud;

        try (Connection con = ds.getConnection()) {
            
            Servicio servicio = new Servicio(con);
            
            ArrayList<ProductoMarcaDTO> listaProductos = servicio.productosMarcaCursor();

            if (cantidad.isEmpty()) {
                mapMensaje.put("errorCantidad", "Debe agregar una cantidad");
            } else {
                dtSol.setCantidad(Integer.parseInt(cantidad));
            }

            dtSol.setIdProducto(Integer.parseInt(idProducto));

            dtSol.setRut(usuarioSlc.getUsuario().getRut());
            
            idSolicitud = servicio.idSolicitudDisponible();
            
            dtSol.setIdSolicitud(idSolicitud);
            
            listaDtSol.add(dtSol);
            
            ArrayList<ProductoMarcaDTO> lstPr = servicio.productosPorId(Integer.parseInt(idProducto));
            
            for (ProductoMarcaDTO pm : lstPr) {
                Producto producto = new Producto();
                Marca marca = new Marca();
                
                producto.setIdProducto(pm.getProducto().getIdProducto());
                producto.setNombre(pm.getProducto().getNombre());
                producto.setModelo(pm.getProducto().getModelo());
                producto.setDescripcion(pm.getProducto().getDescripcion());
                producto.setRutaImagen(pm.getProducto().getRutaImagen());
                producto.setStock(pm.getProducto().getStock());
                producto.setIdCategoria(pm.getProducto().getIdCategoria());
                producto.setIdMarca(pm.getProducto().getIdMarca());

                marca.setIdMarca(pm.getMarca().getIdMarca());
                marca.setIdCategoria(pm.getMarca().getIdCategoria());
                marca.setDescripcion(pm.getMarca().getDescripcion());
                
                listaProductosSolicitud.add(new ProductoMarcaDTO(producto, marca));
            }
            
            request.setAttribute("listaProductos", listaProductosSolicitud);
            request.setAttribute("listaDetalle", listaDtSol);
            request.setAttribute("detalle", dtSol);
            request.setAttribute("lstProductos", listaProductos);
            request.getRequestDispatcher("RegistroSolicitud.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }

}
