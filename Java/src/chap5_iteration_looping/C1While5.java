package chap5_iteration_looping;

import java.util.Scanner;

public class C1While5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter your age -- > ");
		int age = sc.nextInt();
		
		do {
			System.out.println("As you are " + age + 
						" years of age, you can purchase alcohol.");
			System.out.print("Please enter your age -> ");
			age = sc.nextInt();
		} while (age >= 18);

	}

}
