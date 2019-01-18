/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;

import com.ortometh.Dao.CategoriaDaoImpl;
import com.ortometh.model.Categoria_Producto;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Alan
 */
public class Categoria_ProductoController {
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    
    CategoriaDaoImpl categoriaDaoImpl = new CategoriaDaoImpl();
     public String insertarCategoria(String nombre, int usuarioID) {
         Categoria_Producto categoria_Producto = new Categoria_Producto(nombre);

        String mensaje = categoriaDaoImpl.insertCategoria(categoria_Producto, usuarioID);
        return mensaje;
    }
     public String actualizarCategoria(String nombre,int id_categoria,int usuarioID) {
         Categoria_Producto categoria_Producto = new Categoria_Producto(nombre, id_categoria);

        String mensaje = categoriaDaoImpl.updateCategoria(categoria_Producto, usuarioID);
        return mensaje;
    }
     
      public void fillCategorias(JTable tableCategorias) {
        tableCategorias.setModel(model);
        TableColumnModel columnModel = tableCategorias.getColumnModel();

        model.addColumn("NUMERO");
        model.addColumn("NOMBRE");
       

        columnModel.getColumn(0).setPreferredWidth(50);


        Object[] columns = new Object[2];

        ArrayList<Categoria_Producto> listCategoria = categoriaDaoImpl.getCategorias();
        int filas = listCategoria.size();

        for (int i = 0; i < filas; i++) {
            columns[0] = listCategoria.get(i).getId_categoria();
            columns[1] = listCategoria.get(i).getNombre();
      

            model.addRow(columns);
        }
    }
       
      public void refillCategorias(JTable tableCategorias) {
        tableCategorias.setModel(model);
        model.setRowCount(0);

        Object[] columns = new Object[2];

        ArrayList<Categoria_Producto> listCategoria = categoriaDaoImpl.getCategorias();
        int filas = listCategoria.size();

        for (int i = 0; i < filas; i++) {
            columns[0] = listCategoria.get(i).getId_categoria();
            columns[1] = listCategoria.get(i).getNombre();
      

            model.addRow(columns);
        }
    }
}


