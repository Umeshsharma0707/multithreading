package multithreading;

class SharedResource{
	
	private int data;
	private boolean hasData;
	
	public synchronized void produce(int value) {
		while(hasData) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		data = value;
		hasData = true;
		notify();
		System.out.println("data produced : " + data);
	}
	
	public synchronized void consume() {
		while(!hasData) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		hasData = false;
		System.out.println("data consumed : " + data);
		notify();
	}
}

class Produce implements Runnable{
	private SharedResource resource;

	public Produce(SharedResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		for(int i= 1; i<=10; i++) {
			resource.produce(i);
		}
	}
	
	
}

class Consume implements Runnable{
	private SharedResource resource;

	public Consume(SharedResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		for(int i= 1; i<=10; i++) {
			resource.consume();
		}
	}
	
	
}

public class ThreadCommunication {	
	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		
		Thread t1 = new Thread(new Produce(resource));
		Thread t2 = new Thread(new Consume(resource));
		
		t1.start();
		t2.start();
	}
	
}
