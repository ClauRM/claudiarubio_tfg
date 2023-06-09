package controladorDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.Usuario;
import utilidades.Utilidades;

public class UsuarioDB {

	GestorDB gestorDB = new GestorDB(); // objeto de la clase GestorDB.java

	// metodo para validar el login de un usuariio comparando contra la BD
	public Usuario validarUsuario(String email, String password) {
		
		Connection conection; // objeto de la clase Connection
		PreparedStatement prepareStatement; // variable de tipo prepareStatement
		ResultSet resultSet; // variable de tipo resultSet
		
		Usuario user = new Usuario();// creo el objeto usuario de la clase que he creado
		String consulta = "SELECT * FROM usuarios WHERE email=? AND password=?"; // consulta para localizar al usuario
																					// en la bd
		
		try {
			conection = gestorDB.abrirConexion(); // establecer la conexion
			prepareStatement = conection.prepareStatement(consulta);
			//indico cuales son los parametos de la consulta
			prepareStatement.setString(1, email);
			prepareStatement.setString(2, Utilidades.encriptar(password)); //password introducida por el usuario y encriptada
			//ejecuto query
			resultSet = prepareStatement.executeQuery();
			
			// mientras que haya datos en el resultado de la consulta, recorrerla
			while (resultSet.next()) {
				user.setIdusuario(resultSet.getInt("idusuario"));// nombre de la columna en la base de datos
				user.setNombre(resultSet.getString("nombre"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setAdmin(resultSet.getInt("admin"));
			}

		} catch (Exception e) {
			System.out.println("ERROR EN METODO validarUsuario(): " + e.getMessage()); // muestro error por consola
		}

		return user;
	}

	// metodo para validar si el email del nuevo usuario ya esta incluido en la bd
	public String validarUsuario(String email) {
		
		Connection conection; // objeto de la clase Connection
		PreparedStatement prepareStatement; // variable de tipo prepareStatement
		ResultSet resultSet; // variable de tipo resultSet
		
		Usuario user = new Usuario();// creo el objeto usuario de la clase que he creado
		String consulta = "SELECT * FROM usuarios WHERE email=?"; // consulta para localizar al usuario
		
		String mensaje="";// en la bd
		
		try {
			conection = gestorDB.abrirConexion(); // establecer la conexion
			prepareStatement = conection.prepareStatement(consulta);
			//indico cuales son los parametos de la consulta
			prepareStatement.setString(1, email);
			//ejecuto query
			resultSet = prepareStatement.executeQuery();
			
			// si la consulta devuelve datos, indica que el usuario ya esta registrado
			while (resultSet.next()) {
				mensaje = "El email " + email + " ya est� registrado. Accede a la p�gina desde Login. ";
			}

		} catch (Exception e) {
			System.out.println("ERROR EN METODO validarUsuario(email): " + e.getMessage()); // muestro error por consola
		}

		return mensaje;
	}
	// NUEVO USUARIO

	public void insertarUsuario(Usuario usuario) {

		int resultado = 0; // resultado de ejecutar la consulta de insercion: 1 ok | 2 nada

		resultado = gestorDB.insertarUsuarioDB(usuario);

		if (resultado != 1) {
			// ha ocurrido un error en el registro a la bd
			System.out.println("Error al insertar usuario en la BD");
		}

	}
}
