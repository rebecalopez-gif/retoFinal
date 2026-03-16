package modelo;

public class Creature {

	//ATRIBUTOS
	private int codC;
	private String userName;
	private String creatureName;
	private int experience;
	private int energy;
	private int hunger;
	private int hasppiness;
	
	//CONSTRUCTORES
	public Creature(int codC, String userName, String creatureName, int experience, int energy, int hunger, int hasppiness) {
		this.codC = codC;
		this.userName = userName;
		this.creatureName = creatureName;
		this.experience = experience;
		this.energy = energy;
		this.hunger = hunger;
		this.hasppiness = hasppiness;
	}
	public Creature() {
		this.codC = 0;
		this.userName = "";
		this.creatureName = "";
		this.experience = 0;
		this.energy = 0;
		this.hunger = 0;
		this.hasppiness = 0;
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
		return hasppiness;
	}
	public void setHasppiness(int hasppiness) {
		this.hasppiness = hasppiness;
	}
	
	//toString
	@Override
	public String toString() {
		return "Creature [codC=" + codC + ", userName=" + userName + ", creatureName=" + creatureName + ", experience="
				+ experience + ", energy=" + energy + ", hunger=" + hunger + ", hasppiness=" + hasppiness + "]";
	}
	
}
