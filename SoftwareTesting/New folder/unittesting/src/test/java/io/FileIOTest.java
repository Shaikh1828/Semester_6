package io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileIOTest {

    private FileIO fileIO;

    @Before
    public void setUp() throws Exception {
        fileIO = new FileIO();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readFile1() {
        int[] actual=fileIO.readFile("src/test/resources/grades_valid.txt");
        int[] expected={3,9,0,2,10,9,3,8,0,3};
        assertArrayEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void readFile2() {
        int[] actual=fileIO.readFile("src/test/resources/empty_file.txt");
        int[] expected={};
        assertArrayEquals(expected, actual);
    }


    @Test(expected = NumberFormatException.class)
    public void readFile3() {
        int[] actual=fileIO.readFile("src/test/resources/grades_invalid.txt");
        int[] expected={3,9,2,10,8,0,3};
        assertArrayEquals(expected, actual);
    }


    @Test(expected = IllegalArgumentException.class)
    public void readFile4() {
        int[] actual=fileIO.readFile("mpty_file.txt");
        int[] expected={};
        assertArrayEquals(expected, actual);
    }


}