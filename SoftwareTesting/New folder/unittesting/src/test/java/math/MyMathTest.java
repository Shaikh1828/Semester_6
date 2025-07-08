package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {

    private MyMath myMath;

    @Before
    public void setUp() throws Exception {
        myMath = new MyMath();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void factorial1() {
        int actual = myMath.factorial(6);
        int expected = 720;
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorial2() {
        int actual = myMath.factorial(13);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorial3() {
        int actual = myMath.factorial(-1);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void isPrime1() {
        boolean actual =  myMath.isPrime(99);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void isPrime2() {
        boolean actual =  myMath.isPrime(19);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isPrime3() {
        boolean actual =  myMath.isPrime(1);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isPrime4() {
        boolean actual =  myMath.isPrime(0);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}