
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17069_파이프옮기기2 {
	private static int N;
	private static boolean[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 집의 크기 N(3 ≤ N ≤ 32)
		map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0, index = 0; j < N; j++, index += 2) {
				map[i][j] = s.charAt(index) == '0'? true : false; // 0:빈칸 true, 1:벽 false
 			}
		}

		long[][][] memo = new long[N][N][3]; // 파이프 진행방향의 머리칸의 좌표 r,c 방향 k 0:가로, 1:세로, 2:대각
		memo[0][1][0] = 1; // 시작 위치

		for (int i = 0; i < N; i++) { // 행
			for (int j = 1; j < N; j++) { // 열
				if(map[i][j]) { // 빈칸 일때만
					for (int k = 0; k < 3; k++) { // 방향
						switch (k) {
						case 0: // 가로로 진행
							memo[i][j][0] += memo[i][j-1][0] + memo[i][j-1][2];
							break;
						case 1: // 세로로 진행
							if(i >= 1) memo[i][j][1] += memo[i-1][j][1] + memo[i-1][j][2];
							break;
						case 2: // 대각으로 진행
							if(i >= 1 && map[i-1][j] && map[i][j-1]) 
								memo[i][j][2] += memo[i-1][j-1][0] + memo[i-1][j-1][1] + memo[i-1][j-1][2];
							break;
						}
					}
				}
			}
		}
		System.out.println(memo[N-1][N-1][0] + memo[N-1][N-1][1] + memo[N-1][N-1][2]);
	} // end of main
} // end of class
