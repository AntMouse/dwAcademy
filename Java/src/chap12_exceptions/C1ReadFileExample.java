package chap12_exceptions;

import java.io.FileReader;
import java.io.IOException;

public class C1ReadFileExample {

	public static void main(String[] args) {
		try {
			FileReader reader = new FileReader
			("input.txt");
			int character;
			while ((character = reader.read()) != -1) {
				System.out.println((char) character);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// throw new IllegalArgumentException("Age cannot benegative.");

	}

}
