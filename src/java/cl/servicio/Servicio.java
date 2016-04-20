/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Producto;
import cl.dominio.Usuario;
import cl.dto.ProductoMarcaDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
import cl.persistencia.ConsultaDAO;
import cl.persistencia.ProductoDAO;
import cl.persistencia.UsuarioDAO;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class Servicio {

    ProductoDAO productoDAO;
    ConsultaDAO consultaDAO;
    UsuarioDAO usuarioDAO;

    public Servicio(Connection con) {
        productoDAO = new ProductoDAO(con);
        consultaDAO = new ConsultaDAO(con);
        usuarioDAO = new UsuarioDAO(con);
    }

    public ArrayList<Producto> TodosLosProductos() {
        return productoDAO.buscarTodosLosProductos();
    }


    public ArrayList<ProductoMarcaDTO> productosMarcaCursor() {
        return consultaDAO.ProductosMarcaCursor();
    }

    public ArrayList<Usuario> todosLosUsuarios() {
        return usuarioDAO.buscarTodosLosUsuarios();
    }

    public ArrayList<Usuario> usuariosProcedimiento() {
        return usuarioDAO.UsuariosProcedimiento();
    }
    
    public ArrayList<UsuarioPerfilCarreraDTO> usuarioPerfilCarrera(){
        return consultaDAO.usuarioPerfilCarrera();
    }
}
