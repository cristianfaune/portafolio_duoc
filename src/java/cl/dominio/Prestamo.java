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
public class Prestamo implements Serializable{
    private int idPrestamo;
    private byte activa;
    private Timestamp fechaRetiro;
    private Timestamp fechaEstimadaEntrega;
    private int idSolicitud;

    public Prestamo(int idPrestamo, byte activa, Timestamp fechaRetiro, Timestamp fechaEstimadaEntrega, int idSolicitud) {
        this.idPrestamo = idPrestamo;
        this.activa = activa;
        this.fechaRetiro = fechaRetiro;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
        this.idSolicitud = idSolicitud;
    }

    public Prestamo() {
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public byte getActiva() {
        return activa;
    }

    public void setActiva(byte activa) {
        this.activa = activa;
    }

    public Timestamp getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Timestamp fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public Timestamp getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(Timestamp fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idPrestamo;
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
        final Prestamo other = (Prestamo) obj;
        if (this.idPrestamo != other.idPrestamo) {
            return false;
        }
        return true;
    }
    
    
}
