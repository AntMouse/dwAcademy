package chap13_java_core_api;

import java.util.Scanner;

public class C2UsingKeyboardExtra {

	public static void main(String[] args) {
		C2UsingKeyboardExtra.usingKeyboardExtra();

	}
	
	public static void usingKeyboardExtra() {
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter name : ");
		System.out.print(sc1.next());
		System.out.print(sc1.next());
		
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter name : ");
		System.out.println(sc2.nextLine());
	}

}
