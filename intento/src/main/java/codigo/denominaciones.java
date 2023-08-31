/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import cidesi.monto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
    import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author salinas
 */
public class denominaciones {
   
    public float banco(){
     Connection conn =conexion.Coneccion();
        float banco=0;
    String sql="select*from cheque where id=1";
        try {
            Statement sent=conn.createStatement();
            ResultSet rs=sent.executeQuery(sql);
            rs.next();
            
            banco=Float.valueOf(rs.getString("banco"));
            JOptionPane.showMessageDialog(null, banco);
            
        } catch (Exception e) {
        }
    
    
    
    
    return banco;
    
    }
    
      
   public int totalC(){
       int i=0;
         Connection conn =conexion.Coneccion();
       try{
   String sql="select*from denominaciones where id=1";
   Statement sent=conn.createStatement();
   ResultSet rs=sent.executeQuery(sql);
   rs.next();
   int mil=Integer.parseInt(rs.getString("a1000"))*1000;
   int quinientos=Integer.parseInt(rs.getString("a500"))*500;
   int doscientos=Integer.parseInt(rs.getString("a200"))*200;
   int cien=Integer.parseInt(rs.getString("a100"))*100;
   int cincuenta=Integer.parseInt(rs.getString("a50"))*50;
   int veinte=Integer.parseInt(rs.getString("a20"))*20;
   int veinteM=Integer.parseInt(rs.getString("M20"))*20;
   int diez=Integer.parseInt(rs.getString("a10"))*10;
   int cinco=Integer.parseInt(rs.getString("a5"))*5;
   int dos=Integer.parseInt(rs.getString("a2"))*2;
   int uno=Integer.parseInt(rs.getString("a1"))*1;
   float Ccincuenta=Integer.parseInt(rs.getString("C50"))*0.5f;
   float Cveinte=Integer.parseInt(rs.getString("C20"))*0.2f;
   float Cdiez=Integer.parseInt(rs.getString("C10"))*0.1f;
   float totalC=mil+quinientos+doscientos+cien+cincuenta+veinte+veinteM+diez+cinco+dos+uno+Ccincuenta
           +Cveinte+Cdiez;
   String totalcaja=String.valueOf(totalC);
   String todos="Update cheque set totalcaja=? where id=1";
   
   PreparedStatement s=conn.prepareCall(todos);
   s.setString(1, totalcaja);
   int n=s.executeUpdate();
   
   if(n>0){
   
   }
   
       }catch(Exception e){
       JOptionPane.showMessageDialog(null, "Erro total caja chica  "+e);
           System.out.println("erro total caja chica " +e);
       
       }
       
       return i;
   }  
}
