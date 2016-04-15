/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Devolucion;
import cl.dominio.Usuario;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class UsuarioDevolucionDTO implements Serializable{
    private Usuario usuario;
    private Devolucion devolucion;

    public UsuarioDevolucionDTO(Usuario usuario, Devolucion devolucion) {
        this.usuario = usuario;
        this.devolucion = devolucion;
    }

    public UsuarioDevolucionDTO() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Devolucion getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Devolucion devolucion) {
        this.devolucion = devolucion;
    }
    
    
}
