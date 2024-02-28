package chap8_classes_objects_enums;

class Person2 {
	private String name;
	private int count;
	
	Person2(String aName) {
		name = aName;
		count++;
	}
	public String getName() {
		return name;
	}
	public void getName(String aName) {
		name = aName;
	}
	public int getCount() {
		return count;
	}
}

public class C2PersonExample2 {

	public static void main(String[] args) {
		Person2 p1 = new Person2("Maaike");
		Person2 p2 = new Person2("Sean");
		
		System.out.println(p1.getName());
		System.out.println(p2.getName());
		
		p1.getName("Maaike van Puttern");
		p2.getName("Sean Kennedy");
		
		System.out.println(p1.getName());
		System.out.println(p2.getName());
		System.out.println(p1.getCount());
		System.out.println(p2.getCount());
	}

}
