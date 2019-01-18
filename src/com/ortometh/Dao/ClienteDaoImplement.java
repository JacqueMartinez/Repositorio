/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alexis
 */
public class ClienteDaoImplement implements ClienteDao{
    
    Conexion1 con= new Conexion1();
    private Connection cn= con.getConexion();

    @Override
    public String insertarCliente(Cliente newCliente) {
        String serverAnswer = null;
        try {
            CallableStatement sta = cn.prepareCall("{CALL insertarCliente2(?,?,?,?,?,?,?,?,?,?)}");
            sta.setString(1, newCliente.getNombre());
            sta.setString(2, newCliente.getApellidoPaterno());
            sta.setString(3, newCliente.getApellidoMaterno());
            sta.setString(4, newCliente.getTelefono());
            sta.setString(5, newCliente.getDomicilio());
            sta.setString(6, newCliente.getCorreo());
            sta.setString(7, newCliente.getRFC());
            sta.setString(8, newCliente.getNombreFacturacion());
            sta.setString(9, newCliente.getTipoCliente());
            sta.setInt(10, newCliente.getId_usuario());
            
            int numAffectedRows = sta.executeUpdate();
            if(numAffectedRows >=0){
                serverAnswer= "Cliente insertado";
            }
        } catch (SQLException e) {
            System.err.print("SQL error " + e.getMessage());
        }
        return serverAnswer;
    }
    
   


