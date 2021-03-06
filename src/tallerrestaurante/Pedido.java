/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerrestaurante;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jhonathan
 */
public class Pedido {
    private int id;
    private String cliente;
    private String domiciliario;
    private double costo;
    private String estado;
    private String fechaCreacion;
    private String fechaEntrega;
    private String tiempoEntrega;

    public Pedido(int id, String cliente, String domiciliario, double costo, String estado,  String tiempoEntrega) {
        this.id = id;
        this.cliente = cliente;
        this.domiciliario = domiciliario;
        this.costo = costo;
        this.estado = estado;
        this.tiempoEntrega = tiempoEntrega;
    }

    public String registroPed(Connection connection){
        try{
            String sql = "INSERT INTO pedido (cliente, domiciliario, costo, estado, fechaCreacion, tiempoEntrega) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, domiciliario);
            statement.setDouble(2, this.costo);
            statement.setString(3, "En preparacion");
            statement.setString(4, "NOW()");
            statement.setInt(5, Integer.parseInt(this.tiempoEntrega));
            if(statement.executeUpdate() != 1){
                throw new SQLException();
            }
//            statement.setString(3, );
            if(statement.executeUpdate() != 1){
                throw new SQLException();
            }
            return null;
        }
        catch(SQLException ex){
            return "No se pudieron enviar los datos para el registro en la BD";
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDomiciliario() {
        return domiciliario;
    }

    public void setDomiciliario(String domiciliario) {
        this.domiciliario = domiciliario;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }
}
