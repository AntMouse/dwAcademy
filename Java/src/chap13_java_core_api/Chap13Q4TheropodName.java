package chap13_java_core_api;

import java.util.ArrayList;
import java.util.List;

public class Chap13Q4TheropodName {
	
    public static List<String> generateDinosaurNames(String... names) {
        List<String> dinosaurNames = new ArrayList<>();
        for (String name : names) {
            dinosaurNames.add(name);
        }
        return dinosaurNames;
    }

	public static void main(String[] args) {
        // 생성된 이름 리스트 가져오기
        List<String> names = generateDinosaurNames
        ("바둑", "점박", "파랑", "초롱", "말랑", "백구", "흑구", "적구", "동구", "은구");

        // 리스트 출력
        System.out.println("10개의 이름 리스트:");
        for (String name : names) {
            System.out.println(name);
        }
        // 전체 목록 출력
        System.out.println(names);

	}

}
