package math;

import io.FileIO;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ArrayOperationsTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testFindPrimesInFile() {
        // Arrange
        FileIO mockFileIO = mock(FileIO.class);
        MyMath mockMyMath = mock(MyMath.class);
        ArrayOperations operations = new ArrayOperations();

        String dummyPath = "dummy.txt";

        int[] fileData = {2, 4, 5, 6, 7};
        when(mockFileIO.readFile(dummyPath)).thenReturn(fileData);

        when(mockMyMath.isPrime(2)).thenReturn(true);
        when(mockMyMath.isPrime(4)).thenReturn(false);
        when(mockMyMath.isPrime(5)).thenReturn(true);
        when(mockMyMath.isPrime(6)).thenReturn(false);
        when(mockMyMath.isPrime(7)).thenReturn(true);

        // Act
        int[] result = operations.findPrimesInFile(mockFileIO, dummyPath, mockMyMath);

        // Assert
        int[] expected = {2, 5, 7};
        assertArrayEquals(expected, result);
    }
}