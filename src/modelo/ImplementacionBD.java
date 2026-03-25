
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
	final String SQL = "SELECT * FROM UserGame WHERE userName = ? AND passwordUser = ?";		
	final String SQLInsertUser = "INSERT INTO UserGame VALUES (?,?,?)"; //PREGUNTAR SI TIENE QUE SER EN MAYUSCULAS 

	final String SQL_Existe = "SELECT * FROM UserGame WHERE userName = ?";

	final String SQLCONSULTA = "SELECT * FROM Object WHERE HungerEffect=0";
	final String SQLCOMIDA = "SELECT * FROM Object WHERE HungerEffect>0";
	final String SQLDARCOMIDA="UPDATE CREATURE C, OBJECT O, EQUIP E SET C.HUNGER=(C.HUNGER+(?)), C.ENERGY=(C.ENERGY+(?)), C.HAPPINESS=(C.HAPPINESS+(?)) WHERE O.COD_OBJECT=E.cod_object AND E.COD_CREATURE=E.COD_CREATURE AND C.COD_CREATURE=?";

	//final String SQLCONSULTA_Vendido= "SELECT * FROM vendido WHERE dni=?";
	//final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";
	//final String SQLCONSULTA_Vendido= "SELECT * FROM vendido WHERE dni=?";
	//final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";

	final String SQLMODIFICAR = "UPDATE Creature SET experience=?, hunger=?, energy=? WHERE cod_creature=?"; //paera modificar
	final String SQLOBTENER_PARTIDAS = "SELECT * FROM Creature WHERE userName = ?";
	final String SQLBORRAR_PARTIDAS = "DELETE FROM creature WHERE cod_creature=?";

	final String SQL_EXISTE_CRIATURA = "SELECT * FROM Creature WHERE cod_creature = ?";
	final String SQL_INSERT_CRIATURA = "INSERT INTO Creature (userName, creatureName, experience, energy, hunger, happiness) VALUES (?, ?, ?, ?, ?, ?)";

	final String SQL_CRIATURA= "SELECT * FROM Creature WHERE userName = ? AND cod_creature = ?";

	final String FUNCION="{CALL add_user(?, ?, ?)}";

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

	public boolean introducirUser(UserGame user){ //aqui usamos la funcion de SQL
		// Abrimos la conexion
		boolean insertado = false;
		this.openConnection();
		try {
			CallableStatement stmt = con.prepareCall(FUNCION);//CallableStatement es una clase diseñada para procedimientos almacenados
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPasswordUser());

			// combierto el INT en una fecha pa poder mandarla al SQL
			int year = user.getBirthDate();
			LocalDate fecha = LocalDate.of(year, 1, 1);

			// Insertar como DATE se guardarian todos ocmo YYYY-01-01
			stmt.setDate(3, java.sql.Date.valueOf(fecha));

			boolean tieneResultado = stmt.execute();

			//como la funcion en el select devuelve una frase 
			if (tieneResultado) {//si es true 
				ResultSet rs = stmt.getResultSet(); //mi select con el mensaje
				if (rs.next()) {
					String mensaje = rs.getString(1);
					System.out.println("Mensaje BD: " + mensaje);
					if (mensaje.contains("CORRECTAMENTE")) {
						insertado = true;
					}
				}
				rs.close();
			}
			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al crear un usuario: " + e.getMessage());
		}
		return insertado;
	}

	public ArrayList<Creature> obtenerPartidas(UserGame user) {
		ArrayList<Creature> criaturas = new ArrayList<Creature>();

		this.openConnection();
		try {
			stmt = con.prepareStatement(SQLOBTENER_PARTIDAS);
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

	public boolean eliminarPartida(Creature creature) {
		boolean ok=false;

		this.openConnection();
		try {
			stmt = con.prepareStatement(SQLBORRAR_PARTIDAS);
			stmt.setInt(1, creature.getCodC());
			if (stmt.executeUpdate()>0) {
				ok=true;
			}	
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}

		return ok;
	}


	public List<Objectos> verObjectos() {
		List<Objectos> objetos= new ArrayList<>();

		this.openConnection();
		try {
			// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
			stmt = con.prepareStatement(SQLCONSULTA);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Objectos objeto=new Objectos(resultado.getInt("cod_object"),resultado.getString("objectName"));
				objetos.add(objeto);
			}
			resultado.close();

			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al mostrar credenciales: " + e.getMessage());
		}

		return objetos;	
	}

	public ArrayList <Food> listaComida() {
		// TODO Auto-generated method stub
		ArrayList <Food> listaComida  = new ArrayList <Food>();


		this.openConnection();
		try {
			// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
			stmt = con.prepareStatement(SQLCOMIDA);

			ResultSet rs=stmt.executeQuery();

			while (rs.next()) {
				Food o =new Food( rs.getString("objectName"),rs.getInt("HungerEffect"),rs.getInt("energy_effect"),rs.getInt("happiness_effect"));
				listaComida.add(o);
			}	

			rs.close();	
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}

		return listaComida;		
	}

	public boolean darComida(Creature criatura, Food comida) {
		boolean ok=false;
		this.openConnection();//abro la conecexion

		try {
			stmt = con.prepareStatement(SQLDARCOMIDA); 
			stmt.setInt(1, comida.getHunger_effect());
			stmt.setInt(2, comida.getEnergy_effect());
			stmt.setInt(3, comida.getHappiness_effect());
			stmt.setInt(4, criatura.getCodC());
			if (stmt.executeUpdate()>0) {
				ok=true;

			}	
			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}

		return ok;
	}

	public boolean comprobarCriatura(Creature creatureName){ //para comprobar si existe para actualizar su experiencia y hambre
		// Abrimos la conexion
		boolean existe=false;
		this.openConnection();//abro la conecexion

		try {
			stmt = con.prepareStatement(SQL_EXISTE_CRIATURA); 
			stmt.setInt(1, creatureName.getCodC());
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

	@Override
	public boolean irDePaseo(Creature creatureName) {
		boolean ok=false;
		if (comprobarCriatura(creatureName))
		{
			//Generar experiencia aleatoria
			int expGanada=0;

			// Baja el hambre
			int hambreNueva=creatureName.getHunger()-(int)(Math.random() * 30);
			int energiaNueva=creatureName.getEnergy()-(int)(Math.random() * 30);
			if (hambreNueva<0) {
				hambreNueva=0;
			}

			if(hambreNueva!=0) {
				expGanada = (int)(Math.random() * 41) + 10; // entre 10 y 50
			} else {
				expGanada=0;
			}

		


			// meter los nuevos datos
			creatureName.setExperience(creatureName.getExperience() + expGanada);
			creatureName.setHunger(hambreNueva);
			creatureName.setEnergy(energiaNueva);
			this.openConnection();
			try {
				// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
				stmt = con.prepareStatement(SQLMODIFICAR);
				stmt.setDouble(1, creatureName.getExperience());
				stmt.setInt(2, creatureName.getHunger());
				stmt.setInt(3, creatureName.getEnergy());
				stmt.setInt(4, creatureName.getCodC());
				if (stmt.executeUpdate()>0) {
					ok=true;
				}			
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al modificar credenciales: " + e.getMessage());
			}
		}
		return ok;	
	}

	public Object insertarCriatura(Creature c) {
		boolean ok = false;
		this.openConnection();

		try {
			stmt = con.prepareStatement(SQL_INSERT_CRIATURA);
			stmt.setString(1, c.getUserName());
			stmt.setString(2, c.getCreatureName());
			stmt.setInt(3, c.getExperience());
			stmt.setInt(4, c.getEnergy());
			stmt.setInt(5, c.getHunger());
			stmt.setInt(6, c.getHasppiness());

			if (stmt.executeUpdate() > 0) {
				ok = true;
			}

			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al insertar criatura: " + e.getMessage());
		}

		return ok;
	}

	public boolean mirarEmocion(Creature creature) {
		boolean ok=false;
		this.openConnection();

		try {
			stmt = con.prepareStatement(SQL_CRIATURA); 
			stmt.setString(1, creature.getUserName());
			stmt.setInt(2, creature.getCodC());

			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				ok = true;
			}

			resultado.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}

		return ok;
	}

}
