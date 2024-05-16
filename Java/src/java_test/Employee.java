package java_test;  

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

interface Salary {
    double calcSalary();
}

public class Employee implements Salary {
    private String name;
    private int empId;
    private double wage;
    private int workHours;
    private String department;
    private String instructions; // 지시 받은 내용을 저장하는 변수
    private Employee supervisor; // 지시를 받는 상급자 저장
    private final int WORKING_DAYS_PER_MONTH = 25; // 한 달에 근무하는 일수

    public Employee(String name, int empId, double wage, int workHours, String department) {
        this.name = name;
        this.empId = empId;
        this.wage = wage;
        this.workHours = workHours;
        this.department = department;
    }
    
    public String getName() {
        return name;
    }
    public int getEmpId() {
        return empId;
    }
    public double getWage() {
        return wage;
    }
    public int getWorkHours() {
        return workHours;
    }
    public String getDepartment() {
        return department;
    }   
    // 상급자 지정 메서드
    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
    // 상급자 반환 메서드
    public Employee getSupervisor() {
        return supervisor;
    }
    
    // 받은 업무를 저장할 리스트
    private List<String> receivedInstructions = new ArrayList<>();
    // 지시를 받아서 저장하는 메서드
    public void receiveInstructions(String instructions, String supervisorName) {
        System.out.println("[" + supervisorName + "이(가) 업무를 지시합니다.] " + instructions);
        receivedInstructions.add(instructions);
    }
    // 받은 업무를 반환하는 메서드
    public List<String> getReceivedInstructions() {
        return receivedInstructions;
    }  
    // 지시를 내리는 메서드
    public void issueInstructions(String instructions, Employee subordinate) {
        System.out.println("[" + this.getName() + "이(가) " + subordinate.getName() + "에게 업무를 지시합니다.]");
        subordinate.receiveInstructions(instructions, this.getName());
    }
    // 하급자가 상급자에게 받은 업무를 반환하는 메서드
    public List<String> getSubordinateInstructions(Employee subordinate) {
        return subordinate.getReceivedInstructions();
    }
    @Override
    public double calcSalary() {
        return wage * workHours * WORKING_DAYS_PER_MONTH;
    }
    public String toString() {
        return "Employee ID: " + empId + "\nName: " + name + "\nDepartment: " + department + "\nSalary: " + calcSalary();
    }
    
    public static void main(String[] args) {
        // Employee 객체 생성
        Clerk clerk1 = new Clerk("John", 101, 15000, "Administration");
        Clerk clerk2 = new Clerk("Alice", 102, 13000, "Administration");

        Developer developer1 = new Developer("Bob", 201, 14000, 8, "Engineering", 200000);
        Developer developer2 = new Developer("Eva", 202, 17000, 8, "Engineering", 150000);

        Manager manager1 = new Manager("Mike", 301, 19000, 10, "Management", 500000);
        Manager manager2 = new Manager("Sara", 302, 18750, 10, "Management", 600000);

        Salesman salesman1 = new Salesman("Tom", 401, 13000, 9, "Sales", 300000);
        Salesman salesman2 = new Salesman("Linda", 402, 19000, 8, "Sales", 400000);

        Director director1 = new Director("David", 501, 25000, 10, "Executive", 1000000);
        Director director2 = new Director("Emily", 502, 30000, 9, "Executive", 1200000);

        // Set 컬렉션에 종업원 객체 추가
        Set<Employee> employees = new HashSet<>();
        employees.add(clerk1);
        employees.add(clerk2);
        employees.add(developer1);
        employees.add(developer2);
        employees.add(manager1);
        employees.add(manager2);
        employees.add(salesman1);
        employees.add(salesman2);
        employees.add(director1);
        employees.add(director2);

        // Iterator를 사용하여 종업원 정보 출력
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
        	System.out.println(iterator.next());
        	System.out.println("==============================");
        }
        
        // 상급자 설정
        clerk1.setSupervisor(manager1);
        developer1.setSupervisor(manager1);
        manager1.setSupervisor(director1);

        // 상급자가 하급자에게 업무 지시하는 예시
        manager1.issueInstructions("다음주까지 사업 관련 보고서를 제출해주세요.", clerk1);
        manager1.issueInstructions("다음주까지 프로젝트 계획안을 작성해주세요.", developer1);
        director1.issueInstructions("다음주까지 회계 자료 작성을 완료하세요.", manager1);

        // 하급자가 받은 업무를 상급자에게 반환하는 예시
        List<String> clerkInstructions = manager1.getSubordinateInstructions(clerk1);
        List<String> developerInstructions = manager1.getSubordinateInstructions(developer1);
        List<String> managerInstructions = director1.getSubordinateInstructions(manager1);
        
    }
}

// Clerk.java
class Clerk extends Employee {
	public Clerk(String name, int empId, double wage, String department) {
        super(name, empId, wage, 8, department);
    }
}

// Developer.java
class Developer extends Employee {
    private double overtimeRate;

    public Developer(String name, int empId, double wage, int workHours, String department, double overtimeRate) {
        super(name, empId, wage, workHours, department);
        this.overtimeRate = overtimeRate;
    }

    @Override
    public double calcSalary() {
        int normalHours = getWorkHours();
        double normalSalary = super.calcSalary(); // 기본 급여 계산
        double overtime = (normalHours > 8) ? (normalHours - 8) * overtimeRate : 0; // 초과 근무 시간당 정해진 급여 계산
        return normalSalary + overtime;
    }
}

// Manager.java
class Manager extends Employee {
    private double mgrAllowance;

    public Manager(String name, int empId, double wage, int workHours, String department, double mgrAllowance) {
        super(name, empId, wage, workHours, department);
        this.mgrAllowance = mgrAllowance;
    }

    @Override
    public double calcSalary() {
        return super.calcSalary() + mgrAllowance;
    }
}

// Salesman.java
class Salesman extends Employee {
    private double incentive;

    public Salesman(String name, int empId, double wage, int workHours, String department, double incentive) {
        super(name, empId, wage, workHours, department);
        this.incentive = incentive;
    }

    @Override
    public double calcSalary() {
        return super.calcSalary() + incentive;
    }
}

// Director.java
class Director extends Employee {
    private double proExpenses;

    public Director(String name, int empId, double wage, int workHours, String department, double proExpenses) {
        super(name, empId, wage, workHours, department);
        this.proExpenses = proExpenses;
    }

    @Override
    public double calcSalary() {
        return super.calcSalary() + proExpenses;
    }
}



