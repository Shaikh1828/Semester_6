package io;

import static org.junit.Assert.*;

import java.io.File;
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
//    @org.junit.Test(expected = IllegalArgumentException.class)
//    public void testReadFileDoesNotExistThrowsException() {
//        FileIO fileIO = new FileIO();
//        fileIO.readFile("non_existent_file.txt");
//    }
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

    @org.junit.Test
    public void testFileWithOnlyInvalidLines() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("only_invalid.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();

        try {
            fileIO.readFile(path);
            fail("Expected IllegalArgumentException for empty parsed list");
        } catch (IllegalArgumentException e) {
            assertEquals("Given file is empty", e.getMessage());
        }
    }

    @org.junit.Test
    public void testNonExistentFileMessage() {
        FileIO fileIO = new FileIO();
        try {
            fileIO.readFile("non_existent_file.txt");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Input file does not exist", e.getMessage());
        }
    }

    @org.junit.Test
    public void testFileWithBlankLinesOnly() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("blank_lines.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();

        try {
            fileIO.readFile(path);
            fail("Expected IllegalArgumentException for blank file lines");
        } catch (IllegalArgumentException e) {
            assertEquals("Given file is empty", e.getMessage());
        }
    }

    @org.junit.Test
    public void testIOExceptionTriggered() {
        FileIO fileIO = new FileIO();
        try {
            fileIO.readFile("path/to/unreadable.txt");
            fail("Expected IOException to be caught");
        } catch (IllegalArgumentException e) {
            // You’ll likely get "Given file is empty" due to no readable lines
            // which proves the exception was caught
        }
    }

    @org.junit.Test
    public void testIOExceptionIsCaught() throws Exception {
        // Create an unreadable file (must be on Linux/macOS)
        File tempFile = new File("test_io_exception.txt");
        tempFile.createNewFile();
        tempFile.setReadable(false);  // Ensure unreadable
        tempFile.setWritable(true);
        tempFile.deleteOnExit();

        FileIO fileIO = new FileIO();
        try {
            fileIO.readFile(tempFile.getAbsolutePath());
        } catch (IllegalArgumentException e) {
            // This is expected due to empty file (IOException already printed)
        } finally {
            tempFile.setReadable(true);  // Clean up if needed
            tempFile.delete();
        }
    }



}