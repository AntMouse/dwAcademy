package chap14_collections_and_generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.PriorityQueue;

//위험 레벨에 따라 공룡을 정렬하는 Comparator
class DangerLevelComparator implements Comparator<Chap14Q1Dinosaur> {
	@Override
	public int compare(Chap14Q1Dinosaur d1, Chap14Q1Dinosaur d2) {
		return Integer.compare(d1.getDangerLevel(), d2.getDangerLevel());
	}
}

//맞춤형 공룡 클래스
public class Chap14Q1Dinosaur {
	private String name;
	private String species;
	private String size;
	private int age;
    private int dangerLevel; // 위험 레벨
    
    public Chap14Q1Dinosaur(String name, String species, String size, int age, int dangerLevel) {
        this.name = name;
        this.species = species;
        this.size = size;
        this.age = age;
        this.dangerLevel = dangerLevel;
    }

    // Getter 및 Setter 메서드

    public String getName() {
        return name;
    }
    public String getSpecies() {
        return species;
    }
    public String getSize() {
        return size;
    }
    public int getAge() {
        return age;
    }
    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

	
	@Override
	public String toString() {
		return "Dinosaur{" + "name='" + name + '\'' + ", species='" + 
				species + '\'' + ", age=" + age + ", dangerLevel=" + 
				dangerLevel + '}';
	}
	@Override
	public int hashCode() {
	    int result = name != null ? name.hashCode() : 0;
	    result = 31 * result + dangerLevel;
	    return result;
	}
	
	public static void main(String[] args) {
        // 맞춤형 공룡 객체 생성
        Chap14Q1Dinosaur dino1 = new Chap14Q1Dinosaur("바둑이", "티라노사우루스", "매우큼", 4, 10);
        Chap14Q1Dinosaur dino2 = new Chap14Q1Dinosaur("점박이", "벨로키라톱스", "보통", 15, 8);
        Chap14Q1Dinosaur dino3 = new Chap14Q1Dinosaur("삼색이", "트리케라톱스", "작음", 7, 5);
        
        // List에 맞춤형 공룡 객체 추가
        List<Chap14Q1Dinosaur> dinosaurList = new ArrayList<>();
        dinosaurList.add(dino1);
        dinosaurList.add(dino2);
        dinosaurList.add(dino3);

        // List 출력
        for (Chap14Q1Dinosaur dinosaur : dinosaurList) {
            System.out.println(dinosaur);
        }
        System.out.println();
        
        // 위험 레벨에 따라 공룡을 정렬하는 Comparator를 생성
        Comparator<Chap14Q1Dinosaur> comparator = new DangerLevelComparator();

        // 위험 레벨에 따라 우선순위가 부여된 PriorityQueue를 생성
        PriorityQueue<Chap14Q1Dinosaur> priorityQueue = new PriorityQueue<>(comparator);

        // PriorityQueue에 맞춤형 공룡 객체 추가
        priorityQueue.offer(dino1);
        priorityQueue.offer(dino2);
        priorityQueue.offer(dino3);

        // PriorityQueue에서 공룡을 하나씩 꺼내어 출력
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
	}
	
}
