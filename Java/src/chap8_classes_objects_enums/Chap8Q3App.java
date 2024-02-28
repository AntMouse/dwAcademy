package chap8_classes_objects_enums;

public class Chap8Q3App {

	public static void main(String[] args) {
		Chap8Q1Dinosaur dino = new Chap8Q1Dinosaur("바둑이", 4, "티라노", "양호");
		Chap8Q2Employee employee = new Chap8Q2Employee("김민수", "티라노 사육장 관리", 20);
		
		
		System.out.println("공룡 이름 : " + dino.getDinoName());
		System.out.println("공룡 나이 : " + dino.getDinoAge() + "살");
		System.out.println("공룡 종류 : " + dino.getDinoSpecies());
		
		System.out.println();
		
		System.out.println("직원 이름 : " + employee.getEmployeeName());
		System.out.println("직원 직무 : " + employee.getEmployeeJobTitle());
		System.out.println("직원 경력 : " + employee.getEmployeeYearsOfExperience() + "년");
	}

}
