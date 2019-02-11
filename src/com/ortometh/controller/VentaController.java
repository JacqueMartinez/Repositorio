/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;

import com.ortometh.Dao.SQLServer;
import com.ortometh.Dao.Conexion1;
import com.ortometh.Dao.VentaDaoImpl;
import com.ortometh.model.Cliente;
import com.ortometh.model.Proveedor;
import com.ortometh.model.Venta;
import com.ortometh.model.Venta_Producto;
import static com.ortometh.viewMain.Venta.txtCodigoProducto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jacQu
 */
public class VentaController {
     VentaDaoImpl ventaTiendaDaoImpl = new VentaDaoImpl();
    //DEFAULTTABLEMODEL DE LA TABLA
    DefaultTableModel model = new DefaultTableModel() {
//        @Override
//        public boolean isCellEditable(int fila, int columna) {
//            return false;
//        }
        };
    //INICIAR VENTA
    public String iniciar_venta(String tipo_venta,int id_usuario,int id_cliente,int id_departamento) {
        Venta newventa = new Venta( tipo_venta, id_usuario,id_cliente,id_departamento);
        String respuesta = null;
        newventa.setTipo_venta(tipo_venta);
        newventa.setId_usuario(id_usuario);
        newventa.setId_cliente(id_cliente);
        newventa.setId_departamento(id_departamento);
        respuesta = ventaTiendaDaoImpl.iniciar_venta(newventa);
        return respuesta;
    }
    //FINALIZAR VENTA
      public String finalizar_venta(int id_venta,String tipo_pago,String referencia,String comentario,int id_usuario) {
       
        String respuesta = null;
     
        respuesta = ventaTiendaDaoImpl.finalizar_venta(id_venta, tipo_pago, referencia, comentario, id_usuario);

        return respuesta;

    }
    //FINALIZAR VENTA EFECTIVO
         public String finalizar_venta_efectivo(int id_venta,String tipo_pago,int id_usuario) {
       
        String respuesta = null;
     
        respuesta = ventaTiendaDaoImpl.finalizar_venta_efectivo(id_venta, tipo_pago, id_usuario);

        return respuesta;

    }
    //LLENAR VENTA
    public boolean llenar_venta(int id_cliente,int id_venta,int id_producto,int cantidad,double descuento,int id_usario) {
       
        return ventaTiendaDaoImpl.llenar_venta(id_cliente, id_venta, id_producto, cantidad, descuento, id_usario);

    }
    // RELLENAR LA TABLA DE VENTA
    public void fillProductos(JTable jtventa,int id,String clave_producto){
        jtventa.setModel(model);
        
        model.addColumn("CÓDIGO ");
        model.addColumn("DESCRIPCIÓN");
        model.addColumn("CANTIDAD");
        model.addColumn("PRECIO UNITARIO");
        model.addColumn("DESCUENTO ");
        model.addColumn("PRECIO NETO");
        
        
        Object[] columns = new Object[6];
        
        ArrayList<Venta_Producto> listaventa = ventaTiendaDaoImpl.getInformacionVenta(id);
      
        int filas = listaventa.size();
        
        for (int i = 0; i < filas; i++) {
            try {
                int id_producto= listaventa.get(i).getId_producto();
                columns[0] = ventaTiendaDaoImpl.seleccionar_codigo_producto(id_producto);
                columns[1] = ventaTiendaDaoImpl.seleccionar_descripcion(id_producto);
                columns[2] = listaventa.get(i).getCantidad();
                columns[3] = ventaTiendaDaoImpl.seleccionar_precio(id_producto);
                columns[4] = listaventa.get(i).getDescuento();
                columns[5] = listaventa.get(i).getPrecio();
                model.addRow(columns);
            } catch (SQLException ex) {
                Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // RECARGAR DATOS DE LA TABLA VENTA
    public void reFillProveedores(JTable jtventa,int id,String clave_producto) throws SQLException{
        jtventa.setModel(model);
        model.setRowCount(0);
        
        Object[] columns = new Object[6];
        
         ArrayList<Venta_Producto> listaventa = ventaTiendaDaoImpl.getInformacionVenta(id);
      
        int filas = listaventa.size();
        
        for (int i = 0; i < filas; i++) {
            int id_producto= listaventa.get(i).getId_producto();
            columns[0] = ventaTiendaDaoImpl.seleccionar_codigo_producto(id_producto);
            columns[1] = ventaTiendaDaoImpl.seleccionar_descripcion(id_producto);
            columns[2] = listaventa.get(i).getCantidad();
            columns[3] = ventaTiendaDaoImpl.seleccionar_precio(id_producto);
             columns[4] = listaventa.get(i).getDescuento();
            columns[5] = listaventa.get(i).getPrecio();
            model.addRow(columns);
        }
    }
    ////CONTAR LOS PRODUCTOS DE LA VENTA
    public int contar_venta (int id) {
          return ventaTiendaDaoImpl.contar_venta(id);    
     }
    //SELECCIONAR EL ID DE LA VENTA
    public int seleccionar_idVenta () throws SQLException{
        return ventaTiendaDaoImpl.seleccionar_idVenta();
    }
    //SELECCIONAR IMPORTE DE LA VENTA
    public double seleccionar_importe(int id_venta){
        return ventaTiendaDaoImpl.seleccionar_importe(id_venta);
    }
    public double subtotal(int id_venta){
        return ventaTiendaDaoImpl.subtotal(id_venta);
    }
    
    public ArrayList datos_producto(String clave_producto){
        ArrayList<String> datos = new ArrayList<>();
        ResultSet rs2;
        try {
            rs2 = ventaTiendaDaoImpl.datos_producto(clave_producto);

            while (rs2.next()) {
                datos.add(String.valueOf(rs2.getString("clave_producto")));
                datos.add(String.valueOf(rs2.getString("descripcion")));
                datos.add(String.valueOf(rs2.getDouble("precio_venta")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return datos;
    }
    
    public boolean cancelar_venta(int id_venta, int id_usuario){
        return ventaTiendaDaoImpl.cancelar_venta(id_venta,id_usuario);
    }
    
    public boolean cancelar_producto(int id_ve,int id_producto,int cantidad) throws SQLException{
        return ventaTiendaDaoImpl.cancelar_producto(id_ve,id_producto,cantidad);
    }
    public boolean eliminar_producto(int id_ve,int id_prod) throws SQLException{
        return ventaTiendaDaoImpl.eliminar_prodcuto(id_ve,id_prod);
    }
  
   
}
