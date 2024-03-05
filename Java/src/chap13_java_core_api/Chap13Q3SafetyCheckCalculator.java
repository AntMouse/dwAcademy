package chap13_java_core_api;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Chap13Q3SafetyCheckCalculator {
	
    // 다음 안전 점검까지의 일수를 계산하는 메서드
    public static long calculateDaysUntilNextSafetyCheck(LocalDate lastSafetyCheckDate, LocalDate currentDate) {
        // 다음 안전 점검 날짜 계산
        LocalDate nextSafetyCheckDate = lastSafetyCheckDate.plusDays(45);

        // 현재 날짜와 다음 안전 점검 날짜 사이의 일수 계산
        long daysUntilNextSafetyCheck = ChronoUnit.DAYS.between(currentDate, nextSafetyCheckDate);

        // 결과 출력
        if (daysUntilNextSafetyCheck > 45) {
            System.out.println("지난 번 안전 점검을 하지 않았습니다.");
        } else {
            System.out.println("다음 안전 점검까지 남은 일수: " + daysUntilNextSafetyCheck + "일");
        }
        
        return daysUntilNextSafetyCheck;
    }

    public static void main(String[] args) {
        // 마지막 안전 점검 날짜
        LocalDate lastSafetyCheckDate = LocalDate.of(2024, 01, 20);
        // 현재 날짜
        LocalDate currentDate = LocalDate.now();
        // 임의의 날짜를 설정
        LocalDate customDate = LocalDate.of(2024, 01, 21);

        
        // 다음 안전 점검까지의 기간 계산
        long daysUntilNextSafetyCheck = calculateDaysUntilNextSafetyCheck(lastSafetyCheckDate, customDate);
    }

}
