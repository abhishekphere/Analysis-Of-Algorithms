/**
 * Wizard.java
 *
 * Version:
 * 		 v1.2, 2/22/2018, 16:11:09
 *
 *		Initial revision
 */

/**
 * This program determines the shortest path between two nodes with certain limitations
 *
 * @author Patil, Abhishek Sanjay
 *
 */

import java.util.*;

public class Wizard {
	
	// This function finds whether a path using limited time and vials can be formed between two nodes   O(mn)
	public static boolean bellmanFord(Edges edges[], int V, int E, int vials, int time) {

		int distance[] = new int[V];			// keeps track of distance from source to ith node
		int update[] = new int[V];				

		for (int i = 0; i < V; ++i)
			distance[i] = Integer.MAX_VALUE;
		distance[0] = 0;

		for (int j = 0; j < V; j++) {
			update[j] = Integer.MAX_VALUE;
		}

		for (int i = 0; i <= vials; ++i) {
			for (int j = 0; j < edges.length; ++j) {
				int src = edges[j].source;
				int dest = edges[j].dest;
				int weight = edges[j].weight;

				if (distance[src] != Integer.MAX_VALUE && distance[src] + weight < distance[dest]) {
					if (update[dest] > (distance[src] + weight))
						update[dest] = distance[src] + weight;
				}
			}

			for (int j = 0; j < V; j++) {
				if (update[j] != 0) {
					if (distance[j] > update[j])
						distance[j] = update[j];
				}  
			}

			for (int j = 0; j < V; j++) {
				update[j] = Integer.MAX_VALUE;
			}
		}
		return(distance[V - 1] <= time);
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int V = s.nextInt(); // Number of vertices in graph
		int k = s.nextInt();
		int t = s.nextInt();
		int E = s.nextInt(); // Number of edges in graph

		Edges edges[] = new Edges[2 * E];
		for (int i = 0; i < edges.length; ++i)
			edges[i] = new Edges();

		int j = E;
		for (int i = 0; i < E; i++) {
			edges[i].source = s.nextInt();
			edges[i].dest = s.nextInt();
			edges[i].weight = s.nextInt();

			edges[j].source = edges[i].dest;
			edges[j].dest = edges[i].source;
			edges[j].weight = edges[i].weight;
			j++;
		}
		j = E;

		if(bellmanFord(edges, V, E, k, t))
			System.out.println("YES");
		else
			System.out.println("NO");
	}
}

// Cretes a class Edge to store edge values
class Edges {
	int source;
	int dest;
	int weight;
}