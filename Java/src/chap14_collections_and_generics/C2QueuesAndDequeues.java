package chap14_collections_and_generics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.swing.text.Document;

class Person2 {
    String name;
    int age;
    
    Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }
}

public class C2QueuesAndDequeues {

    public static void main(String[] args) {
        Queue<String> printQueue = new LinkedList<>();
        printQueue.add("Document1");
        printQueue.add("Document2");
        printQueue.add("Document3");
        System.out.println(printQueue.peek());
        String nextJob = printQueue.poll();
        System.out.println(nextJob);
        System.out.println(printQueue.poll());
        // Queue
        /*
        Queue는 먼저 넣은 값이 먼저 빠져나가는 선입 선출 구조다. new LinkedList<>(); 사용.
        변수명.poll();을 사용하면 가장 앞의 값을 리턴하고 해당 값을 삭제한다.
        poll을 2번 쓰면 1번째 값 반환 후 삭제, 2번째 값 반환 후 삭제 작업이 이어진다.
        변수명.peek();는 첫 번째요소를 제거하지 않고 값을 리턴한다.
        poll이나 peek나 첫 번째 값을 반환하지만 poll은 값을 제거하고, peek는 값을 제거하지 않는다.
         */
        
        /*
        List<String> studentNames = new ArrayList<>();
        studentNames.add("Sarah-Milou");
        studentNames.add("Tjed");
        studentNames.add("Fahya");
        
        List<Person2> personNames = new ArrayList<>();
        personNames.add(new Person2("Sarah-Milou", 4));
        personNames.add(new Person2("Tjed", 6));
        personNames.add(new Person2("Fahya", 8));
        
        Set<String> emailAddresses = new HashSet<>();
        emailAddresses.add("sarahmilou@amsterdam.com");
        emailAddresses.add("tjed@amsterdam.com");
        emailAddresses.add("fahya@amsterdam.com");
        */
    }
}
