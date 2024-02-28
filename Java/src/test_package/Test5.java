package test_package;

import java.io.FileInputStream;
import java.io.InputStreamReader;

class B5 {
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

public class Test5 {

	public static void main(String[] args) {
		Thread.sleep(1000);
		
		Class cls = Class.forName("java.langObject");
		
		InputStreamReader isr = new InputStreamReader(System.in);
		isr.read();
		
		FileInputStream fis = new FileInputStream("text.txt");
		
		B5 b1 = new B5();
		B5 b2 = (B5) b1.clone();
	}

}
