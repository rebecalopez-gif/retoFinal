package modelo;

public class Accesory extends Objeto{
	private int hapiness_effect;
	private int energy_effect;
	
	public Accesory() {
		super();
		this.hapiness_effect=0;
		this.energy_effect=0;
	}
	public Accesory(int cod_object) {
		super(cod_object);
		this.hapiness_effect=0;
		this.energy_effect=0;
	}
	
	public Accesory(int cod_object,String objectName,int hapiness_effect,int energy_effect) {
		super(cod_object, objectName);
		this.hapiness_effect=0;
		this.energy_effect=0;
	}
	
	public Accesory(int cod_object,String objectName) {
		super(cod_object, objectName);
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
		return cod_object + " - " + objectName;
	}
	
	
	
}
