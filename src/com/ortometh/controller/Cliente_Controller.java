/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;

import com.ortometh.Dao.ClienteDaoImplement;
import com.ortometh.Dao.Conexion1;
import com.ortometh.model.Cliente;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alexis
 */
public class Cliente_Controller {
    ClienteDaoImplement clienteDaoImplement = new ClienteDaoImplement();
    Conexion1 con= new Conexion1();
    private Connection cn= con.getConexion();
    
    public String insertarCliente(String nombre,String apellidoPaterno,String apellidoMaterno,String telefono,String domicilio,String correo,
            String RFC,String nombreFacturacion,String tipoCliente,int id_usuario){
        
        Cliente newCliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, telefono, domicilio, correo, RFC, nombreFacturacion, tipoCliente, id_usuario);
        String serverAnswer = clienteDaoImplement.insertarCliente(newCliente);
        
        return serverAnswer;
    }
    
    public String actualizarCliente(int id_cliente,String nombre,String apellidoPaterno,String apellidoMaterno,String domicilio,String telefono,String correo,
        String RFC,String nombreFacturacion,String tipoCliente,int estado,int id_usuario){
        
        Cliente newCliente = new Cliente(id_cliente, nombre, apellidoPaterno, apellidoMaterno, domicilio, telefono, correo, RFC, nombreFacturacion, tipoCliente, 1, id_usuario);
        String serverAnswer = clienteDaoImplement.actualizarCliente(newCliente);
        
        return serverAnswer;
    }
    
    public String eliminarCliente(int id_cliente){
        
        Cliente newCliente = new Cliente(id_cliente);
        String serverAnswer = clienteDaoImplement.eliminarCliente(newCliente);
        
        return serverAnswer;
    }
    
    public void fillTableClienteFisico(JTable jtable){
        DefaultTableModel tModel = new DefaultTableModel();
        
        jtable.setModel(tModel);
        tModel.addColumn("No.");
        tModel.addColumn("Nombre");
        tModel.addColumn("Apellido Paterno");
        tModel.addColumn("Apellido Materno");
        tModel.addColumn("Domicilio");
        tModel.addColumn("Telefono");
        tModel.addColumn("Correo");
        tModel.addColumn("Tipo");      
        Object[] colums=new Object[8];
        
        ArrayList<Cliente> listOfCliente = clienteDaoImplement.obtenerClienteFisico();
        
        for (Cliente listofCliente : listOfCliente) {
            colums[0] = listofCliente.getId_cliente();
            colums[1] = listofCliente.getNombre();
            colums[2] = listofCliente.getApellidoPaterno();
            colums[3] = listofCliente.getApellidoMaterno();
            colums[4] = listofCliente.getDomicilio();
            colums[5] = listofCliente.getTelefono();
            colums[6] = listofCliente.getCorreo();
            colums[7] = listofCliente.getTipoCliente();
            
            tModel.addRow(colums);
        }
        
        
    }
      public void fillTableClienteMoral(JTable jtable){
        DefaultTableModel tModel = new DefaultTableModel();
        
        jtable.setModel(tModel);
        tModel.addColumn("No.");
        tModel.addColumn("Razon Social");
        tModel.addColumn("RFC");
        tModel.addColumn("Domicilio");
        tModel.addColumn("Telefono");
        tModel.addColumn("Correo");
        tModel.addColumn("Nombre de la factura");
        tModel.addColumn("Tipo");      
        Object[] colums=new Object[8];
        
        ArrayList<Cliente> listOfCliente = clienteDaoImplement.obtenerClienteMoral();
        
        for (Cliente listofCliente : listOfCliente) {
            colums[0] = listofCliente.getId_cliente();
            colums[1] = listofCliente.getNombre();
            colums[2] = listofCliente.getRFC();
            colums[3] = listofCliente.getDomicilio();
            colums[4] = listofCliente.getTelefono();
            colums[5] = listofCliente.getCorreo();
            colums[6] = listofCliente.getNombreFacturacion();
            colums[7] = listofCliente.getTipoCliente();
            
            tModel.addRow(colums);
        }
        
        
    }
      
     public void buscarTableClienteMoral(JTable jtable,String texto){
        DefaultTableModel tModel = new DefaultTableModel();
        
        jtable.setModel(tModel);
        
        tModel.addColumn("No.");
        tModel.addColumn("Razon Social");
        tModel.addColumn("RFC");
        tModel.addColumn("Domicilio");
        tModel.addColumn("Telefono");
        tModel.addColumn("Correo");
        tModel.addColumn("Nombre de la factura");
        tModel.addColumn("Tipo");      
        Object[] colums=new Object[8];
       
    
        ArrayList<Cliente> listOfCliente = clienteDaoImplement.buscarClienteMoral(texto);
        
        for (Cliente listofCliente : listOfCliente) {
            colums[0] = listofCliente.getId_cliente();
            colums[1] = listofCliente.getNombre();
            colums[2] = listofCliente.getRFC();
            colums[3] = listofCliente.getDomicilio();
            colums[4] = listofCliente.getTelefono();
            colums[5] = listofCliente.getCorreo();
            colums[6] = listofCliente.getNombreFacturacion();
            colums[7] = listofCliente.getTipoCliente();
            
            tModel.addRow(colums);
        }
     }
    
    public void buscarTableClienteFisico(JTable jtable,String texto){
        DefaultTableModel tModel = new DefaultTableModel();
        
        jtable.setModel(tModel);
        
        tModel.addColumn("No.");
        tModel.addColumn("Nombre");
        tModel.addColumn("Apellido Paterno");
        tModel.addColumn("Apellido Materno");
        tModel.addColumn("Domicilio");
        tModel.addColumn("Telefono");
        tModel.addColumn("Correo");
        tModel.addColumn("Tipo");      
        Object[] colums=new Object[8];
       
    
        ArrayList<Cliente> listOfCliente = clienteDaoImplement.buscarClienteFisico(texto);
        
        for (Cliente listofCliente : listOfCliente) {
            colums[0] = listofCliente.getId_cliente();
            colums[1] = listofCliente.getNombre();
            colums[2] = listofCliente.getApellidoPaterno();
            colums[3] = listofCliente.getApellidoMaterno();
            colums[4] = listofCliente.getDomicilio();
            colums[5] = listofCliente.getTelefono();
            colums[6] = listofCliente.getCorreo();
            colums[7] = listofCliente.getTipoCliente();
            
            tModel.addRow(colums);
        }
         
    }
}
    
