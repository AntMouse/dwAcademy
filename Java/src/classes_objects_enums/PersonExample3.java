package classes_objects_enums;

class Person3 {
	private String name;
	private static int count;
	
	Person3(String aName) {
		name = aName;
		Person3.count++;
	}
	public String getName() {
		return this.name;
	}
	public void getName(String aName) {
		this.name = aName;
	}
	public int getCount() {
		return Person3.count;
	}
	
	
}

public class PersonExample3 {

	public static void main(String[] args) {
		Person3 p1 = new Person3("Maaike");
		Person3 p2 = new Person3("Sean");
		System.out.println(p1.getCount());
		
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
