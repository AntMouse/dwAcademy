package chap8_classes_objects_enums;

import java.util.Scanner; 

public class Chap8QProject {
	Scanner scanner = new Scanner(System.in);

	// 공룡, 종업원 관리 양식 불러오기
	private Chap8Q4ParkManagement parkTotalManagement = new Chap8Q4ParkManagement();
	
	public void initialValue() {
		// 공룡 초기값
		parkTotalManagement.addDinosaur(0, "첫째티라노", 4, "티라노사우루스", "양호");
		parkTotalManagement.addDinosaur(1, "첫째기가노트", 7, "기가노토사우루스", "양호");
		parkTotalManagement.addDinosaur(2, "둘째티라노", 6, "티라노사우루스", "주의");
		parkTotalManagement.addDinosaur(3, "첫째타르보", 4, "타르보사우루스", "양호");
		parkTotalManagement.addDinosaur(4, "둘째타르보", 10, "타르보사우루스", "주의");
		
		// 직원 초기값
		parkTotalManagement.employeeAdd(0, "Kim", "사육장 관리자", 5);
		parkTotalManagement.employeeAdd(1, "John", "파크 안내인", 7);
		parkTotalManagement.employeeAdd(2, "Tom", "경비원", 2);
		parkTotalManagement.employeeAdd(3, "Smith", "영양 관리사", 12);
		parkTotalManagement.employeeAdd(4, "Dan", "사육장 관리자", 17);
		
		// 티켓 초기값				
		parkTotalManagement.addParkTicket(0, 20000, "이재일", 20240219);				
		parkTotalManagement.addParkTicket(1, 20000, "김민수", 20240219);				
		parkTotalManagement.addParkTicket(2, 20000, "삼미빈", 20240219);
		parkTotalManagement.addParkTicket(3, 20000, "준사함", 20240219);		
		parkTotalManagement.addParkTicket(4, 20000, "힌종신", 20240219);
	}

	boolean mainMenu = false;

	public static void main(String[] args) {	
		Chap8QProject main = new Chap8QProject();
		main.initialValue(); // 초기값 설정
		main.start();
	}
	
	public void start() {
		// This is the main loop of the application. It
		// will keep running until the user decides to exit.
		do {
			displayMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			handleMenuChoice(choice);
			while (true) {
				System.out.print("메뉴를 다시 불러오기는 'M', 종료는 'E'를 입력 : ");
				String menuRestart = scanner.nextLine();
				if (menuRestart.equals("M") || menuRestart.equals("m")) {
					mainMenu = true;
					System.out.println();
					break;
				} else if (menuRestart.equals("E") || menuRestart.equals("e")) {
					System.out.print("프로그램을 종료합니다.");
					mainMenu = false;
					break;
				} else {
					System.out.println("잘못된 값입니다. 다시 입력해주세요.");
				}
			}
		} while (mainMenu);
	}
	
	public void displayMenu() {
		System.out.println("Welcome to Mesozoic Eden ParkManager!");
		System.out.println("1. Manage Dinosaurs");
		System.out.println("2. Manage Park Employees");
		System.out.println("3. Manage Tickets");
		System.out.println("4. Check Park Status");
		System.out.println("5. Handle Special Events");
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");
	}
	
	public void handleMenuChoice(int choice) {
		switch (choice) {
		case 1:
			manageDinosaurs();
			break;
		case 2:
			manageEmployees();
			break;
		case 3:
			manageTickets();
			break;
		case 4:
			checkParkStatus();
			break;
		case 5:
			handleSpecialEvents();
			break;
		case 6:
			System.out.println("Exiting...");
			System.exit(0);
			break;
		default:
			break;
		}	
	}
	
