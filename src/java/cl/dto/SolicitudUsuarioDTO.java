/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Solicitud;
import cl.dominio.Usuario;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class SolicitudUsuarioDTO implements Serializable{
    private Usuario usuario;
    private Solicitud solicitud;

    public SolicitudUsuarioDTO(Usuario usuario, Solicitud solicitud) {
        this.usuario = usuario;
        this.solicitud = solicitud;
    }

    public SolicitudUsuarioDTO() {
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
    
    
}
