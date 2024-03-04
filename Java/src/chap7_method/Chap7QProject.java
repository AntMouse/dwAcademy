package chap7_method;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class Chap7QProject {
	
	int mamaximumGuestAmount = 5000;
	int currentGuestAmount = 4000;
	
	String[] locationList = { "A", "B", "C", "D", "E" };
	
	String[][] employeeList = {
			{"이재일", "이일민", "이수만", "이준군", "이짐훈", "이민진", "이무봄"},
			{"김재일", "김일민", "김수만", "김준군", "김짐훈"},
			{"민재일", "민일민", "민수만", "민준군"},
			{"한재일", "한일민", "한수만", "한준군", "한짐훈", "한김훈"},
			{"박재일", "박일민", "박수만"}	
	};
	
	String[] dinoList = new String[99];
	Scanner scanner = new Scanner(System.in);
	
	boolean mainMenu = false;
	
	public static void main(String[] args) {
		Chap7QProject main = new Chap7QProject();
		main.start();
	}
	
	public void start() {
		do {
			displayMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			handleMenuChoice(choice);
			while (true) {
				System.out.print("메뉴를 다시 불러오기는 '메뉴', 종료는 '종료'를 입력 : ");
				String menuRestart = scanner.nextLine();
				if (menuRestart.equals("메뉴")) {
					mainMenu = true;
					System.out.println();
					break;
				} else if (menuRestart.equals("종료")) {
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
		System.out.println("Welcome to Mesozoic Eden Assistant!");
		System.out.println("1. Add Dinosaur");
		System.out.println("2. Check Park Hours");
		System.out.println("3. Greet Guest");
		System.out.println("4. Check Visitors Count");
		System.out.println("5. Manage Staff");
		System.out.println("6. Exit");
		System.out.print("Enter your choice(선택한 번호에서 '메뉴'를 입력하면 밖으로 이동) : ");
	}
	
	public void handleMenuChoice(int choice) {
		switch (choice) {
		case 1:
			addDinosaur();
			System.out.println();
			break;
			
		case 2:
			checkParkHours();
			System.out.println();
			break;
			
		case 3:
			greetGuest();
			System.out.println();
			break;
			
		case 4:
			checkVisitorsCount();
			System.out.println();
			break;
			
		case 5:
			manageStaff();
			System.out.println();
			break;
			
		case 6:
			System.out.println("Exiting...");
			System.exit(0);
			break;

		default:
			System.out.println();
			System.out.println("잘못된 값입니다. 다시 입력해주세요.");
			System.out.println();
			break;
		}
	}
	// 메소드 1번
	public void addDinosaur() {	
		totalLoop:
		while (true) {
			System.out.print("공룡을 추가하려면 '추가', 제거하려면 '제거', 목록을 보려면 '목록' 입력 : ");
			String inputValue1_1 = scanner.nextLine();
			if (inputValue1_1.equals("추가")) {
				boolean dinoGap = false;
				for (int i = 0; i < dinoList.length; i++) {
					if (dinoList[i] == null) {
						dinoGap = true;
					}
				}
				
				if (!dinoGap) {
					System.out.println("저장 공간이 부족해 공룡을 추가할 수 없습니다.");
					break totalLoop;
				}
				
				while (dinoGap) {
					System.out.print("추가할 공룡을 입력하세요(최대 99까지 가능) : ");
					String inputValue1_2 = scanner.nextLine();
					if (inputValue1_2 != null) {
						for (int i = 0; i < dinoList.length; i++) {
							if (dinoList[i] != null) {
								if (inputValue1_2.equals(dinoList[i])) {
									System.out.println("해당 공룡은 이미 등록되어 있습니다.");
									break totalLoop;
								}
							}
						}
						
						for (int i = 0; i < dinoList.length; i++) {
							if (dinoList[i] == null) {
								dinoList[i] = inputValue1_2;
								System.out.println("공룡이 추가되었습니다.");
								break totalLoop;
							}
						}
					}
				}	
			} else if (inputValue1_1.equals("제거")) {
				System.out.print("제거할 공룡을 입력하세요 : ");
				String inputValue1_3 = scanner.nextLine();
				if (inputValue1_3 != null) {
					for (int i = 0; i < dinoList.length; i++) {
						if (inputValue1_3.equals(dinoList[i])) {
							dinoList[i] = null;
							if (dinoList[i] == null) {
								System.out.println("해당 공룡을 제거했습니다.");
								break totalLoop;
							}
						}
					}
					
					if (true) {
						System.out.println("해당 공룡은 존재하지 않습니다.");
						break totalLoop;
					}
				}
			} else if (inputValue1_1.equals("목록")) {
				boolean dinoCheck = false;
				for (int i = 0; i < dinoList.length; i++) {
					if (dinoList[i] != null) {
						dinoCheck = true;
						break;
					}
				}
				
				if (dinoCheck) {
					System.out.println("등록된 공룡 목록입니다.");
					for (int i = 0; i < dinoList.length; i++) {
						if (dinoList[i] != null) {
							System.out.println((i + 1) + ". " + dinoList[i]);
						}
					}
				} else {
					System.out.println("등록된 공룡이 없습니다.");
				}
				
				break totalLoop;
			} else if (inputValue1_1.equals("메뉴")) {
				System.out.println("메인 메뉴로 이동합니다.");
				break totalLoop;
			} else {
				System.out.println("잘못된 값입니다.");
			}
		}
		
	}
	// 메소드 2번
	public void checkParkHours() {	
		boolean methodTotalCheck = false;
		int inputTimeCheck = 0;
		System.out.print("현재 시간을 사용하려면 24를, 임의로 정한 값을 시간으로 사용하려면 " + 
		"0 ~ 23 사이의 정수를 입력하세요 : ");
		String inputTime = scanner.nextLine();
	
		if (inputTime.equals("메뉴")) {
			System.out.println("메인 메뉴로 이동합니다.");
			methodTotalCheck = true;
		} else {
			inputTimeCheck = Integer.parseInt(inputTime);
		}
		
		if (!methodTotalCheck) {
			if (inputTimeCheck == 24) {
				inputTimeCheck = LocalDateTime.now().getHour();
			} 
			
			if (10 <= inputTimeCheck && inputTimeCheck <= 19) {
				System.out.println("공원이 열려있습니다.");
			} else if (inputTimeCheck < 10 || inputTimeCheck > 19) {
				System.out.println("공원이 닫혀있습니다.");
			} else {
				System.out.println("올바른 값이 아닙니다.");
			}	
		}
	}
	// 메소드 3번
	public void greetGuest() {	
		int totalEmployeeAmount = 0;
		for (int i = 0; i < employeeList.length; i++) {
			totalEmployeeAmount += (employeeList[i].length);
		}
		
		System.out.println();
		System.out.println("Welcome to Mesozoic Eden Assistant!");
		System.out.println("저희 공원에 방문해주셔서 감사합니다.");
		System.out.println("1. 공원 개장 시간 : 10시 ~ 19시");
		System.out.println("2. 현재 방문객 수 : " + currentGuestAmount + "명");	
		System.out.println("3. 현재 근무 중인 직원 수 : " + totalEmployeeAmount + "명");
	}
	// 메소드 4번
	public void checkVisitorsCount() {
		boolean methodTotalCheck = false;
		int additionGuestAmountCheck = 0;
		
		System.out.print("추가 방문객 수 : ");
		String additionGuestAmount = scanner.nextLine();
		
		if (additionGuestAmount.equals("메뉴")) {
			System.out.println("메인 메뉴로 이동합니다.");
			methodTotalCheck = true;
		} else {
			additionGuestAmountCheck = Integer.parseInt(additionGuestAmount);
		}
		
		if (!methodTotalCheck) {
			int guestSum = currentGuestAmount + additionGuestAmountCheck;
			
			if (mamaximumGuestAmount >= guestSum) {
				System.out.println();
				System.out.println("추가 방문객을 받을 수 있습니다.");
				System.out.println(additionGuestAmountCheck + "명을 추가로 받았습니다.");
				System.out.println("현재 공원 상황 : " + guestSum + " / " + mamaximumGuestAmount);
				currentGuestAmount = guestSum;
			} else {
				System.out.println();
				System.out.println("추가 방문객을 받을 수 없습니다.");
				System.out.println("현재 공원 상황 : " + currentGuestAmount + " / " + mamaximumGuestAmount);
				System.out.println("최대 " + (mamaximumGuestAmount - currentGuestAmount) + "명까지만 더 받을 수 있습니다." );
			}
		}
	}
	// 메소드 5번
	public void manageStaff() {	
		boolean secondLoopCondition = true;
		while (true) {
			System.out.print("담당구역으로 직원 찾기 = '담당구역' / 이름으로 직원 찾기 = '이름' : ");
			String selectEmployee = scanner.nextLine();
			
			if (selectEmployee.equals("메뉴")) {
				System.out.println("메인 메뉴로 이동합니다.");
				secondLoopCondition = false;
				break;
			}
				
			if (selectEmployee.equals("담당구역")) {
				for (int i = 0; i < employeeList.length; i++) {
					System.out.println((i + 1) + ". " + locationList[i] + "구역");
				}
				System.out.println();
				System.out.print("원하는 담당구역의 번호를 입력하세요 : ");
				int selectLocation = scanner.nextInt();
				scanner.nextLine();
				
				for (int i = 0; i < employeeList.length; i++) {
					if (selectLocation == (i + 1)) {
						System.out.print(locationList[i] + "구역 직원 목록 : ");
						for (int j = 0; j < employeeList[i].length; j++) {	
							if (j != (employeeList[i].length - 1)) {
								System.out.print(employeeList[i][j] + ", ");
							} else {
								System.out.println(employeeList[i][j]);
							}					
						}
					}
				} break;
			} else if (selectEmployee.equals("이름")) {
				System.out.println("공원 직원 목록입니다.");
				for (int i = 0; i < employeeList.length; i++) {
					for (int j = 0; j < employeeList[i].length; j++) {	
						if (j != (employeeList[i].length - 1)) {
							System.out.print(employeeList[i][j] + ", ");
						} else {
							System.out.println(employeeList[i][j]);
						}					
					}		
				} 
				System.out.println();
				break;
			} else {
				System.out.println("잘못된 값입니다.");
			}
		}	
		
		secondLoop:
		while (secondLoopCondition) {
			System.out.print("정보를 조회하려는 직원의 이름을 입력해주세요 : ");
			String employeeName = scanner.nextLine();
			int conditionCheck = 0;
			
			for (int i = 0; i < employeeList.length; i++) {
				for (int j = 0; j < employeeList[i].length; j++) {
					if (employeeName.equals(employeeList[i][j])) {
						System.out.println(employeeList[i][j] + " 직원에 대한 정보입니다.");
						conditionCheck++;
						break secondLoop;
					}
				}
			}
			
			if (conditionCheck == 0) {
				System.out.println("해당 직원을 찾을 수 없습니다.");
			}
		}
	}

}
