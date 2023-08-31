/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author salinas
 */
public class auto {
   
public int id_vales(){
int id=1;
Connection conn;
PreparedStatement ps=null;
ResultSet rs=null;
conexion a=new conexion();
conn=conexion.Coneccion();
try{
ps=conexion.Coneccion().prepareStatement("SELECT MAX(id) FROM valess");
rs=ps.executeQuery();
while(rs.next()){
id= rs.getInt(1)+1;
}
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error "+e);
}
finally {
    try{
    ps.close();
    rs.close();
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error "+e);
    }
}
return id;
}


public int id_caja(){
int id=1;
Connection conn;
PreparedStatement ps=null;
ResultSet rs=null;
conexion a=new conexion();
conn=conexion.Coneccion();
try{
ps=conexion.Coneccion().prepareStatement("SELECT MAX(id) FROM banco");
rs=ps.executeQuery();
while(rs.next()){
id= rs.getInt(1)+1;
}
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error "+e);
}
finally {
    try{
    ps.close();
    rs.close();
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error "+e);
    }
}
return id;
}


///////////////////////////////////////intento 1

public int usuario
        (){
int id=1;
Connection conn;
PreparedStatement ps=null;
ResultSet rs=null;
conexion a=new conexion();
conn=conexion.Coneccion();
try{
ps=conexion.Coneccion().prepareStatement("SELECT MAX(id) FROM usuario");
rs=ps.executeQuery();
while(rs.next()){
id= rs.getInt(1)+1;
}
}catch(Exception e){
JOptionPane.showMessageDialog(null,"Error "+e);
}
finally {
    try{
    ps.close();
    rs.close();
    }catch(Exception e){
    JOptionPane.showMessageDialog(null,"Error "+e);
    }
}
return id;
}



}