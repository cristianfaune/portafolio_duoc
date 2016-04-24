/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Categoria;
import cl.dominio.Item;
import cl.dominio.Marca;
import cl.dominio.Producto;
import cl.dominio.Usuario;
import cl.dto.ProductoMarcaDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
import cl.persistencia.CategoriaDAO;
import cl.persistencia.ConsultaDAO;
import cl.persistencia.ItemDAO;
import cl.persistencia.MarcaDAO;
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
    ItemDAO itemDAO;
    MarcaDAO marcaDAO;
    CategoriaDAO categoriaDAO;

    public Servicio(Connection con) {
        productoDAO = new ProductoDAO(con);
        consultaDAO = new ConsultaDAO(con);
        usuarioDAO = new UsuarioDAO(con);
        itemDAO = new ItemDAO(con);
        categoriaDAO = new CategoriaDAO(con);
        marcaDAO = new MarcaDAO(con);
    }

    public ArrayList<ProductoMarcaDTO> productosMarcaCursor() {
        return consultaDAO.ProductosMarcaCursor();
    }

    public ArrayList<Usuario> todosLosUsuarios() {
        return usuarioDAO.buscarTodosLosUsuarios();
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public ArrayList<UsuarioPerfilCarreraDTO> usuarioPerfilCarrera() {
        return consultaDAO.usuarioPerfilCarrera();
    }

    public ArrayList<ProductoMarcaDTO> productosPorId(int idProducto) {
        return consultaDAO.productosPorId(idProducto);
    }

    public void registrarItem(Item item) {
        itemDAO.registroItem(item);
    }

    public ArrayList<Marca> listarMarcas() {
        return marcaDAO.listarMarcas();
    }

    public ArrayList<Categoria> listarCategorias() {
        return categoriaDAO.listarCategorias();
    }

    public ArrayList<Item> itemPorId(String nroSerie) {
        return itemDAO.itemPorId(nroSerie);
    }

    public ArrayList<Marca> marcaPorId(int idCategoria) {
        return marcaDAO.MarcasPorId(idCategoria);
    }

    public Usuario buscarUsuarioRut(String rut) {
        return usuarioDAO.buscarUsuarioRut(rut);
    }

    public void registrarProducto(Producto producto) {
        productoDAO.registroProducto(producto);
    }

    public ArrayList<Producto> listarProductos() {
        return productoDAO.listarProductos();
    }
}
