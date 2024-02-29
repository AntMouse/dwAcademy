package chap8_classes_objects_enums;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Chap8Q2Employee {
	Scanner scanner = new Scanner(System.in);
	
	private String employeeName;
	private String employeeJobTitle;
	private int employeeYearsOfExperience;
	private List<String> schedules;
	
	public Chap8Q2Employee(String employeeName, String employeeJobTitle, int employeeYearsOfExperience) {
		this.employeeName = employeeName;
		this.employeeJobTitle = employeeJobTitle;
		this.employeeYearsOfExperience = employeeYearsOfExperience;
		this.schedules = new ArrayList<>();
	}
	
	public String getEmployeeName() {
		return this.employeeName;
	}
	public String getEmployeeJobTitle() {
		return this.employeeJobTitle;
	}
	public int getEmployeeYearsOfExperience() {
		return this.employeeYearsOfExperience;
	}

	public void changeEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public void changeEmployeeJobTitle(String employeeJobTitle) {
		this.employeeJobTitle = employeeJobTitle;
	}
	public void changeEmployeeYearsOfExperience(int employeeYearsOfExperience) {
		this.employeeYearsOfExperience = employeeYearsOfExperience;
	}
	
    public void addSchedule(String schedule) {
        schedules.add(schedule);
    }
    
    public void removeSchedule(int index) {
        if (0 <= index && index < schedules.size()) {
            schedules.remove(index);
        } else {
            System.out.println("유효하지 않은 인덱스입니다.");
        }
    }
    
    public List<String> getSchedules() {
        return schedules;
    }
    
    public void changeSchedule(int index, String newSchedule) {
        if (0 <= index && index < schedules.size()) {
            schedules.set(index, newSchedule);
        } else if (index == schedules.size()) { // 해당 인덱스에 스케줄이 없는 경우
            addSchedule(newSchedule); // 스케줄 추가
        } else {
            System.out.println("유효하지 않은 인덱스입니다.");
        }
    }
    
    public void printAllSchedules() {
        List<String> schedules = getSchedules();
        if (schedules.isEmpty()) {
            System.out.println("현재 일정이 없습니다.");
        } else {
            System.out.println("전체 스케줄:");
            for (int i = 0; i < schedules.size(); i++) {
                System.out.println(i + ". " + schedules.get(i));
            }
        }
    }
	
	public static void main(String[] args) {
		
	}

}
