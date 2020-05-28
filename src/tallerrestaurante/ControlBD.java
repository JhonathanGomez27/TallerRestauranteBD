package tallerrestaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author ubuntu
 */
public class ControlBD {
    
    private Cliente cliente;
    private Pedido pedido;
    private Producto producto;
    private Connection connection;
    

    public ControlBD() {
        conexionBD();
    }
    
    private void conexionBD(){
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
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
            statement.setString(2, contraseña);
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
    
    public String registroCliente(String nombres, String apellidos, String email, 
                                String telefono, String password, String direccion){
    
        Cliente client = new Cliente(nombres,apellidos,email,telefono,password,direccion);
        return client.registroClient(this.connection);
        
    }
    
    public String registroPedido(int id,String cliente, String domiciliario, double costo, 
            String estado, String fechaCreacion, String fechaEntrega, String tiempoEntrega, int cantidad){
        Pedido pedido = new Pedido(id,cliente,domiciliario,costo,estado,fechaCreacion,fechaEntrega,tiempoEntrega);
        return pedido.registroPed(this.connection);
    }

    public String registroProducto(String id, String nombre, String descripcion, Double telefono, int tiempo){
        Restaurante producto = new Restaurante(id,nombre,descripcion,telefono,tiempo);
        return producto.registroProduct(this.connection);
    } 
    
    public String registroDomiciliario(String cc,String nombres,String apellidos,
                                       String email,String telefono,String direccion){
        
        Domiciliario domiciliario = new Domiciliario(cc,nombres,apellidos,email,telefono,direccion);
        return domiciliario.registroDomiciliario(connection);
    }
    
    public ArrayList<Producto> listaProdcutos(){
        ArrayList<Producto> productos = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM producto";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            
            while(res.next()){
                String id = res.getString("id");
                String nombre = res.getString("nombre");
                String descripcion = res.getString("descripcion");
                String pre = res.getString("precio");
                String tP = res.getString("tiempoPreparacion");
                
                double precio = Double.parseDouble(pre);
                int tiempoPreparacion = Integer.parseInt(tP);
                
                Producto product = new Producto(id,nombre,descripcion,precio,tiempoPreparacion);
                productos.add(product);
            }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudieron enviar los datos para la validación en la BD");
        }
        
        return productos;
    }
    
    public ArrayList<Domiciliario> listadoDomi(){
        
        ArrayList<Domiciliario> listaDomiciliarios = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM domiciliario";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            
            while(res.next()){
                String cc = res.getString("cc");
                String nombres = res.getString("nombres");
                String apellidos = res.getString("apellidos");
                String email = res.getString("email");
                String telefono = res.getString("telefono");
                String direccion = res.getString("direccion");
                
                Domiciliario domiciliario = new Domiciliario(cc,nombres,apellidos,email,telefono,direccion);
                listaDomiciliarios.add(domiciliario);
            }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudieron enviar los datos para la validación en la BD");
        }
        
        return listaDomiciliarios;
    }
    
    public void logout(){
        this.cliente=null;
    }
    
    public String getNombres(){
        return this.cliente.getNombres();
    }
    
    public String getApellidos(){
        return this.cliente.getApellidos();
    }
    
    public String getDireccion(){
        return this.cliente.getDireccion();
    }
    
    public String getTelefono(){
        return this.cliente.getTelefono();
    }
    
    public String getEmail(){
        return this.cliente.getEmail();
    }
}