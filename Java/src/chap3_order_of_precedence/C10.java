package chap3_order_of_precedence;

public class C10 {

	public static void main(String[] args) {
		char c = 12;
		// char c2 = 90_000;
		short s = 12;
		// s = c;
		s = (short) c;
		// c = s;
		c = (char) s;
		
		final char c1 = 12;
		short s1 = 12;
		s1 = c1;

		System.out.println("c = " + c + " s = " + s + " s1 = " + s1 + " c1 = " + c1);
	}

}
