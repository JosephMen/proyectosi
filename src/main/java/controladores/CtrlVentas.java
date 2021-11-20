/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.ModVentas;
import modelos.usuario;
import vistas.Ventas;

/**
 *
 * @author frand
 */
public class CtrlVentas implements ActionListener {

    Connection con;
    Ventas vistaVentas;
    ModVentas modVentas;
    usuario usuario;
    
    CtrlVentas(Connection con) {
        this.con = con;
        this.vistaVentas = new Ventas();
        this.modVentas = new ModVentas();
        this.vistaVentas.btnAdd.addActionListener(this);
        this.vistaVentas.btnConsult.addActionListener(this);
        this.vistaVentas.btnDelete.addActionListener(this);
        this.vistaVentas.btnEdit.addActionListener(this);
        this.vistaVentas.btnLimpiar.addActionListener(this);
        
    }

    public void limpiar(){
        this.vistaVentas.txtCodVenta.setText(null);
        this.vistaVentas.txtCodMaterial.setText(null);
        this.vistaVentas.txtPrecioMat.setText(null);
        this.vistaVentas.txtCodEncargado.setText(null);
        this.vistaVentas.txtDescuento.setText(null);
        this.vistaVentas.txtCedulaCliente.setText(null);
        this.vistaVentas.txtIVA.setText(null);
        this.vistaVentas.txtSubTotal.setText(null);
        this.vistaVentas.txtTotal.setText(null);
        this.vistaVentas.txtCantidad.setText(null);
    }
    public void construirModelo(){
        try{
            this.modVentas.cod_venta = this.vistaVentas.txtCodVenta.getText();
            this.modVentas.ced_encargado = this.vistaVentas.txtCodEncargado.getText();
            this.modVentas.ced_cliente= this.vistaVentas.txtCedulaCliente.getText();
            this.modVentas.cod_mat = this.vistaVentas.txtCodMaterial.getText();
            this.modVentas.cantidad_mat = this.vistaVentas.txtCantidad.getText();
            this.modVentas.IVA = this.vistaVentas.txtIVA.getText();
            this.modVentas.descuento = this.vistaVentas.txtDescuento.getText();
            this.modVentas.precio_mat = this.vistaVentas.txtPrecioMat.getText();
            this.modVentas.precio_total = this.vistaVentas.txtTotal.getText();
            this.modVentas.precio_SubTotal = this.vistaVentas.txtSubTotal.getText();
                
            this.modVentas.correcto = true;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(vistaVentas,"Error al crear venta"  + e.getMessage(), "Error",0);
            this.modVentas.limpiar();
        }
    }
    public void consultar(){
        this.modVentas.cod_venta = this.vistaVentas.txtCodVenta.getText();
        this.modVentas.consultar(this.con);
        if (this.modVentas.correcto){
           this.vistaVentas.txtCedulaCliente.setText(this.modVentas.ced_cliente);
           this.vistaVentas.txtCodEncargado.setText(this.modVentas.ced_encargado);
           this.vistaVentas.txtCodMaterial.setText(this.modVentas.cod_mat);
           this.vistaVentas.txtDescuento.setText(this.modVentas.descuento);
           this.vistaVentas.txtIVA.setText(this.modVentas.IVA);
           this.vistaVentas.txtSubTotal.setText(this.modVentas.precio_SubTotal);
           this.vistaVentas.txtTotal.setText(this.modVentas.precio_total);
           this.vistaVentas.txtPrecioMat.setText(this.modVentas.precio_mat);      
        }

    }
    public void agregarVenta(){
        this.construirModelo();
        if(this.modVentas.correcto){
            this.modVentas.agregar(con);
            
        }else
            JOptionPane.showMessageDialog(vistaVentas, "Hay un error");
    }
    
    public void eliminarVenta(){
        this.modVentas.cod_venta = this.vistaVentas.txtCodVenta.getText();
        if (!this.modVentas.cod_venta.equals("")){
            this.modVentas.eliminar(this.con);
        }
        else{
            JOptionPane.showMessageDialog(vistaVentas, "Falta argumento del codigo de venta");
        }
    }
    public void actualizar(){
        this.construirModelo();
        if (!this.modVentas.cod_venta.equals("")){
            this.modVentas.actualizar(this.con);
        }
        else{
            JOptionPane.showMessageDialog(vistaVentas, "Falta argumento del codigo de venta");
        }
    }
    public void Mostrar(usuario usuario){
        
        this.vistaVentas.setVisible(true);
        this.usuario = usuario;
        this.vistaVentas.txtUser.setText("Usuario: " + usuario.nombre);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaVentas.btnAdd){
            this.agregarVenta();
        }
        if (e.getSource() == this.vistaVentas.btnConsult){
            this.consultar();
        }
        if (e.getSource() == this.vistaVentas.btnDelete){
            this.eliminarVenta();
        }
        if (e.getSource() == this.vistaVentas.btnEdit){
            this.actualizar();
        }
        if (e.getSource() == this.vistaVentas.btnLimpiar){
            this.limpiar();
        }
    }


    
}
