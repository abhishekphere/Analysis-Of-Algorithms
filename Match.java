import java.util.Scanner;
import java.util.Stack;

/**
 * Match.java
 *
 * Version:
 * 		 v1.2, 1/27/2018, 16:11:09
 *
 *		Initial revision
 */

/**
 * This program determines the total number of valid pairs available given two sets.
 *
 * @author Patil, Abhishek Sanjay
 *
 */

public class Match {   

	public static void main(String[] args) {
		input();
	} 
	
	/**
	 * This function takes the preference list of set A & set B and finds the stable matching pairs 
	 * 
	 * @return		None
	 */
	public static void input() {	
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();							// The no. of elements in each group
		int setA[][] = new int[n][n];					// Stores elements of set A 
		int setB[][] = new int[n][n];					// Stores elements of set B
		int checkA[][] = new int[n][n];					// tracks ith preference of set A
		int checkB[][] = new int[n][n];					// keeps track of stable pairs
		StackClass stack = new StackClass();	// keeps track of free elements of set A 
		
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++) 
				setA[i][j] = s.nextInt();
			
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++) 
				setB[i][j] = s.nextInt();
		
		int resultA[][] = findStableMatches(setA, setB, checkA, checkB, n, stack);

		checkA = new int[n][n];
		checkB = new int[n][n];
		int resultB[][] = findStableMatches(setB, setA, checkA, checkB, n, stack);
		
		findValidPairs(resultA, resultB, n);
	}
	
	/**
	 * This function finds the stable matching pairs.
	 * 
	 * @return		result		2D array used to store stable pairs
	 */
	public static int[][] findStableMatches(int setA[][], int setB[][], int checkA[][], int checkB[][], int n, StackClass stack) {
		 
		int result[][] = new int[n][n];				// stores the stable pairs
		
		// fills the stack with order of processing
		for(int i = 0; i < n; i++)
			stack.push(i);
			
		// Runs until every element gets a stable match
		while(!stack.empty()) {
			
			int elementA = (int)stack.peek();		// An element is selected for assignment
			int i = 0;
			
			// Keeps track of elements that are not yet asked
			while(checkA[elementA][i] == 1)
				i++;
			checkA[elementA][i] =  1;
			
			int elementB = setA[elementA][i];		// the element to which elementA is assigned
			int j = 1;
			// if setB element is not free 

			for(j = 0; j < n; j++) {
				
				// if setA rejects setB element
				if(checkB[elementB][j] == 1 && setB[elementB][j] != elementA) {
					j++;
					break;
				}
				// if setA prefers setB element more than current assignment
				if( setB[elementB][j] == elementA ) {
					stack.pop();
					checkB[elementB][j] = 1;
					j++;
					break;
				}
			}
			
			// Deletes the previously assigned pair as it was updated
			while(j < n) {
				if( checkB[elementB][j] == 1 ) {
					checkB[elementB][j] = 0;
					stack.push(setB[elementB][j]);
					break;
				}
				j++;  
			}	
		}
		
		// stores the resultant stable pairs in 2D array format
		for(int i = 0; i < n; i++) 
			for(int j = 0; j < n; j++) 
				if(checkB[i][j] == 1) {
					result[i][ setB[i][j] ] = 1;
				//  prints the stable pairs
				//	System.out.println(i + " , " + setB[i][j-1]);  
				}
		
		return result;
	}
	
	/**
	 * This function finds the valid pairs among the stable matchings .
	 * 
	 * @return		None
	 */
	public static void findValidPairs(int resultA[][], int resultB[][], int n) {
		
		int counter = 0;					// Counts the total no of valid pairs
		for(int i = 0; i < n; i++)  
			for(int j = 0; j < n; j++) 
				// checks if the element from first set is paired with the same element from the second set
				if(resultA[i][j] == 1) {
					if(resultB[j][i] == 1)
						counter++;
				}
		 System.out.println( counter );
		
	}
	
}

/**
 * This class implements Stack data structure
 *
 * @author Patil, Abhishek Sanjay
 *
 */
class StackClass {
	
	Node3 rear;
	
	StackClass(){
		this.rear = null;
	}
	
	/**
	 * This function stores the data in the stack data structure
	 * 
	 * @return		None
	 */
	public void push(int data) {
		Node3 node = new Node3(data);
		if(rear == null) {
			rear = node;
		}
		else {
			node.next = rear;
			rear = node;
		}
	}
	
	/**
	 * This function removes the recently added(topmost) element to the stack.
	 * 
	 * @return		boolean		whether popping was done or not 
	 */
	public boolean pop() {
		if(rear == null) {
			return false;
		}
		else {
			rear = rear.next;
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
 *
 */
class Node3{
	
	Node3 next;
	int data;
	
	Node3(int data){
		this.data = data;
		this.next = null;
	}
}


