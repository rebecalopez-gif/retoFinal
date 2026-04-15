package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Creature;
import modelo.UserGame;

/**
 * Clase de pruebas unitarias para la clase {@link UserGame}.
 * 
 * <p>Este conjunto de tests verifica el correcto funcionamiento de:
 * <ul>
 *   <li>El método {@code toString()}</li>
 *   <li>La obtención de valores mediante getters</li>
 *   <li>La asignación de valores nulos</li>
 *   <li>La gestión de excepciones en el constructor</li>
 * </ul>
 * 
 * <p>Se utilizan diferentes tipos de aserciones:
 * <ul>
 *   <li>{@code assertEquals}</li>
 *   <li>{@code assertTrue}</li>
 *   <li>{@code assertNull}</li>
 *   <li>{@code assertThrows}</li>
 * </ul>
 * 
 * @author Unai
 * @version 1.0, 15/04/2026
 */

public class TestUserGame {
	
	/**
     * Verifica que el método {@code toString()} devuelve la cadena esperada
     * con los valores correctos del objeto.
     */
	@Test
	void testToString() {
		UserGame u = new UserGame("Ale", "xyz", 2010);
		String esperado = "UserGame [userName=Ale, passwordUser=xyz, birthDate=2010]";
		assertEquals(esperado, u.toString());
	}

	/**
     * Comprueba que el getter {@code getUserName()} devuelve el nombre correcto.
     */
	@Test
	void testAssertTrue() {
		UserGame u = new UserGame("Pepe", "111", 2000);

		assertTrue(u.getUserName().equals("Pepe"));
	}

	/**
     * Verifica que el método {@code setUserName()} permite asignar un valor nulo
     * y que el getter lo devuelve correctamente.
     */
	@Test
	void testSetterNombreNull() {
		UserGame u = new UserGame("Pepe", "111", 2000);
		u.setUserName(null);

		assertNull(u.getUserName());
	}

	 /**
     * Comprueba que el constructor lanza una excepción cuando se introduce
     * un valor inválido para {@code birthDate}.
     * 
     * <p>Para que este test funcione, el constructor debe lanzar
     * {@link IllegalArgumentException} cuando el año sea negativo.</p>
     */
	@Test
	void testAssertThrows() {
		// birthDate negativo debería lanzar excepción
		assertThrows(IllegalArgumentException.class, () -> {
			UserGame u = new UserGame("Ale", "1542", -10);
		});
	}
}
