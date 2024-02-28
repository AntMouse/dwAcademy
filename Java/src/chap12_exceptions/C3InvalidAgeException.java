package chap12_exceptions;

import java.io.FileReader;
import java.io.IOException;

public class C3InvalidAgeException extends Exception {
	
	
	public C3InvalidAgeException() {
		super();
	}
	public C3InvalidAgeException(String message) {
		super(message);
	}
	public C3InvalidAgeException(Exception e) {
		super(e);
	}
	public static void read(String fileName) throws IOException {
		// FileReader = new FileReader(fileName);
	}

	public static void main(String[] args) {

	}

}
