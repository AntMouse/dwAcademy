package chap14_collections_and_generics;

public class C83LaptopBag {
    private C81Laptop laptop;

    public C83LaptopBag(C81Laptop laptop) {
        this.laptop = laptop;
    }

    public C81Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(C81Laptop laptop) {
        this.laptop = laptop;
    }

    public static void main(String[] args) {
        // Main method content here
    }
}