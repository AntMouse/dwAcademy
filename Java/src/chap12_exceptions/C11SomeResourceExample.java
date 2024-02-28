package chap12_exceptions;

public class C11SomeResourceExample {

	public static void main(String[] args) {
		try (C10SomeResource resource = new C10SomeResource()) {
			resource.doSomething();
		}
	}

}
