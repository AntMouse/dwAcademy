package chap4_scope;

public class C1 {

	public static void main(String[] args) {
		int x = 1;
		x++;
		
		{ // start of block
			int y = 2;
			y++;
			x++;
		} // end of block
		x++;
		// out of scope y++;
	}

}
