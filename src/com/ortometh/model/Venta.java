/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.model;

import java.sql.Date;

/**
 *
 * @author jacQu
 */
public class Venta {
    private int id_venta;
    private int id_cliente;
    private String tipo_venta;
    private Date fecha_venta;
    private String entrada_venta;
    private double importe;
    private double descuento;
    private double apartado;
    private String usuario;
    private Date fecha_umodificacion;
    private int id_usuario;
    private int id_departamento;

    public Venta() {
    }
    

    public Venta(String tipo_venta,int id_usuario,int id_cliente,int id_departamento) {

        this.tipo_venta = tipo_venta;
        this.id_usuario = id_usuario;
        this.id_venta = id_cliente;
        this.id_departamento = id_departamento;
        
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getEntrada_venta() {
        return entrada_venta;
    }

    public void setEntrada_venta(String entrada_venta) {
        this.entrada_venta = entrada_venta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getApartado() {
        return apartado;
    }

    public void setApartado(double apartado) {
        this.apartado = apartado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha_umodificacion() {
        return fecha_umodificacion;
    }

    public void setFecha_umodificacion(Date fecha_umodificacion) {
        this.fecha_umodificacion = fecha_umodificacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }
    

}