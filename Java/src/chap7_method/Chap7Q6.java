package chap7_method;

import java.util.Scanner;

public class Chap7Q6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("현재 방문객 수 : ");
		int currentGuestAmountInformation = sc.nextInt();
		System.out.print("추가 방문객 수 : ");
		int additionGuestAmountInformation = sc.nextInt();
		
		System.out.println(guestAddition(currentGuestAmountInformation, additionGuestAmountInformation));
	}
	
	public static String guestAddition(int currentGuestAmount, int additionGuestAmount) {
		int maximumGuestAmount = 5000;
		int guestSum = (currentGuestAmount + additionGuestAmount);
		
		if (maximumGuestAmount >= guestSum) {
			return "방문객을 들여보낼 수 있습니다.";
		} else {
			return "최대 인원을 초과했습니다. 방문객을 들여보낼 수 없습니다.";
		}
	}

}
