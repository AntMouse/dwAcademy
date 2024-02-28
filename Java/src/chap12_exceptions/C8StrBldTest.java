package chap12_exceptions;

import java.io.FileReader;
import java.io.IOException;

public class C8StrBldTest {

	public static void main(String[] args) {
		try (FileReader fileReader = new FileReader("input.txt")){
			int character;
			StringBuilder content = new StringBuilder();
			while ((character = fileReader.read()) != -1) {
				content.append((char) character);
			}
			System.out.println(content.toString());
		} catch (IOException e) {
			System.out.println("Oops : " + e.getMessage());
		}

	}

}
