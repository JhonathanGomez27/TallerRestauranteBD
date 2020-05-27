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
public class Domiciliario {
    
    private String cc;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;

    public Domiciliario(String cc, String nombres, String apellidos, String email, String telefono, String direccion) {
        this.cc = cc;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    public String registroDomiciliario(Connection connection){
        try{
            String sql = "SELECT * From domiciliario WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.email);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                return "El email ya existe, intenta de nuevo";
            }
            
            sql = "SELECT * From domiciliario WHERE cc = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.cc);
            res = statement.executeQuery();
            if(res.next()){
                return "La CC ya existe, intenta de nuevo";
            }
            
            sql = "SELECT * From domiciliario WHERE telefono = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.telefono);
            res = statement.executeQuery();
            if(res.next()){
                return "El telefono ya existe, intenta de nuevo";
            }
            
            sql = "INSERT INTO domiciliario VALUES (?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.cc);
            statement.setString(2, this.nombres);
            statement.setString(3, this.apellidos);
            statement.setString(4, this.email);
            statement.setString(5, this.telefono);
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

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
