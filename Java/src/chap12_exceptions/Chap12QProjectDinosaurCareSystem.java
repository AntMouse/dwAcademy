package chap12_exceptions;

import java.util.Random;

public class Chap12QProjectDinosaurCareSystem {
    // 공룡의 건강을 처리하는 메서드
    public void handleDinosaurHealth() throws Chap12QProjectSubDinosaurIllException {
        // 랜덤한 숫자를 생성하여 공룡의 건강 상태를 시뮬레이션
        Random random = new Random();
        int healthStatus = random.nextInt(10); // 0부터 9 사이의 랜덤한 숫자 생성

        // 건강 상태가 나쁜 경우 예외를 던짐
        if (healthStatus < 5) {
            throw new Chap12QProjectSubDinosaurIllException("공룡이 아픕니다.");
        }
    }
    
    // 공룡 우리의 보안을 처리하는 메서드
    public void handleEnclosureSecurity() throws Chap12QProjectSubEnclosureBreachException {
        // 랜덤한 숫자를 생성하여 우리의 보안 상태를 시뮬레이션
        Random random = new Random();
        int securityStatus = random.nextInt(10); // 0부터 9 사이의 랜덤한 숫자 생성

        // 보안 상태가 나쁜 경우 예외를 던짐
        if (securityStatus < 3) {
            throw new Chap12QProjectSubEnclosureBreachException("우리의 보안이 위험 상태입니다.");
        }
    }

	public static void main(String[] args) {
		Chap12QProjectDinosaurCareSystem careSystem = new Chap12QProjectDinosaurCareSystem();

        try {
            careSystem.handleDinosaurHealth();
        } catch (Chap12QProjectSubDinosaurIllException e) {
            System.out.println("공룡 건강 처리 중 에러 발생: " + e.getMessage());
        }

        try {
            careSystem.handleEnclosureSecurity();
        } catch (Chap12QProjectSubEnclosureBreachException e) {
            System.out.println("우리의 보안 처리 중 에러 발생: " + e.getMessage());
        }

	}

}
