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
        ArrayList<ProductoMarcaDTO> lstPr = new ArrayList<>();
        int idSolicitud;
        int cant = 0;

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);
            
            int stock = servicio.stockProducto(Integer.parseInt(idProducto));

            ArrayList<ProductoMarcaDTO> listaProductos = servicio.productosMarcaCursor();

            if (cantidad.isEmpty()) {
                mapMensaje.put("errorCantidad", "**Debe agregar una cantidad**");
            } else if (Integer.parseInt(cantidad) <= 0) {
                mapMensaje.put("errorCantidad", "**La cantidad debe ser mayor a 0**");
            }
            
            if (Integer.parseInt(cantidad) > stock) {
                mapMensaje.put("errorCantidad", "**No hay suficiente stock del producto**");
            }

            if (mapMensaje.isEmpty()) {

                dtSol.setIdProducto(Integer.parseInt(idProducto));

                dtSol.setRut(usuarioSlc.getUsuario().getRut());

                idSolicitud = servicio.idSolicitudDisponible();

                dtSol.setIdSolicitud(idSolicitud);

                dtSol.setCantidad(Integer.parseInt(cantidad));

                //método para agregar a carrito
                int indice = -1;

                for (int i = 0; i < listaDtSol.size(); i++) {
                    DetalleSolicitud det = listaDtSol.get(i);

                    if (det.getIdProducto() == dtSol.getIdProducto()) {

                        cant = det.getCantidad();

                        indice = i;

                        break;
                    }
                }

                if (indice == -1) {
                    listaDtSol.add(dtSol);
                } else {

                    dtSol.setCantidad(Integer.parseInt(cantidad) + cant);
                    
                    if (dtSol.getCantidad() > stock) {
                        mapMensaje.put("errorCantidad", "**No puede agregar más productos con ID "+idProducto+"**");
                    }else{
                        listaDtSol.set(indice, dtSol);
                    }
                    
                }
                // fin carrito

                request.setAttribute("listaProductos", listaProductosSolicitud);
                request.setAttribute("listaDetalle", listaDtSol);
                request.setAttribute("detalle", dtSol);
                request.setAttribute("mensajeError", mapMensaje);
                request.setAttribute("lstProductos", listaProductos);
                request.getRequestDispatcher("RegistroSolicitud.jsp").forward(request, response);
            } else {
                request.setAttribute("listaProductos", listaProductosSolicitud);
                request.setAttribute("listaDetalle", listaDtSol);
                request.setAttribute("detalle", dtSol);
                request.setAttribute("lstProductos", listaProductos);
                request.setAttribute("mensajeError", mapMensaje);
                request.getRequestDispatcher("RegistroSolicitud.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }

}
