package chap12_exceptions;

public class Chap12Q3DinosaurHealthChecker {
    private int healthScore;

    public Chap12Q3DinosaurHealthChecker() {
    }
    public Chap12Q3DinosaurHealthChecker(int healthScore) {
        this.healthScore = healthScore;
    }
    
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
    
    public void checkHealth() throws Chap12QS2CriticalHealthException {
        if (healthScore < 50) {
            throw new Chap12QS2CriticalHealthException("건강 상태가 좋지 않습니다. 관리가 필요합니다.");
        } else {
            System.out.println("건강 상태가 정상입니다.");
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
        
        int dinosaurHealthScore = 40; // 예시로 설정한 공룡 건강 점수
        Chap12Q3DinosaurHealthChecker healthChecker2 = new Chap12Q3DinosaurHealthChecker(dinosaurHealthScore);

        try {
            healthChecker2.checkHealth();
        } catch (Chap12QS2CriticalHealthException e) {
            System.out.println("중요한 상태가 감지되었습니다 : " + e.getMessage());
            // 여기서 필요한 추가 동작을 수행할 수 있습니다.
        }

	}

}
