package chap9_inheritance_polymorphism;

import java.util.Scanner;

import m2_02_16.copy.Test8_Q8_Ticket;

public class Chap9QProject1 {
	// 0. 스캐너
	Scanner scanner = new Scanner(System.in);
	// 0-1. 전체 출력 메시지1 - 인덱스 값 불일치 메서드
	public void indexErrorMessage() {
		System.out.println("유효하지 않은 인덱스입니다.");
	}
	// 0-2. 전체 출력 메시지2 - 인덱스 값 불일치 및 해당 타입(공룡, 직원 등) 동시 출력 메서드
	public void typeErrorMessage(String ouputMessage) {
		System.out.println("유효하지 않은 인덱스이거나 해당하는 " + ouputMessage + "이 없습니다.");
	}
	// 0-3-1. 인덱스 유효 범위에 다른 정보가 있는지 체크
    public int findEmptyIndex(Object[] obj) {
        for (int i = 0; i < obj.length; i++) {
            if (isRegistered(i, obj)) { // << 메서드는 아래에 있음
                return i;
            }
        }
        return -1; // 비어있는 인덱스를 찾지 못한 경우
    }
    // 0-3-2. 해당 인덱스가 값이 비어있는지 체크
    public boolean isRegistered(int index, Object[] obj) {
        return obj[index] == null; // 등록이 되어 있지 않아서 비어 있으면 true 반환
    }   
    // 0-3-3. 공통 메서드
    private boolean checkIndex(String addAndChange, Object[] array, int index, String objectType) {
        if (0 <= index && index < array.length) {
            // if를 2번 써야 무조건 아래 메시지가 출력 되는 걸 방지할 수 있다 and 조건 쓰기 힘듦
            if (!isRegistered(index, array) && addAndChange.equals("add")) {
                System.out.println("해당 인덱스에는 이미 " + objectType + "이 등록되어 있습니다.");
                return false;
            } else if (isRegistered(index, array) && addAndChange.equals("change")) {
                System.out.println("해당 인덱스에는 등록된 " + objectType + "이 없습니다.");
                return false;
            }
        } else {
            indexErrorMessage(); // 유효하지 않은 인덱스
            return false;
        }
        return true;
    }

	// 1-1. 공룡 관리
	// 공룡 999마리 추가
    private Chap9Q1Dinosaur[] dino = new Chap9Q1Dinosaur[999];
    // 각 공룡 추가 메서드 통합 관리
    public void addDinosaur(int index, String size, String diet, Chap9Q1Dinosaur dinosaur) {
        if (0 <= index && index < dino.length) {
            if (isDinosaurRegistered(index)) {
                dino[index] = dinosaur;
            } else {
                int findEmptyIndex = findEmptyIndex(); // 비어있는 공간 있는지 체크
                if (findEmptyIndex != -1) {
                	System.out.println("해당 인덱스에는 이미 공룡이 등록되어 있습니다.");
                	System.out.println(findEmptyIndex + " 번은 비어있으니 공룡을 등록하려면 " + 
                	findEmptyIndex + " 번에 등록해주세요.");
				} else {
					System.out.println("현재 빈 저장 공간이 없어서 더 이상 공룡을 등록할 수 없습니다.");
				}
            }
        } else {
            indexErrorMessage();
        }
    }

    // 익룡 추가
    public void addFlyingDinosaur(int index, String size, String diet, int wingspan) {
        addDinosaur(index, size, diet, new FlyingDinosaur(size, diet, wingspan));
    }
    // 어룡 추가
    public void addAquaticDinosaur(int index, String size, String diet, int swimSpeed) {
        addDinosaur(index, size, diet, new AquaticDinosaur(size, diet, swimSpeed));
    }
    // 육상 공룡 추가
    public void addWalkingDinosaur(int index, String size, String diet, int walkingSpeed) {
        addDinosaur(index, size, diet, new WalkingDinosaur(size, diet, walkingSpeed));
    }	
    
