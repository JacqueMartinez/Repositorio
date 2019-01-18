/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import static com.ortometh.Dao.Conexion1.usuario;
import java.util.ArrayList;


/**
 *
 * @author jacQu
 */
public interface UsuarioDAO {
    
    String insertarUsuario(com.ortometh.model.Usuario user);
    String actualizarUsuario(com.ortometh.model.Usuario user,int idUsuario);
    ArrayList<com.ortometh.model.Usuario> getUsuario();
//    boolean login(UsuarioDAO user);
    String eliminarUsuario(String nombreUsuario);
  
        
    
}
