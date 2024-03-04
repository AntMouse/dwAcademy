package chap7_method;

import java.util.Scanner;

public class Chap7Q2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("공룡의 체중을 입력하세요 : ");
		double dinoWeightInput = sc.nextDouble();
		System.out.println("이 공룡은 하루에 " + dinoWeight(dinoWeightInput) + "kg 만큼 먹어야 합니다.");
	}
	
	public static double dinoWeight(double dinoWeightPress) {
		return (dinoWeightPress * 0.05);
	}

}
