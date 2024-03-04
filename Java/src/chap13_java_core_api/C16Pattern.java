package chap13_java_core_api;

import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class C16Pattern {

	public static void main(String[] args) {
		ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt); // 2024-03-04T14:14:56.783483483+09:00[Asia/Seoul]

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yy-MMM-dd E D");
        System.out.println(zdt.format(dateFormatter));

        DateTimeFormatter timeFormatter1 = DateTimeFormatter.ofPattern("hh:mm:ss a z G");
        System.out.println(zdt.format(timeFormatter1)); // 02:14:56 PM KST AD

        // How to insert text
        DateTimeFormatter dateFormatter2 = 
        DateTimeFormatter.ofPattern("'Year:  'yyyy'. Month: 'MMMM'.   Day: 'dd'.'");
        System.out.println(zdt.format(dateFormatter2)); // Year: 2024. Month: March.   Day: 04.

        // Parse
        String dateTimeString = "2024-03-04 14:10"; // today now
        DateTimeFormatter timeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime ldt = LocalDateTime.parse(dateTimeString, timeFormatter2);
        System.out.println(ldt); // 2024-03-04T14:10
	}

}
