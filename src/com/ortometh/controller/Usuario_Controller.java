/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.controller;


import com.ortometh.Dao.UsuarioDaoImpl;
import com.ortometh.model.Encryption;
import com.ortometh.model.Privilegios;
import com.ortometh.model.Venta;
import com.ortometh.model.Usuario;
import com.ortometh.model.UsuarioLogin;
import com.ortometh.viewMain.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Bitia
 */
public class Usuario_Controller {

    
    UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
    String serverAnswer = null;
    DefaultTableModel tModel = new DefaultTableModel() {
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    //-----------------------------------registrar Usuario----------------------------------------------------------------

    public String registrar_usuario(String nombre, String nombreUsuario, String contraseña, String privilegios, String telefono, int id_usuario) {
        Usuario usuario = new Usuario();
        
        List<Privilegios> nivelUusario = usuarioDaoImpl.listPrivilegios().stream().filter(u ->u.getNombre().equals(privilegios)).collect(Collectors.toList());
        
        usuario.setNombre(nombre);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContraseña(contraseña);
        usuario.setPrivilegios(String.valueOf(nivelUusario.get(0).getIdPrivilegio()));
        usuario.setTelefono(telefono);
        usuario.setId_usuario(id_usuario);
        
        String respuesta = usuarioDaoImpl.insertarUsuario(usuario);

        return respuesta;

    }

    //--------------------------------- MODIFICAR USUARIO  ----------------------------------------------

    public String modificar(int id_Usuario,String nombre, String nombreUsuario, String privilegios, String telefono, int idUsuario) {
        Usuario users = new Usuario();
        
        List<Privilegios> nivelUusario = usuarioDaoImpl.listPrivilegios().stream().filter(u ->u.getNombre().equals(privilegios)).collect(Collectors.toList());
        
        users.setNombre(nombre);
        users.setNombreUsuario(nombreUsuario);
        users.setPrivilegios(String.valueOf(nivelUusario.get(0).getIdPrivilegio()));
        users.setTelefono(telefono);
        users.setId_usuario(id_Usuario);
        
        String respuesta = usuarioDaoImpl.actualizarUsuario(users, idUsuario);

        return respuesta;

    }
    
//    //--------------------------------- MODIFICAR USUARIO CON CONTRASEÑA  ----------------------------------------------

    public String modificarConContraseña(int id_Usuario,String nombre, String nombreUsuario, String contrasena, String privilegios,String telefono, int idUsuario) {
        Usuario users = new Usuario();
        
        List<Privilegios> nivelUusario = usuarioDaoImpl.listPrivilegios().stream().filter(u ->u.getNombre().equals(privilegios)).collect(Collectors.toList());
        
        users.setNombre(nombre);
        users.setNombreUsuario(nombreUsuario);
        users.setContraseña(contrasena);
        users.setPrivilegios(String.valueOf(nivelUusario.get(0).getIdPrivilegio()));
        users.setTelefono(telefono);
        users.setId_usuario(id_Usuario);
        
        String respuesta = usuarioDaoImpl.actualizarUsuarioConContraseña(users, idUsuario);

        return respuesta;

    }

    //-------------------------- ELIMINAR ----------------------------------
    public void eliminar(String nombreUsuario) {
        usuarioDaoImpl.eliminarUsuario(nombreUsuario);
    }
    //------------------------------------llenar tabla de usuarios---------------------------------------

    public void fillProducts(JTable tablaUsuarios) {
        tablaUsuarios.setModel(tModel);
        TableColumnModel columnModel = tablaUsuarios.getColumnModel();
        
        tModel.addColumn("ID");
        tModel.addColumn("NOMBRE");
        tModel.addColumn("NOMBRE USUARIO");
        tModel.addColumn("TELÉFONO");
        tModel.addColumn("PRIVILEGIOS");
        tModel.addColumn("ÚLTIMA SESION");

        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(150);
        
        
        
        Object[] columns = new Object[6];
        ArrayList<Usuario> listUsers = usuarioDaoImpl.getUsuario();
        
        
        for (int i = 0; i < listUsers.size(); i++) {
            
             columns[0] = listUsers.get(i).getId_usuario();
            columns[1] = listUsers.get(i).getNombre();
            columns[2] = listUsers.get(i).getNombreUsuario();
            columns[3] = listUsers.get(i).getTelefono();
            columns[4] = listUsers.get(i).getPrivilegios();
            columns[5] = listUsers.get(i).getUltima_sesion();

            tModel.addRow(columns);
        }

        
        

    }


 

    //------------------------RECARGAR -----------------------------------------------
    public void Recargar(JTable tablaUsuarios) {
        ArrayList<Usuario> lista = usuarioDaoImpl.getUsuario();
        tablaUsuarios.setModel(tModel);
        tModel.setRowCount(0);

        Object[] columns = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            columns[0] = lista.get(i).getId_usuario();
            columns[1] = lista.get(i).getNombre();
            columns[2] = lista.get(i).getNombreUsuario();
            columns[3] = lista.get(i).getTelefono();
            columns[4] = lista.get(i).getPrivilegios();
            columns[5] = lista.get(i).getUltima_sesion();
            
            tModel.addRow(columns);

        }

    }

    
    
