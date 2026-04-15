package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modelo.Food;

public class TestFood_Irene {

    @Test
    void testAssertEquals() {
        Food f = new Food("Apple", 10, 5, 3);

        assertEquals("Apple", f.getObjectName());
        assertEquals(10, f.getHunger_effect());
        assertEquals(5, f.getEnergy_effect());
        assertEquals(3, f.getHappines_effect());
    }

    @Test
    void testAssertTrue() {
        Food f = new Food("Meat", 20, 15, 10);
        assertTrue(f.getEnergy_effect() > 0);
    }

    @Test
    void testAssertNull() {
        Food f = new Food(null, 7);

        assertNull(f.getObjectName());
    }

    @Test
    void testAssertThrows() {
        Food f = new Food("Bread", 5, 2, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            f.setHappines_effect(-5);
        });
    }
}
