package math;

import static org.junit.Assert.*;

public class MyMathTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void factorial()
    {
        int expected = 24;
        int actual = (new MyMath()).factorial(4);
        assertEquals(expected, actual);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void factorialNegative() {
        (new MyMath()).factorial(-1);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void factorialgaterTwelve(){
        (new MyMath()).factorial(13);
    }
    @org.junit.Test
    public void isPrime() {
        boolean expected = true;
        boolean actual = new MyMath().isPrime(5);
        assertEquals(expected, actual);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void  invalidInput() {
        (new MyMath()).factorial(-1);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void isPrimeInvalidInput() {
        new MyMath().isPrime(1); // This will trigger the line with the exception
    }

    @org.junit.Test
    public void isNotPrime() {
        boolean expected = false;
        boolean actual = new MyMath().isPrime(6);  // 6 is divisible by 2
        assertEquals(expected, actual);
    }
    @org.junit.Test
    public void isPrimeTwo() {
        boolean expected = true;
        boolean actual = new MyMath().isPrime(2);
        assertEquals(expected, actual);
    }
    @org.junit.Test
    public void isNotPrimeOdd() {
        boolean expected = false;
        boolean actual = new MyMath().isPrime(9);  // 9 is divisible by 3
        assertEquals(expected, actual);
    }

}