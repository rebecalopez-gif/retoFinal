package modelo;

public class Food extends Objectos{
	private int hunger_effect;
	private int energy_effect;
	private int happiness_effect;
	public Food() {
		super();
		this.hunger_effect=0;
		this.happiness_effect=0;
		this.energy_effect=0;
	}
	
	public Food(String objectName,int hunger_effect,  int energy_effect , int happiness_effect) {
		super(objectName);
		this.hunger_effect=hunger_effect;
		this.happiness_effect=happiness_effect;
		this.energy_effect=energy_effect;
	}

	public int getEnergy_effect() {
		return energy_effect;
	}

	public void setEnergy_effect(int energy_effect) {
		this.energy_effect = energy_effect;
	}

	public int getHappiness_effect() {
		return happiness_effect;
	}

	public void setHappiness_effect(int happiness_effect) {
		this.happiness_effect = happiness_effect;
	}

	public int getHunger_effect() {
		return hunger_effect;
	}

	public void setHunger_effect(int hunger_effect) {
		this.hunger_effect = hunger_effect;
	}

	@Override
	public String toString() {
		return getObjectName()+ " - HUN: "+hunger_effect + " E: "+energy_effect+" HAP: "+happiness_effect;
	}
	
	
}
