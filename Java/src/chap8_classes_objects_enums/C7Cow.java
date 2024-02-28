package chap8_classes_objects_enums;

class Tag {
	
}


public class C7Cow {
	Tag tag;
	String country;

	public static void main(String[] args) {
		C7Cow cow1 = new C7Cow();
		C7Cow cow2 = cow1;
		cow2.tagAnimal(cow1);
		

	}
	void tagAnimal(C7Cow cow) {
		tag = new Tag();
		cow.setCountry("France");
		
	}
	void setCountry(String country) {
		this.country = country;
	}

}
