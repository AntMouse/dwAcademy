package chap9_inheritance_polymorphism;

import java.util.Objects;
import java.util.Scanner;

// 기본 직원 클래스
public class Chap9Q2Employee {
	Scanner scanner = new Scanner(System.in);
	
	private String name; // 직원 이름
	private int empld; // 직원 번호
	private String[] schedules; // 직원 스케줄

	// 생성자
	public Chap9Q2Employee(String name, int empld, String...schedules) {
		this.name = name;
		this.empld = empld;
		this.schedules = schedules;
	}

	// 해당 직원 이름값 얻기
	public String getName() {
		return name;
	}
	// 해당 직원 번호값 얻기
	public int getEmpld() {
		return empld;
	}
	// 해당 직원 스케줄값 얻기
	public String printSchedule() {
		StringBuilder scheduleString = new StringBuilder();
	    if (schedules == null || schedules.length == 0) {
	        return "해당 직원은 현재 일정이 비어있습니다.";
	    } else {
	        scheduleString.append("직원 ").append(name).append("의 스케줄:\n");
	        for (int i = 0; i < schedules.length; i++) {
	            if (!schedules[i].isEmpty()) {
	                scheduleString.append(i + 1).append(". ").append(schedules[i]).append("\n");
	            }
	        }
	    }
	    return scheduleString.toString();
	}
	// 해당 직원 스케줄 삭제하기
	public void deleteSchedule(int index) {
	    if (0 <= index && index < schedules.length) {
	        if (!schedules[index].isEmpty()) {
	            System.out.print(schedules[index] + " 번 스케줄을 삭제하시겠습니까?\n" + " (y/n): ");
	            String choice = scanner.nextLine();
	            if (choice.equalsIgnoreCase("y")) {
	                schedules[index] = null; // 스케줄을 삭제함
	                System.out.println("직원 " + name + "의 " + index + "번째 스케줄이 삭제되었습니다.");
	            } else {
	                System.out.println("삭제를 취소합니다.");
	            }
	        } else {
	            System.out.println("해당 인덱스에는 삭제할 스케줄이 없습니다.");
	        }
	    } else {
	        System.out.println("유효하지 않은 인덱스입니다.");
	    }
	}
	
	// 해당 직원 이름 바꾸기
	public void changeName(String name) {
		this.name = name;
	}
	// 해당 직원 번호 바꾸기
	public void changeEmpld(int empld) {
		this.empld = empld;
	}
	// 해당 직원 스케줄 바꾸기
	public void changeSchedule(int index, String newSchedule) {
	    if (0 <= index && index < schedules.length) {
	        if (!schedules[index].isEmpty()) {
	            System.out.print("해당 스케줄에 이미 내용이 있습니다. 변경하시겠습니까? (y/n): ");
	            String choice = scanner.nextLine();
	            if (choice.equalsIgnoreCase("y")) {
	                schedules[index] = newSchedule;
	                System.out.println("직원 " + name + "의 " + (index + 1) + "번째 스케줄이 변경되었습니다.");
	            } else {
	                System.out.println("변경을 취소합니다.");
	            }
	        } else {
	            schedules[index] = newSchedule;
	            System.out.println("직원 " + name + "의 " + (index + 1) + "번째 스케줄이 변경되었습니다.");
	        }
	    } else {
	        System.out.println("유효하지 않은 인덱스입니다.");
	    }
	}
    
	// 해당 직원 정보 출력
    @Override
    public String toString() {
        return "name: " + name + ", empld: " + empld + ", schedule: " + this.printSchedule();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) { 
        	return false;
        }
        Chap9Q2Employee other = (Chap9Q2Employee) obj;
        return Objects.equals(name, other.name) &&
        	   empld == other.empld &&
        	   Objects.equals(schedules, other.schedules);
    }
    
    public static void main(String[] args) {
		
	}
}

