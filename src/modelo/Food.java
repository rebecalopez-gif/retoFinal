package modelo;
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
	
	//EDE
	public void setHappines_effect(int happines_effect) {
		if (happines_effect < 0) {
			throw new IllegalArgumentException("The happiness_effect cannot be negative.");
		}
		this.happines_effect = happines_effect;
	}
	//--
	@Override
	public String toString() {
		return getObjectName()+ " - Effect: "+hunger_effect;
	}
}

