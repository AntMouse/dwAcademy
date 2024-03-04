package chap7_method;

public class C1Method1 {

	public static void main(String[] args) {
		System.out.println("main: before call to simpleExample()");
		simpleExample();
		System.out.println("main: after call to simpleExample()");

	}

	public static void simpleExample() {
		System.out.println("\nExecuting simpleExample() method...");
	}
}
