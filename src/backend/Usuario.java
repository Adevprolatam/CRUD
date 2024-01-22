/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author dell
 */
public class Usuario {
    private static List<Usuario> listaUsuarios = new ArrayList<>();  
    private int ci;
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private String genero;
    // Constructor
    public Usuario(int ci, String nombre, String apellido, String email, int edad, String genero) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.edad = edad;
        this.genero = genero;
    }
    
    // Método para agregar usuario a la lista
    public static void agregarUsuario(Usuario usuario) {
        try {
            // Establecer la conexión con la base de datos (reemplaza los valores de conexión)
  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "1104264112");


            // Preparar la consulta SQL
            String sql = "INSERT INTO usuarios (ci, nombre, apellido, email, edad, genero) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Establecer los parámetros en la consulta
                statement.setInt(1, usuario.getCi());
                statement.setString(2, usuario.getNombre());
                statement.setString(3, usuario.getApellido());
                statement.setString(4, usuario.getEmail());
                statement.setInt(5, usuario.getEdad());
                statement.setString(6, usuario.getGenero());

                // Ejecutar la consulta
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener la lista de usuarios
    public static List<Usuario> getListaUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            // Establecer la conexión con la base de datos (reemplaza los valores de conexión)
  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "1104264112");
            // Preparar la consulta SQL
            String sql = "SELECT * FROM usuarios";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                // Recorrer el resultado y agregar usuarios a la lista
                while (resultSet.next()) {
                    Usuario usuario = new Usuario(
                            resultSet.getInt("ci"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("email"),
                            resultSet.getInt("edad"),
                            resultSet.getString("genero")
                    );
                    listaUsuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
    //GET USER BY ID
 public static Usuario getUserById(String ci) {
        Usuario usuario = null;
        try {
            // Establecer la conexión con la base de datos (reemplaza los valores de conexión)
  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "1104264112");
            // Preparar la consulta SQL
            String sql = "SELECT * FROM usuarios WHERE ci = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Establecer el parámetro en la consulta
                statement.setString(1, ci);

                // Ejecutar la consulta
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Si se encuentra un usuario, asignar los valores
                    if (resultSet.next()) {
                        usuario = new Usuario(
                                resultSet.getInt("ci"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getString("email"),
                                resultSet.getInt("edad"),
                                resultSet.getString("genero")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
//DELETE USER BY ID 
 // Método para eliminar un usuario por CI
public static void eliminarUsuario(String ci) {
    try {
        // Establecer la conexión con la base de datos (reemplaza los valores de conexión)
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "1104264112");

        // Preparar la consulta SQL para eliminar el usuario por CI
        String sql = "DELETE FROM usuarios WHERE ci = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Establecer el parámetro en la consulta
            statement.setString(1, ci);

            // Ejecutar la consulta para eliminar el usuario
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Getters 
    public int getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }
    
 
}
