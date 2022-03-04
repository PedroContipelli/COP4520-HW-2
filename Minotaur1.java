import java.util.*;
import java.util.concurrent.atomic.*;
import java.io.*;

public class Minotaur1
{
	public static void main(String[] args) throws InterruptedException
	{
		int numGuests = 50;
		GuestThread1.totalGuests = numGuests;
		Thread[] guests = new Thread[numGuests];

		AtomicBoolean cupcake = new AtomicBoolean(true);
		AtomicBoolean allVisited = new AtomicBoolean(false);
		int timesEntered = 0; // Just for testing
		
		// Declare all threads. Have first guest be designated as counter
		for (int i = 0; i < guests.length; i++)
			guests[i] = new GuestThread1(cupcake, allVisited, i == 0);

		long startTime = System.currentTimeMillis();
		
		// Minotaur continues choosing until a guest announces they have all visited
		while (!allVisited.get())
		{
			// Choose random guest to enter
			int randomGuest = (int)(Math.random() * numGuests);
			
			// Enter labyrinth
			guests[randomGuest].run();
			timesEntered++;
			
			// Wait until guest has exited
			guests[randomGuest].join();
		}

		// Calculate and print execution time
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		System.out.printf("Execution time: %dms\n", executionTime);
		System.out.printf("In total, there were %d entries into the labyrinth.\n", timesEntered);
		System.out.printf("This should be approximately O(n^2), or about 2500 entries for default N = 50.\n", timesEntered);
	}
}
