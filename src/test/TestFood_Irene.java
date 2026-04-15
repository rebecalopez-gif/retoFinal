package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modelo.Food;

/**
 * Clase de pruebas unitarias para la clase {@link Food}.
 *
 * <p>Este conjunto de tests valida el correcto funcionamiento de:
 * <ul>
 *   <li>La creación de objetos mediante el constructor con parámetros ({@code assertEquals})</li>
 *   <li>La verificación de condiciones lógicas ({@code assertTrue})</li>
 *   <li>La asignación de valores nulos ({@code assertNull})</li>
 *   <li>La gestión de valores inválidos mediante excepciones ({@code assertThrows})</li>
 * </ul>
 * </p>
 *
 * <p>Se emplean diferentes tipos de aserciones de JUnit 5 para garantizar
 * que la clase {@code Food} se comporta según lo esperado en distintos escenarios.</p>
 *
 * <p>Realizado por Irene.</p>
 *
 * @version 1.0, 15/04/2026
 */
public class TestFood_Irene {

	 /**
     * Verifica que el constructor con parámetros asigna correctamente
     * los valores de nombre, hambre, energía y felicidad.
     */
    @Test
    void testAssertEquals() {
        Food f = new Food("Apple", 10, 5, 3);
        assertEquals("Apple", f.getObjectName());
        assertEquals(10, f.getHunger_effect());
        assertEquals(5, f.getEnergy_effect());
        assertEquals(3, f.getHappines_effect());
    }

    /**
     * Comprueba que el método {@code getEnergy_effect()} devuelve un valor positivo.
     */
    @Test
    void testAssertTrue() {
        Food f = new Food("Meat", 20, 15, 10);
        assertTrue(f.getEnergy_effect() > 0);
    }

    /**
     * Verifica que el constructor permite asignar un nombre nulo
     * y que el getter lo devuelve correctamente.
     */
    @Test
    void testAssertNull() {
        Food f = new Food(null, 7);
        assertNull(f.getObjectName());
    }

    /**
     * Comprueba que el método {@code setHappines_effect()} lanza una excepción
     * cuando se intenta asignar un valor negativo.
     *
     * <p>Para que este test funcione, el setter debe lanzar
     * {@link IllegalArgumentException} cuando el valor sea inválido.</p>
     */
    @Test
    void testAssertThrows() {
        Food f = new Food("Bread", 5, 2, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            f.setHappines_effect(-5);
        });
    }
}
