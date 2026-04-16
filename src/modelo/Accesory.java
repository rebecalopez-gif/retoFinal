package modelo;
public class Accesory extends Objetos{
	private int hapiness_effect;
	
	public Accesory() {
		super();
		this.hapiness_effect=0;
	}
	
	public Accesory(int cod_object) {
		super(cod_object);
		this.hapiness_effect=0;
	}
	
	public Accesory(int cod_object,String objectName,int hapiness_effect,int energy_effect) {
		super(cod_object, objectName);
		this.hapiness_effect=0;
	}

 	public Accesory(String objectName,int hapiness_effect,int energy_effect) {
		super(objectName);
		this.hapiness_effect=hapiness_effect;
	}
	
	public Accesory(int cod_object,String objectName) {
		super(cod_object, objectName);
	}
	public int getHapiness_effect() {
		return hapiness_effect;
	}
	
	// lo de if es el añadido para EDE ----------------------------------------------------
	public void setHapiness_effect(int hapiness_effect) {
		if (hapiness_effect < 0) {
	        throw new IllegalArgumentException("Hapiness no puede ser negativo");
	    }
		this.hapiness_effect = hapiness_effect;
	}
	//---------------------------------------------------------------------------------

	@Override
	public String toString() {
		return cod_object + " - " + objectName;
	}
	
  }