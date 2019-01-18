/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class SQLServer {
    private SQLServer() {
    }
    public static SQLServer getInstance() {
        return SQLServerHolder.INSTANCE;
    }
     private static class SQLServerHolder {
         
        private static final SQLServer INSTANCE = new SQLServer();
    }
   
           
    
   
}
