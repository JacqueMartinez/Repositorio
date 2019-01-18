/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.model;

/**
 *
 * @author Alan
 */
public class CorteCaja {
    
    private String producto;
    private String categoria;
    private int cantidad;
    private double venta;
    private String tipo;
    private double subtotalA;
    private double subtotalV;
    private double total;

    public CorteCaja() {
    }

    public CorteCaja(String producto, String categoria, int cantidad, double venta, String tipo, double subtotalA, double subtotalV, double total) {
        this.producto = producto;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.venta = venta;
        this.tipo = tipo;
        this.subtotalA = subtotalA;
        this.subtotalV = subtotalV;
        this.total = total;
    }
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }

    public double getSubtotalA() {
        return subtotalA;
    }

    public void setSubtotalA(double subtotalA) {
        this.subtotalA = subtotalA;
    }

    public double getSubtotalV() {
        return subtotalV;
    }

    public void setSubtotalV(double subtotalV) {
        this.subtotalV = subtotalV;
    }
    
}
