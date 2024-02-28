package chap12_exceptions;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class C2WriteFileExample {

	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter
			("output.txt");
			String content = "I can write!";
			writer.write(content);
			writer.close();
			
			// 읽기
			FileReader reader = new FileReader
			("output.txt");
			int character;
			while ((character = reader.read()) != -1) {
				System.out.print((char) character);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
