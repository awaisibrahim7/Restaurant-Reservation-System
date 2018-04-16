import java.util.*;
import java.util.LinkedList;
public class HeapClass<V extends Comparable<V>> implements Heap<V> 
{
	private Node<V> root;
	private Node<V> last;
	private int nodes;
	public HeapClass()
	{
		root = null;
		last = null;
		nodes = 0;
	}
	public HeapClass(Node<V> root)
	{
		this.root = root;
		last = root;
		nodes = 1;
	}
	public int getSize()
	{
		return nodes;
	}
	//updates the pointer for the node last
	public void setLast()
	{
		Node<V> temp = root;
		int levels = 0;
		if(nodes == 0)
			return;
		else if(nodes == 1)
			last = root;
		else
		{
			while(nodes > (Math.pow(2, levels)- 1)) //levels stops at a number greater than the number of full levels
				levels++; 
			if((Math.pow(2, levels)- 1) - nodes == 0)
			{
				while(temp.getRight() != null)
					temp = temp.getRight();
				last = temp;
			}
			else
			{
				double max = ((Math.pow(2, levels)- 1) - ((Math.pow(2, levels - 1)- 1)))/2;
				temp = root;
				for(int i = 0; i < levels - 2; i++)
				{
					if((nodes - (Math.pow(2, levels - 1)- 1)) <= max/2)
					{
						temp = temp.getLeft();
						max = max/2;
					}
					else
					{	
						temp = temp.getRight();
						max = (max * 3)/4;
					}
				}
				if(temp.getRight() == null)
					last = temp.getLeft();
				else
					last = temp.getRight();
			}
		}
	}
	//adds a node to the heap
	public void add(V value) //nodes++, update last, sift up
	{
		Node<V> newNode = new Node<V>(value);
		Node<V> temp = root;
		if(root == null)
			root = newNode;
		else
		{
			int levels = 0;
			while(nodes > (Math.pow(2, levels)- 1)) //levels stops at the number of levels in the heap
				levels++; 
			if((Math.pow(2, levels)- 1) - nodes == 0)
			{
				while(temp.getLeft() != null)
					temp = temp.getLeft();
				temp.setLeft(newNode);
			}
			else
			{
				//Finds max number of nodes the level can have
				double max = ((Math.pow(2, levels)- 1) - ((Math.pow(2, levels - 1)- 1)))/2;
				temp = root;
				for(int i = 0; i < levels - 2; i++)
				{
					if((nodes - (Math.pow(2, levels - 1)- 1)) < max/2)
					{
						temp = temp.getLeft();
						max = max/2;
					}
					else
					{	
						temp = temp.getRight();
						max = (max * 3)/4;
					}
				}
				if(temp.getLeft() == null)
					temp.setLeft(newNode);
				else
					temp.setRight(newNode);
			}
		}
		newNode.setParent(temp);
		nodes++;
		last = newNode;
		siftUp(newNode);
	}
	//takes the heap and turns it into an array
	  public V[] toArray(V[] array)
	  {
		  V[] array1 =(V[])java.lang.reflect.Array.newInstance(array.getClass().getComponentType(),nodes);
		  Queue<Node<V>> q = new LinkedList<Node<V>>();
		  if(nodes == 0)
			  return null;
		  else
		  {
			  Node<V> temp = root;
			  q.add(temp);
			  for(int i = 0; i < nodes; i++)
			  {
				  temp = q.remove();
				  if(temp.hasLeft() == true)
					  q.add(temp.getLeft());
				  if(temp.hasRight() == true)
					  q.add(temp.getRight());
				  array1[i] = temp.getValue();
			  }
			  return array1;
		  } 
	  }
	  //removes the root node from the heap and sifts down to maintain the heap properties
	  public V remove() //nodes--, update last, sift down, return value of removed Node
	  {
		  Node<V> temp = last;
		  Node<V> temp2;
		  V val = root.getValue();
		  if(root == null)
			  return null;
		  else if(nodes == 1)
			  root = null;
		  else if(nodes == 2)
		  {
			  root = root.getLeft();
			  root.setLeft(null);
			  root.setParent(null);
		  }
		  else if(nodes == 3)
		  {
			  temp.setLeft(root.getLeft());
			  temp.setRight(null);
			  root = temp;
		  }
		  else
		  {
			   temp.setLeft(root.getLeft());
			   temp.setRight(root.getRight());
			   root.setLeft(null);
			   root.setRight(null);
			   root = temp;
			   temp2 = temp.getParent();			   
			   try{
			   if(temp.getParent().hasRight() == true)
				   temp2.setRight(null);
			   else
				   temp2.setLeft(null);
			   }
			   catch(NullPointerException e)
			   {
				   temp2.setLeft(null);
			   }
		  }
		  nodes--;
		  setLast();
		  siftDown(root); 
		  return val;
	  }
	  //takes an array and puts it into a heap
	  public void fromArray(V[] array)
	  {
		  V temp; 
			for(int i = 0; i < array.length; i++)
			{
				temp = array[i];
				add(temp);
			}
	  }
	  //takes a heap, puts it into an array and then applies heapsort
	  public V[] getSortedContents(V[] array)
	  {
		  return heapSort(toArray(array));
	  }
	  
