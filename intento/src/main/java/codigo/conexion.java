    
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author salinas
 */
public class conexion {
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
        System.out.println("hola "+ ex);
    }


    
return conn;
}
}



