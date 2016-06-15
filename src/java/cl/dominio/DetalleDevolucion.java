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
public class DetalleDevolucion implements Serializable{
    private String rut;
    private int idDevolucion;
    private String nroSerie;

    public DetalleDevolucion(String rut, int idDevolucion, String nroSerie) {
        this.rut = rut;
        this.idDevolucion = idDevolucion;
        this.nroSerie = nroSerie;
    }

    public DetalleDevolucion() {
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.rut);
        hash = 43 * hash + this.idDevolucion;
        hash = 43 * hash + Objects.hashCode(this.nroSerie);
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
        final DetalleDevolucion other = (DetalleDevolucion) obj;
        if (this.idDevolucion != other.idDevolucion) {
            return false;
        }
        if (!Objects.equals(this.rut, other.rut)) {
            return false;
        }
        if (!Objects.equals(this.nroSerie, other.nroSerie)) {
            return false;
        }
        return true;
    }
    
    
    
}
