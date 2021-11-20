/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import maiin.Connector;
import modelos.usuario;
import vistas.Bodega;
import vistas.LogisticaFabricacion;
import vistas.LogisticaVentas;
import vistas.Principal;
import vistas.fabricacion;

/**
 *
 * @author frand
 */
public class CtrlPrincipal implements ActionListener{
    
    
    Principal vistaPrincipal;
    CtrlLogisticaFab ctrlLogisticaFab;
    CtrlLogisticaVentas ctrlLogVen;
    CtrlBodega ctrlBodega;
    Bodega bodega;
    CtrlVentas ctrlVentas;
    Connection con;
    Connector conexion;
    LogisticaVentas logVentas;
    LogisticaFabricacion logFab;
    fabricacion fab;
    
    public CtrlPrincipal(Connection con){
        
        this.vistaPrincipal = new Principal();
        this.ctrlBodega = new CtrlBodega(con);
        this.ctrlLogVen = new CtrlLogisticaVentas(con);
        this.ctrlLogisticaFab = new CtrlLogisticaFab(con);
        this.ctrlVentas = new CtrlVentas(con);
        
        this.vistaPrincipal.btnEntrar.addActionListener(this);
        this.conexion = new Connector();
        this.con = conexion.getConeccion();
        this.bodega = new Bodega(con);
        this.bodega.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.logVentas = new LogisticaVentas(con);
        this.logFab = new LogisticaFabricacion(con);
        this.fab = new fabricacion(con);
    }
    
    public void mostrar(){
        this.vistaPrincipal.setVisible(true);
    }
    public void mostrarBodega(){
        this.bodega.setVisible(true);
    }

    public void consultarUsuario(){
        
        usuario usuario = this.conexion.getUsuario(this.vistaPrincipal.txtuser.getText());
        if (usuario != null && usuario.nombre.equals("frander")){
            this.ctrlVentas.Mostrar(usuario);
        }
        else if (usuario != null && usuario.nombre.equals("maria")){
            this.bodega.setNombre("maria");
            this.mostrarBodega();
        }
        else if (usuario != null && usuario.nombre.equals("fernanda")){
            this.logVentas.mostrar(usuario.nombre);
        }
        else if (usuario != null && usuario.nombre.equals("patrick")){
            this.logFab.mostrar(usuario.nombre);
            this.logFab.setVisible(true);
        }
        else if (usuario != null && usuario.nombre.equals("simon")){
            this.fab.mostrar(usuario.nombre);      
        }      
        else{
            JOptionPane.showMessageDialog(vistaPrincipal, "No se ha encontrado usuario", "Error", 0);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaPrincipal.btnEntrar){
            this.consultarUsuario();
        }
    }
    
}
