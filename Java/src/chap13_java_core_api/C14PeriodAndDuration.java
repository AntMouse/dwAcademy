package chap13_java_core_api;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class C14PeriodAndDuration {

	public static void main(String[] args) {
		LocalDate startDate = LocalDate.of(1861, 4, 12);
		LocalDate endDate = LocalDate.of(1865, 4, 9);
		Period howLongP = Period.between(startDate, endDate);
		System.out.println(howLongP);
		System.out.println(howLongP.getYears());
		System.out.println(howLongP.getMonths());
		System.out.println(howLongP.getDays());
		System.out.println();
		
		LocalTime lt1 = LocalTime.of(12, 0, 20);
		LocalTime lt2 = LocalTime.of(14, 45, 40);
		Duration howLongD2 = Duration.between(lt1, lt2);
		System.out.println(howLongD2);
	}

}
