package m2_02_21;

import java.util.Iterator;
import java.util.Scanner; 

public class Project9 {
	Scanner scanner = new Scanner(System.in);
	boolean mainMenu = false;
	private ParkAdministration parkAdministration = new ParkAdministration();

	public static void main(String[] args) {
		Project9 main = new Project9();
		main.initialValue(); // 초기값 설정
		main.start();

	}
	
	public void initialValue() {
		// 공룡 초기값
		parkAdministration.addFlyingDinosaur(0, "큼", "육식성", 12);
		parkAdministration.addFlyingDinosaur(1, "작음", "잡식성", 17);
		parkAdministration.addAquaticDinosaur(2, "작음", "육식성", 24);
		parkAdministration.addAquaticDinosaur(3, "보통", "육식성", 34);
		parkAdministration.addWalkingDinosaur(4, "큼", "초식성", 15);
		parkAdministration.addWalkingDinosaur(5, "보통", "육식성", 8);
		
		parkAdministration.addParkManager(0, "이재일", 374, "티라노둥지 관리", "경리부");
		parkAdministration.addParkManager(0, "김재일", 474, "담당부서 관리", "사업부");
		parkAdministration.addSecurityOfficer(0, "민재일", 107, "어룡둥지 순찰", "보안 1부");
		parkAdministration.addSecurityOfficer(0, "한재일", 213, "화장실 순찰", "보안 2부");
		parkAdministration.addVeterinarian(0, "주재일", 142, "익룡 건강검진", "읍급실");
		parkAdministration.addVeterinarian(0, "신재일", 379, "육상공룡 건강검진", "신경외과");
	}

	
	public void start() {
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
		System.out.println("4. Check Park Status"); // 판매된 티켓 값을 비교해서 공원에 사람이 몇 명이고 앞으로 얼마나 사람을 더 받을 수 있는지 체크
		System.out.println("5. Handle Special Events"); // 비상 사태 때 각 직원의 행동 요령 적어주기.
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");
	}
	
