
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_17070_파이프옮기기1 {
	private static int N, cnt;
	private static boolean[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 3~16
		map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0, index = 0; j < N; j++, index += 2) {
				map[i][j] = s.charAt(index) == '0'? true : false; // 0:빈칸 true, 1:벽 false
 			}
		}
//		문제의 시작 위치 (1,1) (1,2) => 구현 (0,0) (0,1)
		dfs(0, 1, 0); // 파이프 진행방향의 머리칸의 좌표 r,c 방향 dir 0:가로, 1:세로, 2:대각
		System.out.println(cnt);
	} // end of main
	
	/** 파이프 진행방향의 머리칸의 좌표 r,c 방향 dir 0:가로, 1:세로, 2:대각 */
	public static void dfs(int r, int c, int dir) {
		if (r == N-1 && c == N-1) { // 도착
			cnt++;	// 방법의 수 누적
			return;
		}
		
		if(dir == 0 || dir == 2) { // 가로 : 현재 진입방향이 가로 or 대각
			if(c+1 < N && map[r][c+1]) { // 배열 범위 내인지 체크, 빈 칸인지 체크
				dfs(r, c+1, 0); // 가로로 진행
			}
		}
		
		if(dir == 1 || dir == 2) { // 세로 : 현재 진입방향이 세로 or 대각
			if(r+1 < N && map[r+1][c]) { // 배열 범위 내인지 체크, 빈 칸인지 체크
				dfs(r+1, c, 1); // 세로로 진행
			}
		}
		
		// 대각 : 방향은 상관 없음
		if(c+1 < N && r+1 < N && map[r+1][c] && map[r+1][c+1]) // 배열 범위 내인지 체크, 빈 칸인지 체크
			dfs(r + 1, c + 1, 2); // 대각으로 진행
	}
} // end of class
