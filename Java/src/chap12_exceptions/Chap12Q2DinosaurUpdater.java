package chap12_exceptions;

public class Chap12Q2DinosaurUpdater {
    public void updateDinosaurWeight(double weight, Chap12QS1Dinosaur dinosaur) {
        if (weight < 0) {
            throw new IllegalArgumentException("체중은 0보다 작을 수 없습니다.");
        }
        
        dinosaur.setWeight(weight);
        System.out.println("공룡 체중: " + dinosaur.getWeight());
    }
	public static void main(String[] args) {
		Chap12Q2DinosaurUpdater updater = new Chap12Q2DinosaurUpdater();
		Chap12QS1Dinosaur dino = new Chap12QS1Dinosaur();
        double newWeight = -100; // 유효하지 않은 체중

        try {
            updater.updateDinosaurWeight(newWeight, dino);
        } catch (IllegalArgumentException e) {
            System.out.println("체중 업데이트 중 오류 발생: " + e.getMessage());
        }
	}

}
