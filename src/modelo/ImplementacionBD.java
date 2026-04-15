
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

	final String SQLDARCOMIDA="UPDATE CREATURE C JOIN EQUIP E ON C.COD_CREATURE = E.COD_CREATURE JOIN OBJECT O ON O.COD_OBJECT = E.COD_OBJECT SET C.HUNGER = LEAST(100, C.HUNGER + O.HungerEffect),C.ENERGY = LEAST(100, C.ENERGY + O.energy_effect),C.HAPPINESS = LEAST(100, C.HAPPINESS + O.happiness_effect) WHERE C.COD_CREATURE = ? AND O.objectName = ?;"; 
	//LEAST es una funcion que sirve pa elegir el numero mas bajo

	final String SQLMODIFICAR = "UPDATE Creature SET experience=?, hunger=?, energy=? WHERE cod_creature=?"; //para modificar
	final String SQLOBTENER_PARTIDAS = "SELECT * FROM Creature WHERE userName = ?";
	final String SQLBORRAR_PARTIDAS = "DELETE FROM creature WHERE cod_creature=?";
	final String SQL_EXISTE_CRIATURA = "SELECT * FROM Creature WHERE cod_creature = ?";
	final String SQL_INSERT_CRIATURA = "INSERT INTO Creature (userName, creatureName, experience, energy, hunger, happiness) VALUES (?, ?, ?, ?, ?, ?)";
	final String SQLEQUIPAR ="UPDATE CREATURE C, OBJECT O, EQUIP E SET C.HAPPINESS=(C.HAPPINESS+?) WHERE O.COD_OBJECT=E.COD_OBJECT AND E.COD_CREATURE=E.COD_CREATURE AND C.COD_CREATURE=?";
	final String SQL_CRIATURA= "SELECT * FROM Creature WHERE userName = ? AND cod_creature = ?";
	final String SQL_DESCANSAR="UPDATE creature SET energy = 100 WHERE cod_creature = ?";
	//final String SQL_ESTADO="SELECT C.experience, energy,hunger, happiness FROM Creature C WHERE cod_creature=?"; //PARA VER EL ESTADO DEL MOUNSTRUO
	final String FUNCION="{CALL add_user(?, ?, ?)}";
	// Para la conexi n utilizamos un fichero de configuaraci n, config que
	// guardamos en el paquete control: (las pasa a una variable de l programa)
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

	public boolean comprobarUser(UserGame user){ //MIRA SI EXISTE YA ESE USERNAME
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


	public List<Objetos> verObjectos() {
		List<Objetos> objetos= new ArrayList<>();

		this.openConnection();
		try {
			// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
			stmt = con.prepareStatement(SQLCONSULTA);
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Objetos objeto=new Objetos(resultado.getInt("cod_object"),resultado.getString("objectName"));
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
		ArrayList <Food> listaComida  = new ArrayList <Food>();
		this.openConnection();
		try {
			// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
			stmt = con.prepareStatement(SQLCOMIDA);

			ResultSet rs=stmt.executeQuery();

			while (rs.next()) {
				Food o =new Food(rs.getString("objectName"),rs.getInt("HungerEffect"),rs.getInt("energy_effect"),rs.getInt("happiness_effect"));
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

	public boolean darComida(Creature creature, Food comida) {
		boolean ok = false;

		if (comprobarCriatura(creature)) {

			// Subir hambre según el efecto de la comida
			int hambreNueva = creature.getHunger() + comida.getHunger_effect();
			hambreNueva = Math.min(100, hambreNueva); // no pasar de 100

			// Subir energía si la comida da energía
			int energiaNueva = creature.getEnergy() + comida.getEnergy_effect();
			energiaNueva = Math.min(100, energiaNueva);

			// Subir felicidad si aplica
			int felicidadNueva = creature.getHappiness() + comida.getHappines_effect();
			felicidadNueva = Math.min(100, felicidadNueva);

			// Actualizar objeto en memoria
			creature.setHunger(hambreNueva);
			creature.setEnergy(energiaNueva);
			creature.setHappiness(felicidadNueva);

			this.openConnection();
			try {
				stmt = con.prepareStatement(SQLMODIFICAR);	
				stmt.setInt(1, creature.getHunger());
				stmt.setInt(2, creature.getEnergy());
				stmt.setInt(3, creature.getHappiness());
				stmt.setInt(4, creature.getCodC());

				if (stmt.executeUpdate() > 0) {
					ok = true;
				}

				stmt.close();
				con.close();

			} catch (SQLException e) {
				System.out.println("Error al modificar criatura: " + e.getMessage());
			}
		}

		return ok;

	}

	public boolean equiparObjeto(Creature criatura, Accesory accesorio) {
		boolean ok=false;
		this.openConnection();//abro la conecexion

		try {
			stmt = con.prepareStatement(SQLEQUIPAR); //EN EL STM LE AÑADE FELICIDAD
			stmt.setInt(1, accesorio.getHapiness_effect());
			stmt.setInt(2, criatura.getCodC());
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

	public boolean comprobarCriatura(Creature creatureName){ //para comprobar si existe para actualizar su experiencia y hambre (SE USA EN OTROS METODOS)
		boolean existe=false;
		this.openConnection();
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
	public boolean irDePaseo(Creature creature) {
		boolean ok=false;
		if (comprobarCriatura(creature))
		{
			//Generar experiencia aleatoria
			int expGanada=0;

			// Baja el hambre
			int hambreNueva=creature.getHunger()-(int)(Math.random() * 30);
			int energiaNueva = Math.max(0, creature.getEnergy() - (int)(Math.random() * 30));
			if (hambreNueva<0) {
				hambreNueva=0;
			}

			if(hambreNueva!=0) {
				expGanada = (int)(Math.random() * 41) + 10; // entre 10 y 50
			} else {
				expGanada=0;
			}

			// meter los nuevos datos
			creature.setExperience(creature.getExperience() + expGanada);
			creature.setHunger(hambreNueva);
			creature.setEnergy(energiaNueva);
			this.openConnection();
			try {
				// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
				stmt = con.prepareStatement(SQLMODIFICAR);
				stmt.setDouble(1, creature.getExperience());
				stmt.setInt(2, creature.getHunger());
				stmt.setInt(3, creature.getEnergy());
				stmt.setInt(4, creature.getCodC());
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
			stmt.setInt(6, c.getHappiness());

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

	public boolean descansar(Creature criatura) {
		boolean ok=false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQL_DESCANSAR);
			stmt.setInt(1, criatura.getCodC());

			if (stmt.executeUpdate() > 0) {
				ok = true;
			}

			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}
		return ok;
	}

	@Override
	public Creature obtenerDatosCriatura(int codCreature) {
		Creature c = null;
		this.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT * FROM Creature WHERE cod_creature = ?"
					);
			stmt.setInt(1, codCreature);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				c = new Creature(
						rs.getInt("cod_creature"),
						rs.getString("userName"),
						rs.getString("creatureName"),
						rs.getInt("experience"),
						rs.getInt("energy"),
						rs.getInt("hunger"),
						rs.getInt("happiness")
						);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al obtener datos de criatura: " + e.getMessage());
		}
		return c;
	}


}
