package chap13_java_core_api;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Chap13Q2ParkSchedule {
	
    private HashMap<String, LocalDateTime> scheduleMap; // 스케줄을 저장할 HashMap

    // 생성자
    public Chap13Q2ParkSchedule() {
        this.scheduleMap = new HashMap<>();
    }

    // 스케줄 설정 메서드
    public void setSchedule(String scheduleName, LocalDateTime scheduleTime) {
        scheduleMap.put(scheduleName, scheduleTime);
    }

    // 스케줄 반환 메서드
    public LocalDateTime getSchedule(String scheduleName) {
        return scheduleMap.get(scheduleName);
    }

	public static void main(String[] args) {
        // 예시: 공원 스케줄 생성
		Chap13Q2ParkSchedule parkSchedule = new Chap13Q2ParkSchedule();

        LocalDateTime mealTime = LocalDateTime.of(2022, 3, 5, 12, 30); // 급식 시간
        LocalDateTime cleaningTime = LocalDateTime.of(2022, 3, 5, 15, 0); // 청소 시간
        LocalDateTime emergencyDrillTime = LocalDateTime.of(2022, 3, 5, 18, 0); // 비상 훈련 시간

        parkSchedule.setSchedule("mealTime", mealTime);
        parkSchedule.setSchedule("cleaningTime", cleaningTime);
        parkSchedule.setSchedule("emergencyDrillTime", emergencyDrillTime);

        // 공원 스케줄 출력
        System.out.println("급식 시간: " + parkSchedule.getSchedule("mealTime"));
        System.out.println("청소 시간: " + parkSchedule.getSchedule("cleaningTime"));
        System.out.println("비상 훈련 시간: " + parkSchedule.getSchedule("emergencyDrillTime"));



	}

}
