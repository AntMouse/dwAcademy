package chap12_exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Chap12Q4DinoDietReader {
	
    public static void readDinoDietFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("파일에서 읽은 데이터 :");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("데이터를 읽어오는 중 에러가 발생했습니다: " + e.getMessage());
        }
    }

	public static void main(String[] args) {
		readDinoDietFromFile("src\\\\chap12_exceptions\\DinoDiet.txt");
		readDinoDietFromFile("DinoDiet.txt");

	}

}
