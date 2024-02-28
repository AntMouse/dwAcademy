package chap12_exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

class Parent {
	public void readStuff() throws IOException {
	}
}

class Child extends Parent {
	@Override
	public void readStuff () throws FileNotFoundException {
	}
}

public class C12SubClassOverride {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
