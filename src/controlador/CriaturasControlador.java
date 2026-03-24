package controlador;

import vista.*;
import modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CriaturasControlador {
	CriaturasDAO dao = new ImplementacionBD();
	private UserGame usuarioActual; 

	public void visualizarPantalla() {
		VentanaPrincipal ven = new VentanaPrincipal(this);
		ven.setVisible(true);	
	}
	 public boolean iniciarSesion(UserGame user) {
	        if (dao.iniciarSesion(user)) {
	            this.usuarioActual = user;  //SE GUARDA EL USUARIO
	            return true;
	        }
	        return false;
	    }
	 public UserGame getUsuarioActual() { //PARA GUARDARLO EN EL USUARIO QUE INICIA SESION
	        return usuarioActual;
	    }

	    public String getUserNameActual() {
	        return usuarioActual != null ? usuarioActual.getUserName() : null;
	    }
	public boolean introducirUser(UserGame user) {
		return dao.introducirUser(user);
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
	public List<Objectos> verObjectos() {
		return dao.verObjectos();
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
	public Object insertarCriatura(Creature criatura) {
		return dao.insertarCriatura(criatura);
		
	}

}
