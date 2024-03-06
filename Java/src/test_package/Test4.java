package test_package;

import java.util.PriorityQueue;

public class Test4 {

	public static void main(String[] args) {
		// 우선순위 큐 생성
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 요소 추가
        priorityQueue.offer(10);
        priorityQueue.offer(5);
        priorityQueue.offer(8);
        priorityQueue.offer(3);

        // 큐에서 요소 꺼내어 출력
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }

	}

}
