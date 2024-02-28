package chap8_classes_objects_enums;

public class C5Dog {
	private String dogName;
	protected int age;
	public C5Dog(String dogName) {
		this.dogName = dogName;
	}
	public String getDogName() {
		return dogName;
	}
	void pkgPrivate() {
		this.dogName = "chairman";
		System.out.println(this.dogName);
	}
	
	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.testDogAccess();
	}
}

class Cat {
	Cat() {
		
	}
	public void testDogAccess() {
		C5Dog d = new C5Dog("Rex");
		// d.dogName = "Abc";
		d.age = 2;
		System.out.println(d.getDogName() + " " + d.age);
		d.pkgPrivate();
	}
}
