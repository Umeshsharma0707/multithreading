package multithreading.syncronization;

public class Counter {
	public int counter;
	
	public void increment() {
		
		synchronized (this) {
			counter++;
		}
		
	}
	
	public int getCounter() {
		return counter;
	}
	
}
