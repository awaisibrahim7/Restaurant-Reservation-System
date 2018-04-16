import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.*;
public class RestaurantSeating 
{
	private static HeapClass heap = new HeapClass();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void home()
	{
		int response;
		System.out.println("What would you like to do?");
		System.out.println("1.) Make a reservation\n 2.)Check seating");
		try
		{
			response = scanner.nextInt();
			if(response == 1)
				addCustomer();
			else
				checkSeating();
		}
		catch(InputMismatchException e)
		{
			System.out.println("Please enter the number 1 or 2 only.");
			home();
		}
	}
	public static void addCustomer() 
	{
		Customer customer1;
		System.out.println("What type of customer are you?");
		System.out.println("1.) VIP\n 2.) Advanced Call\n 3.) Senior\n 4.) Veteran\n 5.) Large Group(4+)\n 6.) Family\n 7.) Other");
		
			int response = scanner.nextInt();
			System.out.println("\nWhat is your name?");
			String name = scanner.next();
			System.out.println("\nWhat is your party size?");
			int size = scanner.nextInt();
			if(response == 1)
				customer1 = new VIP(name, size);
			else if(response == 2)
				customer1 = new Advance(name, size);
			else if(response == 3)
				customer1 = new Seniors(name, size);
			else if(response == 4)
				customer1 = new Veterans(name, size);
			else if(response == 5)
				customer1 = new LargeGroup(name, size);
			else if(response == 6)
				customer1 = new Family(name, size);
			else 
				customer1 = new Other(name, size);
			heap.add(customer1);
			home();
	}
	public static void checkSeating()
	{
		if(heap.getSize() ==0)
		{
			System.out.println("There are no other reservations.");
			home();
		}
		else
		{
			Customer c = (Customer)heap.remove();
			System.out.println("The next available table is for " + c.getName() + " party of " + c.getSize() + " with a priority of " + c.getPriorityType());
		}
		home();
	}
	public static void main(String[] args)
	{
		System.out.println("Restaurant Seating Home");
		home();
	}
}
