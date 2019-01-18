/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;

import com.ortometh.Dao.DevolucionDAO;
import com.ortometh.model.Devolucion;
import com.ortometh.model.Producto;
import com.ortometh.model.Venta;
import com.ortometh.model.Venta_Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author xfeyz
 */
public class DevolucionController {
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    
    DefaultTableModel modelDevolucion = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    
    DefaultTableModel modelinfo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    
    DevolucionDAO devolucionDAO = new DevolucionDAO();
    
    public void fillTableDevoluciones(JTable jTableDevoluciones){
        jTableDevoluciones.setModel(modelDevolucion);
        TableColumnModel columnModel = jTableDevoluciones.getColumnModel();
        
        modelDevolucion.addColumn("VENTA");
        modelDevolucion.addColumn("PRODUCTO");
        modelDevolucion.addColumn("CANTIDAD");
        modelDevolucion.addColumn("CLIENTE");
        modelDevolucion.addColumn("SOLICITUD");
        modelDevolucion.addColumn("ENTREGA");
        modelDevolucion.addColumn("ESTADO");
        
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(50);
        columnModel.getColumn(6).setPreferredWidth(50);
        
        Object[] columns = new Object[7];
        
        ArrayList<Devolucion> devolucion = devolucionDAO.getDevoluciones();
        int rows = devolucion.size();
        
        for (int i = 0; i < rows; i++) {
            int id_Producto = devolucion.get(i).getId_producto();
            String clave = getProductoClave(id_Producto);
            
            columns [0] = devolucion.get(i).getId_venta();
            columns [1] = clave;
            columns [2] = devolucion.get(i).getCantidad();
            columns [3] = devolucion.get(i).getNombreCliente();
            columns [4] = devolucion.get(i).getFecha();
            columns [5] = devolucion.get(i).getFechaEntrega();
            columns [6] = devolucion.get(i).getEstado();
            
            modelDevolucion.addRow(columns);
        }
    }
    
    public void reFillTableDevoluciones(JTable jTableDevoluciones, int id_venta){
        jTableDevoluciones.setModel(modelDevolucion);
        modelDevolucion.setRowCount(0);
        
        Object[] columns = new Object[7];
        
        ArrayList<Devolucion> devolucion = devolucionDAO.getDevolucionByIDVenta(id_venta);
        
        int rows = devolucion.size();
        
        for (int i = 0; i < rows; i++) {
            int id_Producto = devolucion.get(i).getId_producto();
            String clave = getProductoClave(id_Producto);
            
            columns [0] = devolucion.get(i).getId_venta();
            columns [1] = clave;
            columns [2] = devolucion.get(i).getCantidad();
            columns [3] = devolucion.get(i).getNombreCliente();
            columns [4] = devolucion.get(i).getFecha();
            columns [5] = devolucion.get(i).getFechaEntrega();
            columns [6] = devolucion.get(i).getEstado();
            
            modelDevolucion.addRow(columns);
        }
    }
    
    public void recargarTableDevoluciones(JTable jTableDevoluciones){
        jTableDevoluciones.setModel(model);
        model.setRowCount(0);
        
        Object[] columns = new Object[7];
        
        ArrayList<Devolucion> devolucion = devolucionDAO.getDevoluciones();
        int rows = devolucion.size();
        
        for (int i = 0; i < rows; i++) {
            int id_Producto = devolucion.get(i).getId_producto();
            String clave = getProductoClave(id_Producto);
            
            columns [0] = devolucion.get(i).getId_venta();
            columns [1] = clave;
            columns [2] = devolucion.get(i).getCantidad();
            columns [3] = devolucion.get(i).getNombreCliente();
            columns [4] = devolucion.get(i).getFecha();
            columns [5] = devolucion.get(i).getFechaEntrega();
            columns [6] = devolucion.get(i).getEstado();
            
            modelDevolucion.addRow(columns);
        }
    }      
    
    public void fillTableVentas(JTable jTableVentas){
        jTableVentas.setModel(model);
        TableColumnModel columnModel = jTableVentas.getColumnModel();

        model.addColumn("VENTA");
        model.addColumn("IMPORTE");
        model.addColumn("FECHA");

        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(100);
        
        Object[] columns = new Object[4];
        
        ArrayList<Venta> listVentas = devolucionDAO.getVentas();
    
        int rows = listVentas.size();
            System.out.println(rows);
        
        for (int i = 0; i < rows; i++) {
            columns[0] = listVentas.get(i).getId_venta();
            columns[1] = listVentas.get(i).getImporte();
            columns[2] = listVentas.get(i).getFecha_venta();
            model.addRow(columns);
        }
      
    }
    
