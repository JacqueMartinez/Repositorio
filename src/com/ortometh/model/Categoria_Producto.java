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
public class Categoria_Producto {
    
    public int id_categoria;
    public String nombre;

    public Categoria_Producto() {
    }

    
    public Categoria_Producto( String nombre) {
        
        this.nombre = nombre;
    }
    public Categoria_Producto( String nombre,int id_Categoria) {
        this.id_categoria = id_Categoria;
        this.nombre = nombre;
    }
    

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