    public ArrayList<Cliente> obtenerClienteFisico() {
        ArrayList<Cliente>listClientes=new ArrayList<>();
        String serverAnswer=null;
        
        try {
            Statement stament = cn.createStatement();
            ResultSet resultSet = stament.executeQuery("SELECT id_cliente,nombre,apellidop,apellidom,domicilio,telefono,correo,tipo_cliente FROM clientes WHERE estado = 1 and tipo_cliente = " + "'" + "Fisica" + "'");
            
            while (resultSet.next()) {                
                Cliente auxcliente = new Cliente();
                auxcliente.setId_cliente(resultSet.getInt(1));
                auxcliente.setNombre(resultSet.getString(2));
                auxcliente.setApellidoPaterno(resultSet.getString(3));
                auxcliente.setApellidoMaterno(resultSet.getString(4));
                auxcliente.setDomicilio(resultSet.getString(5));
                auxcliente.setTelefono(resultSet.getString(6));
                auxcliente.setCorreo(resultSet.getString(7));
                auxcliente.setTipoCliente(resultSet.getString(8));
                
                 listClientes.add(auxcliente);
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
        }
        return listClientes;
    }
    
     public ArrayList<Cliente> obtenerClienteMoral() {
        ArrayList<Cliente>listClientes=new ArrayList<>();
        String serverAnswer=null;
        
        try {
            Statement stament = cn.createStatement();
            ResultSet resultSet = stament.executeQuery("SELECT id_cliente,nombre,rfc,domicilio,telefono,correo,nombre_facturacion,tipo_cliente FROM clientes WHERE estado = 1 and tipo_cliente = " + "'" + "Moral" + "'");
            
            while (resultSet.next()) {                
                Cliente auxcliente = new Cliente();
                auxcliente.setId_cliente(resultSet.getInt(1));
                auxcliente.setNombre(resultSet.getString(2));
                auxcliente.setRFC(resultSet.getString(3));
                auxcliente.setDomicilio(resultSet.getString(4));
                auxcliente.setTelefono(resultSet.getString(5));
                auxcliente.setCorreo(resultSet.getString(6));
                auxcliente.setNombreFacturacion(resultSet.getString(7));
                auxcliente.setTipoCliente(resultSet.getString(8));
                
                 listClientes.add(auxcliente);
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
        }
        return listClientes;
    }
     public ArrayList<Cliente> buscarClienteFisico(String texto) {
        ArrayList<Cliente>listClientes=new ArrayList<>();
        String serverAnswer=null;
        
        try {
            Statement stament = cn.createStatement();
            ResultSet resultSet = stament.executeQuery("SELECT id_cliente,nombre,apellidop,apellidom,domicilio,telefono,correo,tipo_cliente FROM clientes WHERE nombre LIKE"+ "'" + texto + "%'" +" and estado=1 and tipo_cliente = " + "'" + "Fisica" + "'");
            
            while (resultSet.next()) {                
                Cliente auxcliente = new Cliente();
                auxcliente.setId_cliente(resultSet.getInt(1));
                auxcliente.setNombre(resultSet.getString(2));
                auxcliente.setApellidoPaterno(resultSet.getString(3));
                auxcliente.setApellidoMaterno(resultSet.getString(4));
                auxcliente.setDomicilio(resultSet.getString(5));
                auxcliente.setTelefono(resultSet.getString(6));
                auxcliente.setCorreo(resultSet.getString(7));
                auxcliente.setTipoCliente(resultSet.getString(8));
                
                
                 listClientes.add(auxcliente);
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
        }
        return listClientes;
     }
     
      public ArrayList<Cliente> buscarClienteMoral(String texto) {
        ArrayList<Cliente>listClientes=new ArrayList<>();
        String serverAnswer=null;
        
        try {
            Statement stament = cn.createStatement();
            ResultSet resultSet = stament.executeQuery("SELECT id_cliente,nombre,rfc,domicilio,telefono,correo,nombre_facturacion,tipo_cliente FROM clientes WHERE nombre LIKE"+ "'" + texto + "%'" +" and estado=1 and tipo_cliente = " + "'" + "Moral" + "'");
            
            while (resultSet.next()) {                
                Cliente auxcliente = new Cliente();
                auxcliente.setId_cliente(resultSet.getInt(1));
                auxcliente.setNombre(resultSet.getString(2));
                auxcliente.setRFC(resultSet.getString(3));
                auxcliente.setDomicilio(resultSet.getString(4));
                auxcliente.setTelefono(resultSet.getString(5));
                auxcliente.setCorreo(resultSet.getString(6));
                auxcliente.setNombreFacturacion(resultSet.getString(7));
                auxcliente.setTipoCliente(resultSet.getString(8));
                
                
                 listClientes.add(auxcliente);
            }
        } catch (SQLException e) {
            System.out.println("SQLException"+ e.getMessage());
        }
        return listClientes;
     }
    @Override
    public String actualizarCliente(Cliente newCliente) {
        String serverAnswer = null;
        try {
            CallableStatement sta = cn.prepareCall("{CALL modificarCliente(?,?,?,?,?,?,?,?,?,?,?,?)}");
            sta.setInt(1, newCliente.getId_cliente());
            sta.setString(2, newCliente.getNombre());
            sta.setString(3, newCliente.getApellidoPaterno());
            sta.setString(4, newCliente.getApellidoMaterno());
            sta.setString(5, newCliente.getTelefono());
            sta.setString(6, newCliente.getDomicilio());
            sta.setString(7, newCliente.getCorreo());
            sta.setString(8, newCliente.getRFC());
            sta.setString(9, newCliente.getNombreFacturacion());
            sta.setString(10, newCliente.getTipoCliente());
            sta.setInt(11, newCliente.getEstado());
            sta.setInt(12, newCliente.getId_usuario());
            
             int numAffectedRows = sta.executeUpdate();
            if(numAffectedRows >=0){
                serverAnswer= "Cliente Modificado";
            }
            
        } catch (SQLException e) {
            System.err.print("SQL error " + e.getMessage());
        }
        return serverAnswer;
    }
    
     public String eliminarCliente(Cliente newCliente){
        String serverAnswer = null;
        try {
            CallableStatement sta = cn.prepareCall("update clientes set estado = 0 where id_cliente= ?");
            sta.setInt(1, newCliente.getId_cliente());
            
            int numAffectedRows  = sta.executeUpdate();
            if(numAffectedRows >=0){
                serverAnswer= "Cliente modificado";
            }
             
        } catch (SQLException e) {
            System.err.print("SQL error " + e.getMessage());
        }
        return serverAnswer;
    }

    @Override
    public ArrayList<Cliente> obtenerCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
