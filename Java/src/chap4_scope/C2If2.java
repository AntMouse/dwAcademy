package chap4_scope;

public class C2If2 {

	public static void main(String[] args) {
		int x = 4, y = 5;
		if (x > y) {
			System.out.println(x + " > " + y);
		} else if (x < y) {
			System.out.println(x + " < " + y);
		} else if (x == y) {
			System.out.println(x + " == " + y);
		}
		System.out.println("Here");
	}

}
