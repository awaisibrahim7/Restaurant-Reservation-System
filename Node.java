/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #1
 *
 * Create a Heap class using nodes and then use it
 * for a Restaurant seating program
 * 
 * Name: Awais Ibrahim
 */
/* 
 * This class implements a Node of a generic type that has a value,
 *  a link to the nodes on its left and right and a link to its parent.
 */
public class Node<V extends Comparable> 
{
	private V value;
	private Node<V> left;
	private Node<V> right;
	private Node<V> parent;
	/*
	 * This first constructor takes in a value and creates a single
	 * node with no links to any children or a parent.
	 */
	public Node(V value)
	{
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	/*
	 * This next constructor creates a node with a value, as well as
	 * a left, right and parent node.
	 */
	public Node(V value, Node<V> left, Node<V> right, Node<V> parent)
	{
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	/*
	 * This method is a "getter" in which it returns the value of the 
	 * private instance variable value.
	 */
	public V getValue() 
	{
		return value;
	}
	/*
	 * This method is a "setter" in which it sets the value of the 
	 * private instance variable value to the parameter value.
	 */
	public void setValue(V value) 
	{
		this.value = value;
	}
	/*
	 * This method is a "getter" in which it returns the value of the 
	 * private instance variable left returning the left node.
	 */
	public Node<V> getLeft() 
	{
		return left;
	}
	/*
	 * This method is a "getter" in which it returns the value of the 
	 * private instance variable right and returns the right node.
	 */
	public Node<V> getRight() 
	{
		return right;
	}
	/*
	 * This method is a "getter" in which it returns the value of the 
	 * private instance variable parent of the current node.
	 */
	public Node<V> getParent()
	{
		return parent;
	}
	/*
	 * This method is a "setter" in which it sets the value of the 
	 * private instance variable left.
	 */
	public void setLeft(Node<V> left) 
	{
		this.left = left;
	}
	/*
	 * This method is a "setter" in which it sets the value of the 
	 * private instance variable right.
	 */
	public void setRight(Node<V> right) 
	{
		this.right = right;
	}
	/*
	 * This method checks if the current node has a left node
	 * returning either true or false.
	 */
	public boolean hasLeft()
	{
		if(getLeft() != null)
			return true;
		else
			return false;
	}
	/*
	 * This method checks if the current node has a right node
	 * returning either true or false.
	 */
	public boolean hasRight()
	{
		if(getRight() != null)
			return true;
		else
			return false;
	}
	/*
	 * This method checks if the current node is the left node
	 * of the current node.
	 */
	public boolean isLeftOf(Node<V> parent)
	{
		if(parent.getLeft() == this)
			return true;
		else
			return false;
	}
	/*
	 * This method checks if the current node is the right node
	 * of the current node.
	 */
	public boolean isRightOf(Node<V> parent)
	{
		if(parent.getRight() == this)
			return true;
		else
			return false;
	}
	/*
	 * This method sets the parent
	 * of the current node.
	 */
	public void setParent(Node<V> parent)
	{
		this.parent = parent;
	}
	/*
	 * This method checks if the current node is a leaf node
	 * meaning it has no children.
	 */
	 public boolean isLeaf(Node<V> current)
	  {
		  if(current.getLeft() == null && current.getRight() == null)
			  return true;
		  else
			  return false;
	  }
	 /*
		 * This method compares two node to see if one is greater than
		 * the other or equal to it.
		 */
	 public int compareTo(Node<V> current)
	 {
		 if(value.compareTo(current.getValue()) > 0)
			 return 1;
		 else if(value.compareTo(current.getValue()) == 0 )
			 return 0;
		 else
			 return -1;
	 }
}

