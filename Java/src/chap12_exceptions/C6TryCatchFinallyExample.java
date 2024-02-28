package chap12_exceptions;

public class C6TryCatchFinallyExample {

	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			System.out.println("Ooops : " + e.getMessage());
		} finally {
			System.out.println("This code willalways run.");
		}

	}

}
