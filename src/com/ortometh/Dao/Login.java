/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import java.sql.Connection;

/**
 *
 * @author xfeyz
 */
public class Login {

    Conexion1 con = new Conexion1();
    private Connection cn = con.getConexion();
    
    public boolean iniciarSesion(UsuarioDAO user){
        return false;
    }
}
