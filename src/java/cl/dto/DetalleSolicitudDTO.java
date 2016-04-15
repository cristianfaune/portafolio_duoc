/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Item;
import cl.dominio.Solicitud;
import cl.dominio.Usuario;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class DetalleSolicitudDTO implements Serializable{
    private Item item;
    private Usuario usuario;
    private Solicitud solicitud;

    public DetalleSolicitudDTO(Item item, Usuario usuario, Solicitud solicitud) {
        this.item = item;
        this.usuario = usuario;
        this.solicitud = solicitud;
    }

    public DetalleSolicitudDTO() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
