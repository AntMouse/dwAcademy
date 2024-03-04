package chap13_java_core_api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class C3UsingFile {

	public static void main(String[] args) {
		usingFile();

	}
	
	public static void usingFile() {
		System.out.println(System.getProperty("user.dir"));
		
		try (Scanner sc = new Scanner(new File(System.getProperty("user.dir") +
				"\\sample.txt"))){
			if (sc.hasNextInt()) {
				int age = sc.nextInt();
				System.out.println(age);
			}
			if (sc.hasNextInt()) {
				int age = sc.nextInt();
				System.out.println(age);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		
	}

}
