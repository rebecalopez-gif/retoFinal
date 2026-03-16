package modelo;

public class Equip {
	private int id;
	private int quantity;
	private boolean equipped;
	
	public Equip() {
		this.quantity=0;
		this.equipped=false;
	}
	
	public Equip(int quantity,boolean equipped) {
		this.quantity=quantity;
		this.equipped=equipped;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isEquipped() {
		return equipped;
	}

	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}

	@Override
	public String toString() {
		return "Equip [id=" + id + ", quantity=" + quantity + ", equipped=" + equipped + "]";
	}
	
	
}
