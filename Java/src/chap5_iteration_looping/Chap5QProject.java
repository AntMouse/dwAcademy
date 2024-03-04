package chap5_iteration_looping;

import java.util.Scanner;
import java.util.Arrays;

public class Chap5QProject {

	public static void main(String[] args) {
		// 급식 계획을 관리할 수 있는 시스템을 만들자.
		// 각 공룡에 대한 음식 양과 급식 시간을 계산하는 프로그램을 만들기.
		
		Scanner sc = new Scanner(System.in);
		int currentHour = 0;
		int currentHourCheck = 0;
		
		do {			
			if (23 < currentHour || currentHour < 0) {
				System.out.println("유효하지 않은 값입니다.");
			}
			
			System.out.print("현재 시간을 입력(0 ~ 23) : ");
			currentHour = sc.nextInt();
			sc.nextLine();
		} while (!(0 <= currentHour && currentHour <= 23));
		
		String[][] dinoInformation = {
				{"T-Rex", "100kg", "이재일"},
				{"Brachiodaurus", "250kg", "김철수"},
				{"Stegosaurus", "500kg", "홍길동"},
				{"Ankylosaurus", "750kg", "신민수"},
				{"Brachiosaurus", "400kg", "박주림"},
		};
		int[][] dinoMealtime = {
				{8, 14, 10},
				{7, 11, 15, 19},
				{10, 20},
				{7, 14, 21},
				{4, 10, 14, 15, 19}
		};
		boolean[] dinoMealtimeCheck = new boolean[5]; // 5를 dinoInformation.length로 하는 게 더 좋음.
		Arrays.fill(dinoMealtimeCheck, false);
		
		for (int i = 0; i < dinoMealtime.length; i++) {
			for (int j = 0; j < dinoMealtime[i].length; j++) {
				if (currentHour == dinoMealtime[i][j]) {
					dinoMealtimeCheck[i] = true;
					currentHourCheck++;
					System.out.println("현재 시간은 " + dinoInformation[i][0] + "(" + 
					dinoInformation[i][2]+ " 담당)" + "의 식사 시간입니다.");
					break;
				}
			}	
		}
		
		if (currentHourCheck == 0) {
			System.out.println("해당 시간에는 식사하는 공룡이 없습니다.");
		}
		
		totalLoop:
		while (currentHourCheck > 0) {
			System.out.print("담당 종을 확인하기 위해 이름을 입력해주세요 : ");
			String name = sc.nextLine();
			boolean nameCheck = false;
			int nameOverlapCheck = 0;
			
			for (int i = 0; i < dinoInformation.length; i++) {
				if (name.equals(dinoInformation[i][2])) {
					if (dinoMealtimeCheck[i]) {
						nameCheck = true;
					} else {
						System.out.println("해당 담당자가 관리하고 있는 공룡은" +
						" 이 시간(" + currentHour + ")에는 식사를 하지 않습니다.");
						while (true) {
							System.out.print("다시 입력하려면 y, 아니면 n을 입력하세요 : ");
							String reTryCheck = sc.nextLine();
							if (reTryCheck.equals("y")) {
								nameOverlapCheck = 0;
								break;
							} else if (reTryCheck.equals("n")) {
								System.out.println("프로그램을 종료합니다.");
								break totalLoop;
							} else {
								System.out.println("유효한 값이 아닙니다.");
							}
						}	
					}
					
				} else if (!name.equals(dinoInformation[i][2])) {
					nameOverlapCheck++;
				}
				
				while (nameOverlapCheck == dinoInformation.length) {
					System.out.print("일치하는 담당자가 없습니다. " +
					"다시 입력하려면 y, 아니면 n을 입력하세요 : ");
					String loopCheck = sc.nextLine();
					if (loopCheck.equals("y")) {
						nameOverlapCheck = 0;
						break;
					} else if (loopCheck.equals("n")) {
						System.out.println("프로그램을 종료합니다.");
						break totalLoop;
					} else {
						System.out.println("유효한 값이 아닙니다.");
					}
				}
				
				if (nameCheck) {
					System.out.println(dinoInformation[i][2] + 
					"님이 담당하고 있는 공룡은 " + dinoInformation[i][0] + "이고, " +
					dinoInformation[i][0] + "는 하루에 총 " + dinoMealtime[i].length +
					" 번 식사를 하며, 매끼 " + dinoInformation[i][1] + "만큼 먹습니다.");
					break totalLoop;
				}
			}
		}

			
		
	}

}
