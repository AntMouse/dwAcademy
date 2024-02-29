package test_package;

interface A2 {
	public abstract void abc();
}

class C2 {
	void cde(A2 a) {
		a.abc();
	}
}

public class Test2 {

	public static void main(String[] args) {
		C2 c = new C2();
		A2 a1 = new A2() {
			public void abc() {
				System.out.println("익명 이너 클래스 메시지1");
			}
		};
		c.cde(a1);
		c.cde(new A2() {
			public void abc() {
				System.out.println("익명 이너 클래스 메시지2");
			}
		});
	}

}
