package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArithmeticOperationsTest {
    ArithmeticOperations check = new ArithmeticOperations();
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void divideCheck() {
        double expected = 2.0;
        double actual = check.divide(10,5);
        assertEquals(expected,actual,1e-3);
    }

    @Test(expected = ArithmeticException.class)
    public void divided_by_zero_exception(){
        check.divide(10,0);
    }


    @Test
    public void multiplyByPos() {
        int expected = 27;
        int actual = check.multiply(3,9);
        assertEquals(expected,actual);
    }

    @Test
    public void testMultiplyWithNegativeX() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            check.multiply(-1, 5);
        });
        assertEquals("x & y should be >= 0", exception.getMessage());
    }

    @Test
    public void testMultiplyWithNegativeY() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            check.multiply(5, -1);
        });
        assertEquals("x & y should be >= 0", exception.getMessage());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyWithOverflow() {
            check.multiply(Integer.MAX_VALUE, 2);
    }
}