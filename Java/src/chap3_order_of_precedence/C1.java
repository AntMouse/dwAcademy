package chap3_order_of_precedence;

public class C1 {

	public static void main(String[] args) {
		boolean a = false, b = false, c = false;
		boolean bool = (a = true) || (b = true) && (c = true);
		System.out.println(a + ", " + b + ", " + c);
	}

}
