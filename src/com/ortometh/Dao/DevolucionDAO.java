/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Devolucion;
import com.ortometh.model.Producto;
import com.ortometh.model.Venta;
import com.ortometh.model.Venta_Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author xfeyz
 */
public class DevolucionDAO {
    Conexion1 con = new Conexion1();
    private Connection cn = con.getConexion();
    PreparedStatement pst;
    ResultSet rs;
    
    public ArrayList<Devolucion> getDevoluciones(){
        ArrayList getDevolucion = new ArrayList();
        Devolucion devolucion;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from devoluciones");
            
            while (rs.next()){
                devolucion = new Devolucion();
                
                devolucion.setId_venta(rs.getInt(1));
                devolucion.setId_producto(rs.getInt(2));
                devolucion.setCantidad(rs.getInt(3));
                devolucion.setTipo_devolucion(rs.getString(4));
                devolucion.setComentario(rs.getString(5));
                devolucion.setId_usuario(rs.getInt(6));
                devolucion.setNombreCliente(rs.getString(7));
                devolucion.setFecha(rs.getString(8));
                devolucion.setFechaEntrega(rs.getString(9));
                devolucion.setEstado(rs.getString(10));
                
                getDevolucion.add(devolucion);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return getDevolucion;
    }
    
    public ArrayList<Devolucion> getDevolucionByIDVenta(int idVenta){
        ArrayList getDevolucion = new ArrayList();
        Devolucion devolucion;
        
        try {
            pst= cn.prepareStatement("select * from devoluciones where id_venta = ?");
            pst.setInt(1, idVenta);
            rs = pst.executeQuery();
            
            while (rs.next()){
                devolucion = new Devolucion();
                
                devolucion.setId_venta(rs.getInt(1));
                devolucion.setId_producto(rs.getInt(2));
                devolucion.setCantidad(rs.getInt(3));
                devolucion.setTipo_devolucion(rs.getString(4));
                devolucion.setComentario(rs.getString(5));
                devolucion.setId_usuario(rs.getInt(6));
                devolucion.setNombreCliente(rs.getString(7));
                devolucion.setFecha(rs.getString(8));
                devolucion.setFechaEntrega(rs.getString(9));
                devolucion.setEstado(rs.getString(10));
                
                getDevolucion.add(devolucion);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return getDevolucion;
    }
    
    public ArrayList<Venta_Producto> getInformacionVenta(int id_venta) {
        ArrayList getVentaInfo = new ArrayList();
        Venta_Producto venta_producto;
        
        ResultSet rs=null;
        try {
            pst= cn.prepareStatement("SELECT id_ventaProducto, id_venta, id_producto, cantidad, precio, descuento, devoluciones FROM venta_producto WHERE id_venta = ?");
            pst.setInt(1, id_venta);
            rs = pst.executeQuery();
            while (rs.next()) {
                venta_producto = new Venta_Producto();
                
                venta_producto.setIdVentaProducto(rs.getInt(1));
                venta_producto.setId_venta(rs.getInt(2));
                venta_producto.setId_producto(rs.getInt(3));
                venta_producto.setCantidad(rs.getInt(4));
                venta_producto.setPrecio(rs.getDouble(5));
                venta_producto.setDescuento(rs.getDouble(6));
                venta_producto.setDevoluciones(rs.getInt(7));
              
                getVentaInfo.add(venta_producto);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return getVentaInfo;
    }
    
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> listProductos = new ArrayList<>();
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from producto where estado = 1");

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_Producto(rs.getInt(1));
                producto.setClave_producto(rs.getString(2));
                producto.setIdCategoria(rs.getInt(3));
                producto.setIdTipo(rs.getInt(4));
                producto.setIdProveedor(rs.getInt(5));
                producto.setClave(rs.getInt(6));
                producto.setMarca(rs.getString(7));
                producto.setDescripcion(rs.getString(8));
                producto.setStock(rs.getInt(9));
                producto.setStock_min(rs.getInt(10));
                producto.setDefectuoso(rs.getInt(11));
                producto.setPrecio_venta(rs.getDouble(12));
                producto.setPrecio_compra(rs.getDouble(13));
                
                listProductos.add(producto);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listProductos;
    }
    
    public ArrayList<Venta> getVentas() {
        ArrayList getVentaInfo = new ArrayList();
        Venta venta;
         
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select id_venta, importe, fecha_venta from venta where importe > 0 and estado like 'finalizado' order by id_venta desc");
            while (rs.next()) {
                venta = new Venta();
                
                venta.setId_venta(rs.getInt(1));
                venta.setImporte(rs.getInt(2));
                venta.setFecha_venta(rs.getDate(3));
              
                getVentaInfo.add(venta);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return getVentaInfo;
    }
    
    public ArrayList<Venta> getVentaByID(int id_venta) {
        ArrayList getVenta = new ArrayList();
        Venta venta;
         
        try {
            pst= cn.prepareStatement("select id_venta, importe, fecha_venta from venta where importe > 0 and id_venta = ? and estado like 'finalizado' order by id_venta desc");
            pst.setInt(1, id_venta);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                venta = new Venta();
                
                venta.setId_venta(rs.getInt(1));
                venta.setImporte(rs.getInt(2));
                venta.setFecha_venta(rs.getDate(3));
              
                getVenta.add(venta);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return getVenta;
    }
    
    public String productoClave(int idProducto){
        String clave = "";
        try {
            pst= cn.prepareStatement("select clave_producto from producto where id_producto = ?");
            pst.setInt(1, idProducto);
            rs = pst.executeQuery();
            
            while (rs.next()) {                
                clave = rs.getString(1);
            }
        } catch (Exception e) {
            
        }
        return clave;
    }
    
    public String devolverProductoInventario(int idVentaProducto, int idVenta, int idProducto, int cantidad, String tipo, String comentario, int idUsuario, String nombreCliente){
        String serverAnswer = null;
        
        try {
            CallableStatement sta = cn.prepareCall("{Call devolverProductoInv (?,?,?,?,?,?,?,?)}");
            sta.setInt(1, idVentaProducto);
            sta.setInt(2, idVenta);
            sta.setInt(3, idProducto);
            sta.setInt(4, cantidad);
            sta.setString(5, tipo);
            sta.setString(6, comentario);
            sta.setInt(7, idUsuario);
            sta.setString(8, nombreCliente);
            
            int filas = sta.executeUpdate();
            
            if(filas > 0){
                serverAnswer = "Devolución realizada";
            }
        } catch (Exception e) {
            serverAnswer = "Problemas al devolver producto";
            System.out.println(e);
        }
        return serverAnswer;
    }
    
    
    public String devolverProductoProveedor(int idVentaProducto, int idVenta, int idProducto, int cantidad, String tipo, String comentario, int idUsuario, String nombreCliente, String fechaEntrega){
        String serverAnswer = null;
        
        try {
            CallableStatement sta = cn.prepareCall("{Call devolverProductoProv (?,?,?,?,?,?,?,?,?)}");
            
            sta.setInt(1, idVentaProducto);
            sta.setInt(2, idVenta);
            sta.setInt(3, idProducto);
            sta.setInt(4, cantidad);
            sta.setString(5, tipo);
            sta.setString(6, comentario);
            sta.setInt(7, idUsuario);
            sta.setString(8, nombreCliente);
            sta.setString(9, fechaEntrega);
            
            int filas = sta.executeUpdate();
            
            if(filas > 0){
                serverAnswer = "Devolución realizada";
            }
        } catch (Exception e) {
            serverAnswer = "Problemas al devolver producto";
            System.out.println(e);
        }
        return serverAnswer;
    }
}
