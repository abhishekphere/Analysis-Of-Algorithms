/**
 * LongestIncreasingSubseqDP.java
 *
 * Version:
 * 		 v1.2, 3/3/2018, 18:01:19
 *
 *		Initial revision 
 */ 

/**
 * This program determines the length of longest Increasing Subsequence using dynamic approach.
 *
 * @author Patil, Abhishek Sanjay
 *
 */

import java.util.ArrayList;
import java.util.Scanner;

public class LongestIncreasingSubseqDP {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int A[] = new int[n];
		
		for(int i = 0; i < n; i++) {
			A[i] = s.nextInt();
		}
		System.out.println(maxUsingDynamic(A, n));
	}

	/*
	 *	This function provides the the length of increasing subsequence.
	 * 
	 */	
	public static int maxUsingDynamic(int A[], int n) {  // O(n^2)
		int S[] = new int[n];							 // Dynamic array
		
		S[0] = 1;
		for(int i = 1; i < n; i++) {
			int k = i - 1;
			int max = 0;
			while(k >= 0) {
				if(A[k] < A[i] && S[k] > max) {
					max = S[k];
				}
				k--;
			}
			S[i] = max + 1;
		}
		
		int max = S[0];
		for(int i = 0; i < n; i++) {
			if(S[i] > max)
				max = S[i];
		}
		return max;
	}

}
