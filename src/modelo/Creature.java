package modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
import interfaces.Estados;

/**
 * La clase Creature representa a la criatura virtual asociada a un usuario dentro del juego.
 * 
 * Cada criatura tiene:
 * <ul>
 *   <li><b>codC</b>: identificador único.</li>
 *   <li><b>userName</b>: nombre del usuario propietario.</li>
 *   <li><b>creatureName</b>: nombre de la criatura.</li>
 *   <li><b>experience</b>: experiencia acumulada.</li>
 *   <li><b>energy</b>: nivel de energía.</li>
 *   <li><b>hunger</b>: nivel de hambre.</li>
 *   <li><b>happiness</b>: nivel de felicidad.</li>
 * </ul>
 *
 * Además, implementa la interfaz {@link interfaces.Estados}, que permite
 * determinar la imagen que debe mostrarse según el estado emocional de la criatura.
 *
 * Incluye también una comprobación de edad (EDE) para validar si el usuario
 * cumple la edad mínima requerida para jugar.
 *
 * @author Unai
 * @version 1.0
 * @since 2026-04-16
 */
public class Creature implements Estados{

	//ATRIBUTOS
	private int codC;
	private String userName;
	private String creatureName;
	private int experience;
	private int energy;
	private int hunger;
	private int happiness;
	
	// EDE ------------
	private UserGame dueno; 
	
	public boolean comprobarEdad (UserGame dueno) {
		boolean ok;
		int edadDe9= 2026-6; 
		
		if (edadDe9<dueno.getBirthDate()) {
			ok=false;
		}else {
			ok=true;
		}
		return ok;
	}
	
	//---------------

	//CONSTRUCTORES
	public Creature(int codC, String userName, String creatureName, int experience, int energy, int hunger, int happiness) {
		this.codC = codC;
		this.userName = userName;
		this.creatureName = creatureName;
		this.experience = experience;
		this.energy = energy;
		this.hunger = hunger;
		this.happiness = happiness;
	}
	public Creature() {
		this.codC = 0;
		this.userName = "";
		this.creatureName = "";
		this.experience = 0;
		this.energy = 0;
		this.hunger = 0;
		this.happiness = 0;
	}
	public Creature(String userName, String creatureName) {
		this.userName = userName;
		this.creatureName = creatureName;
		this.experience = 0;
		this.energy = 50;
		this.hunger = 50;
		this.happiness = 50;
	}

	public Creature(String creatureName) {
		this.creatureName =creatureName;

	}

	//GETTERS Y SETTERS
	public int getCodC() {
		return codC;
	}
	public void setCodC(int codC) {
		this.codC = codC;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatureName() {
		return creatureName;
	}
	public void setCreatureName(String creatureName) {
		this.creatureName = creatureName;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getHunger() {
		return hunger;
	}
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	public int getHappiness() {
		return happiness;
	}
	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}


	@Override
	public String toString() {
		return creatureName ;
	}

	/**
	 * Determina la imagen que debe mostrarse según el estado actual de la criatura.
	 *
	 * Reglas:
	 * <ul>
	 *   <li>Si hambre < 30 o felicidad < 30 → imagen triste.</li>
	 *   <li>Si energía < 30 → imagen cansada.</li>
	 *   <li>Si felicidad ≥ 80 → imagen muy feliz.</li>
	 *   <li>En cualquier otro caso → imagen feliz.</li>
	 * </ul>
	 *
	 * @param criatura criatura cuyo estado se evalúa.
	 * @return ruta del recurso de imagen correspondiente.
	 */
	@Override
	public String setImage(Creature criatura) {
		String url = "";

		if (criatura.getHunger() < 30 || criatura.getHappiness() < 30) {
			url = "/image/Triste.png";
		} else if (criatura.getEnergy() < 30) {
			url = "/image/Cansado.png";
		} else if (criatura.getHappiness() >= 80) {
			url = "/image/MuyFeliz.png";
		} else {
			url = "/image/Feliz.png";
		}
		return url;
	}
}
