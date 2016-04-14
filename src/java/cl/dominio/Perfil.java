/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

/**
 *
 * @author cristian
 */
public class Perfil {
    private int idPerfil;
    private String descripcion;

    public Perfil(int idPerfil, String descripcion) {
        this.idPerfil = idPerfil;
        this.descripcion = descripcion;
    }

    public Perfil() {
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idPerfil;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Perfil other = (Perfil) obj;
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
        return true;
    }    
    
}



