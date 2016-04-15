/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author cristian
 */
public class Devolucion implements Serializable{
    private int idDevolucion;
    private Timestamp fechaDevolucion;
    private String observacion;
    private int idPrestamo;

    public Devolucion(int idDevolucion, Timestamp fechaDevolucion, String observacion, int idPrestamo) {
        this.idDevolucion = idDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.observacion = observacion;
        this.idPrestamo = idPrestamo;
    }

    public Devolucion() {
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Timestamp getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Timestamp fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idDevolucion;
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
        final Devolucion other = (Devolucion) obj;
        if (this.idDevolucion != other.idDevolucion) {
            return false;
        }
        return true;
    }
    
    
}
