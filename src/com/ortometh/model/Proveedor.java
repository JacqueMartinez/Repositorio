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
public class Proveedor {
    
    private int id_proveedor;
    private String nombre;
    private String rfc;
    private String telefono;
    private String correo;
    private int departamento;
    private String observaciones;
    
    public Proveedor(){
        
    }

    public Proveedor(int id_proveedor, String nombre, String rfc, String telefono, String correo, int departamento, String observaciones) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.rfc = rfc;
        this.telefono = telefono;
        this.correo = correo;
        this.departamento = departamento;
        this.observaciones = observaciones;
    }
    
     public Proveedor(String nombre, String rfc, String telefono, String correo, int departamento, String observaciones) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.telefono = telefono;
        this.correo = correo;
        this.departamento = departamento;
        this.observaciones = observaciones;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
