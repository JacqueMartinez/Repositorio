/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Categoria_Producto;
import com.ortometh.model.Cliente;
import com.ortometh.model.Producto;
import com.ortometh.model.Proveedor;
import com.ortometh.model.Tipo_Producto;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jacQu
 */
public class ProductoDaoImpl implements ProductoInterfaz {

    Conexion1 con = new Conexion1();
    private Connection cn = con.getConexion();

    @Override
    /*OBTENER PRODUCTOS PARA LA VISTA DE BUSCAR PRODUCTOS*/
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> listProductos = new ArrayList<>();
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from producto where stock > 0 and estado=1");
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setClave_producto(rs.getString(2));
                producto.setIdCategoria(rs.getInt(3));
                producto.setIdTipo(rs.getInt(4));
                producto.setIdProveedor(rs.getInt(5));
                producto.setClave(rs.getInt(6));
                producto.setMarca(rs.getString(7));
                producto.setPrecio_venta(rs.getDouble(12));
                producto.setDescripcion(rs.getString(8));
                producto.setStock(rs.getInt(9));
                listProductos.add(producto);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listProductos;
    }

    
    public ArrayList<Producto> getProductosInventario() {
        ArrayList<Producto> listProductos = new ArrayList<>();
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from producto where estado = 1");

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setClave_producto(rs.getString(2));
                producto.setIdCategoria(rs.getInt(3));
                producto.setIdTipo(rs.getInt(4));
                producto.setIdProveedor(rs.getInt(5));
                producto.setClave(rs.getInt(6));
                producto.setMarca(rs.getString(7));
                producto.setDescripcion(rs.getString(8));
                producto.setStock(rs.getInt(9));
                producto.setStock_min(rs.getInt(10));
                producto.setPrecio_venta(rs.getDouble(12));
                producto.setPrecio_compra(rs.getDouble(13));
                listProductos.add(producto);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listProductos;
    }

    public boolean insertar_producto(Producto newProducto) {
        boolean var = false;
        try (CallableStatement sta = cn.prepareCall("{Call insertarProducto (?,?,?,?,?,?,?,?,?,?,?)}")) {
            sta.setInt(1, newProducto.getIdCategoria());
            sta.setInt(2, newProducto.getIdTipo());
            sta.setInt(3, newProducto.getIdProveedor());
            sta.setInt(4, newProducto.getClave());
            sta.setString(5, newProducto.getMarca());
            sta.setString(6, newProducto.getDescripcion());
            sta.setInt(7, newProducto.getStock());
            sta.setInt(8, newProducto.getStock_min());
            sta.setDouble(9, newProducto.getPrecio_venta());
            sta.setDouble(10, newProducto.getPrecio_compra());
            sta.setInt(11, newProducto.getId_usuario());

            sta.executeQuery();
            var = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return var;

    }

    public boolean actualizar_producto(Producto newProducto) {
        String respuesta = null;
        try (CallableStatement sta = cn.prepareCall("{Call modificarProducto (?,?,?,?,?,?,?,?,?,?,?,?)}")) {
            sta.setInt(1, newProducto.getId_Producto());
            sta.setInt(2, newProducto.getIdCategoria());
            sta.setInt(3, newProducto.getIdTipo());
            sta.setInt(4, newProducto.getIdProveedor());
            sta.setInt(5, newProducto.getClave());
            sta.setString(6, newProducto.getMarca());
            sta.setString(7, newProducto.getDescripcion());
            sta.setInt(8, newProducto.getStock());
            sta.setInt(9, newProducto.getStock_min());
            sta.setDouble(10, newProducto.getPrecio_venta());
            sta.setDouble(11, newProducto.getPrecio_compra());
            sta.setInt(12, newProducto.getId_usuario());

            sta.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return true;

    }
    /* FILTRAR O BUSCAR PRODUCTOS POR PORVEEODR*/
    public ArrayList<Producto> filtrarProductos(int id_inventario) {
        ResultSet rs;
        CallableStatement st;
        ArrayList<Producto> listProductos = new ArrayList<>();
        try {
            st = cn.prepareCall("{Call filtrarProductos (?)}");
            st.setInt(1, id_inventario);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setClave_producto(rs.getString(2));
                producto.setIdCategoria(rs.getInt(3));
                producto.setIdTipo(rs.getInt(4));
                producto.setIdProveedor(rs.getInt(5));
                producto.setClave(rs.getInt(6));
                producto.setMarca(rs.getString(7));
                producto.setPrecio_venta(rs.getDouble(11));
                producto.setDescripcion(rs.getString(8));
                producto.setStock(rs.getInt(9));
                listProductos.add(producto);
            }
        } catch (Exception e) {

        }
        return listProductos;
    }
    /*FILTRAR PRODUCTOS POR CLAVE_PRODUCTO*/
    public ArrayList<Producto> filtrarProductosClaveProducto(String clave_producto) throws SQLException {
        ArrayList<Producto> listProductos = new ArrayList<>();
        ResultSet rs;
        PreparedStatement pst;
        try {
            pst = cn.prepareStatement("select * from producto where clave_producto = ?");
            pst.setString(1, clave_producto);
            rs = pst.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setClave_producto(rs.getString(2));
                producto.setIdCategoria(rs.getInt(3));
                producto.setIdTipo(rs.getInt(4));
                producto.setIdProveedor(rs.getInt(5));
                producto.setClave(rs.getInt(6));
                producto.setMarca(rs.getString(7));
                producto.setPrecio_venta(rs.getDouble(11));
                producto.setDescripcion(rs.getString(8));
                producto.setStock(rs.getInt(9));
                listProductos.add(producto);
            }
        } catch (Exception e) {
                    
        }
        return listProductos;
    }
    //FILTRAR PRODUCTOS POR PROVEEDOR
     public ArrayList<Producto> filtrarProductosInventario(int id_inventario) {
        ResultSet rs;
        CallableStatement st;
        ArrayList<Producto> listProductos = new ArrayList<>();
        try {
            st = cn.prepareCall("{Call filtrarProductos (?)}");
            st.setInt(1, id_inventario);
            rs = st.executeQuery();
            while (rs.next()) {
               Producto producto = new Producto();
                producto.setClave_producto(rs.getString(2));
                producto.setIdCategoria(rs.getInt(3));
                producto.setIdTipo(rs.getInt(4));
                producto.setIdProveedor(rs.getInt(5));
                producto.setClave(rs.getInt(6));
                producto.setMarca(rs.getString(7));
                producto.setDescripcion(rs.getString(8));
                producto.setStock(rs.getInt(9));
                producto.setStock_min(rs.getInt(10));
                producto.setPrecio_venta(rs.getDouble(12));
                producto.setPrecio_compra(rs.getDouble(13));
                listProductos.add(producto);
            }
        } catch (Exception e) {

        }
        return listProductos;
    }
    //FILTRAR PRODUCTOS POR CATEGORIA
    public ArrayList<Producto> filtrarProductosCategoria(int id_inventario) {
        ResultSet rs;
        CallableStatement st;
        ArrayList<Producto> listProductos = new ArrayList<>();
        try {
            st = cn.prepareCall("{Call filtrarProductosCategoria (?)}");
            st.setInt(1, id_inventario);
            rs = st.executeQuery();
            while (rs.next()) {
               Producto producto = new Producto();
                producto.setClave_producto(rs.getString(2));
                producto.setIdCategoria(rs.getInt(3));
                producto.setIdTipo(rs.getInt(4));
                producto.setIdProveedor(rs.getInt(5));
                producto.setClave(rs.getInt(6));
                producto.setMarca(rs.getString(7));
                producto.setDescripcion(rs.getString(8));
                producto.setStock(rs.getInt(9));
                producto.setStock_min(rs.getInt(10));
                producto.setPrecio_venta(rs.getDouble(12));
                producto.setPrecio_compra(rs.getDouble(13));
                listProductos.add(producto);
            }
        } catch (Exception e) {

        }
        return listProductos;
    }
    
     //FILTRAR PRODUCTOS POR TIPO
    public ArrayList<Producto> filtrarProductosTipo(int id_inventario) {
        ResultSet rs;
        CallableStatement st;
        ArrayList<Producto> listProductos = new ArrayList<>();
        try {
            st = cn.prepareCall("{Call filtrarProductosTipo (?)}");
            st.setInt(1, id_inventario);
            rs = st.executeQuery();
            while (rs.next()) {
               Producto producto = new Producto();
                producto.setClave_producto(rs.getString(2));
                producto.setIdCategoria(rs.getInt(3));
                producto.setIdTipo(rs.getInt(4));
                producto.setIdProveedor(rs.getInt(5));
                producto.setClave(rs.getInt(6));
                producto.setMarca(rs.getString(7));
                producto.setDescripcion(rs.getString(8));
                producto.setStock(rs.getInt(9));
                producto.setStock_min(rs.getInt(10));
                producto.setPrecio_venta(rs.getDouble(12));
                producto.setPrecio_compra(rs.getDouble(13));
                listProductos.add(producto);
            }
        } catch (Exception e) {

        }
        return listProductos;
    }
    public boolean deleteProveedor(String clave_producto) {
        boolean temp = false;
        try {
            PreparedStatement ps = cn.prepareStatement("update producto set estado = 0 where clave_producto = ?");
            ps.setString(1, clave_producto);
            int result = ps.executeUpdate();
            if (result > 0) {
                temp = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
        return temp;
    }

    public ArrayList<Producto> getProductosId() {
        ArrayList listProductos = new ArrayList();
        Proveedor proveedor;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from producto where estado = 1");
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_Producto(rs.getInt(1));
                producto.setIdCategoria(rs.getInt(2));
                producto.setIdTipo(rs.getInt(3));
                producto.setIdProveedor(rs.getInt(4));
                producto.setClave(rs.getInt(5));
                producto.setMarca(rs.getString(6));
                producto.setDescripcion(rs.getString(7));
                producto.setStock(rs.getInt(8));
                producto.setStock_min(rs.getInt(9));
                producto.setPrecio_venta(rs.getDouble(10));
                producto.setPrecio_compra(rs.getDouble(11));
                listProductos.add(producto);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listProductos;
    }

    /* DEVOLVER TIPO_PRODUCTO PARA LLENAR EL COMBOBOX DE VISTA*/
    public ArrayList<Tipo_Producto> listTipoProducto() {
        ArrayList listTipoProducto = new ArrayList();
        Tipo_Producto tipo_producto;

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from tipo_producto");
            while (rs.next()) {
                tipo_producto = new Tipo_Producto();
                tipo_producto.setId_tipo(rs.getInt(1));
                tipo_producto.setNombre(rs.getString(2));

                listTipoProducto.add(tipo_producto);
            }
        } catch (Exception e) {
        }
        return listTipoProducto;
    }

    /* DEVOLVER CATEGORIA PARA LLENAR EL COMBOBOX DE VISTA*/
    public ArrayList<Categoria_Producto> listCategoria() {
        ArrayList listCategoria = new ArrayList();
        Categoria_Producto categoria_Producto;

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM categoria_producto");
            while (rs.next()) {
                categoria_Producto = new Categoria_Producto();
                categoria_Producto.setId_categoria(rs.getInt(1));
                categoria_Producto.setNombre(rs.getString(2));

                listCategoria.add(categoria_Producto);
            }
        } catch (Exception e) {
        }
        return listCategoria;
    }

    /* DEVOLVER PROVEEDOR PARA LLENAR EL COMBOBOX DE VISTA*/
    public ArrayList<Proveedor> listProveedor() {
        ArrayList listProveedor = new ArrayList();
        Proveedor proveedor;

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM proveedor where estado = 1");
            while (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setId_proveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));

                listProveedor.add(proveedor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listProveedor;
    }
    
    //DEVOLVER ID DEL TIPO DE PRODUCTO

    public int seleccionar_id(String tipo_producto) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        int var = 0;
        try {
            pst = cn.prepareStatement("select id_tipo from tipo_producto where nombre=?");
            pst.setString(1, tipo_producto);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
    //DEVOLVER ID DE CATEGORIA

    public int seleccionar_id_categoria(String categoria) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        int var = 0;
        try {
            pst = cn.prepareStatement("select id_categoria from categoria_producto where nombre=?");
            pst.setString(1, categoria);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }

    //DEVOLVER ID DE PROVEEDOR
    public int seleccionar_id_proveedor(String proveedor) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        int var = 0;
        try {
            pst = cn.prepareStatement("select id_proveedor from proveedor where nombre=?");
            pst.setString(1, proveedor);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
    //DEVOLVER STRING DE CATEGORIA

    public String seleccionar_nombre_categoria(int categoria) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        String var = null;
        try {
            pst = cn.prepareStatement("select nombre from categoria_producto where id_categoria=?");
            pst.setInt(1, categoria);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
    //DEVOLVER STRING DE TIPO PRODUCTO

    public String seleccionar_nombre_tipo(int tipo_producto) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        String var = null;
        try {
            pst = cn.prepareStatement("select nombre from  tipo_producto where id_tipo=?");
            pst.setInt(1, tipo_producto);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
    
    //DEVOLVER STRING PROVEEDOR
       public String seleccionar_nombre_proveedor(int proveedor) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        String var = null;
        try {
            pst = cn.prepareStatement("select nombre from proveedor where id_proveedor=?");
            pst.setInt(1, proveedor);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
        public int seleccionarIdProducto(String clave) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        Integer var = null;
        try {
            pst = cn.prepareStatement("select id_producto  from producto where clave_producto = ?");
            pst.setString(1, clave);
            rs = pst.executeQuery();
            rs.next();
            var = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return var;
    }
    //OBTENER DATOS DE CLIENTE
        public ArrayList<Cliente> getCliente() {
        ArrayList<Cliente> listClientes = new ArrayList<>();
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from clientes where estado=1");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellidoPaterno(rs.getString(3));
                cliente.setApellidoMaterno(rs.getString(4));
                cliente.setDomicilio(rs.getString(5));
                cliente.setTelefono(rs.getString(6));
                cliente.setCorreo(rs.getString(7));
                cliente.setRFC(rs.getString(10));
                
                
                listClientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listClientes;
    }
          public ArrayList<Cliente> getCliente2(String parametro) {
        ArrayList<Cliente> listClientes = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs = null;
        try {

           
              pst = cn.prepareStatement("SELECT * FROM clientes WHERE nombre= ?");
            pst.setString(1, parametro);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellidoPaterno(rs.getString(3));
                cliente.setApellidoMaterno(rs.getString(4));
                cliente.setDomicilio(rs.getString(5));
                cliente.setTelefono(rs.getString(6));
                cliente.setCorreo(rs.getString(7));
                cliente.setRFC(rs.getString(10));
                
                
                
                listClientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listClientes;
    }
     
           public void modificarCantidad(int cantidad,String codigo_producto,int id_venta) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        String var = null;
        try {
            pst = cn.prepareStatement("UPDATE venta_producto SET cantidad = ? WHERE id_venta=? and id_producto=?");
            int id=seleccionarIdProducto(codigo_producto);
            pst.setInt(1, cantidad);
            pst.setInt(2, id_venta);
            pst.setInt(3, id);
            
            
            rs = pst.executeQuery();
            rs.next();
            var = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
           public void modificarPorcentaje(double descuento,String codigo_producto,int id_venta) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        String var = null;
        try {
            pst = cn.prepareStatement("UPDATE venta_producto SET descuento = ? WHERE id_venta=? and id_producto=?");
            int id=seleccionarIdProducto(codigo_producto);
            pst.setDouble(1, descuento);
            pst.setInt(2, id_venta);
            pst.setInt(3, id);
            
            
            rs = pst.executeQuery();
            rs.next();
            var = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
        public void modificarImporte(double importe,int id_venta) throws SQLException {
        PreparedStatement pst;
        ResultSet rs = null;
        String var = null;
        try {
            pst = cn.prepareStatement("update venta set importe= ? where id_venta =?");
            
            pst.setDouble(1, importe);
            pst.setInt(2, id_venta);
           
            
            
            rs = pst.executeQuery();
            rs.next();
            var = rs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
           
          
}
