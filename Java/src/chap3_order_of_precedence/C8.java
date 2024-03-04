package chap3_order_of_precedence;

public class C8 {

	public static void main(String[] args) {
		int x = 2;
		x *= 2+5;
		System.out.println(x);
		
		int k = 1;
		k += (k=4) * (k+2);
		System.out.println(k);
	}

}
