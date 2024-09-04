package multithreading.syncronization;

import java.lang.System.Logger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	private int balance = 100;
	
	private final Lock lock = new ReentrantLock();
	
	
	
	public  void withdraw(int amount) {
		System.out.println(Thread.currentThread().getName() + " is attempting to widhdraw " + amount);
		
		try {
			if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
				if(balance >= amount) {
					System.out.println(Thread.currentThread().getName() +"procedding withdraw money");
					
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						Thread.currentThread().interrupt();
					}
					finally {
						lock.unlock();
					}
					balance -= amount;
					System.out.println(Thread.currentThread().getName() +"completed withdrawal! remaing balance : " + balance);
				}else {
					System.out.println(Thread.currentThread().getName() + " insufficient balance");
				}
			}else {
				System.out.println(Thread.currentThread().getName() + " could not aquire lock ! will try later");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}
	
	public int getBalance() {
		return balance;
	}
	
}
