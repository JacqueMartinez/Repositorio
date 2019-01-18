/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Abono;
import com.ortometh.model.Apartados;
import com.ortometh.model.Cliente;
import com.ortometh.model.Proveedor;
import java.util.ArrayList;

/**
 *
 * @author Alan
 */
public interface ApartadosDao {
    
    public void guardarApartado(Apartados newApartado);
    
    public void actualizarApartado(Apartados apartado);
    
    public ArrayList<Proveedor> consultarProveedores();
    
    public ArrayList<Cliente> consultarClientes();
    
    public ArrayList<Apartados> consultarApartadosProceso(String sentencia, String dato);
    
    public ArrayList<Abono> consultarAbonos(int id_apartado);
}
