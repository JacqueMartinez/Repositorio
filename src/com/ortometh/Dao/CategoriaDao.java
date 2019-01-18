/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Categoria_Producto;
import java.util.ArrayList;

/**
 *
 * @author jacQu
 */
public interface CategoriaDao {
    String insertCategoria(Categoria_Producto newCategoria,int usuarioID);
    ArrayList<Categoria_Producto> getCategorias();
    String updateCategoria(Categoria_Producto newCategoria_Producto,int usuarioID);
   
}
