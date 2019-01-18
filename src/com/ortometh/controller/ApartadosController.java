/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;

import com.ortometh.Dao.ApartadosDaoImpl;
import com.ortometh.model.Abono;
import com.ortometh.model.Apartado_Producto;
import com.ortometh.model.Apartados;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Alan
 */
public class ApartadosController {

    ApartadosDaoImpl adi = new ApartadosDaoImpl();

    public void crearApartado(int user, String tipoApartado) {
        adi.crearApartado(user, tipoApartado);
    }

    public String claveApartado() {
        return adi.claveApartado();
    }

    public String claveApartado(int id) {
        return adi.claveApartado(id);
    }

    public String idApartado(String clave) {
        return adi.idApartado(clave);
    }

    public String idProducto(String claveProducto) {
        return adi.idProducto(claveProducto);
    }

    public void cambiarCantidad(int idApartado, int idProducto, int cantidad) {
        adi.cambiarCantidad(idApartado, idProducto, cantidad);
    }
    
    public void llenarApartado(int idApartado, int idProducto, double descuento, int cantidad, int id_usario) {
        if (idApartado < 1 || idProducto < 1 || cantidad < 1) {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos obligatorios controller");
        } else {
            adi.rellenarApartado(idApartado, idProducto, descuento, cantidad, id_usario);
        }
    }

    public void llenarDatos(int idApartado, int idProveedor, String cliente, String doctor, String clinica, String cuarto, String observaciones) {
        if (cliente.isEmpty() || observaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos obligatorios");
        } else {
            adi.llenarDatos(idApartado, idProveedor, cliente, doctor, clinica, cuarto, observaciones);
        }
    }

    public void abonar(int idApartado, double abono, int user) {
        if (abono < 0) {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos obligatorios");
        } else {
            adi.agregarAbono(idApartado, abono, user);
        }
    }

    public String restante(int id) {
        return adi.restanteDao(id);
    }
    
    public ArrayList<Apartado_Producto> productos(String clave){
        return adi.productosDAO(clave);
    }

    public void listProductos(JTable tabla, int id) {
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<Apartado_Producto> products = adi.listaProductos(id);
        tabla.setModel(model);
        TableColumnModel col = tabla.getColumnModel();
        tabla.getTableHeader().setReorderingAllowed(false);

        model.addColumn("Folio");
        model.addColumn("Nombre Producto");
        model.addColumn("Precio");
        model.addColumn("Marca");
        model.addColumn("Proveedor");
        model.addColumn("Cantidad");
        model.addColumn("Descuento");

        col.getColumn(0).setPreferredWidth(80);
        col.getColumn(1).setPreferredWidth(250);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(150);
        col.getColumn(4).setPreferredWidth(100);
        col.getColumn(5).setPreferredWidth(80);
        col.getColumn(6).setPreferredWidth(80);

        Object[] colums = new Object[7];

        int numOfRows = products.size();

        for (int i = 0; i < numOfRows; i++) {
            colums[0] = products.get(i).getCodigo();
            colums[1] = products.get(i).getNombre();
            colums[2] = products.get(i).getPrecio();
            colums[3] = products.get(i).getMarca();
            colums[4] = products.get(i).getProveedor();
            colums[5] = products.get(i).getCantidad();
            colums[6] = products.get(i).getDescuento();
            model.addRow(colums);
        }
        tabla.setModel(model);
    }

    public String piezas(int id) {
        return adi.piezasDAO(id);
    }

    public Double importe(int id) {
        return adi.importeDAO(id);
    }

    public Double subtotal(int id) {
        return adi.subtotalDAO(id);
    }

    public void guardarApartado(int id_venta, int proveedor, String cliente, String doctor, String clinica, String numeroCuarto, double abono, double costo, String observaciones, int id_usuario) {
        if (id_venta < 1 || cliente.isEmpty() || costo < 1 || abono < 1 || observaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos obligatorios");
        } else {
            Apartados apartados = new Apartados(id_venta, proveedor, cliente, doctor, clinica, costo, numeroCuarto, abono, observaciones, id_usuario);
            adi.guardarApartado(apartados);
        }
    }

