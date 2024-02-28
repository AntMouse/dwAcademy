package chap12_exceptions;

public class C10SomeResource implements AutoCloseable {
	public void doSomething() {
		System.out.println("Doing something...");
	}
	
	@Override
	public void close() {
		System.out.println("Resource closed.");
	}

	public static void main(String[] args) {
		try( C10SomeResource someResource = new C10SomeResource()) {
			someResource.doSomething();
		}

	}

}
