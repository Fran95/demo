package demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Lab1Test {
    Lab1 checker = new Lab1(); 

    @Test
    void shouldReturnTrue_WhenInputIsOne() {
        assertTrue(checker.isPowerOfThree(1));
    }

    @Test
    void shouldReturnTrue_WhenInputIsPowerOfThree() {
        assertTrue(checker.isPowerOfThree(27));  // 3^3
    }

    @Test
    void shouldReturnFalse_WhenInputIsNotPowerOfThree() {
        assertFalse(checker.isPowerOfThree(45)); // not a power of 3
    }

    @Test
    void shouldReturnFalse_WhenInputIsZero() {
        assertFalse(checker.isPowerOfThree(0));
    }

    @Test
    void shouldReturnFalse_WhenInputIsNegative() {
        assertFalse(checker.isPowerOfThree(-9));
    }
    
}
