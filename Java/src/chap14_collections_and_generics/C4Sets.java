package chap14_collections_and_generics;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class C4Sets {
	public static void main(String[] args) {
        // HashSet 사용
        Set<String> namesHashSet = new HashSet<>();
        namesHashSet.add("Elizabeth");
        namesHashSet.add("Janie");
        namesHashSet.add("Michael");
        System.out.println(namesHashSet.remove("Janie"));
        // println에 넣었지만 결과만 출력하는 게 아니라 실제로 코드 내용도 실행이 되었다.
        System.out.println("HashSet:");
        for (String name : namesHashSet) {
            System.out.println(name);
        }

        // LinkedHashSet 사용
        Set<String> namesLinkedHashSet = new LinkedHashSet<>();
        namesLinkedHashSet.add("Elizabeth");
        namesLinkedHashSet.add("Janie");
        namesLinkedHashSet.add("Michael");
        
        System.out.println("\nLinkedHashSet:");
        for (String name : namesLinkedHashSet) {
            System.out.println(name);
        }
        // HashSet은 요소의 순서를 보장하지 않지만, LinkedHashSet은 요소의 순서를 보장한다.
        // HashSet은 LinkedHashSet과 다르게 특정한 작업을 하지 않아서 메모리를 더 적게 사용한다.
        // 또한 작업 속도가 더 빠르다.

        // TreeSet 사용
        Set<String> namesTreeSet = new TreeSet<>();
        namesTreeSet.add("Michael");
        namesTreeSet.add("Elizabeth");
        namesTreeSet.add("Janie");
        
        namesTreeSet.remove("Janie");
        System.out.println("\nTreeSet:");
        for (String name : namesTreeSet) {
            System.out.println(name);
        }
        /*
        LinkedHashSet: 요소를 추가한 순서대로 저장합니다. 
        따라서 요소를 추가한 순서와 동일한 순서로 요소가 저장됩니다.
		TreeSet: 요소를 자동으로 정렬하여 저장합니다. 
		문자열인 경우에는 알파벳 순서로, 숫자인 경우에는 작은 숫자부터 큰 숫자 순서로 요소가 저장됩니다.
         */
    }
}