    // 인덱스 유효범위 검사
    public boolean checkDinoIndex(String addAndChange, int index) {
		if (0 <= index && index < dino.length) {
			// if를 2번 써야 무조건 아래 메시지가 출력 되는 걸 방지할 수 있다 and 조건 쓰기 힘듦
            if (!isDinosaurRegistered(index) && addAndChange.equals("add")) {
                System.out.println("해당 인덱스에는 이미 공룡이 등록되어 있습니다.");
                return false;
            } else if (isDinosaurRegistered(index) && addAndChange.equals("change")) {
                System.out.println("해당 인덱스에는 등록된 공룡이 없습니다.");
                return false;
			}
		} else {
			indexErrorMessage(); // 유효하지 않은 인덱스
			return false;
		}
		return true;
    }
    
    // 공룡 정보 출력
    public void printDinoInfo(int index) {
        if (index == -1) {
            for (int i = 0; i < dino.length; i++) {
                if (dino[i] != null) {
                    printSingleDinoInfo(i);
                }
            }
        } else if (0 <= index && index < dino.length && dino[index] != null) {
            printSingleDinoInfo(index);
        } else {
            indexErrorMessageTypePlus(dinosaurMethodInputName);
        }
    }

    // 단일 공룡 정보 출력
    private void printSingleDinoInfo(int index) {
        System.out.println("공룡 번호 : " + index);
        System.out.println("크기 : " + dino[index].getSize());
        System.out.println("식성 : " + dino[index].getDiet());
        System.out.println("위치 : " + dino[index].getHabitat());

        Chap9Q1Dinosaur currentDino = dino[index];
        if (currentDino instanceof FlyingDinosaur) {
            FlyingDinosaur flyingDino = (FlyingDinosaur) currentDino;
            System.out.println("날개 길이 : " + flyingDino.getWingspan());
        } else if (currentDino instanceof AquaticDinosaur) {
            AquaticDinosaur aquaticDino = (AquaticDinosaur) currentDino;
            System.out.println("수영 속도 : " + aquaticDino.getSwimSpeed());
        } else if (currentDino instanceof WalkingDinosaur) {
            WalkingDinosaur walkingDino = (WalkingDinosaur) currentDino;
            System.out.println("걷는 속도 : " + walkingDino.getWalkingSpeed());
        }
        System.out.println("==================================");
    }
    
    // 공룡 정보 제거하기
    public void removeDino(int index) {
        if (0 <= index && index < dino.length && dino[index] != null) {
            System.out.println(dino[index].getSize() + " 크기의 " + dino[index].getClass().getSimpleName() + "을(를) 공룡 목록에서 제거합니다.");
            dino[index] = null;
        } else {
            System.out.println("유효하지 않은 인덱스이거나 제거할 공룡이 없습니다.");
        }
    }
    
    // 등록된 공룡 숫자 확인
    public int dinoAmountCheck() {
        int dinoAmount = 0;
        for (int i = 0; i < dino.length; i++) {
            if (dino[i] != null) {
                dinoAmount++;
            }
        }
        return dinoAmount;
    }
    
    // 공룡 종류 알려주기
    public String getDinosaurType(int index) {
        if (0 <= index && index < dino.length && dino[index] != null) {
            if (dino[index] instanceof FlyingDinosaur) {
                return "익룡";
            } else if (dino[index] instanceof AquaticDinosaur) {
                return "어룡";
            } else if (dino[index] instanceof WalkingDinosaur) {
                return "육상 공룡";
            } else {
                return "알 수 없는 종류";
            }
        } else {
            return "유효하지 않은 인덱스이거나 추가된 공룡이 없습니다.";
        }
    }
    
