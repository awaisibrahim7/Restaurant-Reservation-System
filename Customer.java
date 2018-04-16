
public class Customer implements Comparable<Customer>
{
	private String name;
	private int priority;
	private String priority2;
	private int groupSize;
	
	public Customer(String name, int priority,String priority2, int groupSize)
	{
		this.name = name;
		this.priority = priority;
		this.priority2 = priority2;
		this.groupSize = groupSize;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getPriority()
	{
		return priority;
	}
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	public String getPriorityType()
	{
		return priority2;
	}
	public void setPriorityType(String priority)
	{
		this.priority2 = priority;
	}
	public int getSize()
	{
		return groupSize;
	}
	public void setSize(int size)
	{
		groupSize = size;
	}
	public int compareTo(Customer c)
	{
		if(getPriority() > c.getPriority())
			return 1;
		else if(getPriority() == c.getPriority())
			return 0;
		else
			return -1;
	}
}

