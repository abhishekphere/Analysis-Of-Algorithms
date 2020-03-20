/**
 * Donut.java
 *
 * Version:
 * 		 v1.2, 2/25/2018, 16:11:09
 *
 *		Initial revision
 */

/**
 * This program determines the best possible location for the donut store.
 *
 * @author Patil, Abhishek Sanjay
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Donut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner s =  new Scanner(System.in);
		
		int n = s.nextInt();
		int x[] = new int[n];
		int y[] = new int[n];
		
		for(int i = 0; i < n; i++) {
			x[i] = s.nextInt();
			y[i] = s.nextInt();
		}
		
		int xbest = select(x, (n-1)/2);
		int ybest = select(y, (n-1)/2);
//		System.out.println(xbest+" "+ybest);
		calculateMinDistance(xbest, ybest, x, y); 
		
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
	 *	This function calculates the distance from the best location of the store to every police's locations. 
	 * 
	 */
	public static void calculateMinDistance(int xi, int yi, int x[], int y[]) {
		
		int distance = 0;
		int distFromX = 0, distFromY = 0;
		for(int i = 0; i < x.length; i++) {
			distFromX = Math.abs(xi - x[i]);
			distFromY = Math.abs(yi - y[i]);
			distance += distFromX + distFromY; 
		}
		System.out.println(distance);
	}

}