    // 공룡 타입 입력값 인식, 범위에 없는 값 입력하면 다시 반복
    public String getDinoType(String operation) {
        while (true) {
            String dinoType;
            if (operation.equals("add")) {
                System.out.print("공룡의 타입(익룡/어룡/육상공룡)을 입력하세요 : ");
            } else if (operation.equals("change")) {
                System.out.print("공룡 타입(익룡/어룡/육상공룡) 수정 (수정하지 않으려면 엔터를 누르세요): ");
            } else {
                System.out.println("잘못된 작업입니다. 다시 입력해주세요.");
                return "";
            }
            dinoType = scanner.nextLine();    
            if (dinoType.equals("익룡") || dinoType.equals("어룡") || 
                dinoType.equals("육상공룡")) {
                return dinoType;
            } else if (dinoType.isEmpty() && operation.equals("change")) {
            	return "";
            } else {
                System.out.println("잘못된 값입니다. 다시 입력해주세요.");
            }
        }
    }
    
    // 공룡 정보 수정하기
    public void allChangeDino(int index, String size, String diet, String type, int value) {  
        if (0 <= index && index < dino.length && dino[index] != null) { 
        	// 기존 공룡값 가져오기
        	String prevSize = dino[index].getSize();
        	String prevDiet = dino[index].getDiet();
            // 타입 변경
            switch (type) {
                case "어룡":
                    if (!(dino[index] instanceof AquaticDinosaur)) {
                        dino[index] = new AquaticDinosaur(prevSize, prevDiet, 0);
                    }
                    break;
                case "익룡":
                    if (!(dino[index] instanceof FlyingDinosaur)) {
                        dino[index] = new FlyingDinosaur(prevSize, prevDiet, 0);
                    }
                    break;
                case "육상공룡":
                    if (!(dino[index] instanceof WalkingDinosaur)) {
                        dino[index] = new WalkingDinosaur(prevSize, prevDiet, 0);
                    }
                    break;
                default:
                    break;
            }

            // 입력된 값이 비어있지 않으면 새 값으로 수정, 비어있으면 기존 값으로 유지
	        if (!size.isEmpty()) {
	            dino[index].changeSize(size);
	        } 
	        if (!diet.isEmpty()) {
	            dino[index].changeDiet(diet);
	        } 

	        if ((value != -1)) {
	        	// FlyingDinosaur 인 경우
	        	if (dino[index] instanceof FlyingDinosaur) {
	                FlyingDinosaur flyingDino = (FlyingDinosaur) dino[index];
	                flyingDino.changeWingspan(value);
	            }
	            // AquaticDinosaur 인 경우
	        	else if (dino[index] instanceof AquaticDinosaur) {
	                AquaticDinosaur aquaticDino = (AquaticDinosaur) dino[index];
	                aquaticDino.changeSwimSpeed(value);
	            }
	            // WalkingDinosaur 인 경우
	        	else if (dino[index] instanceof WalkingDinosaur) {
	                WalkingDinosaur walkingDino = (WalkingDinosaur) dino[index];
	                walkingDino.changeWalkingSpeed(value);
	            }
			}
        } else {
            System.out.println("유효하지 않은 인덱스이거나 수정할 공룡이 없습니다.");
        }
    }
  
    // 공룡 타입 고유값 수정
    public int changeDinoTypeValue(String changeDinoTypeValue) {
    	String[][] dinoTypeMessage = {
    			{"익룡", "어룡", "육상공룡"},
    			{"의 날개 길이를", "의 수영 속도를", "의 걷는 속도를"}
    			};
    	
    	for (int i = 0; i < 3; i++) {
    		if (changeDinoTypeValue.equals(dinoTypeMessage[0][i])) {
    			System.out.print(changeDinoTypeValue + dinoTypeMessage[1][i] + "입력하세요 (수정하지 않으려면 엔터를 누르세요): ");
			}
		}
    	String inputOriginalValue = scanner.nextLine();
    	int integerOriginalValue;
    	if (!inputOriginalValue.isEmpty()) {
	    	integerOriginalValue = Integer.parseInt(inputOriginalValue);
	    } else {
	    	integerOriginalValue = -1; // 나이를 -1로 설정하여 입력이 없음을 나타냄
	    }
    	return integerOriginalValue;
    }
    
    
    
    
    // 직원 관리
    // 직원 999명 추가
    private Chap9Q2Employee[] employee = new Chap9Q2Employee[999];
    // 직원 문자열 변수
    String employeeMethodInputName = "직원";
    // 직원 배열 길이 정보
    public int employeeClassLength() {
		return employee.length;
	}

