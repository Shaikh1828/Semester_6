package math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {

    private MyMath myMath;

    @Before
    public void setUp() {
        myMath = new MyMath();
    }

    // Test factorial with valid inputs
    @Test
    public void testFactorialValidInputs() {
        assertEquals(1, myMath.factorial(0));
        assertEquals(1, myMath.factorial(1));
        assertEquals(120, myMath.factorial(5));
        assertEquals(479001600, myMath.factorial(12));
    }

    // Test factorial with invalid inputs
    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegativeInput() {
        myMath.factorial(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialTooLargeInput() {
        myMath.factorial(13);
    }

    // Test isPrime returns false for numbers less than 2
    @Test
    public void testIsPrimeForNumbersLessThanTwo() {
        assertFalse(myMath.isPrime(-10));
        assertFalse(myMath.isPrime(0));
        assertFalse(myMath.isPrime(1));
    }

    // Test isPrime returns true for prime numbers
    @Test
    public void testIsPrimeForPrimeNumbers() {
        assertTrue(myMath.isPrime(2));
        assertTrue(myMath.isPrime(3));
        assertTrue(myMath.isPrime(5));
        assertTrue(myMath.isPrime(7));
        assertTrue(myMath.isPrime(11));
    }

    // Test isPrime returns false for non-prime numbers >= 2
    @Test
    public void testIsPrimeForNonPrimeNumbers() {
        assertFalse(myMath.isPrime(4));
        assertFalse(myMath.isPrime(6));
        assertFalse(myMath.isPrime(8));
        assertFalse(myMath.isPrime(9));
        assertFalse(myMath.isPrime(10));
    }
}
