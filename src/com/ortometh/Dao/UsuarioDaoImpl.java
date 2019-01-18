/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.ortometh.model.Encryption;
import com.ortometh.model.Privilegios;
import com.ortometh.model.Producto;
import com.ortometh.model.Usuario;
import com.ortometh.model.UsuarioLogin;
import com.ortometh.viewMain.LoginView;
import com.ortometh.viewMain.MenuView;
import com.ortometh.viewMain.UsuariosView;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author jacQu
 */
public class UsuarioDaoImpl implements UsuarioDAO {

    Conexion1 con = new Conexion1();
    private Connection cn = con.getConexion();

    //--------------------------------------- INSERTAR USUARIO ------------------------------------------
    @Override
    public String insertarUsuario(Usuario user) {
        String respuesta = "";
        try {
            CallableStatement sta = cn.prepareCall("{Call registrarUsuario (?,?,?,?,?,?)}");
            sta.setString(1, user.getNombre());
            sta.setString(2, user.getNombreUsuario());
            sta.setString(3, user.getContraseña());
            sta.setInt(4, Integer.valueOf(user.getPrivilegios()));
            sta.setString(5, user.getTelefono());
            sta.setInt(6, user.getId_usuario());

            int affectedRows = sta.executeUpdate();

            if (affectedRows > 0) {
                respuesta = "Usuario registrado";
            }
        } catch (SQLException e) {
            respuesta = "Usuario no registrado";
        }
        return respuesta;
    }

    //--------------------------------------- ACTUALIZAR USUARIO ----------------------------------------------
    @Override
    public String actualizarUsuario(Usuario user, int idUsuario) {
        String respuesta = null;

        try {
            CallableStatement statement = cn.prepareCall("{Call actualizarUsuario (?,?,?,?,?,?)}");

            statement.setInt(1, user.getId_usuario());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getNombreUsuario());
            statement.setString(4, user.getPrivilegios());
            statement.setString(5, user.getTelefono());
            statement.setInt(6, idUsuario);

            int numAffectedRows = statement.executeUpdate();

            if (numAffectedRows > 0) {
                respuesta = "Usuario actulizado correctamente";
            }

        } catch (SQLException ex) {
            System.out.println("sql sentence error " + ex.getMessage());
            respuesta = "Hay un problema";
        }
        return respuesta;
    }

