package chap4_scope;

import java.util.Scanner;

public class Chap4Q3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("공룡 타입 : ");
		String dinosaurType = sc.next();
		
		int yearsExperience = 0;
		
		if (dinosaurType.equals("티라노사우르스")) {
			yearsExperience = 3;
		} else if (dinosaurType.equals("트리케라톱스")) {
			yearsExperience = 5;
		} else if (dinosaurType.equals("스테고라사우르스")) {
			yearsExperience = 7;
		} else {
			System.out.println("해당 정보를 찾을 수 없습니다.");
		}

		if (yearsExperience > 0) {
			System.out.println("해당 공룡을 다루는데 필요한 경험 : " + yearsExperience + "년");
			yearsExperience = 0;
		}
	}

}
