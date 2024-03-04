package chap4_scope;

import java.util.Iterator;
import java.util.Scanner;

public class Chap4QProject1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("안전 등급(1~10) : ");
		int safetyRating = sc.nextInt();
		int safetyRatingLimit = 4;
		sc.nextLine();
		String errorMessage = "제대로된 값을 입력해주세요.";
		
		if (0 < safetyRating && safetyRating <= 10) {
			boolean valueCheck = true;
			String[] MissionList = {"순찰", "청소", "공룡들 먹이 주기", "고객 안내"};
			
			String[] safetyMission = {
					"순찰 구역의 손님을 대피시키세요.",
					"청소를 중단하고 대피하세요.",
					"사육장을 확실히 닫고 대피하세요.",
					"안내하고 있는 고객과 함께 대피하세요."
			};
			
			System.out.print("직책 : ");
			String jobTitle = sc.nextLine();
			
			String jobMission = switch (jobTitle) {
				case "Security" -> MissionList[0];
				case "Cleaning" -> MissionList[1];
				case "Feeding" -> MissionList[2];
				case "Tour Guiding" -> MissionList[3];
				default ->  { 
					System.out.println(errorMessage);
					valueCheck = false;	
					yield ""; 
				}
			};
			
			// 첫 번째 내부
			if (valueCheck) {
				if (safetyRating <= safetyRatingLimit) {
					for (int i = 0; i < MissionList.length; i++) {
						if (jobMission.equals(MissionList[i])) {
							System.out.println(safetyMission[i]);
						}
					}
				} else if (safetyRating > safetyRatingLimit) {
					if (safetyRating > 10) {
						System.out.println(errorMessage);
					} else {
						System.out.print("업무 시간(10~19) : ");
						int jobTime = sc.nextInt();
						
						if (10 <= jobTime && jobTime <= 19) {
							for (int i = 0; i < MissionList.length; i++) {
								if (jobMission.equals(MissionList[i])) {
									if (10 <= jobTime && jobTime <= 12) {
										System.out.println("1 번 구역 " + MissionList[i]);
									} else if (12 <= jobTime && jobTime <= 14) {
										System.out.println("2 번 구역 " + MissionList[i]);
									} else if (14 <= jobTime && jobTime <= 16) {
										System.out.println("3 번 구역 " + MissionList[i]);
									} else {
										System.out.println("담당구역 청소");
									}
								}
							}	
						} else {
							System.out.println(errorMessage);
						}													
					} // 첫 번째 else
				} // 첫 번째 else if
			}
		} else {
			System.out.println(errorMessage);
		}
			
		
		
	} 
}
