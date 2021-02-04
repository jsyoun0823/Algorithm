import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_14442_벽부수고이동하기2 {

	static int N, M, K;
	static char[][] map;
	static int ans;

	static class Point {
		int r, c, k, cnt;

		public Point(int r, int c, int k, int cnt) {
			this.r = r; // 행
			this.c = c; // 열
			this.k = k; // 현재까지 부순 벽의 개수
			this.cnt = cnt; // 현재까지 이동거리
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		} // 입력처리

		ans = -1;
		bfs();
		System.out.println(ans);
	} // end of main

	public static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	public static int[] dc = { 0, 0, -1, 1 };

	public static void bfs() { // bfs 탐색
		Queue<Point> q = new LinkedList<>();
		boolean[][][] visited = new boolean[11][N][M]; // 방문처리 배열 - 벽 부순 횟수, 행, 열

		q.offer(new Point(0, 0, 0, 1));
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;
			int k = p.k; // 지금까지 부순 벽 개수
			int cnt = p.cnt; // 지금까지 이동 거리

			if (r == N - 1 && c == M - 1) { // 목적지에 도착하면
				ans = cnt; // 현재까지 이동 거리 저장
				return; // 반환
			}

			for (int i = 0; i < dc.length; i++) { // 인접한 곳 보면서
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M // 배열 범위 체크
						&& !visited[k][nr][nc]) { // 방문체크
					visited[k][nr][nc] = true;
					if (map[nr][nc] == '0') { // 길인 경우
						q.offer(new Point(nr, nc, k, cnt + 1)); // 벽 뚫지않고 방문처리, 큐에 저장
					} else if (map[nr][nc] == '1') {
						if (k < K) { // 아직 횟수 제한 남았으면 벽 뚫기
							q.offer(new Point(nr, nc, k + 1, cnt + 1)); // 벽 뚫은 개수 증가하고 큐에 저장
						}
					}
				}
			}
		} // end of while
	} // end of bfs
} // end of class
