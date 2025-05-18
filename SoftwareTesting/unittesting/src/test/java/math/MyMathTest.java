package math;

import org.junit.After;
import org.junit.Before;
//import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {
    MyMath check = new MyMath();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void factorialCheck() {
        int expected = 120;
        int actual = check.factorial(5);
        assertEquals(expected, actual);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void invalidNegInputCheck() {
        check.factorial(-5);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void invalidGreaterInputCheck() {
        check.factorial(15);
    }


    @org.junit.Test
    public void isPrimeCheck() {
        boolean expected = true;
        boolean actual = check.isPrime(13);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void invalidPrimeInputCheck() {
        check.isPrime(-5);
    }
}