// 공원 매니저 직원 클래스
class ParkManager extends Employee {
	private String department; // 소속 부서 속성

	// 생성자
	public ParkManager(String name, int empld, String department, String...schedules) {
		super(name, empld, schedules);
		this.department = department;
	}

	// 해당 매니저 소속값 반환
	public String getDepartment() {
		return department;
	}
	// 해당 매니저 소속값 바꾸기
	public void changeDepartment(String department) {
		this.department = department;
	}
	
	// 매니저 직원 정보 출력
    @Override
    public String toString() {
        return super.toString() + ", department: " + department;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) { 
        	return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ParkManager other = (ParkManager) obj;
        return Objects.equals(department, other.department);
    }
}

// 보안요원 직원 클래스
class SecurityOfficer extends Employee {
	private String shift; // 근무 시간대 속성

	// 생성자
	public SecurityOfficer(String name, int empld, String shift, String...schedules) {
		super(name, empld, schedules);
		this.shift = shift;
	}

	// 해당 보안 요원 근무 시간대값 얻기
	public String getShift() {
		return shift;
	}
    
	// 해당 보안 요원 근무 시간대 바꾸기
	public void changeShift(String shift) {
		this.shift = shift;
	}
	
	// 보안 요원 직원 정보 출력
    @Override
    public String toString() {
        return super.toString() + ", shift: " + shift;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) { 
        	return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        SecurityOfficer other = (SecurityOfficer) obj;
        return Objects.equals(shift, other.shift);
    }
}

// 수의사 직원 클래스
class Veterinarian extends Employee {
	private String specialization; // 전문 분야 속성

	// 생성자
	public Veterinarian(String name, int empld, String specialization, String...schedules) {
		super(name, empld, schedules);
		this.specialization = specialization;
	}

	// 해당 수의사 전문 분야값 얻기
	public String getSpecialization() {
		return specialization;
	}  
	// 해당 수의사 전문 분야 바꾸기
	public void changeSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	// 스의사 직원 정보 출력
    @Override
    public String toString() {
        return super.toString() + ", specialization: " + specialization;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) { 
        	return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Veterinarian other = (Veterinarian) obj;
        return Objects.equals(specialization, other.specialization);
    }
    
}

class App {
    // 종업원이 특정 우리에서 일하는 데 자격이 있는지를 확인하는 메서드
    public static void checkQualification(Employee employee, int safetyRating) {
        if (employee instanceof ParkManager) {
            ParkManager parkManager = (ParkManager) employee;
            // 매니저의 경우 안전 등급이 2 이상이어야 자격이 있음
            if (safetyRating >= 2) {
                System.out.println(parkManager.getName() + " 은(는) 해당 케이지에서 들어갈 수 있습니다.");
            } else {
                System.out.println(parkManager.getName() + " 은(는) 해당 케이지에서 들어갈 수 없습니다.");
            }
        } else if (employee instanceof SecurityOfficer) {
            SecurityOfficer securityOfficer = (SecurityOfficer) employee;
            // 보안요원의 경우 안전 등급이 1 이상이어야 자격이 있음
            if (safetyRating >= 1) {
                System.out.println(securityOfficer.getName() + " 은(는) 해당 케이지에서 들어갈 수 있습니다.");
            } else {
                System.out.println(securityOfficer.getName() + " 은(는) 해당 케이지에서 들어갈 수 없습니다.");
            }
        } else if (employee instanceof Veterinarian) {
            Veterinarian veterinarian = (Veterinarian) employee;
            // 수의사의 경우 안전 등급이 3 이상이어야 자격이 있음
            if (safetyRating >= 3) {
                System.out.println(veterinarian.getName() + " 은(는) 해당 케이지에서 들어갈 수 있습니다.");
            } else {
                System.out.println(veterinarian.getName() + " 은(는) 해당 케이지에서 들어갈 수 없습니다.");
            }
        } else {
            System.out.println("잘못된 값입니다.");
        }
    }
}
