package chap9_inheritance_polymorphism;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// 기본 직원 클래스
public class Chap9Q2Employee {
	Scanner scanner = new Scanner(System.in);
	
	private String name; // 직원 이름
	private int empld; // 직원 번호
	private List<String> schedules;

	// 생성자
	public Chap9Q2Employee(String name, int empld) {
		this.name = name;
		this.empld = empld;
		this.schedules = new ArrayList<>();
	}

	// 해당 직원 이름값 얻기
	public String getName() {
		return name;
	}
	// 해당 직원 번호값 얻기
	public int getEmpld() {
		return empld;
	}
	
	// 해당 직원 이름 바꾸기
	public void changeName(String name) {
		this.name = name;
	}
	// 해당 직원 번호 바꾸기
	public void changeEmpld(int empld) {
		this.empld = empld;
	}
	// 해당 직원 정보 출력
    @Override
    public String toString() {
        return "name: " + name + ", empld: " + empld + ", schedule: " + schedules;
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
class ParkManager extends Chap9Q2Employee {
	private String department; // 소속 부서 속성

	// 생성자
	public ParkManager(String name, int empld, String department) {
		super(name, empld);
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
class SecurityOfficer extends Chap9Q2Employee {
	private String shift; // 근무 시간대 속성

	// 생성자
	public SecurityOfficer(String name, int empld, String shift) {
		super(name, empld);
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
class Veterinarian extends Chap9Q2Employee {
	private String specialization; // 전문 분야 속성

	// 생성자
	public Veterinarian(String name, int empld, String specialization) {
		super(name, empld);
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
    public static void checkQualification(Chap9Q2Employee employee, int safetyRating) {
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
