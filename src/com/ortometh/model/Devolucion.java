/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.model;

/**
 *
 * @author xfeyz
 */
public class Devolucion {

    private int id_devolucion;
    private int id_venta;
    private int id_producto;
    private int id_cliente;
    private int id_usuario;
    private int cantidad;
    private String tipo_devolucion;
    private String comentario;
    private String fecha;
    private String fechaEntrega;
    private String estado;
    private String nombreCliente;
    
    public Devolucion(){
        
    }

    public Devolucion(int id_devolucion, int id_venta, int id_producto, int id_cliente, int id_usuario, int cantidad, String tipo_devolucion, String comentario, String fecha, String fechaEntrega, String estado) {
        this.id_devolucion = id_devolucion;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.id_cliente = id_cliente;
        this.id_usuario = id_usuario;
        this.cantidad = cantidad;
        this.tipo_devolucion = tipo_devolucion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    public int getId_devolucion() {
        return id_devolucion;
    }

    public void setId_devolucion(int id_devolucion) {
        this.id_devolucion = id_devolucion;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo_devolucion() {
        return tipo_devolucion;
    }

    public void setTipo_devolucion(String tipo_devolucion) {
        this.tipo_devolucion = tipo_devolucion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
