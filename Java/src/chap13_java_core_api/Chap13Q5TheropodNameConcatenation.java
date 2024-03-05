package chap13_java_core_api;

import java.util.List;
import java.util.ArrayList;

public class Chap13Q5TheropodNameConcatenation {
	
    public static String concatenateDinosaurNames(List<String> names) {
        StringBuilder concatenatedNames = new StringBuilder();
        int size = names.size();
        for (int i = 0; i < size; i++) {
            concatenatedNames.append(names.get(i));
            if (i < size - 1) { // 마지막 요소가 아닌 경우에만 ", " 추가
                concatenatedNames.append(", ");
            }
        }
        return concatenatedNames.toString();
    }
    
    public static List<String> generateDinosaurNames(String... names) {
        List<String> dinosaurNames = new ArrayList<>();
        for (String name : names) {
            dinosaurNames.add(name);
        }
        return dinosaurNames;
    }

	public static void main(String[] args) {
        List<String> names = generateDinosaurNames
        ("바둑", "점박", "파랑", "초롱", "말랑", "백구", "흑구", "적구", "동구", "은구");

        // StringBuilder를 사용하여 모든 이름을 결합하고 문자열로 변환
        String concatenatedNames = concatenateDinosaurNames(names);

        // 결과 출력
        System.out.println("전체 이름: " + concatenatedNames);

	}

}
