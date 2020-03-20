/**
 * Shovel.java
 *
 * Version:
 * 		 v1.2, 4/16/2018, 20:11:09
 *
 *		Initial revision
 */

/**
 * This program determines the shortest path to a given point.
 *
 * @author Patil, Abhishek Sanjay
 *
 */

import java.util.*;

public class Shovel {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int rows = s.nextInt();
		int cols = s.nextInt();

		int streets[][] = new int[rows + 1][cols];
		int aves[][] = new int[cols + 1][rows];

		// Inputs streets
		for (int i = 0; i < rows + 1; i++) {
			for (int j = 0; j < cols; j++) {
				streets[i][j] = s.nextInt();
			}
		}

		// Inputs avenues
		for (int i = 0; i < cols + 1; i++) {
			for (int j = 0; j < rows; j++) {
				aves[i][j] = s.nextInt();
			}
		}
 
		createGraph(rows, cols, streets, aves);
	}

	// This function creates a graph in which horizontal nodes have street weights and vertical nodes have avenues weights.
	public static void createGraph(int rows, int cols, int streets[][], int aves[][]) {
		
		int gridLength = (rows + 1) * (cols + 1);		// final graph size
		int grid[][] = new int[gridLength][gridLength]; // final graph

		for (int i = 0; i < gridLength; i++) {
			for (int j = 0; j < gridLength; j++) {
				grid[i][j] = 9;
			}
		}

		// Storing street weights at appropriate locations 
		int gridRow = 0, gridCol = 1;
		for (int i = 0; i < rows + 1; i++) {
			for (int j = 0; j < cols; j++) {
				grid[gridRow][gridCol] = streets[i][j];
				grid[gridCol][gridRow] = streets[i][j];
				gridRow++;
				gridCol++;
			}
			gridRow++;
			gridCol++;
		}

		gridRow = 0;
		gridCol = cols + 1;
		
		// Storing avenue weights at appropriate locations
		int index = 0;
		for (int i = 0; i < cols + 1; i++) {
			for (int j = 0; j < rows; j++) {

				grid[gridRow][gridCol] = aves[i][j];
				grid[gridCol][gridRow] = aves[i][j];

				gridRow += cols + 1;
				gridCol += cols + 1;
			}
			index++;
			gridRow = index;
			gridCol = index + cols + 1;
		}
		 
//		Printing grid
//		for (int i = 0; i < grid.length; i++) {
//			for (int j = 0; j < grid.length; j++) {
//				System.out.print(grid[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		// Applying Dijkstra for shortest path
		dijkstra(grid, gridLength);

	}

	// This function finds the minimum distance from source to a vertex
	public static int min(int dist[], boolean selectedNodes[]) {
		
		int min1 = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < dist.length; i++) {
			if ((selectedNodes[i] == false) & dist[i] < min1) {
				min1 = dist[i];
				index = i;
			}
		}
		return index;
	}

	// This function calculates the shortest path using Dijkstra's algorithm
	public static void dijkstra(int grid[][], int gridLength) {

		boolean selectedNodes[] = new boolean[gridLength];	// Keeps track of selected nodes
		int dist[] = new int[gridLength];					// keeps track of the distance from source to current node
		
		for (int i = 0; i < gridLength; i++)
			dist[i] = Integer.MAX_VALUE;

		dist[0] = 0;

		for (int i = 0; i < gridLength - 1; i++) {
			int node = min(dist, selectedNodes);

			selectedNodes[node] = true;

			for (int j = 0; j < gridLength; j++) {
				if ((selectedNodes[j] == false) && (grid[node][j] != 9) && (dist[node] + grid[node][j] < dist[j])) {
					dist[j] = dist[node] + grid[node][j];
				}
			}
		}

		System.out.println(dist[gridLength - 1]);
	}
}

