package chap3_order_of_precedence;

public class C5 {

	public static void main(String[] args) {
		byte b1 = 6 & 8;
		byte b2 = 7 | 9;
		byte b3 = 5 ^ 4;
		System.out.print(b1 + " " + b2 + " " + b3);
	}
}
