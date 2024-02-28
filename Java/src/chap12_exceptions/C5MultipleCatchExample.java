package chap12_exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class C5MultipleCatchExample {

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("..\\input.txt");
			int character;
			while ((character = fr.read()) != -1) {
				System.out.println((char) character);
			}
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Not found : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO error : " + e.getMessage());
		} finally {
			System.out.println("This is final block");
		}

	}

}
