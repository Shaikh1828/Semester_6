package math;

import io.FileIO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayOperationsTest {

    private ArrayOperations arrayOperations;
    private MyMath myMath;
    private FileIO fileio;

    @Before
    public void setUp() throws Exception {
        arrayOperations = new ArrayOperations();
        myMath = new MyMath();
        fileio = new FileIO();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findPrimesInFile1() {
        int[] actual= arrayOperations.findPrimesInFile(fileio, "src/test/resources/grades_valid.txt", myMath);
        int[] expected= {2,3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findPrimesInFile2() {
        int[] actual= arrayOperations.findPrimesInFile(fileio, "src/test/resources/primes.txt", myMath);
        int[] expected= {2,3,5,7,11,13,17,19};
        assertArrayEquals(expected, actual);
    }

}