package chap7_method;

import java.util.Scanner;

public class C1Method2 {

	public static void main(String[] args) {
		int number = getNumber();
		System.out.println(number);
		number = getNumber();
		System.out.println(number);
		number = getNumber();
		System.out.println(number);

	}
	
	public static int getNumber() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a number (1 ~ 10) -> ");
		int number = sc.nextInt();
		if (10 < number || number < 1) {
			System.out.println("Invalid number! " + number);
		}
		
		return number;
	}

}
