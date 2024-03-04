package chap7_method;

public class C1Method6 {

	public static void main(String[] args) {
		m1();
		m1("A");
		m1("A", "B");
		m1("A", "B", "C");

	}
	
	public static void m1(String... args) {
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		for (String s : args) {
			System.out.println(s);
		}
	}

}
