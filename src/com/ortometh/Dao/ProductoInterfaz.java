/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Producto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jacQu
 */
public interface ProductoInterfaz {
    //Array de productos
    public ArrayList<Producto> getProductos();
    
}
