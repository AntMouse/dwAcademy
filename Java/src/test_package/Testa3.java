package test_package;

class A3 {
	int a = 3;
	int b = 4;
	void abc() {
		System.out.println("A 클래스 메서드");
	}
	class B3 {
		int a = 5;
		int b = 6;
		void abc() {
			System.out.println("B 클래스 메서드");
		}
		
		void bcd() {
			System.out.println(this.a);
			System.out.println(this.b);
			this.abc();
			
			System.out.println(A3.this.a);
			System.out.println(A3.this.b);
			A3.this.abc();
		}
	}
}

public class Testa3 {

	public static void main(String[] args) {
		A3 a = new A3();
		A3.B3 b = a.new B3();
		b.bcd();
	}

}
