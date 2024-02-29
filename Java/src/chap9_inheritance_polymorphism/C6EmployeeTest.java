package chap9_inheritance_polymorphism;

class Employee {
	private int empld;
	private String name;
	Employee(int empld, String name) {
		this.empld = empld;
		this.name = name;
	}
	@Override
	public String toString() {
		return "ID : " + empld + ", " + "Name : " + name + ", ";
	}
}

class Manager extends Employee {
	private String deptName;
	Manager(int empld, String name, String deptName) {
		super(empld, name);
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return super.toString() + "Department : " + deptName + ", ";
	}
}

class Director extends Manager {
	private double budget;
	Director(int empld, String name, String department, double budget) {
		super(empld, name, department);
		this.budget = budget;
	}
	@Override
	public String toString() {
		return super.toString() + " Budget : " + budget;
	}
}

public class C6EmployeeTest {

	public static void main(String[] args) {
		Employee emplDir = new Director(754, "Joe Bloggs", "Marketing", 10_1000.00);
		System.out.println(emplDir);

	}

}
