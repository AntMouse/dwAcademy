package chap4_scope;

import java.util.Scanner;

public class Chap4QProject2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("안전 등급(1~10) : ");
		int safetyRating = sc.nextInt();
		String errorMessage = "제대로된 값을 입력해주세요.";
		
		if (5 <= safetyRating && safetyRating <= 10) {
			boolean valueCheck = true;
			String[] MissionList = {"순찰", "청소", "공룡들 먹이 주기", "고객 안내"};
			
			System.out.print("직책 : ");
			String jobTitle = sc.next();
			
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
			
			if (valueCheck) {
				System.out.print("업무 시간(10~19) : ");
				int jobTime = sc.nextInt();
				
				if (20 < jobTime || jobTime < 9) {
					System.out.println(errorMessage);
					return;
				} 
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
			}
			
		} else if (0 < safetyRating && safetyRating <= 4) {
			System.out.println("긴급상황입니다. 대피하세요.");
		} else {
			System.out.println(errorMessage);
		}
		

	}

}
