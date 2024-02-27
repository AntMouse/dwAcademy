package classes_objects_enums;

class Tag {
	
}


public class Test8_7 {
	Tag tag;
	String country;

	public static void main(String[] args) {
		Test8_7 cow1 = new Test8_7();
		Test8_7 cow2 = cow1;
		cow2.tagAnimal(cow1);
		

	}
	void tagAnimal(Test8_7 cow) {
		tag = new Tag();
		cow.setCountry("France");
		
	}
	void setCountry(String country) {
		this.country = country;
	}

}
