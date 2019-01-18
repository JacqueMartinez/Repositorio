/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.model;

/**
 *
 * @author Alexis
 */
public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String domicilio;
    private String telefono;
    private String correo;
    private String RFC;
    private String nombreFacturacion;
    private String tipoCliente;
    private int estado;
    private int id_usuario;

    public Cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    

    public Cliente(int id_cliente, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String telefono, String correo, String RFC, String nombreFacturacion, String tipoCliente, int estado, int id_usuario) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correo = correo;
        this.RFC = RFC;
        this.nombreFacturacion = nombreFacturacion;
        this.tipoCliente = tipoCliente;
        this.estado = estado;
        this.id_usuario = id_usuario;
    }
    
    

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String telefono, String correo, String RFC, String nombreFacturacion, String tipoCliente, int id_usuario) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correo = correo;
        this.RFC = RFC;
        this.nombreFacturacion = nombreFacturacion;
        this.tipoCliente = tipoCliente;
        this.id_usuario = id_usuario;
    }

  
    
    
    

    public Cliente() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRFC() {
        return RFC;
    }

    public String getNombreFacturacion() {
        return nombreFacturacion;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public int getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public void setNombreFacturacion(String nombreFacturacion) {
        this.nombreFacturacion = nombreFacturacion;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
    
}
