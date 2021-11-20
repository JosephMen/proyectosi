/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author frand
 */
public class ModVentas {
    
    public String cod_venta, ced_encargado, ced_cliente, cod_mat, precio_mat, cantidad_mat;
    public String descuento, IVA, precio_SubTotal, precio_total;
    public boolean correcto;
    public void limpiar(){
        cod_venta = ced_encargado = ced_cliente = cod_mat = precio_mat = cantidad_mat = "1";
        descuento = IVA = precio_SubTotal = precio_total = "1";
        correcto = false;
    }
    public void consultar(Connection con){
        
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM proyectosi.ventas where cod_ventas = ?;");
            ps.setString(1, this.cod_venta);
            
            ResultSet res = ps.executeQuery();           
            
            if (res.next()){
                this.cod_venta = res.getString("cod_ventas");
                this.ced_cliente = res.getString("ced_cliente");
                this.ced_encargado = res.getString("ced_encargado");
                this.cod_mat = res.getString("cod_material");
                this.precio_mat = res.getString("precio_material");
                this.cantidad_mat = res.getString("cantida_material_vendido");
                this.descuento = res.getString("descuento");
                this.IVA = res.getString("IVA");
                this.precio_SubTotal = res.getString("Precio_subtotal");
                this.precio_total = res.getString("Precio_total");
                this.correcto = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "No se obtuvo coincidencia");
                this.limpiar();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            
        }
        
    }

    public void agregar(Connection con) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO `proyectosi`.`ventas` (`cod_ventas`, `ced_encargado`, `ced_cliente`, `cod_material`, `precio_material`, `cantida_material_vendido`, `descuento`, `IVA`, `Precio_subtotal`, `Precio_total`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, this.cod_venta);            
            ps.setString(2, this.ced_encargado);
            ps.setString(3, this.ced_cliente);
            ps.setString(4, this.cod_mat);
            ps.setString(5, this.precio_mat);
            ps.setString(6, this.cantidad_mat);
            ps.setString(7, this.descuento);
            ps.setString(8, this.IVA);
            ps.setString(9, this.precio_total);
            ps.setString(10, this.precio_total);
            
            int res = ps.executeUpdate();
            if (res > 0){
                JOptionPane.showMessageDialog(null, "Exito al guardar venta");
            }
            else
                JOptionPane.showMessageDialog(null, "Error al guardar venta");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void eliminar(Connection con) {
        //DELETE FROM `proyectosi`.`ventas` WHERE (`cod_ventas` = '40');
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM `proyectosi`.`ventas` WHERE (`cod_ventas` = ?);");
            ps.setInt(1, Integer.parseInt(this.cod_venta));
            
            int res = ps.executeUpdate();
            if (res > 0){
                JOptionPane.showMessageDialog(null, "Exito al eliminar");
            }
            else
                JOptionPane.showMessageDialog(null, "Error al eliminar venta");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    public void actualizar(Connection con){
        //UPDATE `proyectosi`.`ventas` SET `precio_material` = '10' WHERE (`cod_ventas` = '30');
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE `proyectosi`.`ventas`  SET `ced_encargado`=?, `ced_cliente`=?, `cod_material`=?, `precio_material`=?, `cantida_material_vendido`=?, `descuento`=?, `IVA`=?, `Precio_subtotal`=?, `Precio_total`=? WHERE cod_ventas=?;");
            int num = 1;                
            ps.setString(num++, this.ced_encargado);
            ps.setString(num++, this.ced_cliente);
            ps.setString(num++, this.cod_mat);
            ps.setString(num++, this.precio_mat);
            ps.setString(num++, this.cantidad_mat);
            ps.setString(num++, this.descuento);
            ps.setString(num++, this.IVA);
            ps.setString(num++, this.precio_total);
            ps.setString(num++, this.precio_total);
            ps.setString(num++, this.cod_venta); 
            
            int res = ps.executeUpdate();
            if (res > 0){
                JOptionPane.showMessageDialog(null, "Exito al actualizar venta");
            }
            else
                JOptionPane.showMessageDialog(null, "Error al actualizar venta");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
    }
}
