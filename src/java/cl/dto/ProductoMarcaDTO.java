/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Marca;
import cl.dominio.Producto;
import java.io.Serializable;

/**
 *
 * @author cristian
 */
public class ProductoMarcaDTO implements Serializable{
    private Producto producto;
    private Marca marca;

    public ProductoMarcaDTO(Producto producto, Marca marca) {
        this.producto = producto;
        this.marca = marca;
    }

    public ProductoMarcaDTO() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    
}
