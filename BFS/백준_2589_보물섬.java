import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2589_보물섬 {
	private static boolean[][] visited;
	private static int N, M;
	private static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 보물 지도의 세로 길이 <=50(행)
		M = Integer.parseInt(st.nextToken()); // 보물 지도의 가로 길이 <=50 (열)
		map = new char[N][M]; // N*M 보물섬 지도
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j); // 육지(L) , 바다(W)
			}
		}
		int answer = 0; // 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간
		// 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있음
		// 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안된다
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					visited = new boolean[N][M];
					int cur = bfs(i, j);
					answer = Math.max(cur, answer);
				}
			}
		}
		System.out.println(answer);
	} // end of main
	
	public static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	public static int[] dc = {0, 0, -1, 1};
	public static int bfs(int i, int j) {
		Queue<Loc> q = new LinkedList<>();
		q.offer(new Loc(i, j, 0));
		visited[i][j] = true;
		int temp = 0;
		
		while(!q.isEmpty()) {
			Loc l = q.poll();
			int r = l.r;
			int c = l.c;
			int dis = l.dis;
			
			for (int k = 0; k < dc.length; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr>=0 && nr<N && nc>=0 && nc<M
						&& !visited[nr][nc]
						&& map[nr][nc] == 'L') {
					q.offer(new Loc(nr, nc, dis + 1));
					visited[nr][nc] = true;
					
					temp = Math.max(temp, (dis + 1));
				}
			}
		} // end of while
		return temp;
	}	
	
	/** 행, 열 좌표를 저장할 객체 */
	static class Loc {
		int r, c, dis;
		public Loc(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}
	}
} // end of class
