package chap8_classes_objects_enums;

public record C12PersonTest(String name, Integer age) {
//	public person20 {
//		if (age < 18) {
//			this.name = "Error";
//			this.age = -1;
//		}
//		this.name = name;
//		this.age = age;
//	}
	public C12PersonTest {
        if(age < 18) {
             name = "Error"; 
             age = -1;
        }
	}
	public static void main(String[] args) {
		C12PersonTest p1 = new C12PersonTest("Joe Bloggs", 20);
        System.out.println(p1);
        System.out.println(p1.name());     // Joe Bloggs
        System.out.println(p1.age()); 
	}
	
}


