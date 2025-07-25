package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The FileIO provides simple file input/output operations that serve as
 * hands-on practice on Unit Testing.
 *
 * @author agkortzis
 * @version 1.0
 * @since 2020-04-06
 */

public class FileIO {

//	protected BufferedReader createReader(File file) throws IOException {
//		return new BufferedReader(new FileReader(file));
//	}

	/**
	 * Reads a file that contains numbers line by line
	 * and returns an array of the integers found in the file.
	 * @param filepath the file that contains the numbersmvn
	 * @return an array of numbers
	 * @exception IllegalArgumentException when the given file does not exist
	 * @exception IllegalArgumentException when the given file is empty
	 * @exception NumberFormatException for checking invalid entries
	 * @exception IOException when an IO interruption occurs (not required to be tested)
	 */


	public int[] readFile(String filepath)
	{
		System.out.println("==> readFile called with: " + filepath);
		File file = new File(filepath);
		if (!file.exists()) {
			System.out.println("==> File does not exist");
			throw new IllegalArgumentException("Input file does not exist");
		}

		List<Integer> numbersList = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
//				System.out.println("==> Line: " + line);
				try {
					int number = Integer.parseInt(line);
					numbersList.add(number);
				} catch (NumberFormatException e) {
					System.out.println("==> Invalid number skipped: " + line);
				}
			}
		} catch (IOException e) {
			System.out.println("==> IOException occurred");
			e.printStackTrace();
			throw new IllegalArgumentException("Could not read file due to IO error: " + filepath, e);
		}

		if (numbersList.size() == 0) {
			System.out.println("==> File was empty");
			throw new IllegalArgumentException("Given file is empty");
		}

		System.out.println("==> Numbers parsed: " + numbersList.size());
		return numbersList.stream().mapToInt(i -> i).toArray();
	}

}
