/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Categoria_Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jacQu
 */
public class CategoriaDaoImpl implements CategoriaDao{
     Conexion1 con = new Conexion1();
    private Connection cn = con.getConexion();
    PreparedStatement ps; 
    
    @Override
    public String insertCategoria(Categoria_Producto newCategoria,int usuarioID) {
       String serverAnswer = null;
        try {
            CallableStatement sta = cn.prepareCall("{Call insertarCategoriaProducto (?,?)}");
            sta.setString(1, newCategoria.getNombre());
            sta.setInt(2, usuarioID);

            int numAffectedRows = sta.executeUpdate();

            if (numAffectedRows > 0) {
                serverAnswer = "Registro exitoso";
            }
        } catch (SQLException e) {
            System.out.println(e);
            serverAnswer = "Ocurrio un error";
        }
        return serverAnswer;
    }

    @Override
    public ArrayList<Categoria_Producto> getCategorias() {
         ArrayList getProveedores = new ArrayList();
        Categoria_Producto categoria_Producto;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from categoria_producto");
            while (rs.next()) {
                categoria_Producto = new Categoria_Producto();
                
                categoria_Producto.setId_categoria(rs.getInt(1));
                categoria_Producto.setNombre(rs.getString(2));
               

                getProveedores.add(categoria_Producto);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return getProveedores;
    }

    @Override
    public String updateCategoria(Categoria_Producto newCategoria_Producto,int usuarioID) {
         String serverAnswer = null;
        try {
            CallableStatement sta = cn.prepareCall("{Call modificarCategoriaProducto (?,?,?)}");
            sta.setInt(1, newCategoria_Producto.getId_categoria());
            sta.setString(2, newCategoria_Producto.getNombre());
            sta.setInt(3, usuarioID);

            int numAffectedRows = sta.executeUpdate();

            if (numAffectedRows > 0) {
                serverAnswer = "Registro exitoso";
            }
        } catch (SQLException e) {
            System.out.println(e);
            serverAnswer = "Ocurrio un error";
        }
        return serverAnswer;
    }
     public boolean deleteCategoria(int  id_categoria,int id_usuario) {
        boolean temp = false;
        try {
            CallableStatement sta = cn.prepareCall("{Call eliminarCategoriaProducto (?,?)}");
            sta.setInt(1, id_categoria);
            sta.setInt(2, id_usuario);
            

            int numAffectedRows = sta.executeUpdate();

            if (numAffectedRows > 0) {
                 temp = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
             temp = false;
        }
        return temp;
    }
    
}
