package test_package;

class A2 {
	public int a = 3;
	protected int b = 4;
	int c = 5;
	private int d = 6;
	static int e = 500;
	void abc() {
		System.out.println("A 클래스 메서드 abc()");
		System.out.println(e);
	}
	class B2 {
		void bcd() {
			System.out.println(a);
			System.out.println(b);
			System.out.println(c);
			System.out.println(d);
			System.out.println(e);
			abc();
		}
	}
}

public class Testa2 {

	public static void main(String[] args) {
		A2 a = new A2();
		A2.B2 b = a.new B2();
		b.bcd();
	}

}
