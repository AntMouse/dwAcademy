package chap4_scope;

import java.util.Scanner;

public class Chap4Q8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("요일 입력(ex: 월,화) : ");
		String days = sc.next();
		
		if (days.equals("토") || days.equals("일")) {
			System.out.print("오늘은 휴일입니다.");
			
		} else if (days.equals("월") || days.equals("화") || 
			days.equals("수") || days.equals("목") || days.equals("금")) {
			
			System.out.print("시간 입력(0~24) : ");
			int time = sc.nextInt();		

			if (0 <= time && time <= 24) {
				System.out.println("현재 시간은 " + time + "시 입니다.");
				
				if (10 <= time && time <=19) {
					System.out.println("방문객 개방시간입니다.");
				} else {
					System.out.println("방문객 개방시간이 아닙니다.");
				} 
			} else {
				System.out.println("제대로 입력해주세요.");
			}
		} else {
			System.out.print("제대로 입력해주세요.");
		}
		
		

	}
}
