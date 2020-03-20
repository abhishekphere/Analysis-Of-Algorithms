/**
 * LongestIncreasingSubseqRecursive.java
 *
 * Version:
 * 		 v1.2, 3/3/2018, 17:15:49
 *
 *		Initial revision 
 */

/**
 * This program determines the length of longest Increasing Subsequence using Recursive approach.
 *
 * @author Patil, Abhishek Sanjay
 *
 */
import java.util.*;

public class LongestIncreasingSubseqRecursive {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		
		int n = s.nextInt();
		int A[] = new int[n];
		
		for(int i = 0; i < n; i++) {
			A[i] = s.nextInt();
		}
		System.out.println(maxUsingRecursion(A, n));
	}
	
	/*
	 *	This function counts the length of the longest increasing subsequence to be found.
	 * 
	 */
	public static int maxUsingRecursion(int A[], int n) {	// O(n.2^(n))
		int max = 0;
		for(int i = 0; i < n; i++) {						// O(n)
			int count = incrSubseqRecursive(i, A);			// O(2^(n))
			if(count > max)
				max = count;
   		}	
		return max;
	}

	/*
	 *	This function provides the the length of increasing subsequence until index j.
	 * 
	 */	
	public static int incrSubseqRecursive(int j, int A[]) {		// O(2power(n))
		if(j == 0)
			return 1;
		
		else {
			ArrayList<Integer> al = new ArrayList<Integer>();
			
			for(int i = 0; i <= j; i++) {
				if (A[i] < A[j]) {
					int count = incrSubseqRecursive(i, A);
					al.add(count);
				}
			}
			return 1 + findMaxOfList(al);
		}
	}

	/*
	 *	This function calculates the max value in the arrayList.
	 * 
	 */
	public static int findMaxOfList(ArrayList<Integer> al) {
		int max = 0;
		for(int i: al) {
			if( i > max)
				max = i;
		}  
		return max;
	}
}