    // 비어있는 직원 등록 가능한 인덱스 찾기
    public int findEmptyEmployeeIndex() {
        for (int i = 0; i < employee.length; i++) {
            if (isEmployeeRegistered(i)) { // << 메서드는 아래에 있음
                return i;
            }
        }
        return -1; // 비어있는 인덱스를 찾지 못한 경우
    }
    // 해당 직원 등록 여부 확인 메서드
    public boolean isEmployeeRegistered(int index) {
        return employee[index] == null; // 직원이 등록이 되어 있지 않아서 비어 있으면 true 반환
    }
 
    // 각 직원 추가 메서드 통합 관리
    public void addEmployee(int index, String name, int empld, Chap9Q2Employee employee, String...schedules) {
        if (0 <= index && index < this.employee.length) {
            if (isEmployeeRegistered(index)) {
            	this.employee[index] = new Chap9Q2Employee(name, empld, schedules);
            } else {
                int findEmptyIndex = findEmptyEmployeeIndex(); // 비어있는 공간 있는지 체크
                if (findEmptyIndex != -1) {
                	System.out.println("해당 인덱스에는 이미 직원이 등록되어 있습니다.");
                	System.out.println(findEmptyIndex + " 번은 비어있으니 직원을 등록하려면 " + 
                	findEmptyIndex + " 번에 등록해주세요.");
				} else {
					System.out.println("현재 빈 저장 공간이 없어서 더 이상 직원을 등록할 수 없습니다.");
				}
            }
        } else {
            indexErrorMessage();
        }
    }

    // 매니저 추가
    public void addParkManager(int index, String name, int empld, String department, String...schedules) {
        addEmployee(index, name, empld, new ParkManager(name, empld, department, schedules));
    }
    // 보안요원 추가
    public void addSecurityOfficer(int index, String name, int empld, String shift, String...schedules) {
        addEmployee(index, name, empld, new SecurityOfficer(name, empld, shift, schedules));
    }
    // 수의사 추가
    public void addVeterinarian(int index, String name, int empld, String specialization, String...schedules) {
        addEmployee(index, name, empld, new Veterinarian(name, empld, specialization, schedules));
    }	
    
    // 인덱스 유효범위 검사
    public boolean checkEmployeeIndex(String addAndChange, int index) {
		if (0 <= index && index < employee.length) {
			// if를 2번 써야 무조건 아래 메시지가 출력 되는 걸 방지할 수 있다 and 조건 쓰기 힘듦
            if (!isEmployeeRegistered(index) && addAndChange.equals("add")) {
                System.out.println("해당 인덱스에는 이미 직원이 등록되어 있습니다.");
                return false;
            } else if (isEmployeeRegistered(index) && addAndChange.equals("change")) {
                System.out.println("해당 인덱스에는 등록된 직원이 없습니다.");
                return false;
			}
		} else {
			indexErrorMessage(); // 유효하지 않은 인덱스
			return false;
		}
		return true;
    }
    
    // 직원 정보 출력
    public void printEmployeeInfo(int index) {
        if (index == -1) {
            for (int i = 0; i < employee.length; i++) {
                if (employee[i] != null) {
                    printSingleEmployeeInfo(i);
                }
            }
        } else if (0 <= index && index < employee.length && employee[index] != null) {
            printSingleEmployeeInfo(index);
        } else {
            indexErrorMessageTypePlus(employeeMethodInputName);
        }
    }

