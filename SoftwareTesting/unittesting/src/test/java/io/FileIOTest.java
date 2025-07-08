package io;

import static org.junit.Assert.*;
import java.net.URL;
import java.nio.file.Paths;


public class FileIOTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
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
    @org.junit.Test
    public void testReadFileWithInvalidContent() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("grades_invalid.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();
        int[] result = fileIO.readFile(path);
        int[] expected = {3, 9, 2, 10, 8, 0, 3};
        assertArrayEquals(expected, result);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testReadEmptyFileThrowsException() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("empty_file.txt");

        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();

        fileIO.readFile(path);
    }
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testReadFileDoesNotExistThrowsException() {
        FileIO fileIO = new FileIO();
        fileIO.readFile("non_existent_file.txt");
    }
    @org.junit.Test
    public void testIOExceptionFromUnreadableFile() throws Exception {
        java.io.File tempFile = java.io.File.createTempFile("unreadable", ".txt");
        tempFile.setReadable(false); // Might not work on all OS
        tempFile.deleteOnExit();

        FileIO fileIO = new FileIO();

        try {
            fileIO.readFile(tempFile.getAbsolutePath());
        } catch (IllegalArgumentException e) {
            // Expected due to empty or unreadable
        }
    }


}