	public void handleMenuChoice(int choice) {
		switch (choice) {
		case 1:
			manageDinosaurs();
			// 다양한 종류의 공룡 관리 기능 호출
			break;
		case 2:
			manageEmployees();
			// 다양한 타입의 종업원 관리 기능 호출
			break;
		case 3:
			manageTickets();
			// 계절권 티켓 관리 기능 호출
			break;
		case 4:
			// checkParkStatus();
			// 공원 상태 확인 기능 호출
			break;
		case 5:
			// handleSpecialEvents();
			// 특별 이벤트 처리 기능 호출
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
		case 1: // 1. 공룡 정보 추가		
			System.out.print("추가할 공룡의 관리 번호를 설정하세요 : ");
			int newDinoIndex = scanner.nextInt();
			scanner.nextLine();
			
			// 인덱스 유효범위 검사
			boolean checkDinoIndex = parkAdministration.checkDinoIndex("add", newDinoIndex);	
			if (!checkDinoIndex) {
				break;
			}
			
			System.out.print("추가할 공룡의 크기를 입력하세요 : ");
			String newDinoSize = scanner.nextLine();
			System.out.print("추가할 공룡의 식성을 입력하세요 : ");
			String newDinoDiet = scanner.nextLine();
				
			String newDinoType = parkAdministration.getDinoType("add"); // 공룡 타입 정하기(익룡, 어룡 등)
				
			if (newDinoType.equals("익룡")) {
				System.out.print("익룡의 날개 길이를 입력하세요 : ");
				int newWingspan = scanner.nextInt();
				scanner.nextLine();	
				parkAdministration.addFlyingDinosaur(newDinoIndex, newDinoSize, newDinoDiet, newWingspan);
			} else if (newDinoType.equals("어룡")) {
				System.out.print("어룡의 수영 속도를 입력하세요 : ");
				int newSwimSpeed = scanner.nextInt();
				scanner.nextLine();		
				parkAdministration.addAquaticDinosaur(newDinoIndex, newDinoSize, newDinoDiet, newSwimSpeed);
			} else if (newDinoType.equals("육상공룡")) {
				System.out.print("육상공룡의 걷는 속도를 입력하세요 : ");
				int newWalkingSpeed = scanner.nextInt();
				scanner.nextLine();	
				parkAdministration.addWalkingDinosaur(newDinoIndex, newDinoSize, newDinoDiet, newWalkingSpeed);
			}
			System.out.println("공룡 정보가 추가되었습니다.");
			break;
			
		case 2: // 2. 공룡 정보 보기
			System.out.print("조회할 공룡의 관리 번호를 입력하세요(전체 조회를 원할 경우 -1 입력) : ");
			int printDinoInfo = scanner.nextInt();
			scanner.nextLine();
			parkAdministration.printDinoInfo(printDinoInfo);
			break;
			
		case 3: // 3. 공룡 제거
			System.out.print("제거할 공룡의 관리 번호를 입력하세요 : ");
			int removeDino = scanner.nextInt();
			scanner.nextLine();
			parkAdministration.removeDino(removeDino);
			break;
			
		case 4: // 4. 공룡 정보 수정
			System.out.print("정보를 수정할 공룡의 관리 번호를 입력하세요 : ");
			int changeDinoIndex = scanner.nextInt();
			scanner.nextLine();
			
			// 인덱스 유효범위 검사
			// checkDinoIndex 변수는 case 1에서 이미 선언했다
			checkDinoIndex = parkAdministration.checkDinoIndex("change", changeDinoIndex);
			if (!checkDinoIndex) {
				break;
			}

			String changeDinoTypeValue = parkAdministration.getDinosaurType(changeDinoIndex);
			
		    System.out.print("공룡 크기 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeDinoSize = scanner.nextLine();
		    System.out.print("공룡 식성 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeDinoDiet = scanner.nextLine();
		    
		    String changeDinoType = parkAdministration.getDinoType("change"); // 타입 수정
		    int changeDinoIntegerValue = parkAdministration.changeDinoTypeValue(changeDinoType);
		    parkAdministration.allChangeDino
			(changeDinoIndex, changeDinoSize, changeDinoDiet, changeDinoType, changeDinoIntegerValue);
    
		    if (!changeDinoSize.isEmpty()) {
		    	System.out.println("입력한 내용이 성공적으로 반영되었습니다.");
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
		case 1: // 1. 직원 정보 추가
			System.out.print("추가할 직원의 사원 번호를 입력하세요 : ");
			int newEmployeeIndexCreate = scanner.nextInt();
			// 해당 사번 중복 체크
			int employeeIndexOverlapCheck = parkAdministration.getEmployeeIndex(newEmployeeIndexCreate);
			
			if (employeeIndexOverlapCheck != -1) {
				System.out.println("해당 사원 번호는 이미 사용중입니다.");
				break;
			}
			// 사원 번호로 인덱스 만들기(문서 관리 번호)
			int employeePaperIndexCreate = parkAdministration.createEmployeeIndex(newEmployeeIndexCreate);
			if (employeePaperIndexCreate == -1) {
				System.out.println("현재 용량 문제로 더 이상 사원을 추가할 수 없습니다.");
				System.out.println("담당부서에 문의해주세요.");
				break;
			}
			
			// 인덱스 유효범위 검사
			boolean checkEmployeeIndex = parkAdministration.checkEmployeeIndex("add", employeePaperIndexCreate);	
			if (!checkEmployeeIndex) {
				break;
			}
			
			System.out.print("추가할 직원의 이름를 입력하세요 : ");
			String newEmployeeName = scanner.nextLine();
			
	        while (true) {
	            System.out.print("추가할 스케줄을 입력하세요: ");
	            String schedule = scanner.nextLine();
	            newEmployee.addSchedule(schedule);

	            System.out.print("더 추가하시겠습니까? (y/n): ");
	            String choice = scanner.nextLine();
	            continueAdding = choice.equalsIgnoreCase("y");
	        }
			
			
			System.out.print("추가할 직원의 스케줄을 입력하세요 : ");
			String newEmployeeSchedule = scanner.nextLine();
			
			// 공룡 타입 정하기(익룡, 어룡 등)
			String newDinoType = parkAdministration.getDinoType("add");
			
			
			
			
			
			
			
			
			
			
			
			
			
			int employeeIndex;
			int newEmployeeEmpld;
			
			System.out.print("추가할 직원의 사원 번호를 입력하세요 : ");
			newEmployeeEmpld = scanner.nextInt();
			scanner.nextLine();
				
			employeeIndex = parkAdministration.createEmployeeIndex(newEmployeeEmpld); // 인덱스값 받아오기
			
			if (employeeIndex != -1) {
				break;
			} else {
				System.out.print("더 이상 사원을 추가할 수 없습니다.");
			}
			
			System.out.print("추가할 직원의 스케줄을 입력하세요 : ");
			String newEmployeeSchedule = scanner.nextLine();
			
			String newEmployeeRole;
			
			while (true) {
				System.out.print("추가할 직원의 직무를 입력하세요 : ");
				newEmployeeRole = scanner.nextLine();
				
				if (newEmployeeRole.equals("매니저") || newEmployeeRole.equals("보안요원") || 
					newEmployeeRole.equals("수의사")) {
					break;
				} else {
					System.out.println("잘못된 값입니다. 다시 입력해주세요.");
				}
			}
			
			if (newEmployeeRole.equals("매니저")) {
				System.out.print("매니저 소속을 입력하세요 : ");
				String newDepartment = scanner.nextLine();
				parkAdministration.addParkManager(employeeIndex, newEmployeeName, newEmployeeEmpld, newEmployeeSchedule, newDepartment);
			} else if (newEmployeeRole.equals("보안요원")) {
				System.out.print("보안요원의 근무시간을 입력하세요 : ");
				String newShift = scanner.nextLine();	
				parkAdministration.addSecurityOfficer(employeeIndex, newEmployeeName, newEmployeeEmpld, newEmployeeSchedule, newShift);
			} else if (newEmployeeRole.equals("수의사")) {
				System.out.print("수의사의 전공을 입력하세요 : ");
				String newSpecialization = scanner.nextLine();
				parkAdministration.addVeterinarian(employeeIndex, newEmployeeName, newEmployeeEmpld, newEmployeeSchedule, newSpecialization);
			}
			System.out.println("직원 정보가 추가되었습니다.");
			break;
			
		case 2:
			System.out.print("조회할 직원의 사원 번호를 입력하세요 : ");
			newEmployeeEmpld = scanner.nextInt();
			scanner.nextLine();
			employeeIndex = parkAdministration.getEmployeeIndex(newEmployeeEmpld); // 인덱스값 받아오기
			
			if (employeeIndex != -1) {
				parkAdministration.printEmployeeInfo(employeeIndex);
			} else {
				System.out.print("해당 사원 번호는 존재하지 않습니다.");
			}
			break;
			
			
		case 3:
			System.out.print("제거할 직원의 사원 번호를 입력하세요 : ");
			newEmployeeEmpld = scanner.nextInt();
			scanner.nextLine();
			employeeIndex = parkAdministration.getEmployeeIndex(newEmployeeEmpld); // 인덱스값 받아오기

			if (employeeIndex != -1) {
				parkAdministration.removeEmployee(employeeIndex);
			} else {
				System.out.print("해당 사원 번호는 존재하지 않습니다.");
			}
			break;
			
		case 4:
			System.out.print("정보를 수정할 직원의 사원 번호를 입력하세요 : ");
			newEmployeeEmpld = scanner.nextInt();
			scanner.nextLine();
			employeeIndex = parkAdministration.getEmployeeIndex(newEmployeeEmpld); // 인덱스값 받아오기

			if (employeeIndex != -1) {
				parkAdministration.removeEmployee(employeeIndex);
			} else {
				System.out.print("해당 사원 번호는 존재하지 않습니다.");
				break;
			}
			
		    System.out.print("직원 이름 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeEmployeeName = scanner.nextLine();

		    System.out.print("직원 사원번호 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String EmployeeEmpldInput = scanner.nextLine(); 
		    int changeEmployeeEmpld;
		    if (!EmployeeEmpldInput.isEmpty()) {
		    	changeEmployeeEmpld = Integer.parseInt(EmployeeEmpldInput);
		    } else {
		    	changeEmployeeEmpld = -1;
		    }
		    
		    System.out.print("직원 스케줄 수정 (수정하지 않으려면 엔터를 누르세요): ");
		    String changeEmployeeSchedule = scanner.nextLine();
		    
		    String changeEmployeeType = parkAdministration.getEmployeeType(employeeIndex);
		    String currentEmployeeValue;
		    
		    if (changeEmployeeType.equals("공원매니저")) {
		    	System.out.print("해당 매니저의 담당 부서를 입력하세요 (수정하지 않으려면 엔터를 누르세요): ");
		    	currentEmployeeValue = scanner.nextLine();
				parkAdministration.allChangeemployee
				(employeeIndex, changeEmployeeName, changeEmployeeEmpld, changeEmployeeSchedule, currentEmployeeValue);
			} else if (changeEmployeeType.equals("보안요원")) {
		    	System.out.print("해당 보안요원의 근무시간을 입력하세요 (수정하지 않으려면 엔터를 누르세요): ");
		    	currentEmployeeValue = scanner.nextLine();
				parkAdministration.allChangeemployee
				(employeeIndex, changeEmployeeName, changeEmployeeEmpld, changeEmployeeSchedule, currentEmployeeValue);
			} else if (changeEmployeeType.equals("수의사")) {
		    	System.out.print("해당 수의사의 전공을 입력하세요 (수정하지 않으려면 엔터를 누르세요): ");
		    	currentEmployeeValue = scanner.nextLine();
				parkAdministration.allChangeemployee
				(employeeIndex, changeEmployeeName, changeEmployeeEmpld, changeEmployeeSchedule, currentEmployeeValue);
			}
		    
		    if (employeeIndex != -1) {
		    	System.out.println("입력한 내용이 성공적으로 반영되었습니다.");
			}
		    break;
		default:
			System.out.println("잘못된 값입니다.");
			break;
		}
	}
	// 케이스 3 티켓 관리
	public void manageTickets() {
		
	}
}
