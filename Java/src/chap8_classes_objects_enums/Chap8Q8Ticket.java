package chap8_classes_objects_enums;

public class Chap8Q8Ticket {
	private int parkTicketPrice;
	private String parkTicketVisitorsName;
	private int parkTicketVisitDate;
	
	public Chap8Q8Ticket(int parkTicketPrice, String parkTicketVisitorsName, int parkTicketVisitDate) {
		this.parkTicketPrice = parkTicketPrice;
		this.parkTicketVisitorsName = parkTicketVisitorsName;
		this.parkTicketVisitDate = parkTicketVisitDate;
	}
	
	public int getParkTicketPrice() {
		return parkTicketPrice;
	}
	public String getParkTicketVisitorsName() {
		return parkTicketVisitorsName;
	}	
	public int getParkTicketVisitDate() {
		return parkTicketVisitDate;
	}
	
	public void changeParkTicketPrice(int parkTicketPrice) {
		this.parkTicketPrice = parkTicketPrice;
	}	
	public void changeParkTicketVisitorsName(String parkTicketVisitorsName) {
		this.parkTicketVisitorsName = parkTicketVisitorsName;
	}	
	public void changeParkTicketVisitDate(int parkTicketVisitDate) {
		this.parkTicketVisitDate = parkTicketVisitDate;
	}

	public static void main(String[] args) {

	}

}
