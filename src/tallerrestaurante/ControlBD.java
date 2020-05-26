/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerrestaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ubuntu
 */
public class ControlBD {
    
    private Cliente cliente;
    private Producto producto;
    private Connection connection;

    public ControlBD() {
        conexionBD();
    }
    
    private void conexionBD(){
        try
        {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
        {
            System.err.println("ERROR! no fue posible cargar el driver JDBC: " + ex.getLocalizedMessage());
            System.exit(1);
        }
        String url = "jdbc:mysql://localhost/Domicilios";
        String username = "admin";
        String password = "pass123";
        try
        {
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e)
        {
            System.err.println("ERROR! no fue posible conectarse a la BD: " + e.getLocalizedMessage());
            System.exit(1);
        }
    }
    
    public String validarDatosLogin(String email, String contraseña){
        try
        {
            String sql = "SELECT * From cliente WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(1, contraseña);
            ResultSet res = statement.executeQuery();
            if(!res.next()){
                return "Datos erróneos, intente de nuevo.";
            }
            String nombres = res.getString("nombres");
            String apellidos = res.getString("apellidos");
            String telefono = res.getString("telefono");
            this.cliente = new Cliente(nombres,apellidos,email,telefono,contraseña,null);
            return null;
        }
        catch (SQLException ex) {
            return "No se pudieron enviar los datos para la validación en la BD";
        }
    }
    
//    public String registroCliente(String nombres, String apellidos, String email, String telefono, String contraseña, String direccion){
//        ArrayList<String>
//    }
}
