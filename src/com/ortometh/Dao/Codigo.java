/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ortometh.Dao;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacQu
 */
public class Codigo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     Conexion1 conexion = new Conexion1();
     Connection con = conexion.getConexion();
    PreparedStatement ps;
    ResultSet rs;
    Image img128;
         try {
            ps = con.prepareStatement("select * from producto");
            rs = ps.executeQuery();
            
            Document doc = new Document();
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("codigos.pdf"));
            
            doc.open();
            
            Barcode128 code128 = new Barcode128();
            
            while (rs.next()) {    
            code128.setCode(rs.getString("clave_producto"));
            img128 = code128.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
            doc.add(img128);
            
            doc.add(new Paragraph(" "));
            }
            
            doc.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Codigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Codigo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Codigo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
    

