package chap13_java_core_api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class C13ZonedDateTime {

	public static void main(String[] args) {
		LocalDateTime flightDepTime = LocalDateTime.of(2023, Month.NOVEMBER, 24,13,00);
		ZonedDateTime flightDepTimeZ = flightDepTime.atZone(ZoneId.of("Europe/Dublin"));
		System.out.println(flightDepTimeZ);
				
		ZonedDateTime arrivalTimeZ = flightDepTimeZ.withZoneSameInstant(ZoneId.of("Europe/Paris"))
			.plusHours(1)
			.plusMinutes(45);
		
		System.out.println(arrivalTimeZ);
		System.out.println(arrivalTimeZ.getHour() + ":" + arrivalTimeZ.getMinute());
	}

}
