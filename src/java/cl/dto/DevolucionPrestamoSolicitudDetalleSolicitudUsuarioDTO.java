/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Devolucion;
import cl.dominio.Prestamo;
import cl.dominio.Solicitud;
import cl.dominio.DetalleSolicitud;
import cl.dominio.Usuario;
import java.io.Serializable;
/**
 *
 * @author Doterzer
 */
public class DevolucionPrestamoSolicitudDetalleSolicitudUsuarioDTO implements Serializable{
    
    private Devolucion devolucion;
    private Prestamo prestamo;
    private Solicitud solicitud;
    private DetalleSolicitud detalleSolicitud;
    private Usuario usuario;
    
    public DevolucionPrestamoSolicitudDetalleSolicitudUsuarioDTO(Devolucion devolucion, Prestamo prestamo, Solicitud solicitud, DetalleSolicitud detalleSolicitud , Usuario usuario){
        
        this.devolucion = devolucion;
        this.prestamo = prestamo;
        this.solicitud = solicitud;
        this.detalleSolicitud = detalleSolicitud;
        this.usuario = usuario;
    }

    public Devolucion getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Devolucion devolucion) {
        this.devolucion = devolucion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public DetalleSolicitud getDetalleSolicitud() {
        return detalleSolicitud;
    }

    public void setDetalleSolicitud(DetalleSolicitud detalleSolicitud) {
        this.detalleSolicitud = detalleSolicitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