    public void actualizarApartado(int apartado, double abono, String observaciones, int id_usuario) {
        if (apartado < 1 || abono < 1 || observaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos obligatorios");
        } else {
            Apartados apartados = new Apartados(apartado, abono, observaciones, id_usuario);
            adi.actualizarApartado(apartados);
        }
    }

//    public void actualizarApartadoTienda(int apartado, String fecha, double abono) {
//        Apartados apartados = new Apartados(apartado, fecha, abono);
//        adi.actualizarApartado(apartados);
//    }
    public void consultarProveedores(JComboBox jcb) {
        List<String> list = adi.consultarProveedores().stream().map(o -> o.getId_proveedor() + " " + o.getNombre()).distinct().collect(Collectors.toList());
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Seleccione Una Opcion");
        list.forEach(of -> model.addElement(of));
        jcb.setModel(model);
    }

    public void consultarClientes(JComboBox jcb) {
        List<String> list = adi.consultarClientes().stream().map(o -> o.getId_cliente() + " " + o.getNombre()).distinct().collect(Collectors.toList());
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Seleccione Una Opcion");
        list.forEach(of -> model.addElement(of));
        jcb.setModel(model);
    }

    public void consultarApartadosProceso(JTable tabla, String sentencia, String dato) {
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<Apartados> apartado = adi.consultarApartadosProceso(sentencia, dato);
        tabla.setModel(model);
        TableColumnModel col = tabla.getColumnModel();
        tabla.getTableHeader().setReorderingAllowed(false);
        
        model.addColumn("Clave");
        model.addColumn("Cliente");
        model.addColumn("Costo");
        model.addColumn("Restante");
        model.addColumn("Fecha De Apartado");
        model.addColumn("Estado");
        model.addColumn("Observaciones");

        col.getColumn(0).setPreferredWidth(100);
        col.getColumn(1).setPreferredWidth(200);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(100);
        col.getColumn(4).setPreferredWidth(150);
        col.getColumn(5).setPreferredWidth(90);
        col.getColumn(6).setPreferredWidth(300);

        Object[] colums = new Object[13];

        int numOfRows = apartado.size();

        for (int i = 0; i < numOfRows; i++) {
            colums[0] = apartado.get(i).getClaveApartado();
            colums[1] = apartado.get(i).getCliente();
            colums[2] = apartado.get(i).getCosto();
            colums[3] = apartado.get(i).getRestantante();
            colums[4] = apartado.get(i).getFecha();
            colums[5] = apartado.get(i).getEstado();
            colums[6] = apartado.get(i).getObservaciones();
            model.addRow(colums);
        }
    }

    public void consultarApartadosFinalizados(JTable tabla, String sentencia, String dato) {
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<Apartados> apartado = adi.consultarApartadosFinalizados(sentencia, dato);
        tabla.setModel(model);
        TableColumnModel col = tabla.getColumnModel();
        tabla.getTableHeader().setReorderingAllowed(false);

        model.addColumn("Clave");
        model.addColumn("Cliente");
        model.addColumn("Costo");
        model.addColumn("Restante");
        model.addColumn("Fecha De Apartado");
        model.addColumn("Estado");
        model.addColumn("Observaciones");

        col.getColumn(0).setPreferredWidth(100);
        col.getColumn(1).setPreferredWidth(200);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(100);
        col.getColumn(4).setPreferredWidth(150);
        col.getColumn(5).setPreferredWidth(90);
        col.getColumn(6).setPreferredWidth(300);

        Object[] colums = new Object[13];

        int numOfRows = apartado.size();

        for (int i = 0; i < numOfRows; i++) {
            colums[0] = apartado.get(i).getClaveApartado();
            colums[1] = apartado.get(i).getCliente();
            colums[2] = apartado.get(i).getCosto();
            colums[3] = apartado.get(i).getRestantante();
            colums[4] = apartado.get(i).getFecha();
            colums[5] = apartado.get(i).getEstado();
            colums[6] = apartado.get(i).getObservaciones();
            model.addRow(colums);
        }
    }
    

