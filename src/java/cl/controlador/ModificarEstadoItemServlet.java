/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dominio.Item;
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
@WebServlet(name = "ModificarEstadoItemServlet", urlPatterns = {"/ModificarEstadoItemServlet"})
public class ModificarEstadoItemServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            int idProducto = Integer.parseInt(request.getParameter("idProducto"));

            ArrayList<ProductoMarcaDTO> lstProductos = servicio.productosPorId(idProducto);
            ArrayList<Item> lstItem = servicio.itemPorIdProducto(idProducto);

            request.setAttribute("lstProductos", lstProductos);
            request.setAttribute("lstItem", lstItem);
            request.getRequestDispatcher("/ModificarEstadoItem.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a bd", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String activo = request.getParameter("activo");
        String nroSerie = request.getParameter("nroSerie");
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        byte activar;

        try (Connection con = ds.getConnection()) {

            Servicio servicio = new Servicio(con);

            if (activo.equals("activar")) {
                activar = 1;
            } else {
                activar = 0;
            }

            servicio.modificarEstadoItem(nroSerie, activar);

            ArrayList<ProductoMarcaDTO> lstProductos = servicio.productosPorId(idProducto);
            ArrayList<Item> lstItem = servicio.itemPorIdProducto(idProducto);

            request.setAttribute("lstProductos", lstProductos);
            request.setAttribute("lstItem", lstItem);
            request.getRequestDispatcher("/ModificarEstadoItem.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión a la bd", e);
        }

    }

}
