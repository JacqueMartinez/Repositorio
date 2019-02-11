/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Cliente;
import com.ortometh.model.Proveedor;
import com.ortometh.model.Venta;
import com.ortometh.model.Venta_Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jacQu
 */
public class VentaDaoImpl implements ventaInterfaz{
    Conexion1 con= new Conexion1();
    private Connection cn= con.getConexion();
    
    @Override
    //METODO PARA INICIAR VENTA AL ABRIR LA VISTA
    public String iniciar_venta(Venta newventa){
        String respuesta=null;
        try (  CallableStatement sta=  cn.prepareCall("{Call crearVenta (?,?,?,?)}")){
              sta.setInt(1, newventa.getId_cliente());
              sta.setString(2 ,newventa.getTipo_venta());
              sta.setInt(3, newventa.getId_usuario());
              sta.setInt(4, newventa.getId_departamento());
              sta.executeQuery();
              respuesta="se a agregado";
        } catch (SQLException e) {
                System.out.println(e.getMessage());
           
        }
        return respuesta;
      
    }
    //DEVOLVER DATOS DE LA VENTA
    public ResultSet seleccionar_id (int id)throws SQLException{
        PreparedStatement pst;
        ResultSet rs=null;
        try{
            pst= cn.prepareStatement("SELECT * from venta where id_venta=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
        }catch(Exception e){
            
        }
        return rs;
    }
    //SELECCIONAR IMPORTE
      public double seleccionar_importe (int id) {
        PreparedStatement pst;
        ResultSet rs=null;
        double var =0;
        try{
            pst= cn.prepareStatement("SELECT SUM(precio) FROM venta_producto WHERE id_venta =?");
             pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            var=rs.getDouble(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return var;
    }
      public double subtotal(int id_venta){
        PreparedStatement pst;
        ResultSet rs=null;
        double var =0;
        try{
            pst= cn.prepareStatement("SELECT sum(precio+((producto.precio_venta*(descuento/100))*cantidad))\n" +
            "FROM venta_producto inner join producto on venta_producto.id_producto = producto.id_producto where id_venta = ?");
             pst.setInt(1, id_venta);
            rs = pst.executeQuery();
            rs.next();
            var=rs.getDouble(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return var;
      }
    //CONTAR LOS PRODUCTOS DE LA VENTA
    public int contar_venta (int id) {
        PreparedStatement pst;
        ResultSet rs=null;
        int var =0;
        try{
            pst= cn.prepareStatement(" SELECT SUM(cantidad) FROM venta_producto WHERE id_venta =?");
             pst.setInt(1, id);
            rs = pst.executeQuery();
            rs.next();
            var=rs.getInt(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return var;
    }
    //SELECCIONAR EL ULTIMO ID DE LA VENTA, SERIA LA VENTA EN EJECUCION 
    public int seleccionar_idVenta ()throws SQLException{
        PreparedStatement pst;
        ResultSet rs=null;
        int var=0;
        try{
            pst= cn.prepareStatement("SELECT TOP 1 id_venta FROM venta ORDER BY id_venta DESC  ");
            rs = pst.executeQuery();
            rs.next();
            var=rs.getInt(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return var;
        
    }
    
    //DATOS DEL PRODUCTO DEL QUE SE SELECCIONE O INGRESE POR CLAVE
     public ResultSet datos_producto (String clave_producto)throws SQLException{
        PreparedStatement pst;
        ResultSet rs=null;
        try{
            pst= cn.prepareStatement("SELECT * from producto where clave_producto=?");
            pst.setString(1, clave_producto);
            rs = pst.executeQuery();
        }catch(Exception e){
            
        }
        return rs;
        
    }
    //LLENAR VENTA CON DATOS
    public boolean llenar_venta(int id_cliente,int id_venta,int id_producto,int cantidad,double descuento,int id_usario){
        boolean var=true;
        try (  CallableStatement sta=  cn.prepareCall("{Call rellenarVenta (?,?,?,?,?,?)}")){
              sta.setInt(1, id_cliente);
              sta.setInt(2, id_venta);
              sta.setInt(3, id_producto);
              sta.setInt(4, cantidad);
              sta.setDouble(5, descuento);
              sta.setInt(6, id_usario);
              
              sta.executeQuery();
              var = false;
              
        } catch (SQLException e) {
            System.out.println(e.getMessage());
           
        }
        return var;
      
    }
    
//    public ArrayList<Venta_Producto> getVenta() {
//        ArrayList getProveedores = new ArrayList();
//        Proveedor proveedor;
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery("select * from venta_producto where id_venta = ?");
//            while (rs.next()) {
//                proveedor = new Proveedor();
//                
//                proveedor.setId_proveedor(rs.getInt(1));
//                proveedor.setNombre(rs.getString(2));
//                proveedor.setRfc(rs.getString(3));
//                proveedor.setTelefono(rs.getString(4));
//                proveedor.setCorreo(rs.getString(5));
//
//                getProveedores.add(proveedor);
//
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return getProveedores;
//    }
      
    //OBTENER INFORMACION PARA LLENAR LA TABLA
    public ArrayList<Venta_Producto> getInformacionVenta(int id) {
        ArrayList getProveedores = new ArrayList();
        Venta_Producto venta_producto;
        PreparedStatement pst;
        ResultSet rs=null;
        try {
            pst= cn.prepareStatement("SELECT * from venta_producto where id_venta=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                venta_producto = new Venta_Producto();
                venta_producto.setId_venta(rs.getInt(2));
                venta_producto.setId_producto(rs.getInt(3));
                venta_producto.setCantidad(rs.getInt(4));
                venta_producto.setPrecio(rs.getDouble(5));
                venta_producto.setDescuento(rs.getDouble(6));
              
                getProveedores.add(venta_producto);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return getProveedores;
    }
    //FINALIZAR VENTA TARJETA
    public String finalizar_venta(int id_venta,String tipo_pago,String referencia,String comentario,int id_usuario){
        String respuesta=null;
        try (  CallableStatement sta=  cn.prepareCall("{Call finalizarVenta (?,?,?,?,?)}")){
              sta.setInt(1, id_venta);
              sta.setString(2, tipo_pago);
              sta.setString(3, referencia);
              sta.setString(4, comentario);
              sta.setInt(5, id_usuario);
              sta.executeQuery();
              respuesta="se a agregado";
        } catch (SQLException e) {
                System.out.println(e.getMessage());
           
        }
        return respuesta;
      
    }
    //FINALIZAR VENTA EFECTIVO
    public String finalizar_venta_efectivo(int id_venta,String tipo_pago,int id_usuario){
        String respuesta=null;
        try (  CallableStatement sta=  cn.prepareCall("{Call finalizarVentaEfectivo (?,?,?)}")){
              sta.setInt(1, id_venta);
              sta.setString(2, tipo_pago);

              sta.setInt(3, id_usuario);
              sta.executeQuery();
              respuesta="se a agregado";
        } catch (SQLException e) {
                System.out.println(e.getMessage());
           
        }
        return respuesta;
      
    }
      public String seleccionar_codigo_producto(int id_producto) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        String var = null;
        try {
            pst = cn.prepareStatement("select clave_producto from producto where id_producto=?");
            pst.setInt(1, id_producto);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
       public String seleccionar_descripcion(int id_producto) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        String var = null;
        try {
            pst = cn.prepareStatement("select descripcion from producto where id_producto=?");
            pst.setInt(1, id_producto);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
        public double seleccionar_precio(int id_producto) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        Double var = null;
        try {
            pst = cn.prepareStatement("select precio_venta from producto where id_producto=?");
            pst.setInt(1, id_producto);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getDouble(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
        
        public boolean cancelar_venta(int id_venta,int id_usuario){
            boolean var=true;
        try (  CallableStatement sta=  cn.prepareCall("{Call cancelarVenta (?,?)}")){
              sta.setInt(1, id_venta);
              sta.setInt(2, id_usuario);
              
              
              sta.executeQuery();
              var = false;
              
        } catch (SQLException e) {
            System.out.println(e.getMessage());
           
        }
        return var;
        }
        
        public boolean cancelar_producto(int id_venta,int id_producto,int cantidad) throws SQLException{
          boolean var=true;
        try (  CallableStatement sta=  cn.prepareCall("{Call modificarVentaProducto (?,?,?)}")){
              sta.setInt(1, id_venta);
              sta.setInt(2, id_producto);
              sta.setInt(3, cantidad);
              
              sta.executeQuery();
              var = false;
              
        } catch (SQLException e) {
            System.out.println(e.getMessage());
           
        }
        return var;
        }
         public boolean eliminar_prodcuto(int id_venta,int id_producto) throws SQLException{
          boolean var=true;
        try (  CallableStatement sta=  cn.prepareCall("{Call eliminarProductoVenta (?,?)}")){
              sta.setInt(1, id_venta);
              sta.setInt(2, id_producto);
              
              sta.executeQuery();
              var = false;
              
        } catch (SQLException e) {
            System.out.println(e.getMessage());
           
        }
        return var;
        }
       
          public ArrayList<Integer> getIdVenta() {
        ArrayList<Integer> listVentas = new ArrayList<>();
        try {
            Integer var ;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select id_venta from venta where estado = 'Finalizado';");
            while (rs.next()) {
                var = rs.getInt(1);
                listVentas.add(var);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listVentas;
        }
        
}
