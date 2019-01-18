/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;

import com.ortometh.Dao.Conexion1;
import com.ortometh.Dao.CorteCajaDAO;
import com.ortometh.Dao.VentaDaoImpl;
import com.ortometh.model.CorteCaja;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author xfeyz
 */
public class ControllerReportes {

    Conexion1 con = new Conexion1();
    private Connection cn = con.getConexion();
    
    CorteCajaDAO corteCajaDAO = new CorteCajaDAO();
    //TICKET
    public void reporteVenta(int idVenta) {
        try {
//            String path = "src/com/ortometh/report/ReporteDeVenta.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteDeVenta.jasper";
            JasperReport jasperReport = null;
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameters = new HashMap();
            parameters.put("NumeroVenta", idVenta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
           // Logger.getLogger(ControllerReportes.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    //REPORTE VENTAS DIARIAS
    public void reporteVentasDiarias(String fecha1, String fecha2) {
        try {
           // String path = "C:\\Users\\jacQu\\Desktop\\OrtomethV1\\Ortometh\\src\\com\\ortometh\\report\\ReporteVentas.jasper";
//            String path = "src/com/ortometh/report/ReporteVentasDiarias.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteVentasDiarias.jasper";
            JasperReport jasperReport = null;
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameter = new HashMap();
            parameter.put("fecha1", fecha1);
            parameter.put("fecha2", fecha2);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
            // Logger.getLogger(ControllerReportes.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }
    
   
    public void reportesProductoCategorias(String categoria){
        try {
//            String path = "src/com/ortometh/report/ReporteProductoCategoria.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteProductoCategoria.jasper";
            JasperReport jasperReport = null;
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameter = new HashMap();
            
            parameter.put("Nombre", categoria);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    
    public void reportesProductoProveedores(String proveedor){
        try {
//            String path = "src/com/ortometh/report/ReporteProductoProveedor.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteProductoProveedor.jasper";
            JasperReport jasperReport = null;
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameter = new HashMap();
            
            parameter.put("Nombre", proveedor);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    
     public void reportesProductoTipo(String tipo){
        try {
//            String path = "src/com/ortometh/report/ReporteProductoTipo.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteProductoTipo.jasper";
            JasperReport jasperReport = null;
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameter = new HashMap();
            
            parameter.put("Nombre", tipo);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
     
    public void ejecutarReporteGeneralStockMin(){
       try {
//              String file= "src/com/ortometh/report/ReporteProductoStockMin.jasper"; 
              String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteProductoStockMin.jasper";
              JasperReport jasperReport = null;
              jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
              JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, cn);
              //para visualizar el objeto
              JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
              jasperViewer.viewReport(jasperPrint, false);
              //mostrar
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
          } catch (JRException e) {
              System.out.println("Error jasper " + e.getMessage());
          }
      }
    
    public void ejecutarReporteGeneralStockMax(){
       try {
//              String file= "src/com/ortometh/report/ReporteProductoStockMax.jasper";
              String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteProductoStockMax.jasper";
              JasperReport jasperReport = null;
              jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
              JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, cn);
              //para visualizar el objeto
              JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
              jasperViewer.viewReport(jasperPrint, false);
              //mostrar
//             jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//             jasperViewer.setVisible(true);
          } catch (JRException e) {
              System.out.println("Error jasper " + e.getMessage());
          }
      }

    
    
      public void ejecutarReporteGeneralProductos(){
       try {
//              String file= "src/com/ortometh/report/ReporteProducto.jasper"; 
              String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteProducto.jasper";
              JasperReport jasperReport = null;
              jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
              JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, cn);
              //para visualizar el objeto
              JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
              jasperViewer.viewReport(jasperPrint, false);
              //mostrar
//              jasperViewer.setVisible(true);
          } catch (JRException e) {
              System.out.println("Error jasper " + e.getMessage());
          }
      }
      //-------------------------- reporte de compras por cliente ----------------------- 
      public void reporteVentaCliente(int  id_cliente) {
        try {
            JasperReport jasperReport = null;
//            String path = "src/com/ortometh/report/pruebaReporteClienteVentas.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "pruebaReporteClienteVentas.jasper";
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameters = new HashMap();
            parameters.put("id_cliente", id_cliente);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
            Logger.getLogger(ControllerReportes.class.getName()).log(Level.SEVERE, null, e);
        }
    }
      
        //-------------------------- reporte Tipo Cliente ----------------------- 
      public void reporteTipoCliente() {
        try {
            JasperReport jasperReport = null;
//            String path = "src/com/ortometh/report/ReporteTipoCliente.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteTipoCliente.jasper";
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
            Logger.getLogger(ControllerReportes.class.getName()).log(Level.SEVERE, null, e);
        }
    }
      //-------------------------- reporte Tipo Cliente Moral----------------------- 
      public void reporteTipoClienteMoral() {
        try {
            JasperReport jasperReport = null;
//            String path = "src/com/ortometh/report/ReporteTipoClienteMoral.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteTipoClienteMoral.jasper";
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
            Logger.getLogger(ControllerReportes.class.getName()).log(Level.SEVERE, null, e);
        }
    }
      
    public void reportesVentasID(int id_venta){
        try {
//            String path = "src/com/ortometh/report/ReporteVenta.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteVenta.jasper";
            JasperReport jasperReport = null;
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameter = new HashMap();
            
            parameter.put("NombreV", id_venta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void reporteDevolucion(int  id_venta) {
        try {
            JasperReport jasperReport = null;
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteDevoluciones.jasper";
//            String path = "src/com/ortometh/report/ReporteDevoluciones.jasper";
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
//            jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("../report/ReporteDevoluciones.jasper"));
            Map parameters = new HashMap();
            parameters.put("idVenta", id_venta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
////            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
////            jasperViewer.setVisible(true);
        } catch (JRException e) {
            Logger.getLogger(ControllerReportes.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void reporteCorteCaja(String user) {
        try {
            // String path = "C:\\Users\\jacQu\\Desktop\\OrtomethV1\\Ortometh\\src\\com\\ortometh\\report\\ReporteVentas.jasper";
//            String path = "src/com/ortometh/report/ReporteCorteCaja.jasper";
            String path = System.getProperty("user.dir") + "/src/com/ortometh/report/" + "ReporteCorteCaja.jasper";
            JasperReport jasperReport = null;
            jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parameters = new HashMap();
            parameters.put("Nombre", user);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.viewReport(jasperPrint, false);
//            jasperViewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            jasperViewer.setVisible(true);
        } catch (JRException e) {
            // Logger.getLogger(ControllerReportes.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }
    
    public void corteCaja(String fecha1, String fecha2){
        corteCajaDAO.corteCajaDAO(fecha1, fecha2);
    }
    
    public void corteCajaTabla(JTable tabla){
        ArrayList<CorteCaja> list = corteCajaDAO.corteCajaDAO();
        DefaultTableModel model = new DefaultTableModel();
        tabla.setModel(model);
        TableColumnModel col = tabla.getColumnModel();
        
        model.addColumn("Categoria");
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        model.addColumn("Monto");
        model.addColumn("Tipo");
        model.addColumn("Subtotal Abonos");
        model.addColumn("Subtotal Venta");
        model.addColumn("Total");
        
        col.getColumn(0).setPreferredWidth(100);
        col.getColumn(1).setPreferredWidth(100);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(100);
        col.getColumn(4).setPreferredWidth(100);
        col.getColumn(5).setPreferredWidth(100);
        col.getColumn(6).setPreferredWidth(100);
        col.getColumn(7).setPreferredWidth(100);
        
        Object[] colums = new Object[10];
        int size = list.size();
        
        for (int i = 0; i < size; i++) {
            colums[0] = list.get(i).getCategoria();
            colums[1] = list.get(i).getProducto();
            colums[2] = list.get(i).getCantidad();
            colums[3] = list.get(i).getVenta();
            colums[4] = list.get(i).getTipo();
            colums[5] = list.get(i).getSubtotalA();
            colums[6] = list.get(i).getSubtotalV();
            colums[7] = list.get(i).getTotal();
            model.addRow(colums);
        }
    }
    public boolean buscarId(int id_venta){
        boolean var = false;
        VentaDaoImpl daoImpl = new VentaDaoImpl();
        ArrayList ventas = daoImpl.getIdVenta();
        for (int i = 0; i < ventas.size(); i++) {
            if(ventas.get(i).equals(id_venta)){
                var = true;
            }
        }
        return var;
    }
}
