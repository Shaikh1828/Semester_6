package math;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void divide() {
        double expected = 2.0;
        double actual = (new ArithmeticOperations()).divide(10, 5);
        assertEquals(expected, actual, 1e-3);
    }

    @org.junit.Test(expected =  ArithmeticException.class)
    public void dividebyzero(){
        (new ArithmeticOperations()).divide(8, 0);
    }

    @org.junit.Test
    public void multiplywithPositiveNumber() {
        int expected = 20;
        int actual = (new ArithmeticOperations()).multiply(4,5);
        assertEquals(expected,actual);
    }
    @org.junit.Test
    public void testMultiplyWithZero() {
        int expected = 0;
        int actual = (new ArithmeticOperations()).multiply(0,9);
        assertEquals(expected,actual);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testMultiplyWithNegativeXThrowsException() {
        (new ArithmeticOperations()).multiply(-5, 10);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testMultiplyWithNegativeYThrowsException() {
        (new ArithmeticOperations()).multiply(5, -10);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testwithoverflowException() {
        (new ArithmeticOperations()).multiply(Integer.MAX_VALUE, 2);
    }
    @org.junit.Test
    public void testMultiplyWhenYIsZero() {
        int expected = 0;
        int actual = new ArithmeticOperations().multiply(9, 0);
        assertEquals(expected, actual);
    }

//    @org.junit.Test(expected = NullPointerException.class)
//    public void testMultiplyWithNullX() {
//        new ArithmeticOperations().multiply(null, 5);
//    }

}