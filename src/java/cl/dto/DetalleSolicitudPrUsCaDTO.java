/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Carrera;
import cl.dominio.Marca;
import cl.dominio.Producto;
import cl.dominio.Solicitud;
import cl.dominio.Usuario;
import cl.persistencia.DetalleSolicitud;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class DetalleSolicitudPrUsCaDTO implements Serializable{
    private DetalleSolicitud detalleSolicitud;
    private Solicitud solicitud;
    private Producto producto;
    private Carrera carrera;
    private Usuario usuario;
    private Marca marca;

    public DetalleSolicitudPrUsCaDTO(DetalleSolicitud detalleSolicitud, Solicitud solicitud, Producto producto, Carrera carrera, Usuario usuario, Marca marca) {
        this.detalleSolicitud = detalleSolicitud;
        this.solicitud = solicitud;
        this.producto = producto;
        this.carrera = carrera;
        this.usuario = usuario;
        this.marca = marca;
    }

    public DetalleSolicitudPrUsCaDTO() {
    }

    public DetalleSolicitud getDetalleSolicitud() {
        return detalleSolicitud;
    }

    public void setDetalleSolicitud(DetalleSolicitud detalleSolicitud) {
        this.detalleSolicitud = detalleSolicitud;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    
    
}
