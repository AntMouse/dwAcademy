package chap5_iteration_looping;

import java.util.Arrays;
import java.util.Scanner;

public class Chap5Q6 {

	public static void main(String[] args) {
		boolean[] safetyRating = new boolean[5];
		Arrays.fill(safetyRating, false);
		Scanner sc = new Scanner(System.in);
		String safetyCheck;
		int safetyTotalCheck = 0;
		
		do {
			System.out.println("보안 체크를 하겠습니다.");
			for (int i = 0; i < safetyRating.length; i++) {
				if (!safetyRating[i]) {
					do {
						System.out.print((i + 1) + 
						" 단계 보안 기준이 충족되었으면 t, 아니면 f를 입력 : ");
						safetyCheck = sc.nextLine();
						
						if (safetyCheck.equals("t")) {
							System.out.println((i + 1) + " 단계 보안 기준이 충족되었습니다.");
							safetyRating[i] = true;
							safetyTotalCheck++;
							break;
						} else if (safetyCheck.equals("f")) {
							System.out.println("보안 기준이 충족되면 다시 시도해주세요.");
						} else {
							System.out.println("잘못된 값입니다. 다시 입력해주세요.");
						}
						
					} while (true);
				}
			}
		} while (safetyTotalCheck != safetyRating.length);
		System.out.println("모든 보안 기준이 충족되었습니다.");

	}

}
