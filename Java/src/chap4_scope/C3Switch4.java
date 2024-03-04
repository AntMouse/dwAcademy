package chap4_scope;

public class C3Switch4 {

	public static void main(String[] args) {
		byte b = 3;
		
		switch (b) {
		case 127:
		case -128:
			System.out.println("ok");
			break;
			
		case (byte)129:
		case 12:
		case 13:

		default:
			break;
		}

	}

}
