package chap8_classes_objects_enums;
import java.util.Objects;

public final class C11PersonRecords {
	private final String name;
	private final Integer age;
	
	public C11PersonRecords(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	public String name() {
		return name;
	}
	public Integer age() {
		return age;
	}
	@Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (C11PersonRecords) obj;
        return Objects.equals(this.name, that.name) &&
        	   Objects.equals(this.age, that.age);
    }
    @Override
     public int hashCode() {
         return Objects.hash(name, age);
     }
     @Override
     public String toString() {
         return "Person[" + "name=" +name + ", " + "age=" + age + "]";
     }
     public static void main(String[] args) {
		

     }

}
