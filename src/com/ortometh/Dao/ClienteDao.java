/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Cliente;
import java.util.ArrayList;



/**
 *
 * @author Alexis
 */
public interface ClienteDao {
   
    public String insertarCliente(Cliente newCliente);
    public ArrayList<Cliente> obtenerCliente();
    public String actualizarCliente(Cliente newCliente);
    
    
}
