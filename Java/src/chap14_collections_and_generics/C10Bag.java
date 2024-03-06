package chap14_collections_and_generics;

import java.util.Objects;

public class C10Bag<T> {
    public C10Bag(T content) {
        this.content = content;
    }
	private T content;
    @Override
    public int hashCode() {
    	return Objects.hashCode(content);
    }


	public static void main(String[] args) {
		C9Person person = new C9Person("Alice", 30);
        C10Bag<C9Person> bag = new C10Bag<>(person);

        System.out.println("Hash code of the bag: " + bag.hashCode());


	}

}
