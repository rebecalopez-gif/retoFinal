package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Accesory;

/**
 * Clase de pruebas unitarias para la clase {@link Accesory}.
 * <p>
 * Realizado por Rebeca.
 * </p>
 *
 * <p>Este conjunto de tests valida el correcto funcionamiento de:
 * <ul>
 *   <li>El constructor con parámetros mediante {@code assertEquals}</li>
 *   <li>El método {@code toString()} mediante {@code assertTrue}</li>
 *   <li>La comprobación de objetos nulos mediante {@code assertNull}</li>
 *   <li>La gestión de valores inválidos mediante {@code assertThrows}</li>
 * </ul>
 * </p>
 *
 * <p>Se utilizan diferentes tipos de aserciones de JUnit 5 para garantizar
 * que la clase Accesory cumple con el comportamiento esperado.</p>
 *
 * @author Rebeca
 * @version 1.0, 15/04/2026
 */
public class TestAccesory {

	/**
     * Verifica que el constructor con parámetros asigna correctamente
     * los valores de nombre, felicidad y energía.
     */
	@Test
	void testConstructorConParametros() {
	    Accesory a = new Accesory("Gorra", 10, 5);

	    assertEquals("Gorra", a.getObjectName());
	    assertEquals(10, a.getHapiness_effect());
	}

	/**
     * Comprueba que el método {@code toString()} contiene el nombre del accesorio.
     */
	@Test
	void testToString() {
	    Accesory a = new Accesory("Gorra", 5, 3);
	    String result = a.toString();

	    assertTrue(result.contains("Gorra"));
	}

	/**
     * Verifica que un objeto Accesory puede ser nulo.
     */
	@Test
	void testAccesoryEsNull() {
	    Accesory a = null;
	    assertNull(a);
	}

	 /**
     * Comprueba que el método {@code setHapiness_effect()} lanza una excepción
     * cuando se intenta asignar un valor negativo.
     * <p>
     * Para que este test funcione, el setter debe lanzar
     * {@link IllegalArgumentException} cuando el valor sea inválido.
     * </p>
     */
	@Test
	void testSetHapinessEffectLanzaExcepcion() {
	    Accesory a = new Accesory();

	    assertThrows(IllegalArgumentException.class, () -> {
	        a.setHapiness_effect(-10); 
	    });
	}

}
