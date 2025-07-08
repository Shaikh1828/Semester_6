package math;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {

    private ArithmeticOperations a;

    @org.junit.Before
    public void setUp() throws Exception {
        a = new ArithmeticOperations();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void divide1() {
        double actual = a.divide(10,5);
        double expected = 2.0;
        double delta = 0.001;
        assertEquals(expected, actual, delta);
    }

    @org.junit.Test(expected = ArithmeticException.class)
    public void divide2() {
        double actual = a.divide(100000,0);
        double expected = 100000000;
        double delta = 0.001;
        assertEquals(expected, actual, delta);
    }

    @org.junit.Test
    public void multiply1() {
        double actual=a.multiply(100,200);
        double expected=20000;
        double delta=0.001;
        assertEquals(expected, actual, delta);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void multiply2() {
        double actual=a.multiply(100000000,200000000);
        double expected=20000000;
        double delta=0.001;
        assertEquals(expected, actual, delta);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void multiply3() {
        double actual=a.multiply(-100,200);
        double expected=-20000;
        double delta=0.001;
        assertEquals(expected, actual, delta);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void multiply4() {
        double actual=a.multiply(100,-200);
        double expected=-20000;
        double delta=0.001;
        assertEquals(expected, actual, delta);
    }
}