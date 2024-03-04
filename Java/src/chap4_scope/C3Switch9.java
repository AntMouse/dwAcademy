package chap4_scope;

import java.util.Scanner;

public class C3Switch9 { // 10번

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        int nLetters = 0;
        String name = "Jane";

        switch (name) {
			case "Jane", "Sean", "Alan", "Paul" -> nLetters = 4;
			case "Janet", "Susan"-> nLetters = 5;
			case "Maaike","Alison", "Miriam" -> nLetters = 6;	
			
			default -> {
				System.out.println("Unrecognized name : " + name);
				nLetters = -1;
			}
		}
        System.out.println(nLetters);
	}

}
