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
public class Apartados {
    
    private int apartado = 0;
    private String claveApartado = "";
    private int id_venta = 0;
    private int id_abono = 0;
    private int proveedor = 0;
    private String cliente = "";
    private String doctor = "";
    private String clinica = "";
    private double costo = 0.0d;
    private String fecha = "";
    private String fechaUM = "";
    private String numeroCuarto = "";
    private double abono = 0.0d;
    private String observaciones = "";
    private double restantante = 0.0d;
    private double cambio = 0.0d;
    private int id_usuario = 0;
    private String estado;
    private String tipo;

    public Apartados() {
    }

    
    public Apartados(int id_venta, int proveedor, String cliente, String doctor, String clinica, double costo, String numeroCuarto, double abono, String observaciones, int id_usuario) {
        this.id_venta = id_venta;
        this.proveedor = proveedor;
        this.cliente = cliente;
        this.doctor = doctor;
        this.clinica = clinica;
        this.costo = costo;
        this.numeroCuarto = numeroCuarto;
        this.abono = abono;
        this.observaciones = observaciones;
        this.id_usuario = id_usuario;
    }
    
    
    public Apartados(int apartado, double abono, String observaciones, int id_usuario) {
        this.apartado = apartado;
        this.abono = abono;
        this.observaciones = observaciones;
        this.id_usuario = id_usuario;
    }

    public Apartados(int id_venta, int id_abono, int proveedor, String cliente, String doctor, String clinica, double costo, String fecha, String numeroCuarto, double abono, String observaciones, double restante) {
        this.id_venta = id_venta;
        this.id_abono = id_abono;
        this.proveedor = proveedor;
        this.cliente = cliente;
        this.doctor = doctor;
        this.clinica = clinica;
        this.costo = costo;
        this.fecha = fecha;
        this.numeroCuarto = numeroCuarto;
        this.abono = abono;
        this.observaciones = observaciones;
        this.restantante = restante;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_abono() {
        return id_abono;
    }

    public void setId_abono(int id_abono) {
        this.id_abono = id_abono;
    }
    
    public int getApartado() {
        return apartado;
    }

    public void setApartado(int apartado) {
        this.apartado = apartado;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumeroCuarto() {
        return numeroCuarto;
    }

    public void setNumeroCuarto(String numeroCuarto) {
        this.numeroCuarto = numeroCuarto;
    }

    public double getRestantante() {
        return restantante;
    }

    public void setRestantante(double restantante) {
        this.restantante = restantante;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getClaveApartado() {
        return claveApartado;
    }

    public void setClaveApartado(String claveApartado) {
        this.claveApartado = claveApartado;
    }

    public String getFechaUM() {
        return fechaUM;
    }

    public void setFechaUM(String fechaUM) {
        this.fechaUM = fechaUM;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
