package chap4_scope;

import java.util.Scanner;

public class Chap4Q6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("공룡의 몸무게 입력 : ");
		int dinoWeight = sc.nextInt();
		int dinoFoddNumber = 0;
		
		if (dinoWeight <= 4000) {
			dinoFoddNumber = 1;
		} else if (dinoWeight <= 10000) {
			dinoFoddNumber = 2;
		} else if (dinoWeight <= 15000) {
			dinoFoddNumber = 3;
		} else {
			dinoFoddNumber = 4;
		}
		
		if (dinoFoddNumber != 0) {
			System.out.println("필요 급식 횟수 : " + dinoFoddNumber);
		}
		
	}

}
