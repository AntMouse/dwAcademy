package chap14_collections_and_generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

class Person {
    String name;
    int age;
    
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }
}

public class C1Collections1 {

    public static void main(String[] args) {
        List<String> studentNames = new ArrayList<>();
        studentNames.add("Sarah-Milou");
        studentNames.add("Tjed");
        studentNames.add("Fahya");       
        System.out.println(studentNames);
        System.out.println(studentNames.get(1));
        studentNames.set(1, "Monica");

        System.out.println();
        
        List<Person> personNames = new ArrayList<>();
        personNames.add(new Person("Sarah-Milou", 4));
        personNames.add(new Person("Tjed", 6));
        personNames.add(new Person("Fahya", 8));
        System.out.println(personNames);
        System.out.println(personNames.get(1));
        System.out.println();
        
        Set<String> emailAddresses = new HashSet<>(); 
        emailAddresses.add("sarahmilou@amsterdam.com");
        emailAddresses.add("tjed@amsterdam.com");
        emailAddresses.add("fahya@amsterdam.com");
        System.out.println(emailAddresses);
        for (String email : emailAddresses) {
            if (email.equals("sarahmilou@amsterdam.com")) {
                System.out.println("Found value: " + email);
                break;
            }
        }
        System.out.println();
        
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("Sarah-Milou", "Sarah-Milou Doyle");
        userInfo.put("Tjed", "Tjed Quist");
        userInfo.put("Fahya", "Fahya Osei");
        System.out.println(userInfo);
        System.out.println(userInfo.get("Tjed"));
        System.out.println();
        
        /*
        List에서는 배열처럼 특정 값을 얻을 때 변수명.get(인덱스);를 사용한다.
        List의 <>안에 들어가는 게 사용자 생성 객체라면 따로 toString를 오버라이딩 해야
        값을 출력했을 때 알아보기가 쉽다. 값의 순서가 보장이 되고 중복을 허용한다.
        
        Set은 HashMap를 사용. 중복 값을 허용하지 않지만 순서가 정렬되지 않는다.
        
        Map은 HashMap을 사용. 순서 보장을 안 하고, 중복을 허용하지 않지만 과정이 복잡하다.
        Map에서는 Value에 대해서는 중복을 허용합니다. 같은 값이 여러 개의 키에 대응할 수 있습니다.
        Map은 각각의 키(key)는 유일해야 하므로, 중복된 키를 허용하지 않습니다. 
        따라서 동일한 키로 값을 추가하면 이전 값은 새로운 값으로 대체됩니다.
         */
    }
}