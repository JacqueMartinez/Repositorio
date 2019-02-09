/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Proveedor;
import java.util.ArrayList;

/**
 *
 * @author xfeyz
 */
public interface ProveedorDAO {
    
    String insertProveedor(Proveedor newProveedor, int usuarioID);
    
    ArrayList<Proveedor> getProveedores(int idDepartamento);
    
    String updateProveedor(Proveedor proveedor, int usuarioID);
    
    String deleteProveedor(int proveedorID);
    
}
