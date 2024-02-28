package chap8_classes_objects_enums;

import java.util.Scanner;

public class Test8_Guest {
	int mamaximumGuestAmount = 5000;
	int currentGuestAmount = 4000;
	
	Scanner scanner = new Scanner(System.in);
	
	public void checkVisitorsCount() {
		boolean methodTotalCheck = false;
		
		System.out.print("추가 방문객 수 : ");
		int additionGuestAmountCheck = scanner.nextInt();
		
		if (!methodTotalCheck) {
			int guestSum = currentGuestAmount + additionGuestAmountCheck;
			
			if (mamaximumGuestAmount >= guestSum) {
				System.out.println();
				System.out.println("추가 방문객을 받을 수 있습니다.");
				System.out.println(additionGuestAmountCheck + "명을 추가로 받았습니다.");
				System.out.println("현재 공원 상황 : " + guestSum + " / " + mamaximumGuestAmount);
				currentGuestAmount = guestSum;
			} else {
				System.out.println();
				System.out.println("추가 방문객을 받을 수 없습니다.");
				System.out.println("현재 공원 상황 : " + currentGuestAmount + " / " + mamaximumGuestAmount);
				System.out.println("최대 " + (mamaximumGuestAmount - currentGuestAmount) + "명까지만 더 받을 수 있습니다." );
			}
		}
	}
	
	public static void main(String[] args) {
		

	}

}
