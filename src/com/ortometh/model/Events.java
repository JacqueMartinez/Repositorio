/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.model;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sergio Eventos al presionar teclas
 *
 */
public class Events {
    //Haciendo Prueba de Commit
    public void textKeyPress(KeyEvent evt) { //Metodo para validar que el tipo de dato ingresado sea solo TEXTO
        char caracter = evt.getKeyChar();
        if ((caracter < 'a' || caracter > 'z') && (caracter < 'A' || caracter > 'Z')
                && (caracter != (char) KeyEvent.VK_BACK_SPACE) && (caracter != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo letras");
        } else if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 255) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo letras");
        }
    }

    public void numberKeyPress(KeyEvent evt) { //Metodo para validar que el tipo de dato ingresado sea solo NUMEROS
        char caracter = evt.getKeyChar();
        if ((caracter < '0' || caracter > '9') && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo numeros");
            /* Utilizamos el codigo ASCII para bloquear el ingreso de caracteres */
        } else if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 255) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo numeros");
        }
    }

    public void numberDecimalKeyPress(KeyEvent evt, JTextField textField) { //Metodo para validar que el tipo de dato ingresado sea solo NUMEROS
        char caracter = evt.getKeyChar();                                   //CON PUNTO DECIMAL
        if ((caracter < '0' || caracter > '9') && textField.getText().contains(".")
                && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        } else if ((caracter < '0' || caracter > '9') && (caracter != '.')
                && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo numeros");
        }
    }

    public void textVarcharKeyPress(KeyEvent evt) { //Metodo para validar que el tipo de dato ingresado NUMEROS Y LETRAS
        char caracter = evt.getKeyChar();
        if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 255) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo letras ó numeros");
        }
    }

    public void limitCharacters(KeyEvent evt, JTextField textField, int numChar) { //Metodo para limitar la cantidad de caracteres ingresados
        char caracter = evt.getKeyChar();
        int lngt = textField.getText().length();
        if (lngt >= numChar) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Numero máximo alcanzado");
        }
    }

    public void upperFisrtLetter(KeyEvent evt, JTextField textField) { //Metodo convertir la primera letra en Mayuscula en TextField
        String text = textField.getText();
        if (text.length() > 0) {
            char firstL = text.charAt(0);
            text = Character.toUpperCase(firstL) + text.substring(1,
                    text.length());
            textField.setText(text);
        }
    }

    public void toUpperCase(JTextField jTextfieldS) {//Metodo para convertir todas las letras a Mayusculas
        String cadena = (jTextfieldS.getText()).toUpperCase();
        jTextfieldS.setText(cadena);
    }

    public void upperFisrtLetterTA(KeyEvent evt, JTextArea textField) { //Metodo convertir la primera letra en Mayuscula en TextArea
        String text = textField.getText();
        if (text.length() > 0) {
            char firstL = text.charAt(0);
            text = Character.toUpperCase(firstL) + text.substring(1,
                    text.length());
            textField.setText(text);
        }
    }

    public boolean validarEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }
}
