/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Prestamo;
import cl.dominio.Usuario;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class DetallePrestamoDTO implements Serializable{
    private Usuario usuario;
    private Prestamo prestamo;

    public DetallePrestamoDTO(Usuario usuario, Prestamo prestamo) {
        this.usuario = usuario;
        this.prestamo = prestamo;
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
    
    
}
