package test_package;

class A3 {
	interface B3 {
		public abstract void bcd();
		public static void bcd2() {
			System.out.println("출력3");
		}
	}
}
class C3 implements A3.B3 {
	public void bcd() {
		System.out.println("이너 인터페이스 구현 클래스 생성1");
	}
}

public class Test3 {

	public static void main(String[] args) {
		// A3.B3 ab3 = new C3();
		C3 c = new C3();
		c.bcd();
		A3.B3 ab1 = new A3.B3() {		
			@Override
			public void bcd() {
				System.out.println("이너 인터페이스 구현 클래스 생성2");
			}
		};
		ab1.bcd();
		
		A3.B3 ab2 = new C3();
		ab2.bcd();
		A3.B3.bcd2();
	}

}
