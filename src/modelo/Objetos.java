package modelo;

/**
 * La clase Objetos representa un objeto genérico dentro del juego.
 * 
 * Cada objeto tiene:
 * <ul>
 *   <li><b>cod_object</b>: identificador numérico del objeto.</li>
 *   <li><b>objectName</b>: nombre del objeto.</li>
 * </ul>
 *
 * Esta clase implementa {@link Lista_Objeto}, lo que permite obtener el nombre
 * del objeto mediante el método {@code getName()}.
 *
 * Se utiliza como clase base para otros tipos de objetos del juego, como
 * accesorios o elementos del inventario.
 *
 * @author Rebeca
 * @version 1.0
 * @since 2026-04-16
 */
public class Objetos{
	protected int cod_object;
	protected String objectName;
	
	public Objetos() {
		this.cod_object=0;
		this.objectName="";
	}
	public Objetos(int cod_object) {
		this.cod_object=cod_object;
		this.objectName="";
	}
	public Objetos(int cod_object,String objectName) {
		this.cod_object=cod_object;
		this.objectName=objectName;
	}
	
	public Objetos(String objectName) {
		this.objectName=objectName;
	}
	public int getCod_object() {
		return cod_object;
	}
	public void setCod_object(int cod_object) {
		this.cod_object = cod_object;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * Devuelve una representación en texto del objeto,
	 * mostrando su código y su nombre.
	 *
	 * @return cadena con el formato "codigo - nombre".
	 */
	@Override
	public String toString() {
		return cod_object+" - " + objectName ;
	}
	
}

