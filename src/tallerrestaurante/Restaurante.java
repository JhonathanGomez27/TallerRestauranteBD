/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerrestaurante;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ubuntu
 */
public class Restaurante {
    private String id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer tiempo;

    public Restaurante(String id, String nombre, String descripcion, Double precio, Integer tiempo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tiempo = tiempo;
    }

    public String registroProduct(Connection connection){
        try{
            String sql = "SELECT * From producto WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.id);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return "El id ya existe, intenta de nuevo";
            }
            
            sql = "SELECT * From producto WHERE nombre = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.nombre);
            res = statement.executeQuery();
            if(res.next()){
                return "El nombre ya existe, intenta de nuevo";
            }
            
            sql = "INSERT INTO producto VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.id);
            statement.setString(2, this.nombre);
            statement.setString(3, this.descripcion);
            statement.setDouble(4, this.precio);
            statement.setInt(5, this.tiempo);
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }
    
}
