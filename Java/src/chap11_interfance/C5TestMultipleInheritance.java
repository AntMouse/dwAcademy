package chap11_interfance;

interface A3 {
	default void foo() {
		System.out.println("A:foo");
	}
}
interface B3 {
	default void foo() {
		System.out.println("B.foo\n");
	}
}

public class C5TestMultipleInheritance implements A3, B3 {
	@Override
	public void foo() {
		System.out.println("TestMultipleInheritance::foo");
		// 1
		A3.super.foo();
		// 2
		// A3.foo();
		B3.super.foo();
	}
	public static void main(String[] args) {
		// 3 
		// A3.super.foo();
		// 4
		new C5TestMultipleInheritance().foo();

	}

}
