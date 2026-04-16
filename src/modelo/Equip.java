package modelo;

/**
 * La clase Equip representa la información de un objeto equipado por una criatura.
 * 
 * Cada instancia almacena:
 * <ul>
 *   <li><b>id</b>: identificador del objeto equipado.</li>
 *   <li><b>quantity</b>: cantidad disponible del objeto.</li>
 *   <li><b>equipped</b>: indica si el objeto está actualmente equipado.</li>
 * </ul>
 *
 * Esta clase se utiliza para gestionar el inventario y el estado de equipamiento
 * de accesorios u objetos dentro del juego.
 *
 * @author Galder
 * @version 1.0
 * @since 2026-04-16
 */
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

	/**
	 * Devuelve una representación en texto del objeto equipado,
	 * mostrando su id, cantidad y estado de equipamiento.
	 *
	 * @return cadena con el formato "Equip [id=..., quantity=..., equipped=...]".
	 */
	@Override
	public String toString() {
		return "Equip [id=" + id + ", quantity=" + quantity + ", equipped=" + equipped + "]";
	}


}
