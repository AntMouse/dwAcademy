package chap4_scope;

public class C2If1 {

	public static void main(String[] args) {
		int x = 5, y = 4;
		if (x > y) {
			System.out.println(x + " > " + y);
		} if (x < y) {
			System.out.println(x + " < " + y);
		} if (x == y) {
			String s = x + " == " + y;
			System.out.println(s);
		}
	}

}
