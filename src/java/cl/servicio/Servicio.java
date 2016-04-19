/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Producto;
import cl.dto.ProductoMarcaDTO;
import cl.persistencia.ConsultaDAO;
import cl.persistencia.ProductoDAO;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class Servicio {

    ProductoDAO productoDAO;
    ConsultaDAO consultaDAO;

    public Servicio(Connection con) {
        productoDAO = new ProductoDAO(con);
        consultaDAO = new ConsultaDAO(con);
    }

    public ArrayList<Producto> TodosLosProductos() {
        return productoDAO.buscarTodosLosProductos();
    }

    public ArrayList<ProductoMarcaDTO> productosConMarca() {
        return consultaDAO.ProductosConMarca();
    }

}
