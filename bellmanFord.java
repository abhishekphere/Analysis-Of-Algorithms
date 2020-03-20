
import java.util.*;
import java.lang.*;
import java.io.*;

// A class to represent a connected, directed and weighted graph
class bellmanFord {
	// A class to represent a weighted edge in graph
	class Edge {
		int src, dest, weight;

		Edge() {
			src = dest = weight = 0;
		}
	};

	int V, E;
	Edge edge[];

	// Creates a graph with V vertices and E edges
	bellmanFord(int v, int e) {
		V = v;
		E = e;
		edge = new Edge[e];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}

	// The main function that finds shortest distances from src
	// to all other vertices using Bellman-Ford algorithm. The
	// function also detects negative weight cycle
	void BellmanFord(bellmanFord graph, int src, int vials, int time) {
		int V = graph.V, E = graph.E;
		int dist[] = new int[V];

		// Step 1: Initialize distances from src to all other
		// vertices as INFINITE
		for (int i = 0; i < V; ++i)
			dist[i] = Integer.MAX_VALUE;
		dist[src] = 0;

		int check[] = new int[V];
		for (int j = 0; j < V; j++) {
			check[j] = Integer.MAX_VALUE;
		}

		for (int i = 0; i <= vials; ++i) {
			for (int j = 0; j < E; ++j) {
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				int weight = graph.edge[j].weight;
				 
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {

					if (check[v] > (dist[u] + weight))
						check[v] = dist[u] + weight;
				}
			}

			for (int j = 0; j < V; j++) {
				if (check[j] != 0) {
					if (dist[j] > check[j])
						dist[j] = check[j];
				}
			}

			for (int j = 0; j < V; j++) {
				check[j] = Integer.MAX_VALUE;
			}
			printArr(dist, V);
		}
		System.out.println(dist[V-1] <= time);
	}

	// A utility function used to print the solution
	void printArr(int dist[], int V) {
		System.out.println("Vertex   Distance from Source");
		for (int i = 0; i < V; ++i)
			System.out.println(i + "\t\t" + dist[i]);
	}

	// Driver method to test above function
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int V = s.nextInt(); // Number of vertices in graph
		int k = s.nextInt();
		int t = s.nextInt();
		int E = s.nextInt(); // Number of edges in graph

		bellmanFord graph = new bellmanFord(V, 2 * E);
		// E = 14;

		int j = E;
		for (int i = 0; i < E; i++) {
			graph.edge[i].src = s.nextInt();
			graph.edge[i].dest = s.nextInt();
			graph.edge[i].weight = s.nextInt();

			graph.edge[j].src = graph.edge[i].dest;
			graph.edge[j].dest = graph.edge[i].src;
			graph.edge[j].weight = graph.edge[i].weight;
			j++;
		}
		// System.out.println("hi");
		graph.BellmanFord(graph, 0, k, t);
	}
}


/*

Vertex   Distance from Source
0		0
1		4
2		9
3		3
4		1
5		7
6		10
7		6
8		3
9		6
10		12
11		13
12		13
13		4
14		11
15		10
16		15
17		19
18		19
19		14
20		15
21		18
22		21
23		17
24		20
25		22

*/

