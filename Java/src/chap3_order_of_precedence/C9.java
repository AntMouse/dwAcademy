package chap3_order_of_precedence;

public class C9 {

	public static void main(String[] args) {
		System.out.println("Float : " + Float.MAX_VALUE);
		System.out.println("Float : " + Float.MIN_VALUE);
		
		System.out.println("Long : " + Long.MAX_VALUE);
		System.out.println("Long : " + Long.MIN_VALUE);
		
		double d1 = .00000000123;
		double d2 = 1.23e-9;
		System.out.println(d1 == d2);
		
		double d3 = 120_000;
		double d4 = 1.2e+5;
		System.out.println(d3 == d4);
		
		char c = 'a';
		int i = c;
		System.out.println(c);
		
		int i2 = (int)3.3;
		System.out.println(i2);
		
		double dt1 = (double)i;
		System.out.println(dt1);
	}

}
