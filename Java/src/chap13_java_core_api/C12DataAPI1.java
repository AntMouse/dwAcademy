package chap13_java_core_api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class C12DataAPI1 {

	public static void main(String[] args) {
		LocalDate nowDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now();
		LocalDateTime nowDateTime = LocalDateTime.now();
		LocalDateTime nowDateTime2 = LocalDateTime.of(nowDate, nowTime);

		System.out.println(nowDate);
		System.out.println(nowTime);
		System.out.println(nowDateTime);
		System.out.println(nowDateTime2);
		System.out.println();
		
		LocalDate ld1 = LocalDate.of(2025, 3, 17);
		LocalDate ld2 = LocalDate.parse("2025-03-17");
		LocalDate ld3 = ld2.withMonth(5);
		LocalDate ld4 = ld3.plusYears(1);
		LocalDate ld5 = ld4.minusDays(5);
		LocalDateTime ldt1 = ld5.atTime(13,45,10);

		System.out.println(ld2.getDayOfWeek());
		System.out.println(ld1);
		System.out.println(ld2);
		System.out.println(ld3);
		System.out.println(ld4);
		System.out.println(ld5);
		System.out.println(ldt1);
	}

}
