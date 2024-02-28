package chap12_exceptions;

public class Chap12Q3DinosaurHealthChecker {

    public void performDailyHealthCheck(Chap12QS1Dinosaur dinosaur) {
        try {
            // 공룡의 건강 검사 수행
            checkDinosaurHealth(dinosaur);
        } catch (Exception e) {
            // 예외 처리
            System.out.println("건강 검사 중 오류 발생: " + e.getMessage());
        } finally {
            // 매일 심사 완료 메시지 출력
            System.out.println("오늘의 심사가 완료되었습니다.");
        }
    }

    private void checkDinosaurHealth(Chap12QS1Dinosaur dinosaur) throws Exception {
    	if (dinosaur.getWeight() < 1000) {
            throw new Exception("저체중 증상 발견");
        } else {
            System.out.println("공룡 체중: " + dinosaur.getWeight());
        }
    }


	public static void main(String[] args) {
		Chap12Q3DinosaurHealthChecker healthChecker = new Chap12Q3DinosaurHealthChecker();
		
		Chap12QS1Dinosaur dino1 = new Chap12QS1Dinosaur();
		dino1.setWeight(1000); // 체중 설정
        healthChecker.performDailyHealthCheck(dino1);
        
        Chap12QS1Dinosaur dino2 = new Chap12QS1Dinosaur();
		dino2.setWeight(999); // 체중 설정
        healthChecker.performDailyHealthCheck(dino2);

	}

}
