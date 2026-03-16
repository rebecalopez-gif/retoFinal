package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ImplementacionBD implements CriaturasDAO{
	// Atributos
	private Connection con;
	private PreparedStatement stmt; //ejecutar sentencias sql

	// Los siguientes atributos se utilizan para recoger los valores del fich de
	// configuración
	private ResourceBundle configFile;
	private String driverBD;
	private String urlBD;
	private String userBD;
	private String passwordBD;

	// Sentencias SQL
	/*MAYUSCULAS*/
	final String SQL = "SELECT * FROM usuario WHERE nombre = ? AND contrasena = ?";		
	final String sql1 = "SELECT * FROM usuario WHERE nombre = ?";
	final String sqlInsert = "INSERT INTO usuario VALUES (?,?)";
	final String SQLCONSULTA = "SELECT * FROM usuario";
	final String SQLCONSULTA_Vendido= "SELECT * FROM vendido WHERE dni=?";
	final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";
	final String SQLMODIFICAR = "UPDATE usuario SET contrasena=? WHERE nombre=?";

	// Para la conexi n utilizamos un fichero de configuaraci n, config que
	// guardamos en el paquete control: (las pasa a una variable de l programa)
	//COPIAR--------------
	public ImplementacionBD() {
		this.configFile = ResourceBundle.getBundle("configClase");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

	//COPIAR--------------
	private void openConnection() {//abre la conexion con la base de datos
		try {
			con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
