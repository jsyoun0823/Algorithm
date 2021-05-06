import java.io.*;
import java.util.*;

public class BFS_상하좌우이동 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 행 (1 ≤ N ≤ 1,000)
		int M = Integer.parseInt(st.nextToken()); // 열 (1 ≤ M ≤ 1,000)
		char[][] map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int minCnt = -1;
		
		boolean[][][] visited = new boolean[2][N][M]; // 방문 배열 [0:벽안부숨, 1:벽깨부숨][r][c]
		Queue<int[]> q = new LinkedList<>(); // 큐 준비
		
		visited[0][0][0] = true;
		q.offer(new int[] {0, 0, 0, 1}); // r, c, mode(0:벽 안부숨, 1:벽깨부숨), cnt(지금까지의 거리)
		
		int dr[] = {-1, 1, 0, 0}; // 상하좌우
		int dc[] = {0, 0, -1, 1};
		
		while(!q.isEmpty()) {
			
			int[] now = q.poll(); // 큐에서 꺼내기
			int r = now[0];
			int c = now[1];
			int mode = now[2];
			int cnt = now[3];
			
			if(r == N-1 && c == M-1) { // 도착했으면
				minCnt = cnt;
				break;
			}
			
			for (int i = 0; i < dc.length; i++) {
				
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr>=0 && nr<N && nc>=0 && nc<M
						&& !visited[mode][nr][nc]) { // 배열 범위 내, 미방문이면
					
					if (map[nr][nc] == '0') { // 길이면
						
						visited[mode][nr][nc] = true; // 방문표시
						q.offer(new int[] {nr, nc, mode, cnt + 1});
						
					} else if (mode == 0){ // 길이 아니면, 벽을 부셔야함(기존에 벽을 부순적이 없는 경우만)
						
						visited[1][nr][nc] = true; // 방문표시
						q.offer(new int[] {nr, nc, 1, cnt + 1});
					
					}
				}
			}
		}
		System.out.println(minCnt);
	} // end of main
} // end of class