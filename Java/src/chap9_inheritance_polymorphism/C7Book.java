package chap9_inheritance_polymorphism;

public class C7Book {
	protected void read() {}

	public static void main(String[] args) {
	}
}

class NonFictionBook extends C7Book {
	public void doThings() {
		read();
	}
}
class Magnifier {
	void magnify() {
		C7Book b = new C7Book();
		// 1
		b.read();
	}
}
