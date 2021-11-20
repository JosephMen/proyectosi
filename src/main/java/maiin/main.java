/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maiin;

import controladores.CtrlPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import vistas.Principal;


/**
 *
 * @author frand
 */
public class main {
    
    public static final String USERNAME = "root";
    public static final String PASSWORD = "Admin123";
    public static final String URL = "jdbc:mysql://localhost:3306/proyectosi";

    
    public static void main(String[] args){
        System.out.println("Hola, ya iniciamos");
        
        //Encargado de ventas, bodega, fabricacion, logisitica
        try{
            Connector classConnector = new Connector();
            Connection con = null;
            con = classConnector.getConeccion();
            
            PreparedStatement ps;
            ResultSet res;
            
            ps = con.prepareStatement("Select * from chofer");
            res = ps.executeQuery();
            
            CtrlPrincipal principal = new CtrlPrincipal(con);
            principal.mostrar();
            /*
            if (res.next()){
                JOptionPane.showMessageDialog(null, res.getString("nombre"));
            }
            else{
                    JOptionPane.showMessageDialog(null, "No hay datos");
   
            }*/
        }catch(Exception e){
            
        }
    }
    
    
}