//    //-------------------------------------------- ACTUALIZAR USUARIO CON CONTRASEÑA -----------------------
    public String actualizarUsuarioConContraseña(Usuario user, int idUsuario) {
        String respuesta = null;

        try {
            CallableStatement statement = cn.prepareCall("{Call actualizarUsuarioConContraseña (?,?,?,?,?,?,?)}");

            statement.setInt(1, user.getId_usuario());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getNombreUsuario());
            statement.setString(4, user.getContraseña());
            statement.setString(5, user.getPrivilegios());
            statement.setString(6, user.getTelefono());
            statement.setInt(7, idUsuario);

            int numAffectedRows = statement.executeUpdate();

            if (numAffectedRows > 0) {
                respuesta = "Usuario actulizado correctamente";
            }

        } catch (SQLException ex) {
            System.out.println("sql sentence error " + ex.getMessage());
            respuesta = "Hay un problema";
        }
        return respuesta;
    }

    //------------------------------ obtener los datos de un Usuario ----------------------------------------------
    @Override
    public ArrayList<Usuario> getUsuario() {
        ArrayList<Usuario> listUsuarios = new ArrayList<>();
        Usuario users = new Usuario();
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select usuario.id_usuario,usuario.nombre,usuario.nombreUsuario,usuario.telefono,privilegios.nombre,usuario.ultima_sesion from usuario inner join privilegios on privilegios.idPrivilegio=usuario.idPrivilegio where estado=1");

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId_usuario(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setNombreUsuario(rs.getString(3));
                user.setTelefono(rs.getString(4));
                user.setPrivilegios(rs.getString(5));
                user.setUltima_sesion(rs.getString(6));

                listUsuarios.add(user);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listUsuarios;
    }

    //------------------------------ obtener la contraseña de un Usuario ----------------------------------------------
    public ArrayList<Usuario> getContraseñaDeUsuario(String nombreUsuario) {
        ArrayList<Usuario> listUsuarios = new ArrayList<>();
        Usuario users = new Usuario();
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select contrasena from usuario where nombreUsuario='" + nombreUsuario + "'");

            while (rs.next()) {
                Usuario user = new Usuario();

                user.setContraseña(rs.getString(1));
                UsuariosView.txtContraseñaParaVerificar.setText(rs.getString(1));
                UsuariosView.jPasswordContrasena.setText(rs.getString(1));
                listUsuarios.add(user);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listUsuarios;
    }

    //-------------------------------- ELIMINAR USUARIO ---------------------------------------------------
    @Override
    public String eliminarUsuario(String nombreUsuario) {
        String serverAnswer = null;
        try {
            PreparedStatement statement = cn.prepareStatement("update usuario set estado=0 where nombreUsuario=?");
            statement.setString(1, nombreUsuario);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");

        } catch (SQLException ex) {
            System.out.println("sql sentence error " + ex.getMessage());
            serverAnswer = "Hay un problema";
        }
        return serverAnswer;
    }

    //------------------------------------combo Box Privilegios --------------------------
    public ArrayList<Privilegios> listPrivilegios() {
        ArrayList listOfPrivilegios = new ArrayList();
        Privilegios privilegios;

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select * from privilegios");
            while (rs.next()) {
                privilegios = new Privilegios();
                privilegios.setIdPrivilegio(rs.getInt(1));
                privilegios.setNombre(rs.getString(2));

                listOfPrivilegios.add(privilegios);
            }
        } catch (Exception e) {
        }
        return listOfPrivilegios;
    }

//    ------------------------------------ Inicio de sesion (LOGIN) -----------------------------------------
    public void acceder(String usuario, String pass) {
        int capturar = 0;
        UsuarioLogin user = new UsuarioLogin();
        String sql = "Select * from usuario where nombreUsuario='" + usuario + "' AND contrasena='" + pass + "' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                capturar = rs.getInt("idPrivilegio");
                user.setIdUserLog(rs.getInt(1));
                user.setNombreUsuario(rs.getString(3));
                user.setIdPrivilegio(rs.getInt(5));
            }
           
            if (capturar == 1) {
                MenuView menu = new MenuView(user);
                menu.setVisible(true);
                menu.pack();
            }
            if (capturar == 2) {
                MenuView menu = new MenuView(user);
                menu.setVisible(true);
                menu.pack();
            }
            if ((capturar != 1) && (capturar != 2)) {
                JOptionPane.showMessageDialog(null, "No existen esos datos");
                LoginView LoginView = new LoginView();
                LoginView.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }

    }
    

    //------------ Validar que el Usuario este en la base de datos para actualizar la contraseña ----------------
    public boolean ValidarUsuarioBaseDatos(String nombreUsuario) {
        boolean respuesta = false;
        String sql = "Select nombreUsuario from usuario where nombreUsuario='" + nombreUsuario + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
//                JOptionPane.showMessageDialog(null, "Usuario encontrado: " + nombreUsuario, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                respuesta = true;
            } else {
//                JOptionPane.showMessageDialog(null, "No existe el Usuario: " + nombreUsuario, "Error de busqueda", JOptionPane.ERROR_MESSAGE);
                respuesta = false;

            }

            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return respuesta;
    }

    //----------------------------- Cambiar Password ---------------------------------------------------------
    public String modificarContrasena(UsuarioLogin user) {
        String serverAnswer = null;

        try {
            PreparedStatement statement = cn.prepareStatement("update usuario set contrasena=? where nombreUsuario=?");

            statement.setString(1, user.getContrasena());

            statement.setString(2, user.getNombreUsuario());

            int numAffectedRows = statement.executeUpdate();

            if (numAffectedRows > 0) {
                serverAnswer = "Contraseña actualizada correctamente";
            }

        } catch (SQLException ex) {
            System.out.println("sql sentence error " + ex.getMessage());
            serverAnswer = "Hay un problema";
        }
        return serverAnswer;
    }

    //------------------------------------ BUSCAR UN USUARIO --------------------------------------------------------------
    public ArrayList<Usuario> obtenerUsuarios(String nombreUsuario) {
        ArrayList<Usuario> listUsuarios = new ArrayList<>();
        Usuario users = new Usuario();
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select usuario.id_usuario,usuario.nombre,usuario.nombreUsuario,usuario.telefono,privilegios.nombre,usuario.ultima_sesion from usuario inner join privilegios on privilegios.idPrivilegio=usuario.idPrivilegio where nombreUsuario='" + nombreUsuario + "'");

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId_usuario(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setNombreUsuario(rs.getString(3));
                user.setTelefono(rs.getString(4));
                user.setPrivilegios(rs.getString(5));
                user.setUltima_sesion(rs.getString(6));

                listUsuarios.add(user);
            }
        } catch (Exception e) {
            System.out.println("SQL error" + e.getMessage());
        }
        return listUsuarios;
    }
    //-------------------------------------

    public boolean ValidarContraseñaAntiguaBaseDatos(String contrasena) {
        boolean respuesta = false;
        String sql = "Select nombreUsuario from usuario where contrasena='" + contrasena + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
//                JOptionPane.showMessageDialog(null, "Usuario encontrado: " + nombreUsuario, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                respuesta = true;
            } else {
//                JOptionPane.showMessageDialog(null, "No existe el Usuario: " + nombreUsuario, "Error de busqueda", JOptionPane.ERROR_MESSAGE);
                respuesta = false;

            }

            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return respuesta;
    }

    //--------------------------------------------------
    public boolean ValidarUsuarioContraseñaBaseDatos(String nombreUsuario) {
        boolean respuesta = false;
        String sql = "Select contrasena from usuario where nombreUsuario='" + nombreUsuario + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
//                JOptionPane.showMessageDialog(null, "Usuario encontrado: " + nombreUsuario, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                respuesta = true;
            } else {
//                JOptionPane.showMessageDialog(null, "No existe el Usuario: " + nombreUsuario, "Error de busqueda", JOptionPane.ERROR_MESSAGE);
                respuesta = false;

            }

            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return respuesta;
    }
//--------------------------------- Modificar el campo de la ultima sesion del Usuario----------------------------

    public String updateUltimaSesionUsuario(Usuario user) {
        String serverAnswer = null;

        try {
            PreparedStatement statement = cn.prepareStatement("update usuario set ultima_sesion=? where nombreUsuario=?");

            statement.setString(1, user.getUltima_sesion());
            statement.setString(2, user.getNombreUsuario());

            int numAffectedRows = statement.executeUpdate();

            if (numAffectedRows > 0) {
                serverAnswer = "Ultima sesion modificada correctamente";
            }

        } catch (SQLException ex) {
            System.out.println("sql sentence error " + ex.getMessage());
            serverAnswer = "Hay un problema";
        }
        return serverAnswer;
    }
}
