package modelo;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Creature {

	//ATRIBUTOS
	private int codC;
	private String userName;
	private String creatureName;
	private int experience;
	private int energy;
	private int hunger;
	private int happiness;
	
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
	public int getHasppiness() {
		return happiness;
	}
	public void setHasppiness(int happiness) {
		this.happiness = happiness;
	}
	
	
	@Override
	public String toString() {
		return creatureName ;
	}
	public String getEmotion() { //ME DEVUELVE LA EMOCION SEGUN LOS VALORES 
		 if (happiness > 80) {
	            return "MuyFeliz";
	        }
	        if (happiness > 50) {
	            return "Feliz";
	        }
	        if (happiness < 30 || hunger > 70) {
	            return "Triste";
	        }
	        if (energy < 30) {
	            return "Cansado";
	        }
	        return "Feliz";
	}
	
	public Image getEmotionImage() { //METODO QUE DEVUELVE LA IMAGEN SEGUN LA EMOCION 
        String emotion = getEmotion();
        switch (emotion) {
            case "MuyFeliz":
                return new ImageIcon(getClass().getResource("/image/MuyFeliz.png")).getImage();
            case "Feliz":
                return new ImageIcon(getClass().getResource("/image/Feliz.png")).getImage();
            case "Triste":
                return new ImageIcon(getClass().getResource("/image/Triste.png")).getImage();
            case "Cansado":
                return new ImageIcon(getClass().getResource("/image/Cansado.png")).getImage();
            default:
                return new ImageIcon(getClass().getResource("/image/Feliz.png")).getImage();
        }
    }

	
	
}
