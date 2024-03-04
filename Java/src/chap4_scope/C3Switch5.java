package chap4_scope;

import java.util.Scanner;

public class C3Switch5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int nLetters = 0;
		String name = "Jane";
		System.out.print("Enter name -> ");
		name = sc.next();
		
		switch (name) {
		case "Jane":
		case "Sean":
		case "Alan":
		case "Paul":
			nLetters = 4;
			break;

		case "Janet":
		case "Susan":
			nLetters = 5;
			break;
			
		case "Maaike":
		case "Alison":
		case "Miriam":
			nLetters = 6;
			break;
			
		default:
			System.out.println("Unrecognized name: " + name);
			nLetters = -1;
			break;
		}
		
		if (name != null) {
			System.out.println("name : " + name);
			System.out.println("nLetters : " + nLetters);
		}

	}
}
