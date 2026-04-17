package controlador;
import vista.*;
import modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import exception.UserExisteException;
public class CriaturasControlador {
	CriaturasDAO dao = new ImplementacionBD();
	private UserGame usuarioActual;
	
	public void visualizarPantalla() {
		VentanaPrincipal ven = new VentanaPrincipal(this);
		ven.setVisible(true);	
	}
	public boolean introducirUser(UserGame user) throws UserExisteException{ //desde el controlador lanzo la exepcion
		if (comprobarUser(user)) {
			throw new UserExisteException("That username is not available.");
		}
		//si no existe lo introduzco
		return dao.introducirUser(user);
	}
	
	public boolean iniciarSesion(UserGame user) {
		return dao.iniciarSesion(user);
	}
	
	public UserGame getUsuarioActual() { //PARA GUARDARLO EN EL USUARIO QUE INICIA SESION
		return usuarioActual;
	}
	
	public ArrayList<Creature> obtenerPartidas(UserGame user) {
		return dao.obtenerPartidas(user);
	}
	public boolean comprobarUser(UserGame user) {
		return dao.comprobarUser(user);
	}
	public boolean eliminarPartida(Creature creature) {
		return dao.eliminarPartida(creature);
	}
	public List<Objetos> verObjectos(Creature creature) {
		return dao.verObjectos(creature);
	}
	public ArrayList<Food> listaComida(){
		return dao.listaComida();
	}
	public boolean darComida(Creature criatura, Food comida) {
		return dao.darComida(criatura, comida);
	}
	public boolean irDePaseo(Creature creatureName) {
		return dao.irDePaseo(creatureName);
	}
	public boolean insertarCriatura(Creature criatura) {
		return dao.insertarCriatura(criatura);
	}
	public boolean mirarEmocion(Creature creature) {
		return dao.mirarEmocion(creature);
	}
	public boolean descansar(Creature criatura) {
		return dao.descansar(criatura);
	}
	public boolean equiparObjeto(Creature criatura, Accesory accesorio) {
		return dao.equiparObjeto(criatura, accesorio);
	}
	public boolean quitarObjeto(Creature criatura, Accesory accesorio) {
		return dao.quitarObjeto(criatura, accesorio);
	}
	public boolean quitarCualquierObjeto(Creature criatura) {
		return dao.quitarCualquierObjeto(criatura);
	}
	public int comprobarObjeto(Creature criatura) {
		return dao.comprobarObjeto(criatura);
	}
	public boolean desbloqueoBH(Creature criatura) {
		return dao.desbloqueoBH(criatura);
	}
	public boolean desbloqueoSG(Creature criatura) {
		return dao.desbloqueoSG(criatura);
	}
	public boolean comprobarBH(Creature criatura) {
		return dao.comprobarBH(criatura);
	}
	public boolean comprobarSG(Creature criatura) {
		return dao.comprobarSG(criatura);
	}
	public boolean efectoAccesorio(Creature criatura, Accesory accesorio) {
		return dao.efectoAccesorio(criatura, accesorio);
	}
	public Creature obtenerDatosCriatura(int codCreature) {
		return dao.obtenerDatosCriatura(codCreature);
	}
	public Creature actualizarCriatura(Creature criatura) {
		// TODO Auto-generated method stub
		return dao.obtenerDatosCriatura(criatura.getCodC());
	}
	public Creature verCriatura(String creatureName) {
		return dao.verCriatura(creatureName);
	}
}