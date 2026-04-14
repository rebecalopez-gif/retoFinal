package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Accesory;

/**
 * Clase de pruebas unitarias para la clase Accesory.
 * Realizado por Rebeca.
 * 
 * Se comprueban:
 * - Constructor con parámetros (assertEquals)
 * - toString (assertTrue)
 * - Objeto nulo (assertNull)
 * - Excepción en setter (assertThrows)
 */
public class TestAccesory {

	//assert equals, constructor con parametros
	@Test
	void testConstructorConParametros() {
	    Accesory a = new Accesory("Gorra", 10, 5);

	    assertEquals("Gorra", a.getObjectName());
	    assertEquals(10, a.getHapiness_effect());
	    assertEquals(5, a.getEnergy_effect());
	}

	//assert true, tostring
	@Test
	void testToString() {
	    Accesory a = new Accesory("Gorra", 5, 3);
	    String result = a.toString();

	    assertTrue(result.contains("Gorra"));
	}

	//assert null
	@Test
	void testAccesoryEsNull() {
	    Accesory a = null;
	    assertNull(a);
	}

	
	//ASSERT THROWS
	@Test
	void testSetHapinessEffectLanzaExcepcion() {
	    Accesory a = new Accesory();

	    assertThrows(IllegalArgumentException.class, () -> {
	        a.setHapiness_effect(-10); //al ser negativo salta la excepcion
	    });
	}

}
