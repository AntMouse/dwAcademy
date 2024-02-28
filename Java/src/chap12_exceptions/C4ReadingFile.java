package chap12_exceptions;

import java.io.FileReader;
import java.io.IOException;

public class C4ReadingFile {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("input.txt");
			int character;
			while ((character = fr.read()) != -1) {
				System.out.println((char) character);
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
