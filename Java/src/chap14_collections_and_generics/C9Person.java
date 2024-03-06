package chap14_collections_and_generics;

public class C9Person {
    public C9Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    private String name;
    private int age;
    @Override
     public int hashCode() {
          int result = 17;
          result = 31 * result + (name == null ? 0: name.hashCode());
          result = 31 * result + age;
          return result;
    }

	public static void main(String[] args) {
		

	}

}
