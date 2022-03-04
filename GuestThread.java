import java.util.concurrent.atomic.*;

public class GuestThread extends Thread
{
	static int totalGuests;
	boolean designatedCounter;
	int count = 1;

	AtomicBoolean cupcake;
	AtomicBoolean allVisited;
	boolean IhaveEaten;
	
	public GuestThread(AtomicBoolean cupcake, AtomicBoolean allVisited, boolean designatedCounter)
	{
		this.cupcake = cupcake;
		this.allVisited = allVisited;
		this.designatedCounter = designatedCounter;
	}
	
	public void run()
	{
		// If I am the counter and see no cupcake, I know a new guest has entered (and eaten it)
		// So I will increase my count and reset the cupcake
		if (designatedCounter && !cupcake.get())
		{
			count++;
			cupcake.set(true);
			
			// Once all guests (including myself) have been counted, announce it.
			if (count == totalGuests)
			{
				allVisited.set(true);
				System.out.println("Guest: \"Mr. Minotaur, all the guests have visited the labyrinth!\"\n");
			}
		}
		
		// If I am not the counter, see the cupcake is there, and have not yet eaten, I will eat it.
		// This will let the counter know that I have entered the labyrinth.
		if (!designatedCounter && cupcake.get() && !IhaveEaten)
		{
			cupcake.set(false);
			IhaveEaten = true;
		}
		
		// Note: conditions above are mutually exclusive (you either are or aren't the designated counter)
	}

}
