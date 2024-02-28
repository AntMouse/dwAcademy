package chap12_exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Chap12Q1DinosaurDataReader {
	
    public String readDataFromFile(String fileName) throws IOException {
    	StringBuilder data = new StringBuilder();
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            return data.toString();
        }      
    }

	public static void main(String[] args) {
		Chap12Q1DinosaurDataReader reader = new Chap12Q1DinosaurDataReader();
        try {
            String data = reader.readDataFromFile("src\\chap12_exceptions\\dinosaur_data.txt");
            System.out.println("파일에서 읽은 데이터 : ");
            System.out.println(data);
        } catch (IOException e) {
            System.err.println("파일을 읽을 수 없습니다: " + e.getMessage());
            // 예외를 더 상위로 throw하여 호출자에게 처리하도록 전파할 수도 있습니다.
        }

	}

}
