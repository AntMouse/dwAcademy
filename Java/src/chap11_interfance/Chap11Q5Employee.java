package chap11_interfance;

//Worker 인터페이스 정의
interface Worker {
	void work();
	void takeBreak();
}

public class Chap11Q5Employee implements Worker {
    private String name;

    // 생성자
    public Chap11Q5Employee(String name) {
        this.name = name;
    }

    @Override
    public void work() {
        System.out.println("직원(" + name + ")은 일하는 중입니다.");
    }

    @Override
    public void takeBreak() {
        System.out.println("직원(" + name + ")은 쉬고 있습니다.");
    }

	public static void main(String[] args) {
        // Employee 객체 생성
        Chap11Q5Employee employee1 = new Chap11Q5Employee("John");
        Chap11Q5Employee employee2 = new Chap11Q5Employee("Alice");

        // work()와 takeBreak() 메서드 호출
        employee1.work();
        employee1.takeBreak();

        employee2.work();
        employee2.takeBreak();

	}

}
