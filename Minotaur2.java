import java.util.*;
import java.util.concurrent.atomic.*;
import java.io.*;

public class Minotaur2
{	
	public static void main(String[] args) throws InterruptedException
	{	
		// @TAs feel free to change these for testing
		int numGuests = 100;
		GuestThread2.enablePrintOut = false;

		int queueSize = numGuests;
		
		// Declare queue
		GuestThread2.size = queueSize;
		GuestThread2.queue = new boolean[queueSize];
		GuestThread2.queue[0] = true;
		GuestThread2.tail = new AtomicInteger(0);
		
		// Declare guests
		GuestThread2[] guests = new GuestThread2[numGuests];
		long startTime = System.currentTimeMillis();

		// Simulate guests queuing up randomly
		for (int id = 0; id < guests.length; id++)
		{
			guests[id] = new GuestThread2(id);
			guests[id].start();
		}			

		// Wait until all guests are done seeing the vase for good
		for (int id = 0; id < guests.length; id++)
			guests[id].join();

		// Calculate and print execution time
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;

		System.out.println("\nAll guests are now bored and left the party.");
		System.out.printf("Room was entered %d times\n", GuestThread2.tail.get());
		System.out.printf("Execution time: %dms\n", executionTime);
	}
}