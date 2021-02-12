import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238_Contact {
	
	static int[][] m;
	static boolean[] visited;
	static int lastMax;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		m = new int[101][101]; // 인접행렬, 한번만 만들어서 재사용
		for (int test_case = 1; test_case <= 10; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // N : 입력 데이터 길이
			int root = Integer.parseInt(st.nextToken()); // 시작점
			// 배열 초기화
			for (int i = 1; i < m.length; i++) {
				for (int j = 1; j < m.length; j++) {
					m[i][j] = 0;
				}
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = N/2; i > 0; i--) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				m[from][to] = 1;
			}
			visited = new boolean[101]; // 방문배열 0번방 안씀
			bfs(root);
			sb.append('#').append(test_case).append(' ').append(lastMax).append('\n');
		} // end of for test case
		System.out.println(sb);
	} // end of main

	public static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(root);
		visited[root] = true;
		while(!queue.isEmpty()) {
			lastMax = 0;
			int size = queue.size();
			while(--size >= 0) {
				int current = queue.poll();
				for (int i = 0; i < m.length; i++) {
					if(m[current][i] == 1 && !visited[i]) {
						queue.offer(i);
						visited[i] = true;
					}
				}
				if (current > lastMax) lastMax = current;
			}
		}
	}
}
