/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerrestaurante;

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
}
