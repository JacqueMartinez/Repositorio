/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.model;

/**
 *
 * @author bitia
 */
public class Privilegios {
    private int idPrivilegio;
    private String nombre;

    public Privilegios(int idPrivilegio, String nombre) {
        this.idPrivilegio = idPrivilegio;
        this.nombre = nombre;
    }
    
    public Privilegios(){
        
    }

    public int getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(int idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
