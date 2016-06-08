/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Carrera;
import cl.dominio.DetalleSolicitud;
import cl.dominio.Perfil;
import cl.dominio.Prestamo;
import cl.dominio.Solicitud;
import cl.dominio.Usuario;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class UsuarioPrestamoDTO implements Serializable{
    private Prestamo prestamo;
    private Usuario usuario;
    private Solicitud solicitud;
    private DetalleSolicitud detalleSolicitud;
    private Carrera carrera;
    private Perfil perfil;

    public UsuarioPrestamoDTO(Prestamo prestamo, Usuario usuario, Solicitud solicitud, DetalleSolicitud detalleSolicitud, Carrera carrera, Perfil perfil) {
        this.prestamo = prestamo;
        this.usuario = usuario;
        this.solicitud = solicitud;
        this.detalleSolicitud = detalleSolicitud;
        this.carrera = carrera;
        this.perfil = perfil;
    }

    public UsuarioPrestamoDTO() {
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    
}
