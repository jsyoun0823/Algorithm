import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2178_미로탐색 {

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		
		map = new char[N + 1][M + 1]; // 0번방 안씀
		visited = new boolean[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1, index = 0; j <= M; j++, index++) {
				map[i][j] = str.charAt(index);
			}
		}
		System.out.println(bfs());
	}
	
	public static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	public static int[] dc = {0, 0, -1, 1};
	
	public static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(1, 1, 1));
		visited[1][1] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int row = p.r;
			int col = p.c;
			int dis = p.d;
			
			if(row == N && col == M) return dis;
			for (int i = 0; i < dc.length; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				if(nr>0 && nr<=N && nc>0 && nc<=M 
						&& map[nr][nc] == '1' 
						&& !visited[nr][nc]) {
					q.offer(new Point(nr, nc, dis + 1));
					visited[nr][nc] = true;
				}
			}
		}
		return 0;
	}

	static class Point{
		int r, c, d;
		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
