package modelo;

/**
 * La clase Food representa un alimento dentro del juego.
 * 
 * Cada alimento hereda de {@link Objetos} el nombre del objeto y añade
 * efectos específicos sobre la criatura:
 * <ul>
 *   <li><b>hunger_effect</b>: cuánto reduce el hambre.</li>
 *   <li><b>energy_effect</b>: cuánto aumenta la energía.</li>
 *   <li><b>happines_effect</b>: cuánto aumenta la felicidad.</li>
 * </ul>
 *
 * Los alimentos se utilizan en la cocina para modificar las estadísticas
 * de la criatura según sus efectos.
 *
 * @author Irene
 * @version 1.0
 * @since 2026-04-16
 */
public class Food extends Objetos{
	private int hunger_effect;
	private int energy_effect;
	private int happines_effect;
	
	
	/*De la calse padre sollo coge el nombre del objeto porque no necitamos los demas */
	public Food(String objectName,int hunger_effect, int energy_effect, int happines_effect) {
		super(objectName);
		this.hunger_effect = hunger_effect;
		this.energy_effect = energy_effect;
		this.happines_effect = happines_effect;
	}
	
	public Food(String objectName,int hunger_effect) {
		super(objectName);
		this.hunger_effect=hunger_effect;
	}
	public int getEnergy_effect() {
		return energy_effect;
	}
	public void setEnergy_effect(int energy_effect) {
		this.energy_effect = energy_effect;
	}
	public int getHappines_effect() {
		return happines_effect;
	}
	public int getHunger_effect() {
		return hunger_effect;
	}
	public void setHunger_effect(int hunger_effect) {
		this.hunger_effect = hunger_effect;
	}
	
	/**
	 * Establece el efecto de felicidad del alimento.
	 * 
	 * Valida que el efecto no sea negativo.
	 *
	 * @param happines_effect nueva cantidad de felicidad.
	 * @throws IllegalArgumentException si el valor es negativo.
	 */
	//EDE
	public void setHappines_effect(int happines_effect) {
		if (happines_effect < 0) {
			throw new IllegalArgumentException("The happiness_effect cannot be negative.");
		}
		this.happines_effect = happines_effect;
	}
	
	/**
	 * Devuelve una representación en texto del alimento,
	 * mostrando su nombre y su efecto principal (hambre).
	 *
	 * @return cadena con el formato "nombre - Effect: valor".
	 */
	//--
	@Override
	public String toString() {
		return getObjectName()+ " - Effect: "+hunger_effect;
	}
}

