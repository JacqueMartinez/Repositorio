/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Abono;
import com.ortometh.model.Apartado_Producto;
import com.ortometh.model.Apartados;
import com.ortometh.model.Cliente;
import com.ortometh.model.Proveedor;
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
public class ApartadosDaoImpl implements ApartadosDao {

    Conexion1 conexion = new Conexion1();
    private Connection db = conexion.getConexion();
    PreparedStatement ps;
    ResultSet rs;

    //METODO PARA INICIAR UN APARTADO AL ABRIR LA VISTA
    public void crearApartado(int user, String tipo) {
        try {
            ps = db.prepareCall("{CALL crear_apartado(?,?)}");
            ps.setInt(1, user);
            ps.setString(2, tipo);
            int row = ps.executeUpdate();

            if (row < 1) {
                JOptionPane.showMessageDialog(null, "No se puede crear el apartado");
            }

        } catch (SQLException e) {
            System.out.println("SQL Crear Apartado error" + e.getMessage());
        }
    }

    public String claveApartado(int id) {
        String respuesta = "";
        try {
            ps = db.prepareCall("SELECT clave_apartado FROM apartado WHERE id_apartado = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL clave where apartado error " + e.getMessage());
        }
        return respuesta;
    }

    public String claveApartado() {
        String respuesta = "";
        try {
            ps = db.prepareCall("SELECT TOP 1 clave_apartado FROM apartado ORDER BY id_apartado DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL clave apartado error" + e.getMessage());
        }
        return respuesta;
    }

    public String restanteDao(int id) {
        String respuesta = "";
        try {
            ps = db.prepareCall("SELECT restante FROM apartado WHERE id_apartado = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL restante apartado error" + e.getMessage());
        }
        return respuesta;
    }

    public String idProducto(String claveProducto) {
        String respuesta = "";
        try {
            ps = db.prepareCall("SELECT id_producto FROM producto WHERE clave_producto = ?");
            ps.setString(1, claveProducto);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL clave apartado error" + e.getMessage());
        }
        return respuesta;
    }

