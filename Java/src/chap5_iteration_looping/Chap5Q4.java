package chap5_iteration_looping;

import java.util.Scanner;
import java.util.Arrays;

public class Chap5Q4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] dinoCages = {"A", "B", "C", "D", "E"};
		System.out.print("A~E중 어떤 우리를 선택할지 입력 : ");
		String selectCage = sc.nextLine();
		
		int[][] dinoAmount = {
				{7500, 3400, 9600, 7700, 2500, 5000},
				{4600, 7400, 8600, 4900, 3300},
				{5800, 1500, 5600, 2200, 5400},
				{5900, 2800, 4600, 4100, 6600},
				{9900, 9400, 1600, 3500, 1800}
		};
		int[] dinoSum = new int[dinoAmount.length];
		
		for (int i = 0; i < dinoAmount.length; i++) {
			for (int j = 0; j < dinoAmount[i].length; j++) {
				dinoSum[i] += dinoAmount[i][j];
			}
		}
		
		boolean[] dinoAmountSelect = new boolean[5];
		Arrays.fill(dinoAmountSelect, false);
		boolean dinoCageCheck = false;
		
		for (int i = 0; i < dinoCages.length; i++) {
			if (selectCage.equals(dinoCages[i])) {
				dinoCageCheck = true;
				dinoAmountSelect[i] = true;
			} 
			
			if (dinoAmountSelect[i]) {
				System.out.println("선택한 " + selectCage + 
						"우리에는 공룡이 총 " + dinoAmount[i].length + 
						"마리 있으며, 이들의 총 무게는" + dinoSum[i] + "입니다.");
			}
		}
		
		if (!dinoCageCheck) {
			System.out.println("잘못된 값입니다. A~E 중 하나를 입력해주세요.");
		}
		
		
		
	}
}
