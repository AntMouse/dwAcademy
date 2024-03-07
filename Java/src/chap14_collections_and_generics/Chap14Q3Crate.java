package chap14_collections_and_generics;

public class Chap14Q3Crate<T> {
	
    private T item;

    public Chap14Q3Crate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Crate containing: " + item.toString();
    }

	public static void main(String[] args) {
        // 문자열을 포장한 상자 생성
		Chap14Q3Crate<String> stringCrate = new Chap14Q3Crate<>("Hello, World!");
        System.out.println(stringCrate);

        // 정수를 포장한 상자 생성
        Chap14Q3Crate<Integer> integerCrate = new Chap14Q3Crate<>(42);
        System.out.println(integerCrate);

        // 공룡 객체를 포장한 상자 생성
        Chap14Q3Crate<Chap14Q1Dinosaur> dinosaurCrate1 = 
        new Chap14Q3Crate<>(new Chap14Q1Dinosaur("바둑이", "티라노사우루스", "매우큼", 4, 10));
        System.out.println(dinosaurCrate1);
        
        System.out.println();
        
        Chap14Q3Crate<Chap14Q1Dinosaur> dinosaurCrate2 = new Chap14Q3Crate<>
        (new Chap14Q1Dinosaur("점박이", "벨로키라톱스", "보통", 15, 8));
        Chap14Q3Crate<Chap14Q41Jeep> jeepCrate = new Chap14Q3Crate<>
        (new Chap14Q41Jeep("랜드로버", "검정"));
        Chap14Q3Crate<Chap14Q42DinosaurFood> foodCrate = new Chap14Q3Crate<>
        (new Chap14Q42DinosaurFood("점박이의 먹이"));
        
        System.out.println(dinosaurCrate2);
        System.out.println(jeepCrate);
        System.out.println(foodCrate);

	}

}
