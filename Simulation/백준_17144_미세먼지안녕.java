import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 
 * 백준 17144 미세먼지 안녕!
 * */

// T초동안
	// 1. 미세먼지 모든 칸에서 동시에 확산
		// (r, c) 에있는 미세먼지 인접한 네방향 확산 (상하좌우)
		// 확산되는 양은 A(r,c)/5 
	// (r,c)에 남은 미세먼지 양 = A(r,c) - (A(r,c)/5 x 방향개수)
	
	// 2. 공기 청정기 작동
		// 위쪽 - 반시계 / 아래쪽 - 시계방향
		// 바람 불면 바람의 방향대로 한칸씩 이동

public class 백준_17144_미세먼지안녕 {
	private static int R, C;
	private static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		int T = Integer.parseInt(st.nextToken()); // 시간
		map = new int[R][C]; // 미세먼지 양 저장할 배열
		int downRow = 0; // 아래 공기청정기 행
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					downRow = i; // 공기 청정기 아래 행 저장
				}
			}
		}
		// T초 동안 진행
		for (int i = 0; i < T; i++) {
			spread(); // 1. 미세먼지 확산
			clean(downRow - 1, downRow); // 2. 공기청정기 작동
		}
		int sum = 2; // 공기청정기 값 만큼 보정
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
	} // end of main

	public static void clean(int ur, int dr) {
//		위쪽 - 반시계 방향대로 한칸씩 이동
		for (int i = ur - 1; i >= 1; i--) {
			map[i][0] = map[i-1][0];
		}
		for (int i = 0; i < C-1; i++) {
			map[0][i] = map[0][i+1];
		}
		for (int i = 0; i <= ur - 1; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		for (int i = C-1; i > 1; i--) {
			map[ur][i] = map[ur][i-1];
		}
		map[ur][1] = 0;
		
//		아래쪽 - 시계 방향대로 한칸씩 이동
		for (int i = dr + 1; i < R-1; i++) {
			map[i][0] = map[i+1][0];
		}
		for (int i = 0; i < C-1; i++) {
			map[R-1][i] = map[R-1][i+1];
		} 
		for (int i = R-1; i >= dr+1; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		for(int i = C - 1; i >= 2; i-- ) {
			map[dr][i] = map[dr][i - 1];
		}
		map[dr][1] = 0;
	} // end of clean

	public static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	public static int[] dc = { 0, 0, -1, 1 };
	/** 특정 r,c에 있는 미세먼지가 상하좌우로 퍼지는 동작 수행 */
	public static void spread() {
		Queue<int[]> q = new LinkedList<>(); // [행, 열, 미세먼지 양]
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] > 0) { // 미세먼지가 있다면
					q.add(new int[]{i, j, map[i][j]}); // 큐에 저장  
				}
			}
		}
		while(!q.isEmpty()) {
			int[] dust = q.poll();
			int row = dust[0]; // 행
			int col = dust[1]; // 열
			int spread = dust[2] / 5; // 확산되는 양은 A(r,c)/5 			
			int sCnt = 0; // 몇 방향으로 퍼졌는지 체크
			for (int k = 0; k < dc.length; k++) {
				int nr = row + dr[k];
				int nc = col + dc[k];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C  // 배열 체크
						&& map[nr][nc] != -1) { // 공기청정기가 아니면
					map[nr][nc] += spread;
					sCnt++;
				}
			}
			map[row][col] -= (spread * sCnt); // A(r,c) - (A(r,c)/5 x 방향개수)
		}
	} // end of spread
} // end of class
