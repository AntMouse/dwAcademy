package chap9_inheritance_polymorphism;

import java.util.Objects;
// 기본 공룡 클래스
public class Chap9Q1Dinosaur {
	private String size; // 크기 속성
    private String diet; // 식성 속성
    private String habitat; // 사육장
    
    // 생성자 여기에 값 추가하면 아래 상속 받은 클래스 생성자도 수정 해야됨
    public Chap9Q1Dinosaur(String size, String diet, String habitat) {
        this.size = size;
        this.diet = diet;
        this.habitat = habitat;
    }

    // 해당 공룡 크기값 얻기
    public String getSize() {
        return size;
    }
    // 해당 공룡 식성값 얻기
    public String getDiet() {
        return diet;
    }
    // 해당 공룡 서식지 얻기
    public String getHabitat() {
        return habitat;
    }
    
    // 해당 공룡 크기 바꾸기
    public void changeSize(String size) {
		this.size = size;
	}
    // 해당 공룡 식성 바꾸기
    public void changeDiet(String diet) {
    	this.diet = diet;
	}
    // 해당 공룡 서식지 바꾸기
    public void changeHabitat(String habitat) {
        this.habitat = habitat;
    }
    
    // 기본 공룡 정보 출력
    @Override
    public String toString() {
        return "size: " + size + ", diet: " + diet + ", Habitat: " + habitat;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        	return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) { 
        	return false;
        }
        Chap9Q1Dinosaur other = (Chap9Q1Dinosaur) obj;
        return Objects.equals(size, other.size) &&
               Objects.equals(diet, other.diet) &&
               Objects.equals(habitat, other.habitat);
    }
}

// 날개 있는 공룡 클래스
class FlyingDinosaur extends Chap9Q1Dinosaur {
    private int wingspan; // 날개 길이
    private static int flyingDinosaurhabitaNumber;

    // 생성자
    public FlyingDinosaur(String size, String diet, int wingspan) {
        super(size, diet, ("익룡 전용 우리 " + (flyingDinosaurhabitaNumber + 1)));
        this.wingspan = wingspan;
        flyingDinosaurhabitaNumber++;
    }

    // 해당 공룡 날개 길이 얻기
    public int getWingspan() {
        return wingspan;
    }
    
    // 해당 공룡 날개 정보 바꾸기
    public void changeWingspan(int wingspan) {
		this.wingspan = wingspan;
	}    
    
    // 날개 있는 공룡 정보 출력
    @Override
    public String toString() {
        return super.toString() + ", wingspan: " + wingspan;
    }
    
    // equals 메서드 오버라이드
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        FlyingDinosaur other = (FlyingDinosaur) obj;
        return wingspan == other.wingspan;
    }
}

// 어룡 클래스
class AquaticDinosaur extends Chap9Q1Dinosaur {
    private int swimSpeed; // 수영 속도
    private static int AquaticDinosaurhabitaNumber;

    // 생성자
    public AquaticDinosaur(String size, String diet, int swimSpeed) {
        super(size, diet, ("어룡 전용 우리 " + (AquaticDinosaurhabitaNumber + 1)));
        this.swimSpeed = swimSpeed;
        AquaticDinosaurhabitaNumber++;
    }

    // 해당 어룡 수영 속도값 얻기
    public int getSwimSpeed() {
        return swimSpeed;
    }
    
    // 해당 공룡 수영 속도 바꾸기
    public void changeSwimSpeed(int swimSpeed) {
		this.swimSpeed = swimSpeed;
	}    

    
    // 어룡 정보 출력
    @Override
    public String toString() {
        return super.toString() + ", swimSpeed: " + swimSpeed;
    }
    
    // equals 메서드 오버라이드
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        AquaticDinosaur other = (AquaticDinosaur) obj;
        return swimSpeed == other.swimSpeed;
    }
}

// 욱상 공룡 클래스
class WalkingDinosaur extends Chap9Q1Dinosaur {
	private int walkingSpeed; // 걷는 속도
	private static int WalkingDinosaurhabitaNumber;

	// 생성자
	public WalkingDinosaur(String size, String diet, int walkingSpeed) {
		super(size, diet, ("육상공룡 전용 우리 " + (WalkingDinosaurhabitaNumber + 1)));
		this.walkingSpeed = walkingSpeed;
		WalkingDinosaurhabitaNumber++;
	}

	// 해당 공룡 걷는 속도값 얻기
	public int getWalkingSpeed() {
		return walkingSpeed;
 	}
	
    // 해당 공룡 걷는 속도 바꾸기
    public void changeWalkingSpeed(int walkingSpeed) {
		this.walkingSpeed = walkingSpeed;
	}    


	// 육상 공룡 정보 출력
    @Override
    public String toString() {
        return super.toString() + ", walkingSpeed: " + walkingSpeed;
    }
    
    // equals 메서드 오버라이드
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        WalkingDinosaur other = (WalkingDinosaur) obj;
        return walkingSpeed == other.walkingSpeed;
    }
    
    public static void main(String[] args) {

	}
}
