package chap4_scope;

public class C2If4 {

	public static void main(String[] args) {
		boolean flag = false;
		
		if (flag)
			if (flag)
				if (flag) {
					System.out.println("True True");
				} else {
					System.out.println("True True");
				}
		
		if (flag) {
			System.out.println("Ture True");
		} else {
			System.out.println("True True"); 
		}
	}

}
