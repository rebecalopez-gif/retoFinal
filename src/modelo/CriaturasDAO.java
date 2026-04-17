package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * La interfaz CriaturasDAO define todas las operaciones necesarias para gestionar
 * usuarios, criaturas, objetos, comida y acciones dentro del juego.
 *
 * Actúa como capa de acceso a datos (DAO), proporcionando métodos para:
 * <ul>
 *   <li>Registrar e iniciar sesión de usuarios.</li>
 *   <li>Gestionar criaturas y sus partidas.</li>
 *   <li>Consultar y modificar emociones y estadísticas.</li>
 *   <li>Administrar objetos, accesorios y desbloqueos.</li>
 *   <li>Aplicar acciones como comer, descansar o ir de paseo.</li>
 * </ul>
 *
 * Las clases que implementen esta interfaz deberán proporcionar la lógica
 * concreta de acceso a base de datos.
 *
 * @author Equipo completo
 * @version 1.0
 * @since 2026-04-16
 */
public interface CriaturasDAO {
	public boolean introducirUser(UserGame user);
	public boolean iniciarSesion(UserGame user);
	public ArrayList<Creature> obtenerPartidas(UserGame user);
	public boolean comprobarUser(UserGame user);
	public boolean eliminarPartida(Creature creature);
	public List<Objetos> verObjectos(Creature creature);
	public ArrayList<Food> listaComida();
	public boolean darComida(Creature criatura, Food comida);
	public boolean irDePaseo(Creature creatureName);
	public boolean insertarCriatura(Creature criatura);
	public boolean mirarEmocion(Creature creature);
	public boolean descansar(Creature criatura);
	public boolean equiparObjeto(Creature criatura, Accesory accesorio);
	public boolean quitarObjeto(Creature criatura, Accesory accesorio);
	public boolean quitarCualquierObjeto(Creature criatura);
	public int comprobarObjeto(Creature criatura);
	public boolean desbloqueoBH(Creature criatura);
	public boolean desbloqueoSG(Creature criatura);
	public boolean comprobarBH(Creature criatura);
	public boolean comprobarSG(Creature criatura);
	public boolean efectoAccesorio(Creature criatura, Accesory accesorio);
	public Creature obtenerDatosCriatura(int codCreature);
	public Creature verCriatura(String creatureName); 
	//public boolean efectosAccesorio(Creature creature, Accesory accesory);
}