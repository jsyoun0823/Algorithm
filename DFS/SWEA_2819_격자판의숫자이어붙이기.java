import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_2819_격자판의숫자이어붙이기 {
	private static int[][] map;
	private static HashSet<Integer> result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		result = new HashSet();
		StringTokenizer st;
		for (int test_case = 1; test_case <= T; test_case++) {
			result.clear();
			map = new int[4][4]; // 4 * 4 격자판
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); // 0 ~ 9 사이의 숫자 저장
				}
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					comb(1, i, j, map[i][j]);
				}
			}
			System.out.println("#" + test_case + " " + result.size());
			
		} // end of for test case 
	} // end of main
	
	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = {0, 0, -1, 1};
	// 임의의 위치에서 시작, 인접한 격자로 6번 이동하며 각 칸에 있는 숫자 차례대로 이어 붙임(7글자)
	// 한번 거쳤던 다시 거쳐도 됨, 0으로 시작 가능
	public static void comb(int cnt, int row, int col, int n) {
		if(cnt == 7) {
			result.add(n);
			return;
		}
		for (int j = 0; j < dc.length; j++) {
			int nr = row + dr[j];
			int nc = col + dc[j]; 
			if(nr>=0 && nr<4 && nc>=0 && nc<4) { // 배열 범위 내
				comb(cnt + 1, nr, nc, (n*10) + map[nr][nc]);
			}
		}
	} // end of comb
} // end of class

