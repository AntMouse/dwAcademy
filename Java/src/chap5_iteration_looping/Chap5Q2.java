package chap5_iteration_looping;

import java.util.Scanner;

public class Chap5Q2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isHungry = true;
		
		do {
			System.out.print("공룡을 관찰하여 공룡이 배가 고픈 거 같으면 h, 아니면 n을 입력 : ");
			String food = sc.nextLine();
			
			if (food.equals("h")) {
				System.out.print("공룡에게 음식을 줄거면 y, 아니면 n을 입력 : ");
				food = sc.nextLine();
				
				if (food.equals("y")) {
					System.out.println("공룡이 음식을 먹습니다.");
				} else if (food.equals("n")) {
					System.out.println("공룡이 배가 고프지만 음식을 주지 않았습니다.");
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			} else if (food.equals("n")) {
				System.out.println("공룡이 배가 부르므로 음식을 주지 않았습니다.");
				isHungry = false;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		} while (isHungry);
		
		sc.close();
		
	}

}
