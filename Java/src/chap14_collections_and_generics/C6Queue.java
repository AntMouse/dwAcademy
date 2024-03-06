package chap14_collections_and_generics;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class C6Queue {

    public static void main(String[] args) {
        // Queue 사용 예시
        Queue<String> queue1 = new LinkedList<>();
        queue1.offer("Task 2");
        queue1.add("Task 1");
        queue1.offer("Task 3");

        // Deque 사용 예시
        Deque<String> deque1 = new LinkedList<>();
        deque1.offer("Task 2");      
        deque1.addLast("Task 1");
        deque1.add("Task 5");    
        deque1.offerFirst("Task 4");
        deque1.addFirst("Task 3");
        // deque1.offerFirst("Task 4");

        String head = deque1.peek();
        System.out.println(deque1.peekLast());
        // String removedElement = deque1.poll();

        for (String element : deque1) {
            System.out.println(element);
        }
    }
}