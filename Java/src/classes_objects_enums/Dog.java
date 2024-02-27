package classes_objects_enums;

public class Dog {
	private String dogName;
	protected int age;
	public Dog(String dogName) {
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
		Dog d = new Dog("Rex");
		// d.dogName = "Abc";
		d.age = 2;
		System.out.println(d.getDogName() + " " + d.age);
		d.pkgPrivate();
	}
}
