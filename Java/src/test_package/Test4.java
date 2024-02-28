package test_package;

class A4 {
	int a = 3;
	static int b = 4;
	void method1() {
		System.out.println("instance method");
	}
	static void method2() {
		System.out.println("static method");
	}
	static class B4 {
		int b = 10;
		void bcd() {
			// System.out.println(a);
			System.out.println(A4.b);
			// method1();
			method2();
		}
	}
}

public class Test4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
