package chap12_exceptions;

class InvalidAgeException extends Exception {
	InvalidAgeException() {
		super();
	}
	InvalidAgeException(String message) {
		super(message);
	}
	InvalidAgeException(Exception e) {
		super(e);
	}
}

public class C3InvalidAge {
	private int age;
	
	C3InvalidAge(int age) {
		this.age = age;
	}
	
	public void setAge(int age) throws InvalidAgeException {
		if (age < 0) {
	        throw new InvalidAgeException("Age must be over zero");
	    }
	    this.age = age;
	}

	public static void main(String[] args) {

	}

}
