package modelo;

public class Food extends Objectos{
	private int hunger_effect;
	
	public Food() {
		super();
		this.hunger_effect=0;
	}
	
	public Food(String objectName,int hunger_effect) {
		super(objectName);
		this.hunger_effect=hunger_effect;
	}

	public int getHunger_effect() {
		return hunger_effect;
	}

	public void setHunger_effect(int hunger_effect) {
		this.hunger_effect = hunger_effect;
	}

	@Override
	public String toString() {
		return getObjectName()+ " - Effect: "+hunger_effect;
	}
	
	
}
