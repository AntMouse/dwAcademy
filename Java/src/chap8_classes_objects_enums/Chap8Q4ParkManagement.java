package chap8_classes_objects_enums;

import java.util.Scanner;

public class Chap8Q4ParkManagement {
	private Chap8Q1Dinosaur[] dino = new Chap8Q1Dinosaur[999];	

	// 0. 스캐너
	Scanner scanner = new Scanner(System.in);	
	// 0-1. 전체 출력 메시지1 - 인덱스 값 불일치 메서드
	public void indexErrorMessage() {
		System.out.println("유효하지 않은 인덱스입니다.");
	}
	// 0-2. 전체 출력 메시지2 - 인덱스 값 불일치 및 해당 타입(공룡, 직원 등) 동시 출력 메서드
	public void findValueIndexErrorMessage(String objectType) {
		System.out.println("유효하지 않은 인덱스이거나 해당하는 " + objectType + "이 없습니다.");
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
    // 0-3-3. 처음 인덱스 입력했을 때 추가일 경우(공룡)
    public boolean checkDinoIndex(String addAndChange, int index) {
		if (0 <= index && index < dino.length) {
			// if를 2번 써야 무조건 아래 메시지가 출력 되는 걸 방지할 수 있다 and 조건 쓰기 힘듦
            if (!isRegistered(index, dino) && addAndChange.equals("add")) {
                System.out.println("해당 인덱스에는 이미 등록된 공룡이 있습니다.");
                return false;
            } else if (isRegistered(index, dino) && addAndChange.equals("change")) {
                System.out.println("해당 인덱스에는 등록된 공룡이 없습니다.");
                return false;
			}
		} else {
			indexErrorMessage(); // 유효하지 않은 인덱스
			return false;
		}
		return true;
    }
    // 0-3-4. 처음 인덱스 입력했을 때 추가일 경우(직원)
    public boolean checkEmployeeIndex(String addAndChange, int index) {
		if (0 <= index && index < employee.length) {
			// if를 2번 써야 무조건 아래 메시지가 출력 되는 걸 방지할 수 있다 and 조건 쓰기 힘듦
            if (!isRegistered(index, employee) && addAndChange.equals("add")) {
                System.out.println("해당 인덱스에는 이미 직원이 등록되어 있습니다.");
                return false;
            } else if (isRegistered(index, employee) && addAndChange.equals("change")) {
                System.out.println("해당 인덱스에는 등록된 직원이 없습니다.");
                return false;
			}
		} else {
			indexErrorMessage(); // 유효하지 않은 인덱스
			return false;
		}
		return true;
    }   
    // 0-3-5. 처음 인덱스 입력했을 때 추가일 경우(티켓)
    public boolean checkTicketIndex(String addAndChange, int index) {
		if (0 <= index && index < tickets.length) {
			// if를 2번 써야 무조건 아래 메시지가 출력 되는 걸 방지할 수 있다 and 조건 쓰기 힘듦
            if (!isRegistered(index, tickets) && addAndChange.equals("add")) {
                System.out.println("해당 인덱스에는 이미 티켓이 등록되어 있습니다.");
                return false;
            } else if (isRegistered(index, tickets) && addAndChange.equals("change")) {
                System.out.println("해당 인덱스에는 등록된 티켓이 없습니다.");
                return false;
			}
		} else {
			indexErrorMessage(); // 유효하지 않은 인덱스
			return false;
		}
		return true;
    }   
	// 1-1. 공룡 정보 추가
    // 각 공룡 추가 메서드 통합 관리
    public void addDinosaur(int index, String dinoName, int dinoAge, String dinoSpecies, String dinoStatus) {
        if (0 <= index && index < dino.length) {
            if (isRegistered(index, dino)) {
                dino[index] = new Chap8Q1Dinosaur(dinoName, dinoAge, dinoSpecies, dinoStatus);
            } else {
            	indexErrorMessage();
            }
        }
    }
    // 1-2-1. 공룡 정보 출력
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
        	findValueIndexErrorMessage("공룡");
        }
    }
    // 1-2-2. 단일 공룡 정보 출력
	public void printSingleDinoInfo(int index) {
		System.out.println("공룡 관리 번호 : " + index);
	    System.out.println("이름 : " + dino[index].getDinoName());
	    System.out.println("나이 : " + dino[index].getDinoAge() + "살");
	    System.out.println("종 : " + dino[index].getDinoSpecies()); 
	    System.out.println("상태 : " + dino[index].getDinoStatus()); 
	    System.out.println("위치 : " + "공룡 우리 " + dino[index].getDinoLocationNumber()); 
	    System.out.println("==================================");
    }
	// 1-3. 공룡 정보 제거
	public void removeDino(int index) {
		if (0 <= index && index < dino.length && dino[index] != null) {
            System.out.println(dino[index].getDinoName() + "을(를) 공룡 목록에서 제거합니다.");
            dino[index] = null; // 공룡 제거
        } else {
        	findValueIndexErrorMessage("공룡");
        }
    }
	// 1-4. 공룡 정보 수정
	public void allChangeDino(int index, String dinoName, String dinoAge, String dinoSpecies, String dinoStatus, String dinoLocationNumber) {
	    if (0 <= index && index < dino.length && dino[index] != null) {       
	        // 입력된 값이 비어있지 않으면 새 값으로 수정, 비어있으면 기존 값으로 유지
	        if (!dinoName.isEmpty()) {
	            dino[index].changeDinoName(dinoName);
	        }
	        if (!dinoAge.isEmpty()) {
	        	int changeDinoAge = Integer.parseInt(dinoAge);
	            dino[index].changeDinoAge(changeDinoAge);
	        }
	        if (!dinoSpecies.isEmpty()) {
	            dino[index].changeDinoSpecies(dinoSpecies);
	        }    
	        if (!dinoStatus.isEmpty()) {
	            dino[index].changeDinoStatus(dinoStatus);
	        }        
	        if (!dinoLocationNumber.isEmpty()) {
	        	int changeDinoLocationNumber = Integer.parseInt(dinoLocationNumber);
	            dino[index].changeDinoLocationNumber(changeDinoLocationNumber);
	        }
	        System.out.println("공룡 정보가 성공적으로 수정되었습니다.");
	    } else {
	    	findValueIndexErrorMessage("공룡");
	    }
	}

	
	private Chap8Q2Employee[] employee = new Chap8Q2Employee[999];
	// 2-1-1. 직원 기본 정보 추가
	public void addEmployee(int index, String employeeName, String employeeJobTitle, int employeeYearsOfExperience) {
		if (0 <= index && index < employee.length) {
			if (isRegistered(index, employee)) {
				employee[index] = new Chap8Q2Employee(employeeName, employeeJobTitle, employeeYearsOfExperience);
			} else {
				indexErrorMessage();
			}
		}
	}
	// 2-1-2. 직원 스케줄 정보 추가
	public void addEmployeeSchedule(int index, String schedule) {
    	employee[index].addSchedule(schedule);
    }
	// 2-2-1. 직원 정보 출력
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
        	findValueIndexErrorMessage("직원");
        }
    }
	// 2-1-2. 단일 직원 정보 출력
	public void printSingleEmployeeInfo(int index) {
		if (0 <= index && index < employee.length && employee[index] != null) {
            System.out.println("직원 관리 번호 : " + index);
            System.out.println("이름 : " + employee[index].getEmployeeName());
            System.out.println("직무 : " + employee[index].getEmployeeJobTitle());
            System.out.println("경력 : " + employee[index].getEmployeeYearsOfExperience() + "년");
            employee[index].printAllSchedules();
            System.out.println("==================================");
        } else {
            System.out.println("유효하지 않은 인덱스이거나 추가된 직원이 없습니다.");
        }
    }
	// 2-3. 직원 정보 제거
	public void removeEmployee(int index) {
		if (0 <= index && index < employee.length && employee[index] != null) {
            System.out.println(employee[index].getEmployeeName() + "을(를) 직원 목록에서 제거합니다.");
            employee[index] = null; // 직원 정보 제거
        } else {
        	findValueIndexErrorMessage("직원");
        }
    }	
	// 2-4. 직원 정보 수정	
	public void allChangeEmployee(int index, String newEmployeeName, String newEmployeeJobTitle, String newEmployeeYearsOfExperience) {
	    if (0 <= index && index < employee.length && employee[index] != null) {    	
	        // 입력된 값이 비어있지 않으면 새 값으로 수정, 비어있으면 기존 값으로 유지
	        if (!newEmployeeName.isEmpty()) {
	        	employee[index].changeEmployeeName(newEmployeeName);
	        }	        
	        if (!newEmployeeJobTitle.isEmpty()) {
	        	employee[index].changeEmployeeJobTitle(newEmployeeJobTitle);
	        }	        
	        if (!newEmployeeYearsOfExperience.isEmpty()) {
	        	int changeEmployeeYearsOfExperience = Integer.parseInt(newEmployeeYearsOfExperience);
	        	employee[index].changeEmployeeYearsOfExperience(changeEmployeeYearsOfExperience);
	        }
	    } else {
	    	findValueIndexErrorMessage("직원");
	    }
	}	
    public void getEmployeeSchedule(int index) {
    	employee[index].printAllSchedules();
    }
    public void changeEmployeeSchedule(int index, int turn, String newSchedule) {
    	employee[index].changeSchedule(turn, newSchedule);
    }

    private int ticketAndGuest = 999;
	private Chap8Q8Ticket[] tickets = new Chap8Q8Ticket[ticketAndGuest];	
	private int currentVisitorCount = 0; // 현재 공원의 손님 숫자
	// 3-1. 티켓 정보 추가
    public void addParkTicket(int index, int parkTicketPrice, String parkTicketVisitorsName, int parkTicketVisitDate) {
        if (0 <= index && index < tickets.length) {
        	if (isRegistered(index, tickets)) {
        		tickets[index] = new Chap8Q8Ticket(parkTicketPrice, parkTicketVisitorsName, parkTicketVisitDate);
        		currentVisitorCount++; // 티켓 발급할 때마다 바로 들어온 걸로 가정
            } else {
            	indexErrorMessage();
            }
        }
    }
    // 3-2-1. 티켓 정보 출력
	public void printParkTicketInfo(int index) {
		if (index == -1) {
			for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] != null) {
                	printSingleParkTicketInfo(i);
                }
            }
		} else if (0 <= index && index < tickets.length && tickets[index] != null) {
			printSingleParkTicketInfo(index); 
        } else {
        	findValueIndexErrorMessage("티켓");
        }
    }
    // 3-2-2. 티켓 단일 정보 출력
    public void printSingleParkTicketInfo(int index) {
		if (0 <= index && index < tickets.length && tickets[index] != null) {
            System.out.println("티켓 번호 : " + index);
            System.out.println("티켓 가격 : " + tickets[index].getParkTicketPrice());
            System.out.println("방문자 이름 : " + tickets[index].getParkTicketVisitorsName());
            System.out.println("방문 일자 : " + tickets[index].getParkTicketVisitDate());
            System.out.println("==================================");
        } else {
        	findValueIndexErrorMessage("티켓");
        }
    }
    // 3-3. 티켓 정보 제거
    public void removeParkTicket(int index) {
		if (0 <= index && index < tickets.length && tickets[index] != null) {
			tickets[index] = null;
            System.out.println(index + " 번 티켓을 제거합니다.");
            currentVisitorCount--; // 티켓 제거로 인원수 감소
        } else {
        	findValueIndexErrorMessage("티켓");
        }
    }
	// 3-4. 티켓 정보 수정
	public void allChangeParkTicket(int index, String parkTicketPrice, String parkTicketVisitorsName, String parkTicketVisitDate) {
	    if (0 <= index && index < tickets.length && tickets[index] != null) {    	
	    	// 입력된 값이 비어있지 않으면 새 값으로 수정, 비어있으면 기존 값으로 유지
	    	if (!parkTicketPrice.isEmpty()) {
	    		int changeParkTicketPrice = Integer.parseInt(parkTicketVisitorsName);
				tickets[index].changeParkTicketPrice(changeParkTicketPrice);
			}	
	    	if (!parkTicketVisitorsName.isEmpty()) {
				tickets[index].changeParkTicketVisitorsName(parkTicketVisitorsName);
			}
	    	if (!parkTicketVisitDate.isEmpty()) {
	    		int changeParkTicketVisitDate = Integer.parseInt(parkTicketVisitDate);
				tickets[index].changeParkTicketVisitDate(changeParkTicketVisitDate);
			}	
	        System.out.println("티켓 정보가 성공적으로 수정되었습니다.");
	    } else {
	    	findValueIndexErrorMessage("티켓");
	    }
	}	
	
	
	// 4-1. 최대 수용 가능 인원 반환
	public int maxGuest() {
		return ticketAndGuest;
	}
	// 4-2. 현재 공원 관람객 숫자
	public int currentGuest() {
		return currentVisitorCount;
	}
		
	public static void main(String[] args) {
		
	}

}
