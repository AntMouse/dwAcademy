package chap3_order_of_precedence;

public class C4 {

	public static void main(String[] args) {
		boolean b1 = (5 > 1) ^ (10 < 20);
		boolean b2 = (5 > 10) ^ (10 < 20);
		boolean b3 = (5 > 10) ^ (10 < 2);
		boolean b4 = (5 < 10) ^ (10 > 2);
		System.out.println(b1 + " " + b2 + " " + b3 + " " + b4);
	}

}
