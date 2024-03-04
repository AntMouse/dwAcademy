package chap13_java_core_api;

import java.util.Scanner;

public class C1UsinKeyborad {

	public static void main(String[] args) {
		C1UsinKeyborad.usinKeyborad();

	}
	
	public static void usinKeyborad() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter age : ");
		// 1
		if (sc.hasNextInt()) {
			// 2
			int age = sc.nextInt();
			System.out.println(age);
		}
		System.out.println("this is the end");
		/*
		sc.close();
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter another age : ");
		int age = sc1.nextInt();
		*/
	}

}
