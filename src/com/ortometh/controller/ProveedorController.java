/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;

import com.ortometh.Dao.ProveedorDAOImpl;
import com.ortometh.model.Proveedor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Alan
 */
public class ProveedorController {

    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    ProveedorDAOImpl proveedorDAOImpl = new ProveedorDAOImpl();

    public String ingresarProveedor(String nombre, String RFC, String telefono, String correo, int usuarioID) {
        Proveedor proveedor = new Proveedor(nombre, RFC, telefono, correo);

        String mensaje = proveedorDAOImpl.insertProveedor(proveedor, usuarioID);
        return mensaje;
    }

    public void fillProveedores(JTable tableProveedores) {
        tableProveedores.setModel(model);
        TableColumnModel columnModel = tableProveedores.getColumnModel();

        model.addColumn("ID");
        model.addColumn("NOMBRE");
        model.addColumn("RFC");
        model.addColumn("TELÉFONO");
        model.addColumn("CORREO ELECTRÓNICO");

        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(200);

        Object[] columns = new Object[5];

        ArrayList<Proveedor> listaProveedores = proveedorDAOImpl.getProveedores();
        int filas = listaProveedores.size();

        for (int i = 0; i < filas; i++) {
            columns[0] = listaProveedores.get(i).getId_proveedor();
            columns[1] = listaProveedores.get(i).getNombre();
            columns[2] = listaProveedores.get(i).getRfc();
            columns[3] = listaProveedores.get(i).getTelefono();
            columns[4] = listaProveedores.get(i).getCorreo();

            model.addRow(columns);
        }
    }

    public void reFillProveedores(JTable tableProveedores) {
        tableProveedores.setModel(model);
        model.setRowCount(0);

        Object[] columns = new Object[5];

        ArrayList<Proveedor> listaProveedores = proveedorDAOImpl.getProveedores();
        int filas = listaProveedores.size();

        for (int i = 0; i < filas; i++) {
            columns[0] = listaProveedores.get(i).getId_proveedor();
            columns[1] = listaProveedores.get(i).getNombre();
            columns[2] = listaProveedores.get(i).getRfc();
            columns[3] = listaProveedores.get(i).getTelefono();
            columns[4] = listaProveedores.get(i).getCorreo();

            model.addRow(columns);
        }
    }

    public String modificarProveedor(int idProveedor, String nombre, String RFC, String telefono, String correo, int usuarioID) {
        Proveedor proveedor = new Proveedor(idProveedor, nombre, RFC, telefono, correo);

        String message = proveedorDAOImpl.updateProveedor(proveedor, usuarioID);
        return message;
    }

    public String eliminarProveedor(int proveedorID) {
        String message = proveedorDAOImpl.deleteProveedor(proveedorID);
        return message;
    }

    public boolean getProveedorByNombre(JTable tableProveedores, String parametro) {
        tableProveedores.setModel(model);
        model.setRowCount(0);
        boolean result = false;

        Object[] columns = new Object[5];

        Proveedor listaProveedores = proveedorDAOImpl.getProveedorByName(parametro);
        if (listaProveedores != null) {
            columns[0] = listaProveedores.getId_proveedor();
            columns[1] = listaProveedores.getNombre();
            columns[2] = listaProveedores.getRfc();
            columns[3] = listaProveedores.getTelefono();
            columns[4] = listaProveedores.getCorreo();

            model.addRow(columns);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean getProveedorByRFC(JTable tableProveedores, String parametro) {
        tableProveedores.setModel(model);
        model.setRowCount(0);
        boolean result = false;

        Object[] columns = new Object[5];

        Proveedor listaProveedores = proveedorDAOImpl.getProveedorByRFC(parametro);
        if (listaProveedores != null) {
            columns[0] = listaProveedores.getId_proveedor();
            columns[1] = listaProveedores.getNombre();
            columns[2] = listaProveedores.getRfc();
            columns[3] = listaProveedores.getTelefono();
            columns[4] = listaProveedores.getCorreo();

            model.addRow(columns);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public void consultarProveedores(JComboBox jcb) {
        List<String> list = proveedorDAOImpl.getProveedores().stream().map(o -> o.getNombre()).distinct().collect(Collectors.toList());
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Proveedores");
        list.forEach(of -> model.addElement(of));
        jcb.setModel(model);
    }
}
