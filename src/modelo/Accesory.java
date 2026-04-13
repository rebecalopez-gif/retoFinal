package modelo;

public class Accesory extends Objetos{
	private int hapiness_effect;
	private int energy_effect;
	
	public Accesory() {
		super();
		this.hapiness_effect=0;
		this.energy_effect=0;
	}
	
	public Accesory(String objectName,int hapiness_effect,int energy_effect) {
		super(objectName);
		this.hapiness_effect=0;
		this.energy_effect=0;
	}

	public int getHapiness_effect() {
		return hapiness_effect;
	}

	public void setHapiness_effect(int hapiness_effect) {
		this.hapiness_effect = hapiness_effect;
	}

	public int getEnergy_effect() {
		return energy_effect;
	}

	public void setEnergy_effect(int energy_effect) {
		this.energy_effect = energy_effect;
	}

	@Override
	public String toString() {
		return "Accesory [hapiness_effect=" + hapiness_effect + ", energy_effect=" + energy_effect
				+ ", getCod_object()=" + getCod_object() + ", getObjectName()=" + getObjectName() + "]";
	}
	
	
	
}