    //---------------------------- combo box --------------------------------------------
    
    public void fillComboPrivilegios(JComboBox comboPrivilegios){
        ArrayList<Privilegios> privilegios = usuarioDaoImpl.listPrivilegios();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Seleccione");
        for (int i = 0; i < privilegios.size(); i++) {
            model.addElement(privilegios.get(i).getNombre());
        }
        comboPrivilegios.setModel(model);
    }
    
    //--------------------------------- Cambiar contraseña ------------------------------------------------
    public String modificarContrasena(
            String nombreUsuario,
            String contrasena) {

        if (nombreUsuario.isEmpty() || contrasena.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Contraseña actualizada");
        }
        UsuarioLogin usuario = new UsuarioLogin(nombreUsuario, contrasena);
        String message = usuarioDaoImpl.modificarContrasena(usuario);
        return message;
    }
    
    //------------------------------------- Buscar un Usuario -------------------------------------------------------------
    public void filtrarUsuario(JTable jtableUsuario,String nombreUsuario){
        DefaultTableModel tModel = new DefaultTableModel();
        jtableUsuario.setModel(tModel);
           tModel.addColumn("ID");
        tModel.addColumn("NOMBRE");
        tModel.addColumn("NOMBRE USUARIO");
        tModel.addColumn("TELÉFONO");
        tModel.addColumn("PRIVILEGIOS");
        tModel.addColumn("ULTIMA SESIÓN");
        
        Object[] columns = new Object[6]; 
        ArrayList <Usuario> listUsuarios =usuarioDaoImpl.obtenerUsuarios(nombreUsuario);
        for (Usuario Usuario : listUsuarios) {
            
            columns[0] = Usuario.getId_usuario();
            columns[1] = Usuario.getNombre();
            columns[2] = Usuario.getNombreUsuario();
            columns[3] = Usuario.getTelefono();
            columns[4] = Usuario.getPrivilegios();
            columns[5] = Usuario.getUltima_sesion();
            
            tModel.addRow(columns);
        } 
    }
    
    //--------------------------------- Cambiar ultima sesion del Usuario ------------------------------------------------
    public String modificarLast_session(
            String nombreUsuario,
            String ultima_sesion) {

        if (nombreUsuario.isEmpty() || ultima_sesion.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Ultima sesion del usuario modificada correctamente");
        }
        Usuario user = new Usuario(nombreUsuario, ultima_sesion);
        String message = usuarioDaoImpl.updateUltimaSesionUsuario(user);
        return message;
    }
    
    //-------------------------------------

}


