import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * 정올 1733. 오목
 * */
public class 정올_1733_오목 {

	private static int[][][] visited;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		map = new int[20][20]; // 19개 가로줄, 19개 세로줄
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < 20; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new int[4][20][20]; // 방향, 행, 열
		boolean flag = false;
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				if(map[j][i] == 0) continue;
				for (int k = 0; k < 4; k++) {
					if(visited[k][j][i] == 1) continue;
					if(dfs(j, i, k) == 5) {
						System.out.println(map[j][i]);
						System.out.println(j + " " + i);
						flag = true;
					}
				}
			}
		}
		if(!flag) System.out.println(0);
	} // end of main

	public static int dir[][] = new int[][] {{1, 0}, {0, 1}, {1, 1}, {-1, 1}}; // 상하좌우대각선
	public static int dfs(int r, int c, int k) { // dfs 탐색
		int cnt = 1;
		visited[k][r][c] = 1;
		int nr = r + dir[k][0];
		int nc = c + dir[k][1];
		if(map[nr][nc] == map[r][c]) {
			cnt += dfs(nr, nc, k);
		}
		return cnt;
	}
} // end of class
