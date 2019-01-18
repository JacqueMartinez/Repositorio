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
public class Usuario {

    //Campos verificados en la Base de datos
    private int id_usuario;
    private String nombre;
    private String nombreUsuario;
    private String contraseña;
    private String privilegios;
    private int estado;
    private String telefono;
    private String ultima_sesion;

    public Usuario(int id_usuario, String nombre, String nombreUsuario, String contraseña, String privilegios, int estado, String telefono, String ultima_sesion) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.privilegios = privilegios;
        this.estado = estado;
        this.telefono = telefono;
        this.ultima_sesion = ultima_sesion;
    }
    
    public Usuario() {

    }

    public Usuario(String nombreUsuario, String ultima_sesion) {
        this.nombreUsuario = nombreUsuario;
        this.ultima_sesion=ultima_sesion;
    }
    
//        public Usuario(String nombreUsuario) {
//        this.nombreUsuario = nombreUsuario;
//      
//    }
    
    
    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getPrivilegios() {
        return privilegios;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setPrivilegios(String privilegios) {
        this.privilegios = privilegios;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUltima_sesion() {
        return ultima_sesion;
    }

    public void setUltima_sesion(String ultima_sesion) {
        this.ultima_sesion = ultima_sesion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    

}
