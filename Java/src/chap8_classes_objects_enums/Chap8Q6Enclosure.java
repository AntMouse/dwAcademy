package chap8_classes_objects_enums;

public class Chap8Q6Enclosure {
    private Chap8Q1Dinosaur[] dinosaurs;

    // 생성자
    public Chap8Q6Enclosure(int capacity) {
    	this.dinosaurs = new Chap8Q1Dinosaur[capacity];
    }

    // 공룡 추가 메서드
    public void addDinosaur(Chap8Q1Dinosaur dinosaur, int index) {
        if (0 <= index && index < dinosaurs.length) {
            dinosaurs[index] = dinosaur;
        } else {
            System.out.println("인덱스가 잘못되었습니다.");
        }
    }

    // 공룡 배열 반환 메서드
    public Chap8Q1Dinosaur[] getDinosaurs() {
        return dinosaurs;
    }

    // 특정 위치의 공룡 반환 메서드
    public Chap8Q1Dinosaur getDinosaur(int index) {
        if (0 <= index && index < dinosaurs.length) {
            return dinosaurs[index];
        } else {
            System.out.println("인덱스가 잘못되었습니다.");
            return null;
        }
    }

    public static void main(String[] args) {
        // Enclosure 인스턴스 생성 (크기 5)
    	Chap8Q6Enclosure enclosure = new Chap8Q6Enclosure(5);

        // 공룡 추가
        enclosure.addDinosaur(new Chap8Q1Dinosaur("바둑이", 4, "티라노", "양호"), 0);
        enclosure.addDinosaur(new Chap8Q1Dinosaur("공룡이", 7, "브라키오사우루스", "나쁨"), 1);
        enclosure.addDinosaur(new Chap8Q1Dinosaur("포도", 5, "스테고사우루스", "양호"), 2);
        
        // 공룡 배열 가져오기
        Chap8Q1Dinosaur[] dinosaurs = enclosure.getDinosaurs();
        
        // 공룡 정보 출력
        for (Chap8Q1Dinosaur dino : dinosaurs) {
            if (dino != null) {
                System.out.println("이름: " + dino.getDinoName() + ", 나이: " + dino.getDinoAge() + ", 종: " + dino.getDinoSpecies());
            }
        }
    }
}
