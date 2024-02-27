package classes_objects_enums;

class A {
	public void m() {
		System.out.println("출력1");
	}
	public static void testName() {
		System.out.println("출력2");
	}
}

public class PersonExample4 {
	int x;
	public void m() {
		
	}

	public static void main(String[] args) {
		// x = 9;
		// this.x = 9;
		// m();
		// this.m();
		
		// a.m();
		A.testName();
		// m();
		// testName();
		PersonExample4 pe = new PersonExample4();
		pe.x = 999; 
		pe.m();
		System.out.println(pe.x); 
	}
}
