package chap12_exceptions;

public class Chap12QProjectSubEnclosureBreachException extends Exception {
    // 생성자를 통해 에러 메시지를 설정할 수 있도록 함
    public Chap12QProjectSubEnclosureBreachException(String message) {
        // 부모 클래스인 Exception 클래스의 생성자를 호출하여 예외 메시지를 설정
        super(message);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
