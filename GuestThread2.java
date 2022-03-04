import java.util.concurrent.atomic.*;

public class GuestThread2 extends Thread
{
	static boolean enablePrintOut;
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
			// Each guest has some random chance to decide to enter the queue
			if (Math.random() < 0.00001)
			{
				waitInQueue();
				admireVase();
				leaveRoomAndNotifyNextGuest();
			}
		}
	}
	
	public void waitInQueue()
	{		
		// Line up at end of queue
		myQueueIndex = tail.getAndIncrement() % size;
		
		if (enablePrintOut)
			System.out.printf("Guest %d lines up at queue index %d.\n", id, myQueueIndex);
		
		// Wait until its my turn to enter showroom
		while (!queue[myQueueIndex]) {};
	}
	
	public void admireVase()
	{
		if (enablePrintOut)
			System.out.printf("Guest %d enters room.\n", id);

		// Stay in the room for random length of time
		while (Math.random() > 0.0001) {}

		boredom += 0.25 + Math.random();
	}
	
	public void leaveRoomAndNotifyNextGuest()
	{
		queue[myQueueIndex] = false;
		
		if (enablePrintOut)
			System.out.printf("Guest %d leaves and notifies next guest.\n", id);
		
		queue[(myQueueIndex + 1) % size] = true;
	}
}
