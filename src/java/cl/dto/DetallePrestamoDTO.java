/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.DetallePrestamo;
import cl.dominio.Item;
import cl.dominio.Marca;
import cl.dominio.Prestamo;
import cl.dominio.Producto;
import cl.dominio.Usuario;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class DetallePrestamoDTO implements Serializable {

    private Usuario usuario;
    private Prestamo prestamo;
    private DetallePrestamo detallePrestamo;
    private Item item;
    private Producto producto;
    private Marca marca;

    public DetallePrestamoDTO(Usuario usuario, Prestamo prestamo, DetallePrestamo detallePrestamo,
            Item item, Producto producto, Marca marca) {
        this.usuario = usuario;
        this.prestamo = prestamo;
        this.detallePrestamo = detallePrestamo;
        this.item = item;
        this.producto = producto;
        this.marca = marca;
    }

    public DetallePrestamoDTO() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public DetallePrestamo getDetallePrestamo() {
        return detallePrestamo;
    }

    public void setDetallePrestamo(DetallePrestamo detallePrestamo) {
        this.detallePrestamo = detallePrestamo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    
}
