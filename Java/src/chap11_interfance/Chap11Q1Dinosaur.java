package chap11_interfance;

import java.util.*;

// 인터페이스 정의
interface DinosaurBehavior {
	void eat();
	void move();
}
// 초식&육식 공룡 인터페이스
interface Carnivore {
	void eatMeat();
}

interface Herbivore {
	void eatPlants();
}

//Carnivore를 구현하는 고릴라 클래스
class CarnivorousDinosaur extends Chap11Q1Dinosaur implements Carnivore {
	public CarnivorousDinosaur(String name, int age) {
		super(name, age);
	}

	@Override
	public void eatMeat() {
		System.out.println(getName() + "는(은) 육식성 공룡입니다.");
	}
}

//Herbivore를 구현하는 공룡 클래스
class HerbivorousDinosaur extends Chap11Q1Dinosaur implements Herbivore {
	public HerbivorousDinosaur(String name, int age) {
		super(name, age);
	}

	@Override
	public void eatPlants() {
		System.out.println(getName() + "는(은) 초식성 공룡입니다.");
	}
}

public class Chap11Q1Dinosaur implements DinosaurBehavior, Comparable<Chap11Q1Dinosaur> {
	private String name;
    private int age;
    private boolean isInPark;

    // 생성자
    public Chap11Q1Dinosaur(String name, int age) {
        this.name = name;
        this.age = age;
        this.isInPark = false;
    }
    
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
    
	@Override
	public void eat() {
		System.out.println("공룡(" + this.name + ")이 밥을 먹습니다.");
	}

	@Override
	public void move() {
		System.out.println("공룡(" + this.name + ")이 움직입니다.");
	}
	
    // 나이를 기준으로 공룡을 비교하는 compareTo 메서드 구현
    // @Override
    //public int compareTo(Dinosaur other) {
        // 현재 공룡의 나이와 다른 공룡의 나이를 비교하여 순서를 결정
        // 나이가 작은 공룡이 먼저 나오도록 설정
//        return Integer.compare(this.age, other.age);
//    }

    // 공룡 정보 출력을 위한 toString 메서드
    @Override
    public String toString() {
        // 우리에 들어간 공룡이면 "우리에 있는 공룡"을 출력하고, 그렇지 않으면 일반적인 공룡 정보를 출력
        return isInPark ? "우리에 있는 공룡{name='" + name + "', age=" + age + '}' : 
        "우리에 없는 공룡{name='" + name + "', age=" + age + '}';
    }
    
    // 우리에 들어간 공룡으로 표시하는 메서드
    public void enterCage() {
        this.isInPark = true;
    }
    
    // 우리에서 나간 공룡으로 표시하는 메서드
    public void leaveCage() {
        this.isInPark = false;
    }
    public int compareTo(Chap11Q1Dinosaur otherDinosaur) {
    	if (this.age < otherDinosaur.age) {
			return -1;
		} else if (this.age == otherDinosaur.age) {
			return 0;
		} else {
			return 1;
		}
    }
    
    class DinosaurSorter {
        static void bubbleSort(Chap11Q1Dinosaur[] array) {
            int size = array.length;
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size - i - 1; j++) {
                    if (array[j].compareTo(array[j + 1]) > 0) {
                        // 현재 객체와 다음 객체를 비교하여 순서가 뒤집혔을 경우 swap
                        Chap11Q1Dinosaur temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }

	public static void main(String[] args) {
        // Dinosaur 객체 생성
        Chap11Q1Dinosaur dino1 = new Chap11Q1Dinosaur("Tyrannosaurus", 10);
        Chap11Q1Dinosaur dino2 = new Chap11Q1Dinosaur("Velociraptor", 7);
        Chap11Q1Dinosaur dino3 = new Chap11Q1Dinosaur("Stegosaurus", 9);

        // Dinosaur 객체를 담을 리스트 생성
        List<Chap11Q1Dinosaur> dinosaurs = new ArrayList<>();
        dinosaurs.add(dino1);
        dinosaurs.add(dino2);
        dinosaurs.add(dino3);
        
        // 우리에 있는 공룡으로 표시
        dino1.enterCage();
        dino3.enterCage();
        
        // 새로운 리스트를 만들어서 기존 리스트의 요소를 복사
        List<Chap11Q1Dinosaur> copiedDinosaurs = new ArrayList<>(dinosaurs);
        // 리스트 정렬 (나이를 기준으로)
        Collections.sort(copiedDinosaurs);

        // 정렬된 리스트 출력
        for (Chap11Q1Dinosaur dino : copiedDinosaurs) {
            System.out.println(dino);
        }
        
        System.out.println();
        
        // 우리에 있는 공룡들 출력
        System.out.println("공룡 상태 : ");
        for (Chap11Q1Dinosaur dino : dinosaurs) {
            System.out.println(dino);
        }
        
        Chap11Q1Dinosaur[] dinosaurs2 = {
                new Chap11Q1Dinosaur("Tyrannosaurus", 20),
                new Chap11Q1Dinosaur("Velociraptor", 15),
                new Chap11Q1Dinosaur("Brachiosaurus1", 25),
                new Chap11Q1Dinosaur("Brachiosaurus2", 40),
                new Chap11Q1Dinosaur("Brachiosaurus3", 5)
        };

        // 정렬 이전
        System.out.println("정렬 이전:");
        for (Chap11Q1Dinosaur d : dinosaurs2) {
            System.out.println(d.getName() + ": " + d.getAge() + "세");
        }

        // 정렬 수행
        DinosaurSorter.bubbleSort(dinosaurs2);

        // 정렬 이후
        System.out.println("\n정렬 이후:");
        for (Chap11Q1Dinosaur d : dinosaurs2) {
            System.out.println(d.getName() + ": " + d.getAge() + "세");
        }
    }
}
