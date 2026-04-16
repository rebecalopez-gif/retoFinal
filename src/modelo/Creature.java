package modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
import interfaces.Estados;

public class Creature implements Estados{

	//ATRIBUTOS
	private int codC;
	private String userName;
	private String creatureName;
	private int experience;
	private int energy;
	private int hunger;
	private int happiness;
	
	// EDE (creado para mockito) ------------
	private UserGame dueno; 
	
	public boolean comprobarEdad (UserGame dueno) {
		boolean ok;
		int edadDe6= 2026-6; 
		
		if (edadDe6<dueno.getBirthDate()) {
			ok=false;
		}else {
			ok=true;
		}
		return ok;
	}
	// ---------------

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
