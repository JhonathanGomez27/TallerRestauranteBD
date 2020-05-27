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
 * @author jhonathan
 */
public class Cliente {
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String password;
    private String direccion;

    public Cliente(String nombres, String apellidos, String email, String telefono, String password,String direccion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.direccion = direccion;
    }
    
    public Cliente(){
        
    }

    public String registroClient(Connection connection){
        try{
            String sql = "SELECT * From cliente WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.email);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return "El email ya existe, intenta de nuevo";
            }
            
            sql = "SELECT * From cliente WHERE telefono = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.telefono);
            res = statement.executeQuery();
            if(res.next()){
                return "El telefono ya existe, intenta de nuevo";
            }
            
            sql = "INSERT INTO cliente VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.nombres);
            statement.setString(2, this.apellidos);
            statement.setString(3, this.email);
            statement.setString(4, this.telefono);
            statement.setString(5, this.password);
            if(statement.executeUpdate() != 1){
                throw new SQLException();
            }
            sql = "INSERT INTO direccionCliente VALUES (?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.email);
            statement.setString(2, this.direccion);
            if(statement.executeUpdate() != 1){
                throw new SQLException();
            }
            return null;
        }
        catch(SQLException ex){
            return "No se pudieron enviar los datos para el registro en la BD";
        }
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
