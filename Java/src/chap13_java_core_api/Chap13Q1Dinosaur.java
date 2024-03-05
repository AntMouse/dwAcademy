package chap13_java_core_api;

import java.util.Date;

public class Chap13Q1Dinosaur {
    private String name;
    private Date birthday; // birthday 속성 추가

    // 생성자
    public Chap13Q1Dinosaur(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    // 이름 설정 메서드
    public void setName(String name) {
        this.name = name;
    }

    // 이름 반환 메서드
    public String getName() {
        return name;
    }

    // 생일 설정 메서드
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    // 생일 반환 메서드
    public Date getBirthday() {
        return birthday;
    }

	public static void main(String[] args) {
		

	}

}
