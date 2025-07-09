//package math;
//
//import static org.junit.Assert.*;
//
//public class MyMathTest {
//
//    @org.junit.Before
//    public void setUp() throws Exception {
//    }
//
//    @org.junit.After
//    public void tearDown() throws Exception {
//    }
//
//    @org.junit.Test
//    public void factorial()
//    {
//        int expected = 24;
//        int actual = (new MyMath()).factorial(4);
//        assertEquals(expected, actual);
//    }
//    @org.junit.Test(expected = IllegalArgumentException.class)
//    public void factorialNegative() {
//        (new MyMath()).factorial(-1);
//    }
//    @org.junit.Test(expected = IllegalArgumentException.class)
//    public void factorialgaterTwelve(){
//        (new MyMath()).factorial(13);
//    }
//    @org.junit.Test
//    public void isPrime() {
//        boolean expected = true;
//        boolean actual = new MyMath().isPrime(5);
//        assertEquals(expected, actual);
//    }
//    @org.junit.Test(expected = IllegalArgumentException.class)
//    public void  invalidInput() {
//        (new MyMath()).factorial(-1);
//    }
//    @org.junit.Test(expected = IllegalArgumentException.class)
//    public void isPrimeInvalidInput() {
//        new MyMath().isPrime(1); // This will trigger the line with the exception
//    }
//
//    @org.junit.Test
//    public void isNotPrime() {
//        boolean expected = false;
//        boolean actual = new MyMath().isPrime(6);  // 6 is divisible by 2
//        assertEquals(expected, actual);
//    }
//    @org.junit.Test
//    public void isPrimeTwo() {
//        boolean expected = true;
//        boolean actual = new MyMath().isPrime(2);
//        assertEquals(expected, actual);
//    }
//    @org.junit.Test
//    public void isNotPrimeOdd() {
//        boolean expected = false;
//        boolean actual = new MyMath().isPrime(9);  // 9 is divisible by 3
//        assertEquals(expected, actual);
//    }
//
//
//}

package math;

import static org.junit.Assert.*;

public class MyMathTest {

    @org.junit.Before
    public void setUp() throws Exception {}

    @org.junit.After
    public void tearDown() throws Exception {}

    @org.junit.Test
    public void testFactorialValidInput() {
        assertEquals(24, new MyMath().factorial(4));
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testFactorialNegativeInput() {
        new MyMath().factorial(-1);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testFactorialAboveLimit() {
        new MyMath().factorial(13);
    }

    @org.junit.Test
    public void testFactorialEdgeLimitValid() {
        assertEquals(479001600, new MyMath().factorial(12)); // 12! is valid
    }

    @org.junit.Test
    public void testIsPrimeTrue() {
        assertTrue(new MyMath().isPrime(5));
    }

    @org.junit.Test
    public void testIsPrimeTwo() {
        assertTrue(new MyMath().isPrime(2)); // smallest prime
    }

    @org.junit.Test
    public void testIsNotPrimeEven() {
        assertFalse(new MyMath().isPrime(6)); // divisible by 2
    }

    @org.junit.Test
    public void testIsNotPrimeOdd() {
        assertFalse(new MyMath().isPrime(9)); // divisible by 3
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testIsPrimeWithOne() {
        new MyMath().isPrime(1); // triggers exception
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testIsPrimeWithZero() {
        new MyMath().isPrime(0); // not a prime, should throw
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testIsPrimeWithNegative() {
        new MyMath().isPrime(-7); // negative input
    }

    @org.junit.Test
    public void testFactorialAtUpperLimitBoundary() {
        MyMath math = new MyMath();
        assertEquals(479001600, math.factorial(12));  // This confirms upper bound is accepted
        try {
            math.factorial(13);  // This should fail
            fail("Expected IllegalArgumentException for input > 12");
        } catch (IllegalArgumentException e) {
            assertEquals("number should be 0 or above and 12 or below", e.getMessage());
        }
    }

    @org.junit.Test
    public void testIsPrimeWithEdgeBoundary() {
        // 29 is prime, but would fail if loop ends before 29/2 = 14
        assertTrue(new MyMath().isPrime(29));
    }

    @org.junit.Test
    public void testIsNotPrimeWithLateDivisor() {
        // 49 = 7 * 7, but 7 > 49/2 = 24
        assertFalse(new MyMath().isPrime(49));
    }

    @org.junit.Test
    public void testFactorialZero() {
        assertEquals(1, new MyMath().factorial(0)); // 0! = 1
    }

    @org.junit.Test
    public void testIsNotPrimeWithDivisorAtHalf() {
        assertFalse("98 is not prime, divisible by 49", new MyMath().isPrime(98));
    }

    @org.junit.Test
    public void testIsPrimeSmallPrime() {
        assertTrue(new MyMath().isPrime(3));
    }

    @org.junit.Test
    public void testIsNotPrimeFour() {
        assertFalse(new MyMath().isPrime(4));
    }


}
