package chap12_exceptions;

public class Chap12QS2CriticalHealthException extends Exception {
    // 예외 메시지를 저장할 변수
    private String errorMessage;

    // 생성자를 통해 예외 메시지를 전달 받아 설정
    public Chap12QS2CriticalHealthException(String message) {
        // 부모 클래스의 생성자를 호출하지 않고 직접 메시지를 설정
        this.errorMessage = message;
    }

    // getMessage() 메서드를 오버라이딩하여 예외 메시지 반환
    @Override
    public String getMessage() {
        return errorMessage;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
