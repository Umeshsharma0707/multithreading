package multithreading.syncronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLock {
	private final Lock fairLock = new ReentrantLock(true);
	
	public void accessResource() {
		
		fairLock.lock();
		
		System.out.println(Thread.currentThread().getName() + " accessing resource...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		finally {
			System.out.println(Thread.currentThread().getName() + " released resource...");
			fairLock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		FairnessLock fairnessLock = new FairnessLock();
		
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				fairnessLock.accessResource();
			}
		};
		
		Thread t1 = new Thread(task, "thread 1 "); 
		Thread t2 = new Thread(task, "thread 2 "); 
		Thread t3 = new Thread(task, "thread 3 "); 
		
		t1.start();
		Thread.sleep(100);
		t2.start();
		Thread.sleep(100);
		t3.start();
		
	}
}
