package chap13_java_core_api;

import java.util.Scanner;

public class C4UsingString {

	public static void main(String[] args) {
		usingString();

	}
	
	public static void usingString() {
		String input = "maaike delim vandelin Putten delim 22";
		Scanner sc = new Scanner(input).useDelimiter("\\s*delim\\s*");
		System.out.println(sc.next());
		System.out.println(sc.next());
		// System.out.println(sc.next());
		System.out.println(sc.nextInt());
		
		String input2 = "maaike delim vandelinPutten delim 22";
		Scanner sc2 = new Scanner(input2).useDelimiter("\\s*delim\\s*");
		System.out.println(sc2.next());
		System.out.println(sc2.next());
		// System.out.println(sc2.next());
		System.out.println(sc2.nextInt());
		sc.close();
	}

}