    public void consultarApartadosProceso(JTable tabla) {
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<Apartados> apartado = adi.consultarApartadosProceso();
        tabla.setModel(model);
        TableColumnModel col = tabla.getColumnModel();
        tabla.getTableHeader().setReorderingAllowed(false);

        model.addColumn("Clave");
        model.addColumn("Cliente");
        model.addColumn("Costo");
        model.addColumn("Restante");
        model.addColumn("Fecha De Apartado");
        model.addColumn("Estado");
        model.addColumn("Observaciones");

        col.getColumn(0).setPreferredWidth(100);
        col.getColumn(1).setPreferredWidth(200);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(100);
        col.getColumn(4).setPreferredWidth(150);
        col.getColumn(5).setPreferredWidth(90);
        col.getColumn(6).setPreferredWidth(300);

        Object[] colums = new Object[13];

        int numOfRows = apartado.size();

        for (int i = 0; i < numOfRows; i++) {
            colums[0] = apartado.get(i).getClaveApartado();
            colums[1] = apartado.get(i).getCliente();
            colums[2] = apartado.get(i).getCosto();
            colums[3] = apartado.get(i).getRestantante();
            colums[4] = apartado.get(i).getFecha();
            colums[5] = apartado.get(i).getEstado();
            colums[6] = apartado.get(i).getObservaciones();
            model.addRow(colums);
        }
        tabla.setModel(model);
    }
    
    public void consultarApartadosFinalizados(JTable tabla) {
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<Apartados> apartado = adi.consultarApartadosFinalizados();
        tabla.setModel(model);
        TableColumnModel col = tabla.getColumnModel();
        tabla.getTableHeader().setReorderingAllowed(false);

        model.addColumn("Clave");
        model.addColumn("Cliente");
        model.addColumn("Costo");
        model.addColumn("Restante");
        model.addColumn("Fecha De Apartado");
        model.addColumn("Estado");
        model.addColumn("Observaciones");

        col.getColumn(0).setPreferredWidth(100);
        col.getColumn(1).setPreferredWidth(200);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(100);
        col.getColumn(4).setPreferredWidth(150);
        col.getColumn(5).setPreferredWidth(90);
        col.getColumn(6).setPreferredWidth(300);

        Object[] colums = new Object[13];

        int numOfRows = apartado.size();

        for (int i = 0; i < numOfRows; i++) {
            colums[0] = apartado.get(i).getClaveApartado();
            colums[1] = apartado.get(i).getCliente();
            colums[2] = apartado.get(i).getCosto();
            colums[3] = apartado.get(i).getRestantante();
            colums[4] = apartado.get(i).getFecha();
            colums[5] = apartado.get(i).getEstado();
            colums[6] = apartado.get(i).getObservaciones();
            model.addRow(colums);
        }
        tabla.setModel(model);
    }

    public void consultarAbonos(JTable tabla, int clave) {
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<Abono> abono = adi.consultarAbonos(clave);
        tabla.setModel(model);
        TableColumnModel col = tabla.getColumnModel();
        tabla.getTableHeader().setReorderingAllowed(false);

        model.addColumn("Folio Abono");
        model.addColumn("Apartado");
        model.addColumn("Abono");
        model.addColumn("Fecha De Abono");

        col.getColumn(0).setPreferredWidth(100);
        col.getColumn(1).setPreferredWidth(100);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(100);

        Object[] colums = new Object[6];

        int numOfRows = abono.size();

        for (int i = 0; i < numOfRows; i++) {
            colums[0] = abono.get(i).getId_abono();
            colums[1] = abono.get(i).getId_apartado();
            colums[2] = abono.get(i).getImporte();
            colums[3] = abono.get(i).getFecha();
            model.addRow(colums);
        }
    }
}
