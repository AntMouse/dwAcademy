package chap3_order_of_precedence;

public class C3 {

	public static void main(String[] args) {
		boolean b1 = false, b2 = true;
		boolean res = b2 | (b1 = true);
		System.out.println(b1 + " " + b2 + " " + res);
	}

}
