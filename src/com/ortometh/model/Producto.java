/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.model;

/**
 *
 * @author jacQu
 */
public class Producto {
    
    private int id_Producto;
    String clave_producto;
    private int idCategoria;
    private int idTipo;
    private int idProveedor;
    private int clave;
    private String marca;
    private String descripcion;
    private int stock;
    private int stock_min;
    private int defectuoso;
    private double precio_venta;
    private double precio_compra;
    private int estado;
    private int id_usuario;
    public Producto() {
    }

    public Producto(int id_Producto, int idCategoria, int idTipo, int idProveedor, int clave, String marca, String descripcion, int stock, int stock_min, double precio_venta, double precio_compra,int id_usuario) {
        this.id_Producto = id_Producto;
        this.idCategoria = idCategoria;
        this.idTipo = idTipo;
        this.idProveedor = idProveedor;
        this.clave = clave;
        this.marca = marca;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stock_min = stock_min;
        this.precio_venta = precio_venta;
        this.precio_compra = precio_compra;
        this.id_usuario= id_usuario;
    }

    public Producto(int id_Producto, int idCategoria, int idTipo, int idProveedor, int clave, String marca, String descripcion, int stock, int stock_min, double precio_venta, double precio_compra, int estado, int id_usuario) {
        this.id_Producto = id_Producto;
        this.idCategoria = idCategoria;
        this.idTipo = idTipo;
        this.idProveedor = idProveedor;
        this.clave = clave;
        this.marca = marca;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stock_min = stock_min;
        this.precio_venta = precio_venta;
        this.precio_compra = precio_compra;
        this.estado = estado;
        this.id_usuario = id_usuario;
    }
    

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_min() {
        return stock_min;
    }

    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
    }

    public int getDefectuoso() {
        return defectuoso;
    }

    public void setDefectuoso(int defectuoso) {
        this.defectuoso = defectuoso;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getClave_producto() {
        return clave_producto;
    }

    public void setClave_producto(String clave_producto) {
        this.clave_producto = clave_producto;
    }
    

}