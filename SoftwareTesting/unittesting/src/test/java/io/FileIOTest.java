//package io;
//
//
//import org.junit.Before;
//import org.junit.After;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.net.URL;
//import java.nio.file.Paths;
//
//
//public class FileIOTest {
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void testReadFileWithValidContent() throws Exception{
////        System.out.println(">> Running <testReadFileWithValidContent>");
//
//        FileIO fileIO = new FileIO();
//        URL resourceUrl = getClass().getClassLoader().getResource("grades_valid.txt");
//        System.out.println("Path: " + resourceUrl);
//        assertNotNull("File not found", resourceUrl);
//        String path = Paths.get(resourceUrl.toURI()).toString();
//
//        System.out.println("File path: " + path);
//        int[] result = fileIO.readFile(path);
//        int[] expected = {3, 9, 0, 2, 10, 9, 3, 8, 0, 3};
//        assertArrayEquals(expected, result);
//    }
//
//    @Test
//    public void testReadFileWithInvalidContent() throws Exception {
////        System.out.println(">> Running <testReadFileWithInvalidContent>");
//        FileIO fileIO = new FileIO();
//        URL resourceUrl = getClass().getClassLoader().getResource("grades_invalid.txt");
//        assertNotNull("File not found", resourceUrl);
//        String path = Paths.get(resourceUrl.toURI()).toString();
//        int[] result = fileIO.readFile(path);
//        int[] expected = {3, 9, 2, 10, 8, 0, 3};
//        assertArrayEquals(expected, result);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testReadEmptyFileThrowsException() throws Exception {
////        System.out.println(">> Running <testReadEmptyFileThrowsException>");
//        FileIO fileIO = new FileIO();
//        URL resourceUrl = getClass().getClassLoader().getResource("empty_file.txt");
//
//        assertNotNull("File not found", resourceUrl);
//        String path = Paths.get(resourceUrl.toURI()).toString();
//
//        fileIO.readFile(path);
//    }
////    @org.junit.Test(expected = IllegalArgumentException.class)
////    public void testReadFileDoesNotExistThrowsException() {
////        FileIO fileIO = new FileIO();
////        fileIO.readFile("non_existent_file.txt");
////    }
//    @Test
//    public void testIOExceptionFromUnreadableFile() throws Exception {
////        System.out.println(">> Running <testIOExceptionFromUnreadableFile>");
//        java.io.File tempFile = java.io.File.createTempFile("unreadable", ".txt");
//        tempFile.setReadable(false); // Might not work on all OS
//        tempFile.deleteOnExit();
//
//        FileIO fileIO = new FileIO();
//
//        try {
//            fileIO.readFile(tempFile.getAbsolutePath());
//        } catch (IllegalArgumentException e) {
//            // Expected due to empty or unreadable
//        }
//    }
//
//    @Test
//    public void testFileWithOnlyInvalidLines() throws Exception {
////        System.out.println(">> Running <testFileWithOnlyInvalidLines>");
//        FileIO fileIO = new FileIO();
//        URL resourceUrl = getClass().getClassLoader().getResource("only_invalid.txt");
//        assertNotNull("File not found", resourceUrl);
//        String path = Paths.get(resourceUrl.toURI()).toString();
//
//        try {
//            fileIO.readFile(path);
//            fail("Expected IllegalArgumentException for empty parsed list");
//        } catch (IllegalArgumentException e) {
//            assertEquals("Given file is empty", e.getMessage());
//        }
//    }
//
//    @Test
//    public void testNonExistentFileMessage() {
////        System.out.println(">> Running <testNonExistentFileMessage>");
//        FileIO fileIO = new FileIO();
//        try {
//            fileIO.readFile("non_existent_file.txt");
//            fail("Should throw IllegalArgumentException");
//        } catch (IllegalArgumentException e) {
//            assertEquals("Input file does not exist", e.getMessage());
//        }
//    }
//
//    @Test
//    public void testFileWithBlankLinesOnly() throws Exception {
////        System.out.println(">> Running <testFileWithBlankLinesOnly>");
//        FileIO fileIO = new FileIO();
//        URL resourceUrl = getClass().getClassLoader().getResource("blank_lines.txt");
//        assertNotNull("File not found", resourceUrl);
//        String path = Paths.get(resourceUrl.toURI()).toString();
//
//        try {
//            fileIO.readFile(path);
//            fail("Expected IllegalArgumentException for blank file lines");
//        } catch (IllegalArgumentException e) {
//            assertEquals("Given file is empty", e.getMessage());
//        }
//    }
//
//    @Test
//    public void testIOExceptionTriggered() {
////        System.out.println(">> Running <testIOExceptionTriggered>");
//        FileIO fileIO = new FileIO();
//        try {
//            fileIO.readFile("path/to/unreadable.txt");
//            fail("Expected IOException to be caught");
//        } catch (IllegalArgumentException e) {
//            // You’ll likely get "Given file is empty" due to no readable lines
//            // which proves the exception was caught
//        }
//    }
//
//    @Test
//    public void testIOExceptionIsCaught() throws Exception {
////        System.out.println(">> Running <testIOExceptionIsCaught>");
//        // Create an unreadable file (must be on Linux/macOS)
//        File tempFile = new File("test_io_exception.txt");
//        tempFile.createNewFile();
//        tempFile.setReadable(false);  // Ensure unreadable
//        tempFile.setWritable(true);
//        tempFile.deleteOnExit();
//
//        FileIO fileIO = new FileIO();
//        try {
//            fileIO.readFile(tempFile.getAbsolutePath());
//        } catch (IllegalArgumentException e) {
//            // This is expected due to empty file (IOException already printed)
//        } finally {
//            tempFile.setReadable(true);  // Clean up if needed
//            tempFile.delete();
//        }
//    }
//
//    @Test
//    public void debugResourceLoading() throws Exception {
////        System.out.println(">> Running <debugResourceLoading>");
//        URL resource = getClass().getClassLoader().getResource("grades_valid.txt");
//        assertNotNull("grades_valid.txt not found!", resource);
//        System.out.println("grades_valid.txt Path: " + resource.getPath());
//    }
//
//    @Test
//    public void testRealIOExceptionThrown() throws Exception {
////        System.out.println(">> Running <testRealIOExceptionThrown>");
//        FileIO fileIO = new FileIO();
//
//        File file = new File("locked_file.txt");
//        file.createNewFile();
//        file.setWritable(false); // Try making it unwritable
//        file.setReadable(false); // For extra precaution
//
//        try {
//            fileIO.readFile(file.getAbsolutePath());
//        } catch (IllegalArgumentException e) {
//            // Success if exception is from IO failure, not parsing
//            assertTrue(e.getMessage().contains("empty") || e.getMessage().contains("error"));
//        } finally {
//            file.setWritable(true);
//            file.setReadable(true);
//            file.delete();
//        }
//    }
//
//
//}

package io;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class FileIOTest {
    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() throws Exception {
        systemOutRule.clearLog();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReadFileWithValidContent() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("grades_valid.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();

        int[] result = fileIO.readFile(path);
        int[] expected = {3, 9, 0, 2, 10, 9, 3, 8, 0, 3};
        assertArrayEquals(expected, result);
        assertTrue(systemOutRule.getLog().contains("==> Numbers parsed:"));
    }

    @Test
    public void testReadFileWithInvalidContent() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("grades_invalid.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();

        int[] result = fileIO.readFile(path);
        int[] expected = {3, 9, 2, 10, 8, 0, 3};
        assertArrayEquals(expected, result);
        assertTrue(systemOutRule.getLog().contains("==> Invalid number skipped:"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadEmptyFileThrowsException() throws Exception {
        FileIO fileIO = new FileIO();
        URL resourceUrl = getClass().getClassLoader().getResource("empty_file.txt");
        assertNotNull("File not found", resourceUrl);
        String path = Paths.get(resourceUrl.toURI()).toString();

        fileIO.readFile(path);
    }

    @Test
    public void testNonExistentFileMessage() {
        FileIO fileIO = new FileIO();
        systemOutRule.clearLog();
        try {
            fileIO.readFile("non_existent_file.txt");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Input file does not exist", e.getMessage());
            assertTrue(systemOutRule.getLog().contains("==> File does not exist"));
        }
    }

    @Test
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
            assertTrue(systemOutRule.getLog().contains("==> Invalid number skipped:"));
        }
    }

    @Test
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



    @Test
    public void testReadFileWithSingleLine() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("42\n");
        }

        FileIO fileIO = new FileIO();
        int[] result = fileIO.readFile(tempFile.getAbsolutePath());
        assertArrayEquals(new int[]{42}, result);
    }
}