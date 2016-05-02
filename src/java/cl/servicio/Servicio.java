/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Carrera;
import cl.dominio.Categoria;
import cl.dominio.Item;
import cl.dominio.Marca;
import cl.dominio.Perfil;
import cl.dominio.Producto;
import cl.dominio.Usuario;
import cl.dto.ProductoMarcaDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
import cl.persistencia.CarreraDAO;
import cl.persistencia.CategoriaDAO;
import cl.persistencia.ConsultaDAO;
import cl.persistencia.ItemDAO;
import cl.persistencia.MarcaDAO;
import cl.persistencia.PerfilDAO;
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
    PerfilDAO perfilDAO;
    CarreraDAO carreraDAO;

    public Servicio(Connection con) {
        productoDAO = new ProductoDAO(con);
        consultaDAO = new ConsultaDAO(con);
        usuarioDAO = new UsuarioDAO(con);
        itemDAO = new ItemDAO(con);
        categoriaDAO = new CategoriaDAO(con);
        marcaDAO = new MarcaDAO(con);
        perfilDAO = new PerfilDAO(con);
        carreraDAO = new CarreraDAO(con);
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

    public ArrayList<Item> itemPorIdProducto(int idProducto) {
        return itemDAO.itemPorIdProducto(idProducto);
    }

    public void modificarEstadoItem(String nroSerie, byte activo) {
        itemDAO.ModificarEstadoItem(nroSerie, activo);
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

    public ArrayList<ProductoMarcaDTO> existeProducto(int idMarca, String modelo) {
        return consultaDAO.existeProducto(idMarca, modelo);
    }

    public void modificarEstadoUsuario(String rut, byte activar) {
        usuarioDAO.ModificarEstadoUsuario(rut, activar);
    }

    public void modificarUsuario(String rut, String nombre, String apellido, int telefono, int celular, String direccion, String email) {
        usuarioDAO.ModificarUsuario(rut, nombre, apellido, telefono, celular, direccion, email);
    }

    public ArrayList<Perfil> listarPerfiles() {
        return perfilDAO.listarPerfiles();
    }

    public ArrayList<Carrera> listarCarreras() {
        return carreraDAO.listarCarreras();
    }

    public void registrarUsuario(Usuario usuario) {
        usuarioDAO.registroUsuario(usuario);
    }
}
