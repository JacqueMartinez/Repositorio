/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Proveedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xfeyz
 */
public class ProveedorDAOImpl implements ProveedorDAO {

    Conexion1 con = new Conexion1();
    private Connection cn = con.getConexion();
    PreparedStatement ps; 
    
    @Override
    public String insertProveedor(Proveedor newProveedor, int usuarioID) {
        String serverAnswer = null;

        try {
            CallableStatement sta = cn.prepareCall("{Call agregarProveedor (?,?,?,?,?)}");
            sta.setString(1, newProveedor.getNombre());
            sta.setString(2, newProveedor.getRfc());
            sta.setString(3, newProveedor.getTelefono());
            sta.setString(4, newProveedor.getCorreo());
            sta.setInt(5, usuarioID);

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
    public ArrayList<Proveedor> getProveedores() {
        ArrayList getProveedores = new ArrayList();
        Proveedor proveedor;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from proveedor where estado = 1");
            while (rs.next()) {
                proveedor = new Proveedor();
                
                proveedor.setId_proveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setRfc(rs.getString(3));
                proveedor.setTelefono(rs.getString(4));
                proveedor.setCorreo(rs.getString(5));

                getProveedores.add(proveedor);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return getProveedores;
    }

    @Override
    public String updateProveedor(Proveedor proveedor, int usuarioID) {
        String serverAnswer = null;

        try {
            CallableStatement sta = cn.prepareCall("{Call actualizarProveedor (?,?,?,?,?,?)}");
            sta.setInt(1, proveedor.getId_proveedor());
            sta.setString(2, proveedor.getNombre());
            sta.setString(3, proveedor.getRfc());
            sta.setString(4, proveedor.getTelefono());
            sta.setString(5, proveedor.getCorreo());
            sta.setInt(6, usuarioID);

            int numAffectedRows = sta.executeUpdate();

            if (numAffectedRows > 0) {
                serverAnswer = "Proveedor Modificado";
            }
        } catch (SQLException ex) {
            serverAnswer = "Problemas!";
            System.out.println(ex);
        }
        return serverAnswer;
    }

    @Override
    public String deleteProveedor(int proveedorID) {
        String message = null;
        try {
            ps = cn.prepareStatement("update proveedor set estado = 0 where id_proveedor = ?");
            ps.setInt(1, proveedorID);
            int result = ps.executeUpdate();
            if (result > 0) {
                message = "Proveedor eliminado";
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            message = "Error";
        }
        return message;
    }
    
    public Proveedor getProveedorByName(String parametro) {
        Proveedor proveedor = null;
        try {
            ps = cn.prepareStatement("select * from proveedor where nombre like ?");
            ps.setString(1, parametro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proveedor = new Proveedor();
                
                proveedor.setId_proveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setRfc(rs.getString(3));
                proveedor.setTelefono(rs.getString(4));
                proveedor.setCorreo(rs.getString(5));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }
    
    public Proveedor getProveedorByRFC(String parametro){
        Proveedor proveedor = null;
        try {
            ps = cn.prepareStatement("select * from proveedor where rfc like ?");
            ps.setString(1, parametro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proveedor = new Proveedor();
                
                proveedor.setId_proveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setRfc(rs.getString(3));
                proveedor.setTelefono(rs.getString(4));
                proveedor.setCorreo(rs.getString(5));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }
}
