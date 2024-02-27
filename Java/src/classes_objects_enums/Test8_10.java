package classes_objects_enums;

enum WorkDay {
	// values must be first
	MONDAY("9-5"),
	TUESDAY("9-5"),
	WEDNESDAY("9-5"),
	THURSDAY("9-5"),
	FRIDAY("9-5"),
	SATURDAY("10-1") {
		public String getWorkLocation() {
			return "Home";
		}
	};
	
	private String hoursOfWork;
	
	WorkDay(String hoursOfWork) {
		this.hoursOfWork = hoursOfWork;
	}
	public String getHoursOfWork() {
		return hoursOfWork;
	}
	public String getWorkLocation() {
		return "Office";
	}
}

public class Test8_10 {

	public static void main(String[] args) {
		WorkDay Monday = WorkDay.MONDAY;
		System.out.println(Monday.getHoursOfWork() + ", " + 
		Monday.getWorkLocation());
		System.out.println(WorkDay.SATURDAY.getHoursOfWork() + ", " + 
		WorkDay.SATURDAY.getWorkLocation());
		
	}

}
