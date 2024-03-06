package chap14_collections_and_generics;

import java.util.ArrayList;
import java.util.List;

public class C3ArrayListAndLickedLisk {

	public static void main(String[] args) {
        List<String> studentNames = new ArrayList<>();
        studentNames.add("Sarah-Milou");
        studentNames.add("Tjed");
        studentNames.add("Fahya1");  
        studentNames.add("Fahya2");  
        studentNames.add("Fahya3");  
        System.out.println(studentNames);
        System.out.println(studentNames.get(1));
        studentNames.set(1, "Monica");
        System.out.println(studentNames.get(1));
        System.out.println(studentNames);
        
        System.out.println(studentNames.remove("Sarah-Milou"));
        System.out.println(studentNames);
        System.out.println(studentNames.remove(1));
        System.out.println(studentNames);
        /*
        List에서는 변수명.set(번호, 바꿀 내용); 으로 List 내의 내용 변경 갸능.
        변수명.remove(); 에서 값으로 숫자 또는 변수의 실제 값 중 아무거나 입력 가능.
        단, 숫자를 입력하면 리턴으로 삭제한 값을 반환하고, 실제 값을 삭제 칸에 입력하면
        true and false를 반환한다.
         */

	}

}
