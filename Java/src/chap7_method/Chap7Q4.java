package chap7_method;

import java.util.Scanner;
import java.time.LocalDateTime;

public class Chap7Q4 {

	public static void main(String[] args) {
		System.out.println(parkOpenAndClose(LocalDateTime.now().getHour()));
	}
	
	public static String parkOpenAndClose(int currentTime) {
		if (10 <= currentTime && currentTime <= 19) {
			return "공원이 열려있습니다.";
		} else if (currentTime < 10 || currentTime > 19) {
			return "공원이 닫혀있습니다.";
		} else {
			return "올바른 값이 아닙니다.";
		}
	}

}
