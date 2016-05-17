/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.util.Objects;

/**
 *
 * @author cristian
 */
public class DetalleSolicitud {
    private String rut;
    private int idSolicitud;
    private int idProducto;
    private int cantidad;

    public DetalleSolicitud(String rut, int idSolicitud, int idProducto, int cantidad) {
        this.rut = rut;
        this.idSolicitud = idSolicitud;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public DetalleSolicitud() {
    }
    
    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.rut);
        hash = 47 * hash + this.idSolicitud;
        hash = 47 * hash + this.idProducto;
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
        final DetalleSolicitud other = (DetalleSolicitud) obj;
        if (this.idSolicitud != other.idSolicitud) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (!Objects.equals(this.rut, other.rut)) {
            return false;
        }
        return true;
    }
    
    
}
