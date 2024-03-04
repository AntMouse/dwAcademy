package chap4_scope;

import java.util.Scanner;

public class C3Switch1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a sport -> ");
		String sport = sc.next();
		
		switch (sport) {
		case "Soccer":
			System.out.println("I play soccer");
			break;
			
		case "Rugby":
			System.out.println("I play Rugby");
			break;

		default:
			System.out.println("Unknoen sport");
			break;
		}
	}
}
