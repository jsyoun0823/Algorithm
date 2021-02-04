import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_1260_DFS와BFS {
	
	static int N, M, V;
	static boolean adjMatrix[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 정점의 개수 N(1 ≤ N ≤ 1,000)
		M = sc.nextInt(); // 간선의 개수 M(1 ≤ M ≤ 10,000)
		V = sc.nextInt(); // 탐색을 시작할 정점의 번호 V

		adjMatrix = new boolean[N+1][N+1];
		visited = new boolean[N+1];

		for (int i = 0; i < M; ++i) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = true;
		}
		dfs(V);
		System.out.println();
		bfs();
	}

	private static boolean[] visited;
	private static void dfs(int current) {
		visited[current] = true;
		System.out.print(current + " ");

		for (int i = 1; i <= N; i++) {
			if (adjMatrix[current][i] && !visited[i]) {
				dfs(i);
			}
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean visited[] = new boolean[N + 1];

		int current = V;
		queue.offer(current);
		
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (visited[current]) continue;
			
			visited[current] = true;
			System.out.print(current + " ");
			for (int i = 1; i <= N; ++i) {
				if (adjMatrix[current][i] && !visited[i]) {
					queue.offer(i);
				}
			}
		}
	}
}