    // 단일 직원 정보 출력
    private void printSingleEmployeeInfo(int index) {
        System.out.println("문서 관리 번호 : " + index);
        System.out.println("이름 : " + employee[index].getName());
        System.out.println("직원 사번 : " + employee[index].getEmpld());
        System.out.println("스케줄 : " + employee[index].printSchedule());

        Chap9Q2Employee currentEmployee = employee[index];
        if (currentEmployee instanceof ParkManager) {
        	ParkManager parkManager = (ParkManager) currentEmployee;
            System.out.println("소속 부서 : " + parkManager.getDepartment());
        } else if (currentEmployee instanceof SecurityOfficer) {
        	SecurityOfficer securityOfficer = (SecurityOfficer) currentEmployee;
            System.out.println("교대 시간 : " + securityOfficer.getShift());
        } else if (currentEmployee instanceof Veterinarian) {
        	Veterinarian veterinarian = (Veterinarian) currentEmployee;
            System.out.println("전공 : " + veterinarian.getSpecialization());
        }
        System.out.println("==================================");
    }
    
    // 직원 정보 제거하기
    public void removeEmployee(int index) {
        if (0 <= index && index < employee.length && employee[index] != null) {
            System.out.println(employee[index].getClass().getSimpleName() + " " + employee[index].getName() + "(" + 
            employee[index].getEmpld() + ")" + " 의 정보를 직원 목록에서 제거합니다.");
            employee[index] = null;
        } else {
            System.out.println("유효하지 않은 인덱스이거나 제거할 직원이 없습니다.");
        }
    }

    // 직원 직무 알려주기
    public String getEmployeeType(int index) {
        if (0 <= index && index < employee.length && employee[index] != null) {
            if (employee[index] instanceof ParkManager) {
                return "공원매니저";
            } else if (employee[index] instanceof SecurityOfficer) {
                return "보안요원";
            } else if (employee[index] instanceof Veterinarian) {
                return "수의사";
            } else {
                return "알 수 없는 부류";
            }
        } else {
            return "유효하지 않은 인덱스이거나 추가된 직원이 없습니다.";
        }
    }
    
    // 직원 타입 입력값 인식, 범위에 없는 값 입력하면 다시 반복
    public String getEmployeeType(String operation) {
        while (true) {
            String employeeType;
            if (operation.equals("add")) {
                System.out.print("직원의 직무(공원매니저/보안요원/수의사)를 입력하세요 : ");
            } else if (operation.equals("change")) {
                System.out.print("직원 직무(공원매니저/보안요원/수의사) 수정 (수정하지 않으려면 엔터를 누르세요): ");
            } else {
                System.out.println("잘못된 값입니다. 다시 입력해주세요.");
                return "";
            }
            employeeType = scanner.nextLine();    
            if (employeeType.equals("공원매니저") || employeeType.equals("보안요원") || 
                employeeType.equals("수의사")) {
                return employeeType;
            } else if (employeeType.isEmpty() && operation.equals("change")) {
            	return "";
            } else {
                System.out.println("잘못된 값입니다. 다시 입력해주세요.");
            }
        }
    }
    
