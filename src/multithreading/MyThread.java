package multithreading;

public class MyThread extends Thread{
	
	
	
	@Override
	public void run() {
		System.out.println("thread 1 method");
		try {
			Thread.sleep(2000);
			System.out.println("thread restart after sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		MyThread t1 = new MyThread();
		System.out.println("t1 thread state : " + t1.getState());
		t1.start();
		System.out.println("t1 thread state after calling start method : " + t1.getState());
		System.out.println("t1 state: " + t1.getState());
		
		System.out.println("main method state : " + Thread.currentThread().getState());
		Thread.sleep(200);
			
		t1.join();
		
		
		System.out.println("t1 method : " + t1.getState());
		System.out.println("main method ended");
	}
	
}
