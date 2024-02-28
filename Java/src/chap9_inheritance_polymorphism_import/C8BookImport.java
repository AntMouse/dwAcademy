package chap9_inheritance_polymorphism_import;
import chap9_inheritance_polymorphism.C7Book;

class FictionBook extends C7Book {
	public void doThings() {
		//a
		read();
		//1
		this.read();
		FictionBook fb = new FictionBook();
		//2
		fb.read();
		//3
		C7Book b = new C7Book();
		//4 
		// b.read();
	}
}
class SpaceFictionBook extends FictionBook {
	public void doThings() {
		//5
		read();
		//6
		// new Test9_7().read();
		//7
		// new FictionBook().read();
		//8
		new SpaceFictionBook().read();
	}
}
class Reader{
	public void doThings() {
		C7Book b = new C7Book();
		//9
		// b.read();
		FictionBook fb = new FictionBook();
		//10
		// fb.read();
	}
}

public class C8BookImport {

	public static void main(String[] args) {
		

	}

}
