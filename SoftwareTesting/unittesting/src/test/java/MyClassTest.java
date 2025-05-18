import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyClassTest {

    MyClass myClass ;
    @Before
    public void setUp() throws Exception {
        myClass = new MyClass();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void div() {
        float expected = 2.0F;
        float actual = myClass.div(10,5);
        assertEquals(expected,actual,1e-3);
    }
//    @Test(expected = IllegalArgumentException.class)
//    public void divided_by_zero_exception(){
//
//    }

}