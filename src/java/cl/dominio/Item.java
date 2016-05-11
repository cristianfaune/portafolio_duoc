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
public class Item implements Serializable {

    private String nroSerie;
    private byte activo;
    private int idProducto;
    private byte prestamo;

    public Item(String nroSerie, byte activo, int idProducto, byte prestamo) {
        this.nroSerie = nroSerie;
        this.activo = activo;
        this.idProducto = idProducto;
        this.prestamo = prestamo;
    }

    public Item() {
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public byte getActivo() {
        return activo;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public byte getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(byte prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.nroSerie);
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
        final Item other = (Item) obj;
        if (this.nroSerie != other.nroSerie) {
            return false;
        }
        return true;
    }

}
