package chap13_java_core_api;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class C15Formatter {

	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.now();
		String text = date.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);
		
		System.out.println(date);
		System.out.println(text);
		System.out.println(parsedDate);
		System.out.println();
		
		LocalDate date2 = LocalDate.now();
		DateTimeFormatter isoDate = DateTimeFormatter.ISO_DATE;
		System.out.println(date2.format(isoDate));                     
		DateTimeFormatter fullDateStyle = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		System.out.println(date2.format(fullDateStyle));     
		  
		LocalTime time = LocalTime.now();
		DateTimeFormatter isoTime = DateTimeFormatter.ISO_TIME;
		System.out.println(time.format(isoTime));                        
		DateTimeFormatter shortTimeStyle = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(time.format(shortTimeStyle));  
	}

}
