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
public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int tiempoPreparacion;

    public Producto(String id, String nombre, String descripcion, double precio, int tiempoPreparacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tiempoPreparacion = tiempoPreparacion;
    }
    
    
    public String registroProducto(Connection connection){
        try{
            
            String sql = "SELECT * From producto WHERE nombre = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.id);
            ResultSet res = statement.executeQuery();
            
            if(res.next()){
                return "Ya existe un producto con este ID, intenta de nuevo";
            }
            
            sql = "SELECT * From producto WHERE nombre = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.nombre);
            res = statement.executeQuery();
            
            if(res.next()){
                return "Ya existe un producto con este nombre, intenta de nuevo";
            }
            
            sql = "INSERT INTO pruducto VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.id);
            statement.setString(2, this.nombre);
            statement.setString(3, this.descripcion);
            statement.setDouble(4, this.precio);
            statement.setInt(5, this.tiempoPreparacion);
            
            if(statement.executeUpdate() != 1){
                throw new SQLException();
            }
            
            return null;
            
            
            
        }
        catch(SQLException ex){
            return "No se pudieron enviar los datos para el registro en la BD";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
}
