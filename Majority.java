/**
 * Majority.java
 *
 * Version:
 * 		 v1.2, 2/10/2018, 16:11:09
 *
 *		Initial revision
 */

/**
 * This program determines whether the frequency of particular elements in the array is greater than a particular number.
 *
 * @author Patil, Abhishek Sanjay
 *
 */

import java.util.*;

public class Majority {
	
	public static void main(String args[]) {
		
		Scanner  s = new Scanner(System.in);

		int n = s.nextInt();
		int arr[] = new int[n];
		
		for(int i = 0; i < n; i++) 
			arr[i] = s.nextInt();
		
		moreThanNBy2(arr, n);
		moreThanNBy3(arr, n);

	}
	
	/*
	 *	This function checks whether a number occurs more than n/2 time in a list
	 * 
	 */
	public static void moreThanNBy2(int[] arr, int n) {
		
		int num = select(arr, (n-1)/2);
		int count = 0;
		for(int i = 0; i < n; i++) {
			if( arr[i] == num)
				count++;
		}
		if(count > n/2)
			System.out.println("YES");
		else
			System.out.println("NO");
		
	}
	
	/*
	 *	This function checks whether a number occurs more than n/3 times in a list
	 * 
	 */
	public static void moreThanNBy3(int[] arr, int n) {
		
		int count1 = 0, count2 = 0, count3 = 0;
		int num1 = select(arr, (n-1)/3);
		int num2 = select(arr, (n-1)/2);
		int num3 = select(arr, (n-1) - (n-1)/3);
		
		for(int i = 0; i < n; i++) {
			if( arr[i] == num1)
				count1++;
			else if(arr[i] == num2)
				count2++;
			else if(arr[i] == num3)
				count3++;
		}
		
		int count = max ( count1, count2, count3);
		if(count > n/3)
			System.out.println("YES");
		else
			System.out.println("NO");
		
	}
	
	/*
	 *	This function implements k-select algorithm to find the frequency of k-th element
	 * 
	 */
	public static int select(int arr[], int k) {
		
		if (arr.length <= 5) {		
			Arrays.sort(arr);							// 
			return arr[k];
		}
		
		ArrayList<int[]> al = new ArrayList<int[]>();	// Stores arrays containing group of five elements
		int a = 0;
		
		// Splits array into  n/5 groups of 5 elements
		for( int i = 0; i < Math.floor(arr.length / 5); i++ ) {
			int smallArr[] = new int[5];
			for(int j = 0; j < 5; j++) {
				smallArr[j] = arr[a];
				a++;
			}
			al.add(smallArr);							// Adding at the end of ArrayList has a complexity of O(1)
		}
		// The rest of the array containing less than 5 elements
		if ((arr.length % 5) != 0) {
			int lastIndex = arr.length - a;
			int smallArr[] = new int[lastIndex];
			for(int j = 0; j < lastIndex; j++) {
				smallArr[j] = arr[a];
				a++;
			}
			al.add(smallArr);							// Adding at the end of ArrayList has a complexity of O(1)
		}
		
		// Array B contains the median of each groups of 5 elements 
		int B[] = new int[al.size()];
		for( int i = 0; i < B.length; i++ ) {
			B[i] = select( al.get(i), (al.get(i).length-1)/2);
		}
		
		int medianB = select(B, (B.length - 1) / 2);		
		
		
		// Partition the list in L, E, G
		ArrayList<Integer> Llist = new ArrayList<Integer>();
		ArrayList<Integer> Elist = new ArrayList<Integer>();
		ArrayList<Integer> Glist = new ArrayList<Integer>();
		
		for(int i = 0; i < arr.length; i++ ) {			// O(n)
			if(arr[i] < medianB)
				Llist.add(arr[i]);
			else if(arr[i] > medianB) 
				Glist.add(arr[i]);
			else
				Elist.add(arr[i]);
		}
		
		int L[] = new int[Llist.size()];
		int E[] = new int[Elist.size()];
		int G[] = new int[Glist.size()];

		for(int i = 0; i < L.length; i++) 
			L[i] = Llist.get(i);						// get operation of ArrayList has a complexity of O(1)
		
		for(int i = 0; i < E.length; i++) 
			E[i] = Elist.get(i);						// get operation of ArrayList has a complexity of O(1)
		
		for(int i = 0; i < G.length; i++) 
			G[i] = Glist.get(i);						// get operation of ArrayList has a complexity of O(1)
		
		if( k < L.length )
			return select(L, k);
		else if((k >= L.length) && (k < (L.length + E.length)))
			return medianB;
		else
			return select(G, k - L.length - E.length);
		
	}
	
	/*
	 * This function computes the max value of 3 elements.
	 */
	public static int max(int x, int y, int z) {
		if(x > y) {
			if(x>z)
				return x;
			else
				return z;
		}
		else {
			if(y>z)
				return y;
			else
				return z;
		}
	}
	
}
