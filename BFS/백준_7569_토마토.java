import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_7569_토마토 {
	static int H, M, N, noRipeCnt;
	static int[][][] map;
	static Queue<Tomato> q;
	
	static class Tomato { // 토마토 정보 저장
		int h, r, c, day;
		public Tomato(int h, int r, int c, int day) {
			this.h = h;
			this.r = r;
			this.c = c;
			this.day = day; // 경과 일수
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 가로칸 수 (열) 2 ≤ M ≤ 10
		N = Integer.parseInt(st.nextToken()); // 세로칸 수 (행) 2 ≤ N ≤ 100,
		H = Integer.parseInt(st.nextToken()); // 높이 1 ≤ H ≤ 100 
		map = new int[H][N][M];
		q = new LinkedList<Tomato>();
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < M; c++) {
					map[h][r][c] = Integer.parseInt(st.nextToken());
					
					if(map[h][r][c] == 1) {
						q.add(new Tomato(h, r, c, 0));
					} else if(map[h][r][c] == 0) {
						noRipeCnt++;
					}
				}
			}
		} // 입력처리
		bfs();
	} // end of main

	static int[][] dir = {{1,0,0}, {-1,0,0}, {0,-1,0}, {0,1,0}, {0,0,-1}, {0,0,1}}; // 위 아래 상 하 좌 우
	public static void bfs() {
		int day = 0;
		while(!q.isEmpty()) {
			Tomato t = q.poll();
			int h = t.h;
			int r = t.r;
			int c = t.c;
			day = t.day;
			
			for (int k = 0; k < dir.length; k++) {
				int nh = h + dir[k][0];
				int nr = r + dir[k][1];
				int nc = c + dir[k][2];
				if(nh<0 || nh>=H || nr<0 || nr>=N || nc<0 || nc>=M) continue;
				
				if(map[nh][nr][nc] == -1) continue;
				
				if(map[nh][nr][nc] == 0) {
					map[nh][nr][nc] = day + 1;
					noRipeCnt--;
					q.offer(new Tomato(nh, nr, nc, day + 1));
				}
			}
		}
		System.out.println(noRipeCnt == 0? day : -1);
		// 다 익었으면 경과일수 출력, 그렇지 않으면 -1 출력
	} // end of bfs
} // end of class
