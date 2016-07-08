/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Carrera;
import cl.dominio.Categoria;
import cl.dominio.DetalleDevolucion;
import cl.dominio.DetallePrestamo;
import cl.dominio.DetalleSolicitud;
import cl.dominio.Devolucion;
import cl.dominio.HistorialClienteDevolucion;
import cl.dominio.Item;
import cl.dominio.Marca;
import cl.dominio.Perfil;
import cl.dominio.Prestamo;
import cl.dominio.Producto;
import cl.dominio.Solicitud;
import cl.dominio.Usuario;
import cl.dto.DetallePrestamoDTO;
import cl.dto.DetalleSolicitudPrUsCaDTO;
import cl.dto.ProductoMarcaDTO;
import cl.dto.SolicitudUsuarioDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
import cl.dto.UsuarioPrestamoDTO;
import cl.persistencia.CarreraDAO;
import cl.persistencia.CategoriaDAO;
import cl.persistencia.ConsultaDAO;
import cl.persistencia.DevolucionDAO;
import cl.persistencia.HistorialClienteDevolucionDAO;
import cl.persistencia.ItemDAO;
import cl.persistencia.MarcaDAO;
import cl.persistencia.PerfilDAO;
import cl.persistencia.PrestamoDAO;
import cl.persistencia.ProductoDAO;
import cl.persistencia.SolicitudDAO;
import cl.persistencia.UsuarioDAO;
import java.io.ByteArrayOutputStream;
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
    PrestamoDAO prestamoDAO;
    SolicitudDAO solicitudDAO;
    DevolucionDAO devolucionDAO;
    HistorialClienteDevolucionDAO historialClienteDevolucionDAO;
    
    public Servicio(Connection con) {
        productoDAO = new ProductoDAO(con);
        consultaDAO = new ConsultaDAO(con);
        usuarioDAO = new UsuarioDAO(con);
        itemDAO = new ItemDAO(con);
        categoriaDAO = new CategoriaDAO(con);
        marcaDAO = new MarcaDAO(con);
        perfilDAO = new PerfilDAO(con);
        carreraDAO = new CarreraDAO(con);
        prestamoDAO = new PrestamoDAO(con);
        solicitudDAO = new SolicitudDAO(con);
        devolucionDAO = new DevolucionDAO(con);
        historialClienteDevolucionDAO = new HistorialClienteDevolucionDAO(con);
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
    
    public ArrayList<UsuarioPerfilCarreraDTO> usuarioPerfilCarrera(int idPerfil) {
        return consultaDAO.usuarioPerfilCarrera(idPerfil);
    }
    
    public ArrayList<UsuarioPerfilCarreraDTO> usuarioPerfilCarreraPorRut(String rut) {
        return consultaDAO.usuarioPerfilCarreraPorRut(rut);
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
    
    public ArrayList<Usuario> buscarUsuarioRut(String rut) {
        return usuarioDAO.buscarUsuarioRut(rut);
    }
    
    public ArrayList<Usuario> buscarUsuarioRutFiltro(String rut, int idPerfil) {
        return usuarioDAO.buscarUsuarioRutFiltro(rut, idPerfil);
    }
    
    public void registrarProducto(Producto producto) {
        productoDAO.registroProducto(producto);
    }
    
    public void modificarProducto(Producto producto) {
        productoDAO.modificarProducto(producto);
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
    
    public void modificarUsuario(Usuario usuario) {
        usuarioDAO.ModificarUsuario(usuario);
    }
    
    public ArrayList<Perfil> listarPerfiles() {
        return perfilDAO.listarPerfiles();
    }
    
    public ArrayList<Perfil> listarPerfilesFiltro(int idPerfil) {
        return perfilDAO.listarPerfilesFiltro(idPerfil);
    }
    
    public ArrayList<Carrera> listarCarreras() {
        return carreraDAO.listarCarreras();
    }
    
    public void registrarUsuario(Usuario usuario) {
        usuarioDAO.registroUsuario(usuario);
    }
    
    public ArrayList<DetalleSolicitudPrUsCaDTO> buscarSolicitudId(int idSolicitud) {
        return consultaDAO.buscarSolicitudId(idSolicitud);
    }
    
    public ArrayList<DetalleSolicitudPrUsCaDTO> totalSolicitudId(int idSolicitud) {
        return consultaDAO.totalSolicitudId(idSolicitud);
    }
    
    public ArrayList<Item> itemsDisponibles(int idProducto, int cantidad) {
        return itemDAO.itemsDisponibles(idProducto, cantidad);
    }
    
    public void registroPrestamo(Prestamo prestamo, int cantidadDias) {
        prestamoDAO.registroPrestamo(prestamo, cantidadDias);
    }
    
    public ArrayList<Prestamo> prestamoPorIdSolicitud(int idSolicitud) {
        return prestamoDAO.prestamoPorIdSolicitud(idSolicitud);
    }
    
    public void registroDetallePrestamo(DetallePrestamo detallePrestamo) {
        prestamoDAO.registroDetallePrestamo(detallePrestamo);
    }
    
    public int idUltimoPrestamo() {
        return prestamoDAO.idUltimoPrestamo();
    }
    
    public void ModificarEstadoSolicitud(int idSolicitud, byte activa) {
        solicitudDAO.ModificarEstadoSolicitud(idSolicitud, activa);
    }
    
    public int idSolicitudDisponible() {
        return solicitudDAO.idSolicitudDisponible();
    }
    
    public int idUltimaSolicitud() {
        return solicitudDAO.idUltimaSolicitud();
    }
    
    public void registrarSolicitud(Solicitud solicitud) {
        solicitudDAO.registroSolicitud(solicitud);
    }
    
    public void registrarDetalleSolicitud(DetalleSolicitud detalleSolicitud) {
        solicitudDAO.registroDetalleSolicitud(detalleSolicitud);
    }
    
    public void modificarEstadoPrestamoItem(String nroSerie, byte prestamo) {
        itemDAO.modificarEstadoPrestamoItem(nroSerie, prestamo);
    }
    
    public void enviarEmailSolicitud(String nombre, int idSolicitud, String email, ByteArrayOutputStream doc, String especial) {
        solicitudDAO.enviarEmailSolicitud(nombre, idSolicitud, email, doc, especial);
    }
    
    public void enviarEmailPrestamo(String nombre, int idPrestamo, String email, ByteArrayOutputStream doc) {
        prestamoDAO.enviarEmailPrestamo(nombre, idPrestamo, email, doc);
    }
    
    public int stockProducto(int idProducto) {
        return productoDAO.stockProducto(idProducto);
    }
    
    public Prestamo buscarPrestamoPorId(int idPrestamo) {
        return prestamoDAO.buscarPrestamoPorId(idPrestamo);
    }
    
    public ArrayList<DetallePrestamoDTO> buscarDetallePrestamoPorId(int idPrestamo) {
        return consultaDAO.buscarDetallePrestamoPorId(idPrestamo);
    }
    
    public ArrayList<UsuarioPrestamoDTO> buscarUsuarioPrestamoPorId(int idPrestamo) {
        return consultaDAO.buscarUsuarioPrestamoPorId(idPrestamo);
    }
    
    public void registroDevolucion(Devolucion devolucion) {
        devolucionDAO.registroDevolucion(devolucion);
    }
    
    public void registroDetalleDevolucion(DetalleDevolucion detalleDevolucion) {
        devolucionDAO.registroDetalleDevolucion(detalleDevolucion);
    }
    
    public void registroHistorialCliente(HistorialClienteDevolucion historialCliente) {
        historialClienteDevolucionDAO.registroHistorialCliente(historialCliente);
    }
    
    public int idDevolucionDisponible() {
        return devolucionDAO.idDevolucionDisponible();
    }
    
    public void ModificarEstadoPrestamo(int idPrestamo, byte activa) {
        prestamoDAO.ModificarEstadoPrestamo(idPrestamo, activa);
    }
    
    public ArrayList<SolicitudUsuarioDTO> listarSolicitudesEspeciales() {
        return solicitudDAO.listarSolicitudesEspeciales();
    }
    
    public void activarSolicitudEspecial(int idSolicitud) {
        solicitudDAO.activarSolicitudEspecial(idSolicitud);
    }
    
    public void avisoAutorizacionSolicitud(int idSolicitud, Usuario usuario) {
        solicitudDAO.avisoAutorizacionSolicitud(idSolicitud, usuario);
    }
    
    public void NegarSolicitudEspecial(int idSolicitud) {
        solicitudDAO.NegarSolicitudEspecial(idSolicitud);
    }
    
    public void avisoNegacionSolicitud(int idSolicitud, Usuario usuario) {
        solicitudDAO.avisoNegacionSolicitud(idSolicitud, usuario);
    }
    
    public ArrayList<Integer> revisarStockPrestamo(ArrayList<Item> lista) {
        return productoDAO.revisarStockPrestamo(lista);
    }
    
    public void alertaStockPrestamo (ArrayList<Integer> lista){
        productoDAO.alertaStockPrestamo(lista);
    }
    
    public boolean verificadorRut(String rut, String digito) {
        return usuarioDAO.verificadorRut(rut, digito);
    }
}
