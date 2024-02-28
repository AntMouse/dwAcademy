package chap12_exceptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class C9BufferedWriter {

	public static void main(String[] args) {
		try (FileReader fileReader = new FileReader("input.txt")){
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWriter = new FileWriter("output.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); {
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					String uppercaseLine = line.toUpperCase();
					bufferedWriter.write(uppercaseLine);
					bufferedWriter.newLine();
				}
				bufferedWriter.close();
			}
			try (FileReader reader = new FileReader("output.txt")) {
                int character;
                while ((character = reader.read()) != -1) {
                    System.out.print((char) character);
                }
            }
		} catch (IOException e) {
			System.out.println("Oops : " + e.getMessage());
		}

	}

}
