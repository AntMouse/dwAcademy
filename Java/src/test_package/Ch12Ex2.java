package test_package;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

class EventLogger {
	private HashMap<String, LocalDateTime> eventLog;
	private HashMap<String, DayOfWeek> weekEvent;
	private HashMap<String, HashMap<DayOfWeek, LocalTime>> weeklyEvent;
	
	public EventLogger() {
		eventLog = new HashMap<>();
		weekEvent = new HashMap<>();
		weeklyEvent = new HashMap<>();
	}
	
	public void logEvent(String event, LocalDateTime eventTime) {
		eventLog.put(event,  eventTime);
	}
	
	public LocalDateTime getEventDateTime(String eventName ) {
		return this.eventLog.get(eventName);
	}
	
	public void logWeekEvent(String event, DayOfWeek dayOfWeek) {
		weekEvent.put(event, dayOfWeek);
	}
	
	public DayOfWeek getWeekEvent(String event) {
		return this.weekEvent.get(event);
	}
	
	public void logWeeklyEvent(String event, HashMap<DayOfWeek, LocalTime> weeklyTime) {
		weeklyEvent.put(event, weeklyTime);
	}
	
	public HashMap<DayOfWeek, LocalTime> getWeeklyEvent(String event) {
		return weeklyEvent.get(event);
	}
}

public class Ch12Ex2 {

	public static void main(String[] args) {
		EventLogger eventLogger = new EventLogger();
		
		eventLogger.logEvent("feedingTime1", LocalDateTime.of(LocalDate.now(), LocalTime.NOON));
		
		System.out.println(eventLogger.getEventDateTime("feedingTime1"));
		
		eventLogger.logWeekEvent("Park Cleaning", DayOfWeek.FRIDAY);
		
		System.out.println(eventLogger.getWeekEvent("Park Cleaning"));
		
		HashMap<DayOfWeek, LocalTime> weeklyTime = new HashMap<>();
		weeklyTime.put(DayOfWeek.FRIDAY, LocalTime.of(10,30));
		
		eventLogger.logWeeklyEvent("Emergency Drill", weeklyTime);
		
		System.out.println(eventLogger.getWeeklyEvent("Emergency Drill"));

	}

}
