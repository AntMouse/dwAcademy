package m2_02_21;

// 정규권 티켓 클래스
public class Ticket {
	private String ticketType; // 티켓 종류
	private int price; // 가격

	// 생성자
	public Ticket(String ticketType, int price) {
		this.ticketType = ticketType;
		this.price = price;
	}

	// 티켓 정보 출력
	@Override
	public String toString() {
		return "Ticket Type: " + ticketType + ", Price: " + price;
	}
	
	public static void main(String[] args) {

	}
}

// 계절권 티켓 클래스
class SeasonTicket extends Ticket {
	private String startDate; // 시작일
    private String endDate; // 종료일

	// 생성자
	public SeasonTicket(String ticketType, int price, String startDate, String endDate) {
		super(ticketType, price);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// 시작일 반환
	public String getStartDate() {
		return startDate;
	}
	// 종료일 반환
	public String getEndDate() {
		return endDate;
	}

	// 계절권 티켓 정보 출력
	@Override
	public String toString() {
		return super.toString() + ", Start Date: " + startDate + ", End Date: " + endDate;
	}
}
