/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Carrera;
import cl.dominio.Perfil;
import cl.dominio.Usuario;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class UsuarioPerfilCarreraDTO implements Serializable{
    private Usuario usuario;
    private Perfil perfil;
    private Carrera carrera;

    public UsuarioPerfilCarreraDTO(Usuario usuario, Perfil perfil, Carrera carrera) {
        this.usuario = usuario;
        this.perfil = perfil;
        this.carrera = carrera;
    }

    public UsuarioPerfilCarreraDTO() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    
}
