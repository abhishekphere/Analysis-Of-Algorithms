import java.util.*;

public class PathInMaze {

	static int m, n;
	static int findPath[][], visited[][];

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		m = s.nextInt();
		n = s.nextInt();

		findPath = new int[m][n];
		visited = new int[m][n];

		Position start = null, end = null;

		// Input
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				findPath[i][j] = s.nextInt();
				if (findPath[i][j] == 2)
					start = new Position(i, j, 0);
				else if (findPath[i][j] == 3)
					end = new Position(i, j, 0);
			}
		}
		BFS(start, end);
	}

	public static void BFS(Position start, Position end) {
		Queue<Position> q = new LinkedList<Position>();
		q.add(start); // O(1)
		int flag = 0;
		Position current = null;

		while (q.size() != 0) {
			current = q.remove(); // O(1)
			if (end.x == current.x & end.y == current.y) {
				flag = 1;
				break;
			}
			if (isLeft(current.x, current.y)) {
				q.add(new Position(current.x, current.y - 1, current.cost + 1));
				visited[current.x][current.y - 1] = 1;
			}
			if (isDown(current.x, current.y)) {
				q.add(new Position(current.x + 1, current.y, current.cost + 1));
				visited[current.x + 1][current.y] = 1;
			}
			if (isRight(current.x, current.y)) {
				q.add(new Position(current.x, current.y + 1, current.cost + 1));
				visited[current.x][current.y + 1] = 1;
			}
			if (isUp(current.x, current.y)) {
				q.add(new Position(current.x - 1, current.y, current.cost + 1));
				visited[current.x - 1][current.y] = 1;
			}
		}
		if (flag == 1)
			System.out.println(current.cost);
		else if (flag == 0)
			System.out.println("-1");
	}

	public static boolean isLeft(int i, int j) {
		if (j - 1 >= 0) {
			if ((findPath[i][j - 1] == 0 & visited[i][j - 1] == 0) || findPath[i][j - 1] == 3)
				return true;
		}
		return false;
	}

	public static boolean isRight(int i, int j) {
		if (j + 1 < n) {
			if ((findPath[i][j + 1] == 0 & visited[i][j + 1] == 0) || findPath[i][j + 1] == 3)
				return true;
		}
		return false;
	}

	public static boolean isUp(int i, int j) {
		if (i - 1 >= 0) {
			if ((findPath[i - 1][j] == 0 & visited[i - 1][j] == 0) || findPath[i - 1][j] == 3)
				return true;
		}
		return false;
	}

	public static boolean isDown(int i, int j) {
		if (i + 1 < m) {
			if ((findPath[i + 1][j] == 0 & visited[i + 1][j] == 0) || findPath[i + 1][j] == 3)
				return true;
		}
		return false;
	}
}

class Position {
	int x, y, cost;

	Position(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

}