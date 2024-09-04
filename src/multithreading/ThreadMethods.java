package multithreading;

public class ThreadMethods extends Thread{

	public ThreadMethods(String name) {
		super.setName(name);
	}

	public ThreadMethods() {
		
	}
	
	@Override
	public void run() {
		for(int i = 0; i<5; i++) {
			System.out.println(" thread name : " + Thread.currentThread().getName() 
					+ " | thread priority : " + Thread.currentThread().getPriority()
					+" thread output : " +i);
			/*
			 * try { Thread.sleep(2000); } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			 Thread.yield();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		ThreadMethods t1 = new ThreadMethods("t1 ");
		ThreadMethods t2 = new ThreadMethods("t2 ");
		ThreadMethods t3 = new ThreadMethods("t3 ");
		/*
		 * t1.setPriority(NORM_PRIORITY); t2.setPriority(MIN_PRIORITY);
		 * t3.setPriority(MAX_PRIORITY);
		 */
		t1.start();
		t1.interrupt(); // to stop the thread at any state
		t2.start();
		t3.start();
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		 * ThreadMethods t1 = new ThreadMethods("thread 1 "); t1.start(); // will starts
		 * the thread , state will be runnable
		 * 
		 * t1.join(); // waits the main thread for t1 to complete
		 * 
		 * System.out.println(t1.getState()); // returns thread current state
		 * 
		 * System.out.println(t1.getName()); // return thread name
		 */	}
}
