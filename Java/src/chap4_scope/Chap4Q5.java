package chap4_scope;

import java.util.Scanner;

public class Chap4Q5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean valueCheck = true;
		
		System.out.print("공룡 사이즈 입력 : ");
		String dinoSize = sc.next();
		
		String dinoHouse = switch (dinoSize) {
			case "XS", "S" -> "Small Enclose /";
			case "M" -> "Medium Enclose /";
			case "L","XL" -> "Large Enclose /";			
			default -> { 
				System.out.println("제대로된 값을 입력해주세요.");
				valueCheck = false;	
				yield ""; 
				}
			};
		
		String dinoType = null;
		
		if (valueCheck) {
			System.out.print("육식 / 초식 입력 : ");
			dinoType = sc.next();
			
			if (dinoType.equals("육식") || dinoType.equals("초식")) {
				System.out.println(dinoHouse + dinoType);
			} else {
				System.out.println("제대로된 값을 입력해주세요.");
			}

		} 
		
		
	}
}
