package cl.controlador;

import cl.dominio.Categoria;
import cl.dominio.Marca;
import cl.dominio.Producto;
import cl.dto.ProductoMarcaDTO;
import cl.servicio.Servicio;
import java.io.IOException;
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

        try (Connection con = ds.getConnection()) {

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

        request.setCharacterEncoding("UTF-8");

        int idCategoria = Integer.parseInt(request.getParameter("seleccionCategoria"));
        int idMarca = Integer.parseInt(request.getParameter("seleccionMarca"));
        String nombre = request.getParameter("nombre");
        String modelo = request.getParameter("modelo");
        String descripcion = request.getParameter("descripcion");
        Map<String, String> mapMensaje = new HashMap<>();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);
            ArrayList<Categoria> lstCategorias = servicio.listarCategorias();
            ArrayList<Marca> lstMarcas = servicio.listarMarcas();
            ArrayList<Producto> lstProductos = servicio.listarProductos();
            Producto producto = new Producto();

            if (nombre.isEmpty()) {
                mapMensaje.put("mensaje", "Todos los campos son obligatorios");
            } else if (!nombre.isEmpty() && nombre.length() > 50) {
                mapMensaje.put("mensaje", "El valor es demasiado largo");
            } else {
                producto.setNombre(nombre);
            }

            if (modelo.isEmpty()) {
                mapMensaje.put("mensaje", "Todos los campos son obligatorios");
            } else if (!modelo.isEmpty() && modelo.length() > 50) {
                mapMensaje.put("mensaje", "El valor es demasiado largo");
            } else {
                producto.setModelo(modelo);
            }

            if (descripcion.isEmpty()) {
                mapMensaje.put("mensaje", "Todos los campos son obligatorios");
            } else if (!descripcion.isEmpty() && descripcion.length() > 200) {
                mapMensaje.put("mensaje", "Escriba una descripción más corta");
            } else {
                producto.setDescripcion(descripcion);
            }

            producto.setRutaImagen("imgProductos/imagenNoDisponible.png");

            if ((String.valueOf(idCategoria)).equals("0")) {
                mapMensaje.put("mensaje", "Todos los campos son obligatorios");
            } else {
                producto.setIdCategoria(idCategoria);
            }

            if ((String.valueOf(idMarca)).equals("0")) {
                mapMensaje.put("mensaje", "Todos los campos son obligatorios");
            } else {
                producto.setIdMarca(idMarca);
            }

            if (!modelo.isEmpty() && !String.valueOf(idMarca).isEmpty()) {
                ArrayList<ProductoMarcaDTO> lista = servicio.existeProducto(idMarca, modelo);

                if (lista.size() > 0) {
                    mapMensaje.put("mensaje", "El producto ya está registrado");
                }
            }

            if (mapMensaje.isEmpty()) {

                servicio.registrarProducto(producto);
                mapMensaje.put("mensaje", "Producto registrado con éxito");

                ArrayList<ProductoMarcaDTO> listaProductos = servicio.productosMarcaCursor();
                request.setAttribute("lstProductos", listaProductos);
                request.getRequestDispatcher("/AdminProductos.jsp").forward(request, response);
            }

            request.setAttribute("mapMensaje", mapMensaje);
            request.setAttribute("lstMarcas", lstMarcas);
            request.setAttribute("lstCategorias", lstCategorias);
            request.getRequestDispatcher("/RegistroProducto.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a bd", e);
        }

    }

}
