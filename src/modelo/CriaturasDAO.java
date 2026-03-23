package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface CriaturasDAO {
	public boolean introducirUser(UserGame user);
	public boolean iniciarSesion(UserGame user);
	public ArrayList<Creature> obtenerPartidas(UserGame user);
	public boolean comprobarUser(UserGame user);
	public boolean eliminarPartida(Creature creature);
	public List<Objectos> verObjectos();
	public ArrayList<Food> listaComida();
	public boolean darComida(Creature criatura, Food comida);
	public boolean irDePaseo(Creature creatureName);

}
