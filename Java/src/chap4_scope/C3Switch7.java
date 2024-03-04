package chap4_scope;

import java.util.Scanner;

public class C3Switch7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        int nLetters = 0;
        String name = "Jane";
        System.out.print("Enter name -> ");
        name = sc.next();

        String result = switch (name) {
        case "Jane", "Sean", "Alan", "Paul" -> "4";
        case "Janet", "Susan" -> "5";
        case "Maaike", "Alison", "Miriam" -> "6";
        default -> "Unrecognized name: " + name;
        };

        System.out.println(result);
		
	}

}