    public void reFillTableVentas(JTable jTableVentas, int id_venta){
        jTableVentas.setModel(model);
        model.setRowCount(0);
        
        Object[] columns = new Object[4];
        
        ArrayList<Venta> listVentas = devolucionDAO.getVentaByID(id_venta);
    
        int rows = listVentas.size();
            System.out.println(rows);
        
        for (int i = 0; i < rows; i++) {
            columns[0] = listVentas.get(i).getId_venta();
            columns[1] = listVentas.get(i).getImporte();
            columns[2] = listVentas.get(i).getFecha_venta();
            model.addRow(columns);
        }
      
    }
    
    public void recargarTableVentas(JTable jTableVentas){
        jTableVentas.setModel(model);
        model.setRowCount(0);
        
        Object[] columns = new Object[4];
        
        ArrayList<Venta> listVentas = devolucionDAO.getVentas();
    
        int rows = listVentas.size();
            System.out.println(rows);
        
        for (int i = 0; i < rows; i++) {
            columns[0] = listVentas.get(i).getId_venta();
            columns[1] = listVentas.get(i).getImporte();
            columns[2] = listVentas.get(i).getFecha_venta();
            model.addRow(columns);
        }
    }
    
    public void fillTableInfoVenta(JTable jTableInfoVentas){
        jTableInfoVentas.setModel(modelinfo);
        modelinfo.setColumnCount(0);
        TableColumnModel columnModel = jTableInfoVentas.getColumnModel();

        modelinfo.addColumn("NO. CONTROL");
        modelinfo.addColumn("ID");
        modelinfo.addColumn("PRODUCTO");
        modelinfo.addColumn("VENDIDO");
        modelinfo.addColumn("SUBTOTAL");
        modelinfo.addColumn("DESCUENTO");
        modelinfo.addColumn("DEVUELTO");

        columnModel.getColumn(0).setPreferredWidth(75);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(75);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(75);
        columnModel.getColumn(6).setPreferredWidth(50);
        
        Object[] columns = new Object[7];
        
        modelinfo.addRow(columns);
        modelinfo.setRowCount(0);
    }
    
    public void reFillTableInfoVenta(JTable jTableInfoVentas, int id_venta){
        jTableInfoVentas.setModel(modelinfo);
        modelinfo.setRowCount(0);
        
        Object[] columns = new Object[7];
        
        ArrayList<Venta_Producto> listVentas = devolucionDAO.getInformacionVenta(id_venta);
        int rows = listVentas.size();
        
        for (int i = 0; i < rows; i++) {
            int id_Producto = listVentas.get(i).getId_producto();
            String clave = getProductoClave(id_Producto);
            
            columns[0] = listVentas.get(i).getIdVentaProducto();
            columns[1] = listVentas.get(i).getId_producto();
            columns[2] = clave;
            columns[3] = listVentas.get(i).getCantidad();
            columns[4] = listVentas.get(i).getPrecio();
            columns[5] = listVentas.get(i).getDescuento();
            columns[6] = listVentas.get(i).getDevoluciones();
            modelinfo.addRow(columns);
        }
    }
    
    public String getProductoCodigo(int id_producto){
        List<Producto> productoCodigo = devolucionDAO.getProductos().stream().filter(p -> p.getId_Producto() == id_producto).collect(Collectors.toList());
        String claveProducto = productoCodigo.get(0).getClave_producto();
        
        return claveProducto;
    }
    
    public String getProductoClave(int id_producto){
        String claveProducto = devolucionDAO.productoClave(id_producto);
        return claveProducto;
    }
    
    public String devolverProductoInventario(int idVentaProducto, int idVenta, int idProducto, int cantidad, String tipo, String comentario, int idUsuario, String nombreCliente){
        String message = devolucionDAO.devolverProductoInventario(idVentaProducto,idVenta, idProducto, cantidad, tipo, comentario, idUsuario, nombreCliente);
        
        return message;
    }
    
    public String devolverProductoProveedor(int idVentaProducto, int idVenta, int idProducto, int cantidad, String tipo, String comentario, int idUsuario, String nombreCliente, String fechaEntrega){
        String message = devolucionDAO.devolverProductoProveedor(idVentaProducto, idVenta, idProducto, cantidad, tipo, comentario, idUsuario, nombreCliente, fechaEntrega);
        
        return message;
    }
}
