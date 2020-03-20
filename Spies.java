/**
 * Spies.java
 *
 * Version:
 * 		 v1.1, 4/16/2018, 12:36:09
 *
 *		Initial revision
 */

/**
 * This program determines the minimum cost network of a graph.
 *
 * @author Patil, Abhishek Sanjay
 *
 */

import java.util.*;

public class Spies {

	public static int parent[];
	public static boolean done[];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		// Input
		int V = s.nextInt();						// Number of nodes
		int E = s.nextInt();						// Number of edges

		int k = s.nextInt(), j;						// Number of unreliable nodes
		int data[][] = new int[E][3];				// stores cost and vertices in 2D array 
		boolean isUnreliable[] = new boolean[V];	// Stores unreliable nodes
		
		for (int i = 0; i < k; i++) {
			j = s.nextInt();
			isUnreliable[j] = true;
		}

		for (int i = 0; i < E; i++) {
			int from = s.nextInt();
			int to = s.nextInt();
			int cost = s.nextInt();
	
			data[i][0] = cost;
			data[i][1] = from;
			data[i][2] = to;
		}

		minCostNetwork(E, V, isUnreliable, data);
	}

	// This function finds the boss element (the root parent of a node) 
	public static int find(int i) {				// O(V) where V is the number of vertices
		if (i == 0) {
			return 0;
		}
		while (parent[i] != 999999) {
			i = parent[i];
			if (i == 0)
				break;
		}
		return i;
	}

	// This function returns a boolean value based on if the edge is possible to create or not
	public static boolean union(int node1, int node2, boolean isUnreliable[]) {
		
		// If both nodes are unreliable
		if (isUnreliable[node1] && isUnreliable[node2]) {
			return false;
		} 
		// If both nodes are reliable
		else if (!isUnreliable[node1] && !isUnreliable[node2]) {
			// Checks if both have different root parent
			if (find(node1) != find(node2)) {
				parent[find(node2)] = find(node1);
				return true;
			}
		}
		// If node 1 are unreliable
		else if (isUnreliable[node1]) {
			// Checks if any edge was previously created using node 1
			if (done[node1] == false) {
				// Checks if both have different root parent
				if (find(node1) != find(node2)) {
					parent[find(node2)] = find(node1);
				}
				done[node1] = true;
				return true;
			}
		} 
		// If node 2 are unreliable
		else if (isUnreliable[node2]) {
			// Checks if any edge was previously created using node 2
			if (done[node2] == false) {
				// Checks if both have different root parent
				if (find(node1) != find(node2)) {
					parent[find(node2)] = find(node1);
				}
				done[node2] = true;
				return true;
			}
		}
		return false;
	}

	// This function calculates the minimum cost network by using kruskal's idea of MST	(O(ElogV))
	public static void minCostNetwork(int E, int V, boolean isUnreliable[], int data1[][]) { 
		
		merge(data1, 0, data1.length - 1, 0);					// Sorts all edges based on weights O(ElogE)
		
		ArrayList<Integer> network = new ArrayList<Integer>();	// keeps track of weights of the edges that are selected
		done = new boolean[V];									// tracks whether edges were made using unreliable nodes
		parent = new int[V];
		for (int i = 0; i < V; i++) {
			parent[i] = 999999;
		}
		parent[0] = 0;

		int count = 0, flag = 0;
		 
		for (int i = 0; i < V-1; ) {						// O(V^2)
			if(count > E-1) {
				flag = 1;
				break;
			}
				
			int cost1 = data1[count][0];
			int node1 = data1[count][1]; 
			int node2 = data1[count][2];
			
			// Checks if edge from node1 to node2 is valid
			if (union(node1, node2, isUnreliable)) {
				network.add(cost1);
				i++;
			}
			count++;
		}
		
		// If all vertices are used in creating MST
		if (flag == 0) {
			int sum = 0;
			for (int i = 0; i < network.size(); i++) {
				sum += network.get(i);
			}
			System.out.println(sum);
		}
		// If MST is incomplete
		else {
			System.out.println("NONE");
		}
	}

	// This function implements merge sort
	public static void merge(int arr[][], int start, int end, int column) {
		int middle = 0;
		if (start < end) {
			middle = (start + end) / 2;

			merge(arr, start, middle, column);
			merge(arr, middle + 1, end, column);

			mergeSort(arr, start, middle, end, column);
		}
	}

	// This function sorts and merges based on column number
	public static void mergeSort(int arr[][], int start, int middle, int end, int column1) {

		int temp[][] = new int[end - start + 1][3];

		int i = start;
		int j = middle + 1;
		int k = 0;
		int column2, column3 = 2;
		if (column1 == 0)
			column2 = 1;
		else
			column2 = 0;

		while (i <= middle && j <= end) {
			// System.out.println(i+" "+j);
			if (arr[i][column1] < arr[j][column1]) {
				temp[k][column1] = arr[i][column1];
				temp[k][column2] = arr[i][column2];
				temp[k][column3] = arr[i][column3];
				i++;
			} else {
				temp[k][column1] = arr[j][column1];
				temp[k][column2] = arr[j][column2];
				temp[k][column3] = arr[j][column3];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			temp[k][column1] = arr[i][column1];
			temp[k][column2] = arr[i][column2];
			temp[k][column3] = arr[i][column3];
			i++;
			k++;
		}
		while (j <= end) {
			temp[k][column1] = arr[j][column1];
			temp[k][column2] = arr[j][column2];
			temp[k][column3] = arr[j][column3];
			j++;
			k++;
		}
		j = 0;
		for (i = start; i <= end; i++) {
			arr[i][column1] = temp[j][column1];
			arr[i][column2] = temp[j][column2];
			arr[i][column3] = temp[j][column3];
			j++;
		}
	}

}
