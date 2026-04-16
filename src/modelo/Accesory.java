package modelo;

/**
 * La clase Accesory representa un accesorio que una criatura puede equipar.
 * 
 * Hereda de {@link Objetos} el código y el nombre del objeto, y añade:
 * <ul>
 *   <li><b>hapiness_effect</b>: efecto sobre la felicidad de la criatura.</li>
 *   <li><b>energy_effect</b>: efecto sobre la energía de la criatura.</li>
 * </ul>
 *
 * Los accesorios pueden desbloquearse y equiparse en distintas zonas del juego,
 * afectando a las estadísticas de la criatura.
 *
 * Incluye validación EDE para evitar valores negativos en el efecto de felicidad.
 *
 * @author Irene
 * @version 1.0
 * @since 2026-04-16
 */
public class Accesory extends Objetos{
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

 	public Accesory(String objectName,int hapiness_effect,int energy_effect) {
		super(objectName);
		this.hapiness_effect=hapiness_effect;
		this.energy_effect=energy_effect;
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