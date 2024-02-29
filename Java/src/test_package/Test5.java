package test_package;

class A5 {
	int a = 3;
	static int b = 10;
	int c = 10;
	void abc() {
		int b = 5;
		class B5 {

			void bcd() {
				System.out.println(a);
				System.out.println(A5.this.c);
				// System.out.println(this.b);
				System.out.println(b);
			}
		}
	}
}

public class Test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
