package io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileIOTest {
    private FileIO fileIO;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReadFileWithValidContent() throws Exception{
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("grades_valid.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();

        System.out.println("File path: " + path);
        int[] result = fileIO.readFile(path);
        int[] expected = {3, 9, 0, 2, 10, 9, 3, 8, 0, 3};
        assertArrayEquals(expected, result);
    }


    @Test
    public void testReadFileWithInvalidContent() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("grades_invalid.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();
        int[] result = fileIO.readFile(path);

        // Should only return valid integers, skipping "a", "9.42", "b"
        int[] expected = {3, 9, 2, 10, 8, 0, 3};
        assertArrayEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadEmptyFileThrowsException() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("empty_file.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();
        fileIO.readFile(path);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadFileDoesNotExistThrowsException() {
        FileIO fileIO = new FileIO();
        fileIO.readFile("non_existent_file.txt");
    }


}