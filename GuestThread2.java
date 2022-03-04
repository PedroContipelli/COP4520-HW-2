import java.util.concurrent.atomic.*;

public class GuestThread2 extends Thread
{
	static int size;
	static volatile boolean[] queue;
	static AtomicInteger tail;

	int id;
	int myQueueIndex = 0;
	volatile double boredom = 0;
	
	public GuestThread2(int id)
	{
		this.id = id;
	}
	
	public void run()
	{
		while (boredom < 1)
		{
			waitInQueue();
			admireVase();
			leaveRoomAndNotifyNextGuest();
		}
	}
	
	public void waitInQueue()
	{		
		// Line up at end of queue
		myQueueIndex = tail.getAndIncrement() % size;
		
		// System.out.printf("Guest %d lines up.\n", id);
		
		// Wait until its my turn to enter showroom
		while (!queue[myQueueIndex]) {};
	}
	
	public void admireVase()
	{
		// System.out.printf("Guest %d enters room.\n", id);
		boredom += 0.25;
	}
	
	public void leaveRoomAndNotifyNextGuest()
	{
		queue[myQueueIndex] = false;
		
		// System.out.printf("Guest %d notifies next guest.\n", id);
		
		queue[(myQueueIndex + 1) % size] = true;
	}
}