	// 케이스 1 공룡 관리
	public void manageDinosaurs() {
		System.out.println("1. 공룡 정보 추가 2. 공룡 정보 보기 3. 공룡 제거 4. 공룡 정보 수정");
		int menuChoice = scanner.nextInt();
		
		switch (menuChoice) {
		case 1:
			System.out.print("추가할 공룡의 관리 번호를 설정하세요 : ");
			int newDinoIndex = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("추가할 공룡의 이름을 지어주세요 : ");
			String newDinoName = scanner.nextLine();
			
			System.out.print("추가할 공룡의 나이를 입력하세요 : ");
			int newDinoAge = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("추가할 공룡의 종을 적어주세요 : ");
			String newDinoSpecies = scanner.nextLine();
			
			System.out.print("추가할 공룡의 현재 상태를 적어주세요 : ");
			String newDinoStatus = scanner.nextLine();
			
			parkTotalManagement.addDinosaur(newDinoIndex, newDinoName, newDinoAge, newDinoSpecies, newDinoStatus);
			break;
			
		case 2:
			System.out.print("조회할 공룡의 관리 번호를 입력하세요 : ");
			int printDinoInfo = scanner.nextInt();
			scanner.nextLine();
			parkTotalManagement.printDinoInfo(printDinoInfo);
			break;
			
		case 3:
			System.out.print("제거할 공룡의 관리 번호를 입력하세요 : ");
			int removeDino = scanner.nextInt();
			scanner.nextLine();
			parkTotalManagement.removeDino(removeDino);
			break;
			
		case 4:
			System.out.print("정보를 수정할 공룡의 관리 번호를 입력하세요 : ");
			int changeDinoIndex = scanner.nextInt();
			scanner.nextLine();
			
		    System.out.print("공룡 이름 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeDinoName = scanner.nextLine();

		    System.out.print("공룡 나이 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeDinoAge = scanner.nextLine();

		    System.out.print("공룡 종 정보 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeDinoSpecies = scanner.nextLine();
		    
		    System.out.print("공룡 상태 정보 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeDinoStatus = scanner.nextLine();
		    
		    System.out.print("공룡 케이지 번호 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeDinoLocation = scanner.nextLine();
		    
		    // 정보 종합 후 정보 수정 판단
		    if (!changeDinoName.isEmpty() || !changeDinoAge.isEmpty() || !changeDinoSpecies.isEmpty() || !changeDinoStatus.isEmpty() || !changeDinoLocation.isEmpty()) {
				parkTotalManagement.allChangeDino(changeDinoIndex, changeDinoName, changeDinoAge, changeDinoSpecies, changeDinoStatus, changeDinoLocation);
			} else {
		        System.out.println("수정된 정보가 없습니다. 공룡 정보가 그대로 유지됩니다.");
		    }
		    break;
		default:
			scanner.nextLine();
			System.out.println("잘못된 값입니다.");
			break;
		}
	}
	
	// 케이스 2 직원 관리
	public void manageEmployees() {
		System.out.println("1. 직원 정보 추가 2. 직원 정보 보기 3. 직원 제거 4. 직원 정보 수정");
		int menuChoice = scanner.nextInt();
		
		switch (menuChoice) {
		case 1:
			System.out.print("추가할 직원의 관리 번호를 설정하세요 : ");
			int newEmployeeIndex = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("추가할 직원의 이름을 입력하세요 : ");
			String newEmployeeName = scanner.nextLine();
			
			System.out.print("추가할 직원의 직무를 입력하세요 : ");
			String newEmployeeJobTitle = scanner.nextLine();
			
			System.out.print("추가할 직원의 경력을 입력하세요 : ");
			int newEmployeeYearsOfExperience = scanner.nextInt();
			scanner.nextLine();
			
			while (true) {
				System.out.print("직원의 스케줄을 입력하세요 (스케줄을 입력하지 않을 거면 엔터를 입력) : ");
				String newEmployeeSchedule = scanner.nextLine();
				if (newEmployeeSchedule.isEmpty()) {
					break;
				}
				parkTotalManagement.changeEmployeeSchedule(newEmployeeIndex, newEmployeeIndex, newEmployeeSchedule);
			}
			parkTotalManagement.employeeAdd(newEmployeeIndex, newEmployeeName, newEmployeeJobTitle, newEmployeeYearsOfExperience);
			
			break;
			
		case 2:
			System.out.print("조회할 직원의 관리 번호를 입력하세요 : ");
			int printEmployeeInfo = scanner.nextInt();
			scanner.nextLine();
			parkTotalManagement.printEmployeeInfo(printEmployeeInfo);
			break;
			
		case 3:
			System.out.print("제거할 직원의 관리 번호를 입력하세요 : ");
			int removeEmployee = scanner.nextInt();
			scanner.nextLine();
			parkTotalManagement.removeEmployee(removeEmployee);
			break;
			
		case 4:
			System.out.print("정보를 수정할 직원의 관리 번호를 입력하세요 : ");
			int changeEmployeeIndex = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("직원 이름 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeEmployeeName = scanner.nextLine();
		    
		    System.out.print("직원 직무 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeEmployeeJobTitle = scanner.nextLine();

		    System.out.print("직원 경력 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeEmployeeYearsOfExperience = scanner.nextLine();
		    
			parkTotalManagement.allChangeEmployee(changeEmployeeIndex, changeEmployeeName, changeEmployeeJobTitle, changeEmployeeYearsOfExperience);
		    
			while (true) {
				System.out.print("직원의 스케줄 수정 (수정하지 않으려면 엔터를 누르세요) : ");
				String changeEmployeeSchedule = scanner.nextLine();
				if (!changeEmployeeSchedule.isEmpty()) {
					break;
				}
				parkTotalManagement.changeEmployeeSchedule(changeEmployeeIndex, changeEmployeeIndex, changeEmployeeSchedule);
			}    	    						
			break;
		default:
			scanner.nextLine();
			System.out.println("잘못된 값입니다.");
			break;
		}
	}
	
	// 케이스 3 티켓 관리
	public void manageTickets() {
		System.out.println("1. 티켓 정보 추가 2. 티켓 정보 보기 3. 티켓 제거 4. 티켓 정보 수정");
		int menuChoice = scanner.nextInt();
		
		switch (menuChoice) {
		case 1:
			System.out.print("추가할 티켓의 관리 번호를 설정하세요 : ");
			int newParkTicketIndex = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("추가할 티켓의 가격을 입력하세요 : ");
			int newParkTicketPrice = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("추가할 티켓을 구매한 고객의 이름을 입력하세요 : ");
			String newParkTicketVisitorsName = scanner.nextLine();
			
			System.out.print("추가할 티켓의 방문 날짜를 입력하세요 : ");
			int newParkTicketVisitDate = scanner.nextInt();
			scanner.nextLine();
			
			parkTotalManagement.addParkTicket(newParkTicketIndex, newParkTicketPrice, newParkTicketVisitorsName, newParkTicketVisitDate);
			break;
			
		case 2:
			System.out.print("조회할 티켓의 관리 번호를 입력하세요 : ");
			int printParkTicketInfo = scanner.nextInt();
			scanner.nextLine();
			parkTotalManagement.printParkTicketInfo(printParkTicketInfo);
			break;
			
		case 3:
			System.out.print("제거할 티켓의 관리 번호를 입력하세요 : ");
			int removeParkTicket = scanner.nextInt();
			scanner.nextLine();
			parkTotalManagement.removeParkTicket(removeParkTicket);
			break;
			
		case 4:
			System.out.print("정보를 수정할 티켓의 관리 번호를 입력하세요 : ");
			int changeParkTicketIndex = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("티켓 가격 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeParkTicketPrice = scanner.nextLine();
		    
		    System.out.print("티켓 구매 고객 이름 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeParkTicketVisitorsName = scanner.nextLine();

		    System.out.print("티켓 방문 날짜 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeParkTicketVisitDate = scanner.nextLine();
	    		    
		    // 정보 종합 후 정보 수정 판단
		    if (!changeParkTicketPrice.isEmpty() || !changeParkTicketVisitorsName.isEmpty() || !changeParkTicketVisitDate.isEmpty()) {
		    	parkTotalManagement.allChangeParkTicket(changeParkTicketIndex, changeParkTicketPrice, changeParkTicketVisitorsName, changeParkTicketVisitDate);
			} else {
		        System.out.println("수정된 정보가 없습니다. 티켓 정보가 그대로 유지됩니다.");
		    }    	    						
			break;
		default:
			scanner.nextLine();
			System.out.println("잘못된 값입니다.");
			break;
		}
	}
	
	// 케이스 4 공원 상태 / 관람객 현황
	public void checkParkStatus() {
		System.out.println("공원 관람객 현황 : " + parkTotalManagement.currentGuest() + 
		" / " +	parkTotalManagement.maxGuest());
	}
	// 케이스 5 비상 사태 및 VIP 방문객 시나리오
	public void handleSpecialEvents() {
		System.out.println("1. 공원에 비상 사태 발생시 행동 요령\n" + 
		"1-1. 공룡 우리를 닫고, 관람객과 함께 대피해주세요.\n" + 
		"1-2. 우리 안의 직원은 즉시 밖으로 빠져나와 주세요.");
		
		System.out.println("2. VIP 방문객 방문시 행동 요령\n" + 
				"2-1. 각 직원들은 즉시 팀장에게 보고해주세요.\n" + 
				"2-2. 팀장님들은 팀장 행동 가이드라인에 따라 VIP 방문객을 안내해주시기 바랍니다.");
	}
	
}
