package multithreading.syncronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {
	private int count = 0;
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private final Lock readLock = lock.readLock();
	
	private final Lock writeLock = lock.writeLock();
	
	public void increment()  {
		writeLock.lock();
		try {
			count++;
			Thread.sleep(50);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		finally {
			writeLock.unlock();
		}
	}
	
	public int getCount() {
		readLock.lock();
		try {
			return count;
		}finally {
			readLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ReadWriteCounter counter = new ReadWriteCounter();
		
		Runnable readTask = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName() + " is reading | counter value : "
							+ counter.getCount());
				}
			}
		};
		
		Runnable writeTask = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i< 10; i++) {
					
					counter.increment();
					
					System.out.println(Thread.currentThread().getName() 
							+ " is writing count " );
				}
			}
		};
		
		Thread writerThread = new Thread(writeTask,"writer thread");
		Thread readThread1 = new Thread(readTask,"reader 1");
		Thread readThread2 = new Thread(readTask,"reader 2");
		
		writerThread.start();
		readThread1.start();
		readThread2.start();
		
		try {
			writerThread.join();
			readThread1.join();
			readThread2.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println("total counts : " + counter.getCount());
	}
}
