package chap4_scope;

import java.util.Scanner;

public class C3Switch3 {

	public static void main(String[] args) {

		int numDays = 0;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter month -> ");
		String month = sc.next();
		
		switch (month) {
		case "JAN":
		case "MAR":
		case "MAY":
		case "JUL":
		case "AUG":
		case "OCT":
		case "DEC":
			numDays = 31;
			break;
			
		case "APR":
		case "JUN":
		case "SEP":
		case "NOV":
			numDays = 30;
			break;
			
		case "FEB":
			System.out.print("Enter year -> ");
			int year = sc.nextInt();
			
			if  ( (year % 400 == 0) || (year % 4 == 0 && !(year % 100 ==0))) {
				numDays = 29;
			} else {
				numDays = 28;
			}
			
			break;

		default:
			System.out.println("Invalid month: " + month);
			break;
		}
		
		if (numDays > 0) {
			System.out.println("Number of days is: " + numDays);
		}

	}

}
