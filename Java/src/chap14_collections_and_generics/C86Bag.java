package chap14_collections_and_generics;

public class C86Bag<T> {
    private T content;
    
    public C86Bag(T content) {
        this.content = content;
    }
    
    public T getContent() {
        return content;
    }
    
    public void setContent(T content) {
        this.content = content;
    }

    public static void main(String[] args) {
        C86Bag<C81Laptop> laptopBag = new C86Bag<>(new C81Laptop("Dell", "XPS 15"));
        C86Bag<C82Book> bookbag = new C86Bag<>(new C82Book("Why Java is fun", "Maaike and Sean"));


	}

}
