/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.CorteCaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan
 */
public class CorteCajaDAO {

    Conexion1 conexion = new Conexion1();

    private Connection db = conexion.getConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public void corteCajaDAO(String fecha1, String fecha2) {
        try {
            ps = db.prepareStatement("{CALL corteCaja(?,?)}");
            ps.setString(1, fecha1);
            ps.setString(2, fecha2);
            int row = ps.executeUpdate();

            if (row > 0) {
                JOptionPane.showMessageDialog(null, "Reporte generado correctamente");
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR corte caja procedure " + e.getMessage());
        }
        
    }

    public ArrayList<CorteCaja> corteCajaDAO() {
        ArrayList<CorteCaja> list = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT * FROM corte_caja");
            rs = ps.executeQuery();
            CorteCaja caja;
            while (rs.next()) {
                caja = new CorteCaja();
                caja.setCategoria(rs.getString(2));
                caja.setProducto(rs.getString(3));
                caja.setCantidad(rs.getInt(4));
                caja.setVenta(rs.getDouble(5));
                caja.setTipo(rs.getString(6));
                caja.setSubtotalA(rs.getDouble(7));
                caja.setSubtotalV(rs.getDouble(8));
                caja.setTotal(rs.getDouble(9));
                list.add(caja);
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR corte caja tabla " + e.getMessage());
        }
        return list;
    }

}
