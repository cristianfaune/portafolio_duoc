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
public class DetallePrestamo implements Serializable{
    
    private String rut;
    private int idPrestamo;
    private String nroSerie;

    public DetallePrestamo(String rut, int idPrestamo, String nroSerie) {
        this.rut = rut;
        this.idPrestamo = idPrestamo;
        this.nroSerie = nroSerie;
    }

    public DetallePrestamo() {
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.rut);
        hash = 83 * hash + this.idPrestamo;
        hash = 83 * hash + Objects.hashCode(this.nroSerie);
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
        final DetallePrestamo other = (DetallePrestamo) obj;
        if (this.idPrestamo != other.idPrestamo) {
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
