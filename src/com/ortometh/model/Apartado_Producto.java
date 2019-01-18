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
public class Apartado_Producto {
    
    private String codigo;
    private String nombre;
    private double precio;
    private String marca;
    private String proveedor;
    private String descripcion;
    private int id_apartado;
    private int id_producto;
    private int descuento;
    private int cantidad;

    public Apartado_Producto() {
    }

    public Apartado_Producto(String codigo, String nombre, double precio, String marca, String descripcion, int id_apartado, int id_producto, int descuento, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.descripcion = descripcion;
        this.id_apartado = id_apartado;
        this.id_producto = id_producto;
        this.descuento = descuento;
        this.cantidad = cantidad;
    }

    public Apartado_Producto(int id_apartado, int id_producto, int cantidad) {
        this.id_apartado = id_apartado;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public int getId_apartado() {
        return id_apartado;
    }

    public void setId_apartado(int id_apartado) {
        this.id_apartado = id_apartado;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
}
