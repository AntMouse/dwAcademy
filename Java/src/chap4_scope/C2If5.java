package chap4_scope;

import java.util.Scanner;

public class C2If5 {

	public static void main(String[] args) {
		final int JAN = 1, FEB = 2, MAR = 3;
		final int APR = 4, MAY = 5, JUN = 6;
		final int JUL = 7, AUG = 8, SEP = 9;
		final int OCT = 10, NOV = 11, DEC = 12;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter month -> ");
		int month = sc.nextInt();
		
		int numDays = 0;
		
		if (month == JAN || month == MAR || month == MAY || // 
			month == JUL || month == OCT || month == DEC || month == AUG) {
			
			numDays = 31;
		} else if (month == APR || month == JUN || month == SEP || // 4,6,9,11
					month == NOV) {
			
			numDays = 30;
		} else if (month == FEB) { // 2
			System.out.print("Enter year -> ");
			int year = sc.nextInt();
			
			if ( (year % 400 == 0) || (year % 4 == 0 && !(year % 100 == 0))) {
				numDays = 29;
			} else {
				numDays = 28;
			}
		} else {
			System.out.println("Invalid month: " + month);
		} 
		
		if (numDays > 0) {
			System.out.println("Number of days is: " + numDays);
		}
	}

}
