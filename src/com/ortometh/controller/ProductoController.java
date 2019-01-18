/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;

import com.ortometh.Dao.ProductoDaoImpl;
import com.ortometh.model.Categoria_Producto;
import com.ortometh.model.Cliente;
import com.ortometh.model.Producto;
import com.ortometh.model.Proveedor;
import com.ortometh.model.Tipo_Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jacQu
 */
public class ProductoController {
     ProductoDaoImpl productoVentaDaoImpl = new ProductoDaoImpl();
     DefaultTableModel tModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    /*MOSTRAR PRODUCTOS EN EL CRUD DE PRODUCTOS */ 
    public void fillProductoInventario(JTable jProductos) throws SQLException{
        jProductos.setModel(tModel);
        TableColumnModel columnModel = jProductos.getColumnModel();
        tModel.addColumn("CODIGO");
        tModel.addColumn("CATEGORIA");
        tModel.addColumn("TIPO");
        tModel.addColumn("PROVEEDOR");
        tModel.addColumn("CLAVE");
        tModel.addColumn("MARCA");
        tModel.addColumn("DESCRIPCION");
        tModel.addColumn("STOCK ACTUAL");
        tModel.addColumn("STOCK MINIMO");
        tModel.addColumn("VENTA");
        tModel.addColumn("COMPRA");
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(250);
        columnModel.getColumn(7).setPreferredWidth(120);
        columnModel.getColumn(8).setPreferredWidth(120);
        
        Object[] columns = new Object[11]; 
        ArrayList <Producto> listProductos = productoVentaDaoImpl.getProductosInventario();
        for (Producto product : listProductos) {
            int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0]= product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getDescripcion();
            columns[7] = product.getStock();
            columns[8] = product.getStock_min();
            columns[9] = product.getPrecio_venta();
            columns[10] = product.getPrecio_compra();
            tModel.addRow(columns);
        } 
    }
    /*ELIMINAR PRODUCTOS EN EL CRUD DE PRODUCTOS*/
    public boolean eliminarProducto(String clave_producto){
        return productoVentaDaoImpl.deleteProveedor(clave_producto);  
    }
    /*RECARGAR PRODUCTOS PARA MOSTRAR EN EL CRUD DE PRODUCTOS*/
    public void refillProducto(JTable jProductos) throws SQLException{
        jProductos.setModel(tModel);
        tModel.setRowCount(0);
        
 
         Object[] columns = new Object[11]; 
        ArrayList <Producto> listProductos = productoVentaDaoImpl.getProductosInventario();
        for (Producto product : listProductos) {
            int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0]= product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getDescripcion();
            columns[7] = product.getStock();
            columns[8] = product.getStock_min();
            columns[9] = product.getPrecio_venta();
            columns[10] = product.getPrecio_compra();
            tModel.addRow(columns);
        } 
    }
    /* DEVOLVER ID PROVEEDOR EN EL CRUD DE PRODUCTOS*/
    public int id_proveedor(String proveedor) throws SQLException{
            int id = productoVentaDaoImpl.seleccionar_id_proveedor(proveedor);
            return id;
   }
   //FILTRAR PRODUCTO POR PROVEEDOR EN EL CRUD DE PRODUCTOS//
    public void filtrarProductoProveedor(JTable jProductos,int id_inventario) throws SQLException{
         jProductos.setModel(tModel);
        tModel.setRowCount(0);
        
        Object[] columns = new Object[11]; 
        ArrayList <Producto> listProductos = productoVentaDaoImpl.filtrarProductosInventario(id_inventario);
         for (Producto product : listProductos) {
            int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0]= product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getDescripcion();
            columns[7] = product.getStock();
            columns[8] = product.getStock_min();
            columns[9] = product.getPrecio_venta();
            columns[10] = product.getPrecio_compra();
            tModel.addRow(columns);
        } 
       
        }
    /* DEVOLVER ID CATEGORIA EN EL CRUD DE PRODUCTOS*/
    public int id_categoria(String categoria) throws SQLException{
            int id = productoVentaDaoImpl.seleccionar_id_categoria(categoria);
            return id;
        }
    //FILTRAR PRODUCTO POR CATEGORIA PARA EL CRUD DE PRODUCTOS*/
    public void filtrarProductoCategoria(JTable jProductos,int id_inventario) throws SQLException{
         jProductos.setModel(tModel);
        tModel.setRowCount(0);
        
        Object[] columns = new Object[11]; 
        ArrayList <Producto> listProductos = productoVentaDaoImpl.filtrarProductosCategoria(id_inventario);
            for (Producto product : listProductos) {
            int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0]= product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getDescripcion();
            columns[7] = product.getStock();
            columns[8] = product.getStock_min();
            columns[9] = product.getPrecio_venta();
            columns[10] = product.getPrecio_compra();
            tModel.addRow(columns);
        } 
       
        }
    /* DEVOLVER ID TIPO_PRODUCTO EN EL CRUD DE PRODUCTOS */
    public int Id_tipo(String tipo) throws SQLException{
            int id = productoVentaDaoImpl.seleccionar_id(tipo);
            return id;
        }
    /*FILTRAR PRODUCTO POR TIPO EN EL CRUD DE PRODUCTOS */
    public void filtrarProductoTipo(JTable jProductos,int id_inventario) throws SQLException{
         jProductos.setModel(tModel);
        tModel.setRowCount(0);
        
        Object[] columns = new Object[11]; 
        ArrayList <Producto> listProductos = productoVentaDaoImpl.filtrarProductosTipo(id_inventario);
            for (Producto product : listProductos) {
            int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0]= product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getDescripcion();
            columns[7] = product.getStock();
            columns[8] = product.getStock_min();
            columns[9] = product.getPrecio_venta();
            columns[10] = product.getPrecio_compra();
            tModel.addRow(columns);
        } 
       
        }
    /* CONTROLADOR PARA LLENAR EL COMBO DE CATEGORIA EN EL CRUD DE PRODUCTOS */
    public void fillComboCategoria(JComboBox comboCategoria){
        ArrayList<Categoria_Producto> categoria_producto = productoVentaDaoImpl.listCategoria();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Categorias");
        for (int i = 0; i < categoria_producto.size(); i++) {
            model.addElement(categoria_producto.get(i).getNombre());
        }
        comboCategoria.setModel(model);
    }
    /* CONTROLADOR PARA LLENAR EL COMBO DE TIPO_PRODUCTO */
    public void fillCombotTipo_producto(JComboBox comboTipo_producto){
        ArrayList<Tipo_Producto> tipo_producto = productoVentaDaoImpl.listTipoProducto();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Tipo de Producto");
        for (int i = 0; i < tipo_producto.size(); i++) {
            model.addElement(tipo_producto.get(i).getNombre());
        }
        comboTipo_producto.setModel(model);
    }
    /* CONTROLADOR PARA LLENAR EL COMBO DE PROVEEDOR */
    public void fillComboProveedor(JComboBox comboProveedor){
        ArrayList<Proveedor> proveedor = productoVentaDaoImpl.listProveedor();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Seleccione");
        for (int i = 0; i < proveedor.size(); i++) {
            model.addElement(proveedor.get(i).getNombre());
        }
        comboProveedor.setModel(model);
    }   
    /*INSERTAR UN PRODUCTO EN EL CRUD DE PRODUCTOS*/
    public boolean insertar_producto(int id_categoria,int id_tipo,int id_proveedor,int clave,String marca
    ,String descripcion,int stock,int stock_min,double precio_venta,double precio_compra,int id_usuario) {
              boolean var;
        Producto newproducto = new Producto(id_proveedor, id_categoria, id_tipo, id_proveedor, clave, marca, descripcion, stock, stock_min, precio_venta, precio_compra, id_usuario);
        String respuesta = null;
        newproducto.setIdCategoria(id_categoria);
        newproducto.setIdTipo(id_tipo);
        newproducto.setIdProveedor(id_proveedor);
        newproducto.setClave(clave);
        newproducto.setMarca(marca);
        newproducto.setDescripcion(descripcion);
        newproducto.setStock(stock);
        newproducto.setStock_min(stock_min);
        newproducto.setPrecio_venta(precio_venta);
        newproducto.setPrecio_compra(precio_compra);
        newproducto.setId_usuario(id_usuario);

        
        var =productoVentaDaoImpl.insertar_producto(newproducto);

        return var;

    }
    /* SELECCIONAR EL ID DEL PRODUCTO EN EL CRUD DE PRODUCTOS*/
    public int SeleccionarIdProducto(String clave) throws SQLException{
            int id = productoVentaDaoImpl.seleccionarIdProducto(clave);
            return id;
        }
    /*MODIFICAR PRODUCTO EN EL CRUD DE PRODUCTOS*/
    public boolean actualizar_producto(int id_producto,int id_categoria,int id_tipo,int id_proveedor,int clave,String marca
    ,String descripcion,int stock,int stock_min,double precio_venta,double precio_compra,int id_usuario) {
              boolean var;
        Producto newproducto = new Producto(id_producto,id_categoria, id_tipo, id_proveedor, clave,  marca,  descripcion,  stock,  stock_min,  precio_venta,  precio_compra, id_usuario);
        
        newproducto.setId_Producto(id_producto);
        newproducto.setIdCategoria(id_categoria);
        newproducto.setIdTipo(id_tipo)  ;
        newproducto.setIdProveedor(id_proveedor);
        newproducto.setClave(clave);
        newproducto.setMarca(marca);
        newproducto.setDescripcion(descripcion);
        newproducto.setStock(stock);
        newproducto.setStock_min(stock_min);
        newproducto.setPrecio_venta(precio_venta);
        newproducto.setPrecio_compra(precio_compra);
        newproducto.setId_usuario(id_usuario);


        var = productoVentaDaoImpl.actualizar_producto(newproducto);

        return var;
    }
    /*MOSTRAR PRODUCTOS EN LA TABLA DE LA VISTA BUSCAR PRODUCTO*/
    public void fillProducto(JTable jProductos) throws SQLException{  
        jProductos.setModel(tModel);
        tModel.addColumn("CÓDIGO");
        tModel.addColumn("CATEGORIA");
        tModel.addColumn("TIPO");
        tModel.addColumn("PROVEEDOR"); 
        tModel.addColumn("CLAVE");
        tModel.addColumn("MARCA");
        tModel.addColumn("PRECÍO");
        tModel.addColumn("DESCRIPCIÓN");
        tModel.addColumn("STOCK ACTUAL");
        Object[] columns = new Object[9]; 
        ArrayList <Producto> listProductos = productoVentaDaoImpl.getProductos();
        for (Producto product : listProductos) {
            int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0] = product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getPrecio_venta();
            columns[7] = product.getDescripcion();
            columns[8] = product.getStock();
            tModel.addRow(columns);    
        } 
    }
    public void RefillProducto(JTable jProductos) throws SQLException{  
        jProductos.setModel(tModel);
        tModel.setRowCount(0);
        
        Object[] columns = new Object[9]; 
        ArrayList <Producto> listProductos = productoVentaDaoImpl.getProductos();
        for (Producto product : listProductos) {
            int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0] = product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getPrecio_venta();
            columns[7] = product.getDescripcion();
            columns[8] = product.getStock();
            tModel.addRow(columns);    
        } 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  
      public void fillCliente(JTable jCliente) throws SQLException{   
     jCliente.setModel(tModel);
     
        tModel.addColumn("NO.CLIENTE");
        tModel.addColumn("NOMBRE");
        tModel.addColumn("APELLIDO PATERNO");
        tModel.addColumn("APELLIDO MATERNO");
        tModel.addColumn("DOMICILIO"); 
        tModel.addColumn("TELEFONO");
        tModel.addColumn("CORREO");
        tModel.addColumn("RFC");
        Object[] columns = new Object[8]; 
        ArrayList <Cliente> listProductos = productoVentaDaoImpl.getCliente();
        for (Cliente cliente : listProductos) {
            columns[0] = cliente.getId_cliente();
            columns[1]= cliente.getNombre();
            columns[2]= cliente.getApellidoPaterno();
            columns[3]= cliente.getApellidoMaterno();
            columns[4]= cliente.getDomicilio();
            columns[5]= cliente.getTelefono();
            columns[6]= cliente.getCorreo();
            columns[7]= cliente.getRFC();
            
            tModel.addRow(columns);    
        } 
      }
       public void refillCliente(JTable jCliente) throws SQLException{   
      jCliente.setModel(tModel);
         tModel.setRowCount(0);
          Object[] columns = new Object[8]; 
        ArrayList <Cliente> listProductos = productoVentaDaoImpl.getCliente();
        for (Cliente cliente : listProductos) {
            columns[0] = cliente.getId_cliente();
            columns[1]= cliente.getNombre();
            columns[2]= cliente.getApellidoPaterno();
            columns[3]= cliente.getApellidoMaterno();
            columns[4]= cliente.getDomicilio();
            columns[5]= cliente.getTelefono();
            columns[6]= cliente.getCorreo();
            columns[7]= cliente.getRFC();
            
            tModel.addRow(columns);    
        } 
      }
      //cliente por nombre
       public void fillClienteNombre(JTable jCliente,String parametro) throws SQLException{   
     jCliente.setModel(tModel);
        tModel.setRowCount(0);
        
          Object[] columns = new Object[8]; 
        ArrayList <Cliente> listProductos = productoVentaDaoImpl.getCliente2(parametro);
        for (Cliente cliente : listProductos) {
            columns[0] = cliente.getId_cliente();
            columns[1]= cliente.getNombre();
            columns[2]= cliente.getApellidoPaterno();
            columns[3]= cliente.getApellidoMaterno();
            columns[4]= cliente.getDomicilio();
            columns[5]= cliente.getTelefono();
            columns[6]= cliente.getCorreo();
            columns[7]= cliente.getRFC();
            
            tModel.addRow(columns);    
        } 
      }
       //MOSTRAR DATOS CUANDO SE FILTRAN POR PROVEEDOR
      public void filtrarProducto(JTable jProductos,int id_inventario) throws SQLException{
        DefaultTableModel tModel = new DefaultTableModel();
       jProductos.setModel(tModel);
        tModel.addColumn("CÓDIGO");
        tModel.addColumn("CATEGORIA");
        tModel.addColumn("TIPO");
        tModel.addColumn("PROVEEDOR"); 
        tModel.addColumn("CLAVE");
        tModel.addColumn("MARCA");
        tModel.addColumn("PRECÍO");
        tModel.addColumn("DESCRIPCIÓN");
        tModel.addColumn("STOCK ACTUAL");
        
        Object[] columns = new Object[9];
        ArrayList <Producto> listProductos = productoVentaDaoImpl.filtrarProductos(id_inventario);
        for (Producto product : listProductos) {
             int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0] = product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getPrecio_venta();
            columns[7] = product.getDescripcion();
            columns[8] = product.getStock();
            tModel.addRow(columns);  
            
        } 
    }
      //MOSTRAR DATOS FILTRADOS POR CLAVE_PRODUCTO
      public void filtrarProductoPorClave(JTable jProductos,String clave_producto) throws SQLException{
        DefaultTableModel tModel = new DefaultTableModel();
        jProductos.setModel(tModel);
        tModel.addColumn("CÓDIGO");
        tModel.addColumn("CATEGORIA");
        tModel.addColumn("TIPO");
        tModel.addColumn("PROVEEDOR"); 
        tModel.addColumn("CLAVE");
        tModel.addColumn("MARCA");
        tModel.addColumn("PRECÍO");
        tModel.addColumn("DESCRIPCIÓN");
        tModel.addColumn("STOCK ACTUAL");
        Object[] columns = new Object[9];
        ArrayList <Producto> listProductos = productoVentaDaoImpl.filtrarProductosClaveProducto(clave_producto);
        for (Producto product : listProductos) {
             int categoria = product.getIdCategoria();
            int tipo = product.getIdTipo();
            int proveedor = product.getIdProveedor();
            columns[0] = product.getClave_producto();
            columns[1] = productoVentaDaoImpl.seleccionar_nombre_categoria(categoria);
            columns[2] = productoVentaDaoImpl.seleccionar_nombre_tipo(tipo);
            columns[3] = productoVentaDaoImpl.seleccionar_nombre_proveedor(proveedor);
            columns[4] = product.getClave();
            columns[5] = product.getMarca();
            columns[6] = product.getPrecio_venta();
            columns[7] = product.getDescripcion();
            columns[8] = product.getStock();
            tModel.addRow(columns);  
            
        } 
    }
     
     
          
          
      
      
   
     
       
     
 
   
  
   
       
       
         
         
        
        public void modificarCantidad(int cantidad,String codigo_producto,int id_venta) throws SQLException{
            productoVentaDaoImpl.modificarCantidad(cantidad, codigo_producto, id_venta);
        }
        public void modificarDescuento(double descuento,String codigo_producto,int id_venta) throws SQLException{
            productoVentaDaoImpl.modificarPorcentaje(descuento, codigo_producto, id_venta);
        }
        public void modificarImporte(double importe,int id_venta) throws SQLException{
            productoVentaDaoImpl.modificarImporte(importe, id_venta);
        }
}
