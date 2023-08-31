/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author pavlo
 */
public class holacon {
public static Connection conn;    
public static String url="jdbc:sqlserver://localhost:1433;databaseName=cidesiedomex;encrypt=true;trustServerCertificate=true;";
public static String usuario="root";
public static String contraseña="root";

public static Connection Coneccion (){
    
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn=DriverManager.getConnection(url,usuario,contraseña);
        //JOptionPane.showMessageDialog(null, usuario);
        
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, usuario+" error " +ex);
    }


    
return conn;
}
}
