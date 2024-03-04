package chap5_iteration_looping;

import java.util.Scanner;

public class Chap5Q3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String countInput;
		int countValue;
		
		System.out.print("카운트 시작 지점을 정수로 입력하세요 : ");
		countValue = sc.nextInt();
		
		while (countValue >= 0) {
			System.out.print("카운트 다운하기(y 입력) : ");
			countInput = sc.next();
			
			if (countInput.equals("y")) {
				System.out.println("카운트 : " + countValue + "!!");
				countValue--;
			} else {
				System.out.println("잘못된 값입니다. 다시 해주세요.");
			}
		}
		System.out.println("Mesozoic Eden 공원이 열렸습니다.");	
	}
}
