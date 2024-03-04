package chap4_scope;

import java.util.Scanner;

public class C3Switch8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        int nLetters = 0;
        String name = "Jane";
        System.out.print("Enter name -> ");
        name = sc.next();
        
        nLetters = switch (name) {
			case "Jane", "Sean", "Alan", "Paul" -> {
				System.out.println("There are 4 letters in : " + name);
				yield 4;
			}
			
			case "Janet", "Susan" -> {
				System.out.println("There are 5 letters in : " + name);
				yield 5;
			}
			
			case "Maaike", "Alison", "Miriam" -> {
				System.out.println("There are 6 letters in : " + name);
				yield 6;
			}
					
			default -> {
				System.out.println("Unrecognized name : " + name);
				yield -1;
			}
		};
		System.out.println(nLetters);

	}

}
