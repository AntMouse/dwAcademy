package test_package;

interface A1 {
	public abstract void abc();
}

class B1 implements A1 {
	public void abc() {
		System.out.println("입력매개변수 전달");
	}
}

class C1 {
	void cde(A1 a) {
		a.abc();
	}
}

public class Test1 {

	public static void main(String[] args) {
		C1 c = new C1();
		A1 a = new B1();
		c.cde(a);
		c.cde(new B1());

	}

}
