package chap7_method;

import java.util.Scanner;

public class Chap7Q1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("공룡의 나이를 입력하세요 : ");
		int dinoAgeInput = sc.nextInt();
		System.out.println(dinoLevel(dinoAgeInput));
	}
	
	public static String dinoLevel(int dinoAge) {
		if (dinoAge == 0) {
			return "갓 부화한 것";
		} else if (1 <= dinoAge && dinoAge <= 3) {
			return "청소년";
		} else if (4 <= dinoAge) {
			return "성체";
		} else {
			return "잘못된 값입니다.";
		}
	}
}
