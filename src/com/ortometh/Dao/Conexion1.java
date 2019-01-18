/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jacQu
 */
public class Conexion1 {
    //Usuario de sql predeterminado
    static  Connection conexion = null;
    public static String usuario = "DB_A43EB1_Ortometh20_admin";
    public static String contraseña = "Ortometh20";
    public static boolean status= false;
    
    public static Connection getConexion(){
        String url ="jdbc:sqlserver://sql5006.site4now.net;databaseName=DB_A43EB1_Ortometh20";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexion" + e.getMessage(),
            "Error de conexion",JOptionPane.ERROR_MESSAGE);
        }
        try {
            conexion= DriverManager.getConnection(url,Conexion1.usuario,Conexion1.contraseña);
            status= true;
        } catch (SQLException e) {  
            JOptionPane.showMessageDialog(null, "eror"+ e.getMessage(),"error de conexiones",JOptionPane.ERROR_MESSAGE);
        }
        return conexion;
    }   
    //Si se realizo la conexion el status pasa a true
    public static boolean getStatus(){
        return status;
    }
}
