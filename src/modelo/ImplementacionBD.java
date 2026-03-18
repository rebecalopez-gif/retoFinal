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
	final String SQL = "SELECT * FROM UserGame WHERE userName = ? AND passwordUser = ?";		
	final String SQLInsertUser = "INSERT INTO UserGame VALUES (?,?,?)";


	final String SQL_Existe = "SELECT * FROM UserGame WHERE userName = ?";

	final String SQLCONSULTA = "SELECT * FROM usuario";
	final String SQLCONSULTA_Vendido= "SELECT * FROM vendido WHERE dni=?";
	final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";
	final String SQLMODIFICAR = "UPDATE usuario SET contrasena=? WHERE nombre=?";
	final String OBTENER_PARTIDAS = "SELECT * FROM Creature WHERE userName = ?";

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
	public boolean iniciarSesion(UserGame user){
		// Abrimos la conexion
		boolean existe=false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPasswordUser());

			ResultSet resultado = stmt.executeQuery();
			//Si hay un resultado, el usuario existe
			if (resultado.next()) {
				existe = true;
			}

			resultado.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}
		return existe;
	}

	public boolean introducirUser(UserGame user){
		// Abrimos la conexion
		boolean existe=false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQLInsertUser);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPasswordUser());
			//FUNCION DE BASE DE DATOS 
			stmt.setDate(3, java.sql.Date.valueOf(user.getBirthDate())); //para insertar la fecha 

			if (stmt.executeUpdate()>0) {
				existe=true;
			}

			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al crear un usuario: " + e.getMessage());
		}
		return existe;
	}

	public ArrayList<Creature> obtenerPartidas(UserGame user) {
		ArrayList<Creature> criaturas = new ArrayList<Creature>();
		
		this.openConnection();
		try {
			stmt = con.prepareStatement(OBTENER_PARTIDAS);
            stmt.setString(1, user.getUserName());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
            	criaturas.add(new Creature(resultado.getInt("cod_creature"), resultado.getString("userName"), resultado.getString("creatureName"), resultado.getInt("experience"), resultado.getInt("energy"), resultado.getInt("hunger"), resultado.getInt("happiness")));
            }
            resultado.close();
            stmt.close();
            con.close();
		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
        }
		
		return criaturas;
	}

	public boolean comprobarUser(UserGame user){
		// Abrimos la conexion
		boolean existe=false;
		this.openConnection();//abro la conecexion

		try {
			stmt = con.prepareStatement(SQL_Existe); 
			stmt.setString(1, user.getUserName());
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				existe = true;
			}
			resultado.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}

		return existe;
	}


}