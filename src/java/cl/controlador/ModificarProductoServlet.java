/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Categoria;
import cl.dominio.Marca;
import cl.dominio.Producto;
import cl.dto.ProductoMarcaDTO;
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
import javax.sql.DataSource;

/**
 *
 * @author cristian
 */
@WebServlet(name = "ModificarProductoServlet", urlPatterns = {"/ModificarProductoServlet"})
public class ModificarProductoServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        ProductoMarcaDTO pmdto;
        Producto producto = new Producto();
        Marca marca = new Marca();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            ArrayList<Categoria> lstCategorias = servicio.listarCategorias();
            ArrayList<Marca> lstMarcas = servicio.listarMarcas();

            ArrayList<ProductoMarcaDTO> listaProducto = servicio.productosPorId(idProducto);

            for (ProductoMarcaDTO pm : listaProducto) {
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
            }

            pmdto = new ProductoMarcaDTO(producto, marca);

            request.setAttribute("producto", pmdto);
            request.setAttribute("lstMarcas", lstMarcas);
            request.setAttribute("lstCategorias", lstCategorias);
            request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int idCategoria = Integer.parseInt(request.getParameter("seleccionCategoria"));
        int idMarca = Integer.parseInt(request.getParameter("seleccionMarca"));
        String nombre = request.getParameter("nombre");
        String modelo = request.getParameter("modelo");
        String descripcion = request.getParameter("descripcion");
        Map<String, String> mapMensaje = new HashMap<>();
        Map<String, String> mapMensajeExito = new HashMap<>();

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);
            ArrayList<Marca> lstMarcas = servicio.listarMarcas();
            ArrayList<Categoria> lstCategorias = servicio.listarCategorias();

            Producto producto = new Producto();

            if (nombre.isEmpty()) {
                mapMensaje.put("errorNombre", "**Debe ingresar un nombre**");
            } else if (!nombre.isEmpty() && nombre.length() > 50) {
                mapMensaje.put("errorNombre", "**El valor es demasiado largo**");
            } else {
                producto.setNombre(nombre);
            }

            if (modelo.isEmpty()) {
                mapMensaje.put("errorModelo", "**Debe ingresar modelo**");
            } else if (!modelo.isEmpty() && modelo.length() > 50) {
                mapMensaje.put("errorModelo", "**El valor es demasiado largo**");
            } else {
                producto.setModelo(modelo);
            }

            if (descripcion.isEmpty()) {
                mapMensaje.put("errorDescripcion", "**Debe ingresar una descripción**");
            } else if (!descripcion.isEmpty() && descripcion.length() > 200) {
                mapMensaje.put("errorDescripcion", "**Escriba una descripción más corta**");
            } else {
                producto.setDescripcion(descripcion);
            }

            producto.setRutaImagen("imgProductos/imagenNoDisponible.png");

            if ((String.valueOf(idCategoria)).equals("0")) {
                mapMensaje.put("errorCategoria", "**Debe ingresar una categoria**");
            } else {
                producto.setIdCategoria(idCategoria);
            }

            if ((String.valueOf(idMarca)).equals("0")) {
                mapMensaje.put("errorMarca", "**Debe ingresar una marca**");
            } else {
                producto.setIdMarca(idMarca);
            }

            if (!modelo.isEmpty() && !String.valueOf(idMarca).isEmpty()) {
                ArrayList<ProductoMarcaDTO> lista = servicio.existeProducto(idMarca, modelo);

                if (lista.size() > 0) {
                    mapMensaje.put("errorExiste", "**El producto ya está registrado**");
                }
            }
            
            producto.setIdProducto(idProducto);

            if (mapMensaje.isEmpty()) {

                servicio.modificarProducto(producto);
                mapMensajeExito.put("mensaje", "Producto modificado con éxito");
                request.setAttribute("mensaje", mapMensajeExito);
                request.getRequestDispatcher("/ModificarProducto.jsp").forward(request, response);
            } else {
                request.setAttribute("mapMensaje", mapMensaje);
                request.setAttribute("lstMarcas", lstMarcas);
                request.setAttribute("lstCategorias", lstCategorias);
                request.getRequestDispatcher("/ModificarProducto.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexion bd", e);
        }

    }

}