	  public void siftUp(Node<V> current) 
	  {
		  Node<V> temp = current;
		  if(current.getParent() == null)
			  return;
		  if(temp.getValue().compareTo(temp.getParent().getValue()) > 0)
		  {
			  swap(temp.getParent(), temp);
			  siftUp(temp.getParent());
		  }
		  setLast();
	  }
	 
	  public void siftDown(Node<V> temp)
	  {
		  if(nodes == 0)
			  return;
		  if(temp.hasLeft() && temp.hasRight())
		  {
			  if(temp.getValue().compareTo(temp.getLeft().getValue()) == 0 && temp.getValue().compareTo(temp.getRight().getValue()) == 0)
				  return;
			  else if(temp.getValue().compareTo(temp.getLeft().getValue()) < 0 || temp.getValue().compareTo(temp.getRight().getValue()) < 0)
			  {
				  if(temp.getLeft().getValue().compareTo(temp.getRight().getValue()) > 0)
				  {
					  swap(temp, temp.getLeft());
					  siftDown(temp);
				  }
				  else if(temp.getLeft().getValue().compareTo(temp.getRight().getValue()) <= 0)
				  {	  
					  swap(temp, temp.getRight());
					  siftDown(temp);
				  }
			  }
		  }
		  else if(temp.hasRight() == false && temp.hasLeft() == true && temp.getLeft().getValue().compareTo(temp.getValue()) > 0)
		  {
			  swap(temp, temp.getLeft());
			  siftDown(temp);
			  return;
		  }
		  
	  }
	  public void swap(Node<V> parent, Node<V> child)
	  {
		  V val = parent.getValue();
		  parent.setValue(child.getValue());
		  child.setValue(val);
		  
	  }
	 
	  public V[] heapSort(V[] array)
	  {
		  int length = array.length - 1;
		  for(int i = (array.length)/2; i >= 0; i--)
		  {
			  heapify(array,i);
		  }
		  for(int j = length; j > 0; j--)
		  {
			  arraySwap(array,0,j);
			  length--;
			  heapify(array,0);
		  }
		  return array;
	  }
	  public void arraySwap(V[] array, int i, int j)
	  {
		  V temp = array[i];
		  array[i] = array[j];
		  array[j] = temp;
	  }
	  public void heapify(V[]array, int i)
	  {
		  int j = i * 2;
		  int k = j + 1;
		  int l = i;
		  if(j <= array.length && array[j].compareTo(array[l]) > 0)
			  l = j;
		  if(k <= array.length && array[k].compareTo(array[i]) > 0)
			  l = k;
		  if(l != i) 
		  {
			  arraySwap(array,i,l);
			  heapify(array,l);
		  }
	  }
	  public String toString()
	  {
		  String ans = "";
		  Node<V> temp = root;
		  ans = ans + temp.getValue()+ " ";
		  ans += temp.getLeft().getValue();
		  ans = ans + " "  + temp.getRight().getValue();
		  ans += " " + temp.getLeft().getLeft().getValue();
		  return ans;
	  }




public static void main(String[] args)
{
	HeapClass<Integer> h = new HeapClass<Integer>();
	HeapClass<Integer> t = new HeapClass<Integer>();
	Integer[] a = {3,4,6,8};
	Integer[] b = new Integer[h.getSize()];
	h.add(2);
	h.add(6);
	h.add(12);
	h.add(15);
	b = h.toArray(b);
	//h.add(16);
	//t.fromArray(a);
	//System.out.println(h);
	//h.remove();
	System.out.println(t);
	/*for(int i = 0; i < b.length; i++)
	{
		System.out.print(b[i] + " ");
	}*/
}
}