    public String idApartado(String clave) {
        String respuesta = "";
        try {
            ps = db.prepareCall("SELECT id_apartado FROM apartado WHERE clave_apartado = ?");
            ps.setString(1, clave);
            rs = ps.executeQuery();
            while (rs.next()) {
                respuesta = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL idApartado error" + e.getMessage());
        }
        return respuesta;
    }

    public void rellenarApartado(int idApartado, int idProducto, double descuento, int cantidad, int id_usario) {
        try {
            ps = db.prepareCall("{CALL agregarProductosApartados(?,?,?,?,?)}");
            ps.setInt(1, idApartado);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.setDouble(4, descuento);
            ps.setInt(5, id_usario);

            int row = ps.executeUpdate();

            if (row < 1) {
                JOptionPane.showMessageDialog(null, "No se pudo insertar el producto");
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR anadirProductosApartados " + e.getMessage());

        }
    }

    public void llenarDatos(int idApartado, int idProveedor, String cliente, String doctor, String clinica, String cuarto, String observaciones) {
        try {
            ps = db.prepareCall("{CALL llenarDatosApartado(?,?,?,?,?,?,?)}");
            ps.setInt(1, idApartado);
            ps.setInt(2, idProveedor);
            ps.setString(3, cliente);
            ps.setString(4, doctor);
            ps.setString(5, clinica);
            ps.setString(6, cuarto);
            ps.setString(7, observaciones);

            int row = ps.executeUpdate();

            if (row < 1) {
                JOptionPane.showMessageDialog(null, "No se pudieron ingersar los datos");
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR llenarDatosApartado " + e.getMessage());

        }
    }

    public void agregarAbono(int idApartado, double abono, int user) {
        try {
            ps = db.prepareCall("{CALL agregar_abono(?,?,?)}");
            ps.setInt(1, idApartado);
            ps.setDouble(2, abono);
            ps.setInt(3, user);

            int row = ps.executeUpdate();

            if (row > 1) {
                JOptionPane.showMessageDialog(null, "Se agrego el abono correctamente");
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR agregar_abono " + e.getMessage());

        }
    }

    public ArrayList<Apartado_Producto> listaProductos(int id) {
        ArrayList<Apartado_Producto> products = new ArrayList();
        Apartado_Producto ap;
        try {
            ps = db.prepareStatement("SELECT * FROM view_productos_apartados WHERE Apartado = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ap = new Apartado_Producto();
                ap.setCodigo(rs.getString(2));
                ap.setNombre(rs.getString(3));
                ap.setPrecio(rs.getDouble(4));
                ap.setMarca(rs.getString(5));
                ap.setProveedor(rs.getString(6));
                ap.setCantidad(rs.getInt(7));
                ap.setDescuento(rs.getInt(8));

                products.add(ap);

            }
        } catch (SQLException ex) {
            System.out.println("SQL ERROR lita de productos " + ex);
        }
        return products;
    }

    public String piezasDAO(int id) {
        String numPiezas = "";
        try {
            ps = db.prepareStatement("SELECT SUM(cantidad) FROM apartados_productos WHERE id_apartado = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                numPiezas = String.valueOf(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("SQL cantidad error" + e.getMessage());
        }
        return numPiezas;
    }

    public Double importeDAO(int id) {
        Double importe = 0.0d;
        try {
            ps = db.prepareStatement("SELECT importe FROM apartado WHERE id_apartado = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                importe = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL importe error" + e.getMessage());
        }
        return importe;
    }

    public Double subtotalDAO(int id) {
        Double subtotal = 0.0d;
        try {
            ps = db.prepareStatement("SELECT subtotal FROM apartado WHERE id_apartado = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                subtotal = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL subtotal error" + e.getMessage());
        }
        return subtotal;
    }

    public void cambiarCantidad(int idApartado, int idProducto, int cantidad){
        try {
            ps = db.prepareCall("{CALL modificarApartadoProducto(?,?,?)}");
            ps.setInt(1, idApartado);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);

            int result = ps.executeUpdate();

            if (result < 0) {
                JOptionPane.showMessageDialog(null, "No se pude cambiar la cantidad");
            } 

        } catch (SQLException e) {
            System.out.println("SQL ERROR cambiar cantidad: " + e.getMessage());
        }
    }
    
    @Override
    public void guardarApartado(Apartados newApartado) {
        try {
            ps = db.prepareCall("{CALL insertar_apartado(?,?,?,?,?,?,?,?,?,?)}");
            ps.setInt(1, newApartado.getId_venta());
            ps.setInt(2, newApartado.getProveedor());
            ps.setString(3, newApartado.getCliente());
            ps.setString(4, newApartado.getDoctor());
            ps.setString(5, newApartado.getClinica());
            ps.setString(6, newApartado.getNumeroCuarto());
            ps.setDouble(7, newApartado.getAbono());
            ps.setDouble(8, newApartado.getCosto());
            ps.setString(9, newApartado.getObservaciones());
            ps.setInt(10, newApartado.getId_usuario());

            int result = ps.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Insertado Correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar");
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
    }

    @Override
    public void actualizarApartado(Apartados apartado) {
        try {
            ps = db.prepareCall("{CALL agregar_abono(?,?,?,?)}");
            ps.setString(1, apartado.getObservaciones());
            ps.setDouble(2, apartado.getAbono());
            ps.setInt(3, apartado.getApartado());
            ps.setInt(4, apartado.getId_usuario());

            int result = ps.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Actualizado Correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar");
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Proveedor> consultarProveedores() {
        ArrayList<Proveedor> list = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT * FROM proveedor");
            rs = ps.executeQuery();
            Proveedor proveedor;
            while (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setId_proveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setRfc(rs.getString(3));
                proveedor.setTelefono(rs.getString(4));
                proveedor.setCorreo(rs.getString(5));
                list.add(proveedor);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error " + ex.getMessage());
        }
        return list;
    }

    @Override
    public ArrayList<Cliente> consultarClientes() {
        ArrayList<Cliente> list = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT * FROM clientes");
            rs = ps.executeQuery();
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId_cliente(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellidoPaterno(rs.getString(3));
                cliente.setApellidoMaterno(rs.getString(4));
                cliente.setDomicilio(rs.getString(5));
                cliente.setTelefono(rs.getString(6));
                cliente.setCorreo(rs.getString(7));
                cliente.setRFC(rs.getString(8));
                cliente.setNombreFacturacion(rs.getString(9));
                cliente.setEstado(rs.getInt(10));
                list.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error " + ex.getMessage());
        }
        return list;
    }

    @Override
    public ArrayList<Apartados> consultarApartadosProceso(String sentencia, String dato) {
        ArrayList<Apartados> list = new ArrayList<>();
        try {
            if (dato.equalsIgnoreCase("Fecha")) {
                ps = db.prepareStatement("SELECT *,CAST(Fecha as DATE) FROM view_all_apartados WHERE CAST(Fecha as DATE) LIKE CONCAT(?,'%') AND Importe > 0 AND Estado = 'Proceso'");
            } else {
                ps = db.prepareStatement("SELECT * FROM view_all_apartados WHERE " + dato + " LIKE CONCAT(?,'%') AND Importe > 0 AND Estado = 'Proceso'");
            }
//            ps.setString(1, dato);
            ps.setString(1, sentencia);
            rs = ps.executeQuery();
            Apartados apartado;
            while (rs.next()) {
                apartado = new Apartados();
                apartado.setApartado(rs.getInt(1));
                apartado.setClaveApartado(rs.getString(2));
                apartado.setTipo(rs.getString(3));
                apartado.setProveedor(rs.getInt(4));
                apartado.setCliente(rs.getString(5));
                apartado.setDoctor(rs.getString(6));
                apartado.setClinica(rs.getString(7));
                apartado.setNumeroCuarto(rs.getString(8));
                apartado.setCosto(rs.getDouble(9));
                apartado.setRestantante(rs.getDouble(10));
                apartado.setFecha(rs.getString(11));
                apartado.setEstado(rs.getString(12));
                apartado.setObservaciones(rs.getString(13));
                list.add(apartado);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error consultar apartados filtro " + ex.getMessage());
            list = consultarApartadosProceso();
        }
        return list;
    }

    public ArrayList<Apartados> consultarApartadosFinalizados(String sentencia, String dato) {
        ArrayList<Apartados> list = new ArrayList<>();
        try {
            if (dato.equalsIgnoreCase("Fecha")) {
                ps = db.prepareStatement("SELECT *,CAST(Fecha as DATE) FROM view_all_apartados WHERE CAST(Fecha as DATE) LIKE CONCAT(?,'%') AND Importe > 0 AND Estado = 'Finalizado'");
            } else {
                ps = db.prepareStatement("SELECT * FROM view_all_apartados WHERE " + dato + " LIKE CONCAT(?,'%') AND Importe > 0 AND Estado = 'Finalizado'");
            }
//            ps.setString(1, dato);
            ps.setString(1, sentencia);
            rs = ps.executeQuery();
            Apartados apartado;
            while (rs.next()) {
                apartado = new Apartados();
                apartado.setApartado(rs.getInt(1));
                apartado.setClaveApartado(rs.getString(2));
                apartado.setTipo(rs.getString(3));
                apartado.setProveedor(rs.getInt(4));
                apartado.setCliente(rs.getString(5));
                apartado.setDoctor(rs.getString(6));
                apartado.setClinica(rs.getString(7));
                apartado.setNumeroCuarto(rs.getString(8));
                apartado.setCosto(rs.getDouble(9));
                apartado.setRestantante(rs.getDouble(10));
                apartado.setFecha(rs.getString(11));
                apartado.setEstado(rs.getString(12));
                apartado.setObservaciones(rs.getString(13));
                list.add(apartado);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error consultar apartados filtro " + ex.getMessage());
            list = consultarApartadosFinalizados();
        }
        return list;
    }

    public ArrayList<Apartados> consultarApartadosProceso() {
        ArrayList<Apartados> list = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT * FROM view_all_apartados WHERE Importe > 0 AND Estado = 'Proceso'");
            rs = ps.executeQuery();
            Apartados apartado;
            while (rs.next()) {
                apartado = new Apartados();
                apartado.setApartado(rs.getInt(1));
                apartado.setClaveApartado(rs.getString(2));
                apartado.setTipo(rs.getString(3));
                apartado.setProveedor(rs.getInt(4));
                apartado.setCliente(rs.getString(5));
                apartado.setDoctor(rs.getString(6));
                apartado.setClinica(rs.getString(7));
                apartado.setNumeroCuarto(rs.getString(8));
                apartado.setCosto(rs.getDouble(9));
                apartado.setRestantante(rs.getDouble(10));
                apartado.setFecha(rs.getString(11));
                apartado.setEstado(rs.getString(12));
                apartado.setObservaciones(rs.getString(13));
                list.add(apartado);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error consultar apartados " + ex.getMessage());
        }
        return list;
    }

    public ArrayList<Apartados> consultarApartadosFinalizados() {
        ArrayList<Apartados> list = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT * FROM view_all_apartados WHERE Importe > 0 AND Estado = 'Finalizado'");
            rs = ps.executeQuery();
            Apartados apartado;
            while (rs.next()) {
                apartado = new Apartados();
                apartado.setApartado(rs.getInt(1));
                apartado.setClaveApartado(rs.getString(2));
                apartado.setTipo(rs.getString(3));
                apartado.setProveedor(rs.getInt(4));
                apartado.setCliente(rs.getString(5));
                apartado.setDoctor(rs.getString(6));
                apartado.setClinica(rs.getString(7));
                apartado.setNumeroCuarto(rs.getString(8));
                apartado.setCosto(rs.getDouble(9));
                apartado.setRestantante(rs.getDouble(10));
                apartado.setFecha(rs.getString(11));
                apartado.setEstado(rs.getString(12));
                apartado.setObservaciones(rs.getString(13));
                list.add(apartado);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error consultar apartados " + ex.getMessage());
        }
        return list;
    }

    public ArrayList<Apartados> consultarApartados(int id) {
        ArrayList<Apartados> list = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT * FROM view_all_apartados WHERE Apartado = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Apartados apartado;
            while (rs.next()) {
                apartado = new Apartados();
                apartado.setApartado(rs.getInt(1));
                apartado.setClaveApartado(rs.getString(2));
                apartado.setTipo(rs.getString(3));
                apartado.setProveedor(rs.getInt(4));
                apartado.setCliente(rs.getString(5));
                apartado.setDoctor(rs.getString(6));
                apartado.setClinica(rs.getString(7));
                apartado.setNumeroCuarto(rs.getString(8));
                apartado.setCosto(rs.getDouble(9));
                apartado.setRestantante(rs.getDouble(10));
                apartado.setFecha(rs.getString(11));
                apartado.setEstado(rs.getString(12));
                apartado.setObservaciones(rs.getString(13));
                list.add(apartado);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error consultar apartados " + ex.getMessage());
        }
        return list;
    }

    @Override
    public ArrayList<Abono> consultarAbonos(int id_apartado) {
        ArrayList<Abono> list = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT * FROM view_all_abonos WHERE Apartado = ?");
            ps.setInt(1, id_apartado);
            rs = ps.executeQuery();
            Abono abono;
            while (rs.next()) {
                abono = new Abono();
                abono.setId_abono(rs.getInt(1));
                abono.setId_apartado(rs.getInt(2));
                abono.setImporte(rs.getDouble(3));
                abono.setFecha(rs.getString(4));
                list.add(abono);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error abonos " + ex.getMessage());
        }
        return list;
    }
    
    public ArrayList<Apartado_Producto> productosDAO(String clave){
        ArrayList<Apartado_Producto> products = new ArrayList();
        Apartado_Producto ap;
        try {
            ps = db.prepareStatement("SELECT descripcion,precio_venta,marca FROM producto WHERE clave_producto = ?");
            ps.setString(1, clave);
            rs = ps.executeQuery();
            while (rs.next()) {
                ap = new Apartado_Producto();
                ap.setNombre(rs.getString(1));
                ap.setPrecio(rs.getDouble(2));
                ap.setMarca(rs.getString(3));
                products.add(ap);

            }
        } catch (SQLException ex) {
            System.out.println("SQL ERROR lita de productos venta" + ex);
        }
        return products;
    }
    
}

