
/**
 * This class implements Stack data structure
 *
 * @author Patil, Abhishek Sanjay
 *
 */
public class Stack {
	
	Node2 rear;
	int count;
	
	Stack(){
		this.rear = null;
		this.count = 0;
	}
	
	/**
	 * This function stores the data in the stack data structure
	 * 
	 * @return		None
	 */
	public void push(int data) {
		Node2 node = new Node2(data);
		if(rear == null) {
			rear = node;
		}
		else {
			node.next = rear;
			rear = node;
		}
		count = count + 1;
	}
	
	/**
	 * This function removes the recently added element to the stack.
	 * 
	 * @return		boolean		whether popping was done or not 
	 */
	public boolean pop() {
		if(rear == null) {
			return false;
		}
		else {
			rear = rear.next;
			count = count - 1;
			return true;
		}
	}
	
	/**
	 * This function returns the top most element of the stack.
	 * 
	 * @return		int		the value the was most recently added
	 */
	public int peek() {
		if(rear != null) {
			return rear.data;
		}
		else
			return -1;
	}
	
	
	public int size() {
		return count;
	}
	
	/**
	 * This function returns whether stack is empty or not.
	 * 
	 * @return		boolean		whether stack is empty or not
	 */
	public boolean empty() {
		if(rear == null)
			return true;
		else
			return false;
	}
	
}


/**
 * This class creates nodes with data and link to other nodes.
 *
 * @author Patil, Abhishek Sanjay
 * @author Shachi, Turakhia
 *
 */
class Node2{
	
	Node2 next;
	int data;
	
	Node2(int data){
		this.data = data;
		this.next = null;
	}
	
	
}
