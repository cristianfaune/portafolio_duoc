/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author cristian
 */
public class HistorialClienteDevolucion {
    private String rut;
    private int idDevolucion;
    private String nroSerie;
    private Timestamp fecha;
    private String descripcion;
    private String observacion;

    public HistorialClienteDevolucion(String rut, int idDevolucion, String nroSerie, Timestamp fecha, String descripcion, String observacion) {
        this.rut = rut;
        this.idDevolucion = idDevolucion;
        this.nroSerie = nroSerie;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.observacion = observacion;
    }

    public HistorialClienteDevolucion() {
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.rut);
        hash = 97 * hash + this.idDevolucion;
        hash = 97 * hash + Objects.hashCode(this.nroSerie);
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
        final HistorialClienteDevolucion other = (HistorialClienteDevolucion) obj;
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