    // 직원 정보 수정하기, empld 여기서 String로 쓰임
    public void allChangeEmployee(int index, String name, String empld, String type, String value ,String...schedules) {  
        if (0 <= index && index < employee.length && employee[index] != null) { 
        	// 기존 직원값 가져오기
        	String prevName = employee[index].getName();
        	int prevEmpld = employee[index].getEmpld();
            // 타입 변경
            switch (type) {
                case "공원매니저":
                    if (!(employee[index] instanceof ParkManager)) {
                        employee[index] = new ParkManager(prevName, prevEmpld, "", schedules);
                    }
                    break;
                case "보안요원":
                    if (!(employee[index] instanceof SecurityOfficer)) {
                        employee[index] = new SecurityOfficer(prevName, prevEmpld, "", schedules);
                    }
                    break;
                case "수의사":
                    if (!(employee[index] instanceof Veterinarian)) {
                        employee[index] = new Veterinarian(prevName, prevEmpld, "", schedules);
                    }
                    break;
                default:
                    break;
            }

            // 입력된 값이 비어있지 않으면 새 값으로 수정, 비어있으면 기존 값으로 유지
	        if (!name.isEmpty()) {
	            employee[index].changeName(name);
	        } 
	        if (!empld.isEmpty()) {
	        	int integerEmpld = Integer.parseInt(empld);
	            employee[index].changeEmpld(integerEmpld);
	        } 

	        if (!value.isEmpty()) {
	        	if (employee[index] instanceof ParkManager) {
	        		ParkManager ParkManager = (ParkManager) employee[index];
	        		ParkManager.changeDepartment(value);
	            }
	        	else if (employee[index] instanceof SecurityOfficer) {
	        		SecurityOfficer securityOfficer = (SecurityOfficer) employee[index];
	        		securityOfficer.changeShift(value);
	            }
	        	else if (employee[index] instanceof Veterinarian) {
	        		Veterinarian veterinarian = (Veterinarian) employee[index];
	        		veterinarian.changeSpecialization(value);
	            }
			}
        } else {
            System.out.println("유효하지 않은 인덱스이거나 수정할 직원이 없습니다.");
        }
    }
  
    // 직원 타입 고유값 수정
    public int changeEmployeeTypeValue(String changeEmployeeTypeValue) {
    	String[][] employeeTypeMessage = {
    			{"공원매니저", "보안요원", "수의사"},
    			{"의 소속 부서를", "의 교대시간을", "의 전공을"}
    			};
    	
    	for (int i = 0; i < 3; i++) {
    		if (changeEmployeeTypeValue.equals(employeeTypeMessage[0][i])) {
    			System.out.print(changeEmployeeTypeValue + employeeTypeMessage[1][i] + "입력하세요 (수정하지 않으려면 엔터를 누르세요): ");
			}
		}
    	String inputOriginalValue = scanner.nextLine();
    	int integerOriginalValue;
    	if (!inputOriginalValue.isEmpty()) {
	    	integerOriginalValue = Integer.parseInt(inputOriginalValue);
	    } else {
	    	integerOriginalValue = -1; // 나이를 -1로 설정하여 입력이 없음을 나타냄
	    }
    	return integerOriginalValue;
    }
    
    // 사원 번호로 해당 사원 인덱스 반환하기
    public int getEmployeeIndex(int a) {
		for (int i = 0; i < employee.length; i++) {
			if (employee[i].getEmpld() == a) {
				return i;
			}
		}
		return -1;
	}
    // 사원 번호로 인덱스 만들기
    public int createEmployeeIndex(int a) {
		for (int i = 0; i < employee.length; i++) {
			if (employee[i] == null) {
				return i;
			}
		}
		return -1;
	}


    
    
    
    
    // 티켓 10개 추가
    private Chap9Q3Ticket[] ticket = new Chap9Q3Ticket[10];

    // 티켓 정보 추가
    public void addSeasonTicket(int index, String ticketType, int price, String startDate, String endDate) {
        if (0 <= index && index < ticket.length) {
        	if (ticketType.equals("일반")) {
        		ticket[index] = new Chap9Q3Ticket(ticketType, price);
			} else if (ticketType.equals("계절")) {
				System.out.print("계절 티켓의 시작일을 입력하세요: ");
				
				ticket[index] = new SeasonTicket(ticketType, price, startDate, endDate);
			}
        } else {
            System.out.println("유효하지 않은 인덱스입니다.");
        }
    }	

    
    
    
    
	public static void main(String[] args) {
		

	}
}
