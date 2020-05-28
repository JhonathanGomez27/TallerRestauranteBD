/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerrestaurante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jhonathan
 */
public class ProductoPedido {
    private int pedido;
    private String producto;
    private int cantidad;

    public ProductoPedido(int pedido, String producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    public String registroProductoPedido(Connection connection){
        try{
            
            String sql = "SELECT * From productoPedido WHERE pedido = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.pedido);
            ResultSet res = statement.executeQuery();
            
            sql = "SELECT * From productoPedido WHERE producto = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.producto);
            ResultSet res1 = statement.executeQuery();
            
            if(res.next() && res1.next()){
                return "El id del pedido ya esta registrado";
            }
            
            sql = "";
            
            sql = "";
            
            return null;
        }catch(SQLException ex){
            return "No se pudieron enviar los datos para el registro en la BD";
        }
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
