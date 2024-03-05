package test_package;

class MyData2 {
	int data = 3;
	
	public void plusData() {
		synchronized(this) {
		int mydata = data;

		data = mydata + 1;
		System.out.println("실행 결과 : " + data);
		}
	}
}

class PlusThread2 extends Thread {
	MyData2 myData;
	public PlusThread2(MyData2 myData) {
		this.myData = myData;
	}
	@Override
	public void run() {
		myData.plusData();
		// System.out.println(getName() + "실행 결과 : " + myData.data);
	}
}

public class Test3 {

	public static void main(String[] args) {
		MyData2 myData = new MyData2();
		
		Thread plusThread1 = new PlusThread2(myData);
		plusThread1.setName("plusThread1");
		plusThread1.start();
		
		Thread plusThread2 = new PlusThread2(myData);
		plusThread2.setName("plusThread2");
		plusThread2.start();

	}

}
