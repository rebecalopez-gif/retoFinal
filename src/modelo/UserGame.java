package modelo;

import java.time.LocalDate;

/**
 * La clase UserGame representa a un usuario dentro del juego.
 * 
 * Contiene la información básica necesaria para la autenticación y
 * para aplicar restricciones relacionadas con la edad del jugador.
 *
 * Atributos principales:
 * <ul>
 *   <li><b>userName</b>: nombre del usuario.</li>
 *   <li><b>passwordUser</b>: contraseña del usuario.</li>
 *   <li><b>birthDate</b>: año de nacimiento del usuario.</li>
 * </ul>
 *
 * Esta clase se utiliza tanto para el inicio de sesión como para la creación
 * de nuevos usuarios.
 * 
 * @author Unai
 * @version 1.0, 16/04/2026
 */
public class UserGame {
	/** Nombre del usuario del juego. */
	private String userName;
	/** Contraseña asociada al usuario. */
	private String passwordUser;
	/** Año de nacimiento del usuario. */
	private int birthDate;

	/**
	 * Constructor principal que crea un usuario con nombre, contraseña y año de nacimiento.
	 * 
	 * Valida que el año de nacimiento no sea negativo.
	 *
	 * @param userName nombre del usuario.
	 * @param passwordUser contraseña del usuario.
	 * @param birthDate año de nacimiento del usuario.
	 * @throws IllegalArgumentException si el año de nacimiento es negativo.
	 */
	// el if para lo de EDE-----------------------------------------
	public UserGame(String userName,String passwordUser,int birthDate) {
		if (birthDate < 0) {
			throw new IllegalArgumentException("Fecha inválida");
		}
		this.userName = userName;
		this.passwordUser = passwordUser;
		this.birthDate = birthDate;
	}
	//----------------------------------------------------------------------

	/**
	 * Constructor alternativo utilizado para el inicio de sesión,
	 * donde no es necesario proporcionar el año de nacimiento.
	 *
	 * @param userName nombre del usuario.
	 * @param passwordUser contraseña del usuario.
	 */
	public UserGame(String userName,String passwordUser) {
		this.userName=userName;
		this.passwordUser=passwordUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public int getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(int birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Devuelve una representación en texto del usuario,
	 * incluyendo nombre, contraseña y año de nacimiento.
	 *
	 * @return cadena con los datos del usuario.
	 */
	@Override
	public String toString() {
		return "UserGame [userName=" + userName + ", passwordUser=" + passwordUser + ", birthDate=" + birthDate + "]";
	}


}