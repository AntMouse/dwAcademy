package chap12_exceptions;

public class C3InvalidAgeTest {

	public static void main(String[] args) {
		C3InvalidAge invalidAge = new C3InvalidAge(34);

		try {
			invalidAge.setAge(-5);
		} catch(InvalidAgeException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
