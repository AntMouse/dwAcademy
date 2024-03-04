package chap3_order_of_precedence;

public class C6 {

	public static void main(String[] args) {
		int x = 4;
		String s = x % 2 == 0 ? " is an even number" : " is an odd number";
		System.out.println(x + s);
	}
}
