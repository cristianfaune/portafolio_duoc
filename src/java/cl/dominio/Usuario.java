/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author cristian
 */
public class Usuario implements Serializable{
    private String rut;
    private String nombres;
    private String apellidos;
    private int telefono;
    private int celular;
    private String direccion;
    private String email;
    private String password;
    private byte activo = 1;
    private int idPerfil;
    private int idCarrera;

    public Usuario(String rut, String nombres, String apellidos, int telefono, int celular, String direccion, String email, String password, int idPerfil, int idCarrera) {
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        this.idPerfil = idPerfil;
        this.idCarrera = idCarrera;
    }

    public Usuario() {
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getActivo() {
        return activo;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    
    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.rut);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.rut, other.rut)) {
            return false;
        }
        return true;
    }
    
    
}
