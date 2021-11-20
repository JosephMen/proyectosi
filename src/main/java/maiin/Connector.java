/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maiin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import static maiin.main.PASSWORD;
import static maiin.main.URL;
import static maiin.main.USERNAME;
import modelos.usuario;

/**
 *
 * @author frand
 */
public class Connector {
    
    Connection con;
    
    public Connector(){
        this.con = this.getConeccion();
    }
    public Connection getConeccion(){
        
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD); 
            this.con = con;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return con;
    }
    
    public usuario getUsuario(String cedula){
        
        usuario usuario = null;
        try {
            PreparedStatement ps;
            ResultSet res;

            ps = con.prepareStatement("Select * from encargado where ced_encargado = ?");
            ps.setString(1, cedula);
            res = ps.executeQuery();
            usuario = new usuario();
            if (res.next()){
                usuario.cedula = cedula;
                usuario.nombre = res.getString("nombre_encargado");
                if(usuario.nombre.equals("frander")){
                    usuario.tipo = "ventas";
                }
                else{
                    usuario.tipo = "bodega";
                }
                return usuario;
            }
            else{
                JOptionPane.showMessageDialog(null, "No se encontro usuario" );
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage() );
        }
        return usuario;
    }
    

}
