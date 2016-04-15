/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class Item implements Serializable{
    private int nroSerie;
    private byte activo;
    private int idProducto;

    public Item(int nroSerie, byte activo, int idProducto) {
        this.nroSerie = nroSerie;
        this.activo = activo;
        this.idProducto = idProducto;
    }

    public Item() {
    }

    public int getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(int nroSerie) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.nroSerie;
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
