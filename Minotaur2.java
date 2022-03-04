import java.util.*;
import java.util.concurrent.atomic.*;
import java.io.*;

public class Minotaur2
{	
	public static void main(String[] args) throws InterruptedException
	{	
		int numGuests = 100;
		int queueSize = numGuests;
		
		// Declare queue
		GuestThread2.size = queueSize;
		GuestThread2.queue = new boolean[queueSize];
		GuestThread2.queue[0] = true;
		GuestThread2.tail = new AtomicInteger(0);
		
		// Declare guests
		GuestThread2[] guests = new GuestThread2[numGuests];
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		// Initialize guests
		for (int id = 0; id < guests.length; id++)
		{
			guests[id] = new GuestThread2(id);
			ids.add(id);
		}
		
		long startTime = System.currentTimeMillis();
		
		// Simulate guests queuing up in random order
		Collections.shuffle(ids);
		for (int id : ids)
			guests[id].start();
		
		// Wait until all guests are done seeing the vase for good
		for (int id = 0; id < guests.length; id++)
			guests[id].join();
		
		// Calculate and print execution time
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		System.out.printf("Execution time: %dms\n", executionTime);
	}
}