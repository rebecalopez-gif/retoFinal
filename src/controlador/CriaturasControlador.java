package controlador;

import vista.*;
import modelo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class CriaturasControlador {
	CriaturasDAO dao = new ImplementacionBD();

	public void visualizarPantalla() {
		VentanaPrincipal ven = new VentanaPrincipal(this);
		ven.setVisible(true);	
	}
	public boolean iniciarSesion(UserGame user) {
		return dao.iniciarSesion(user);
	}
	public boolean introducirUser(UserGame user) {
		return dao.introducirUser(user);
	}
	public ArrayList<Creature> obtenerPartidas(UserGame user) {
		return dao.obtenerPartidas(user);
	}
	public boolean comprobarUser(UserGame user){
		return dao.comprobarUser(user);
	}
}
