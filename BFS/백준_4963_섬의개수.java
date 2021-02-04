import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 백준 4963 섬의개수 */

// visited 배열 굳이 만들지 않아도 방문한 부분을 1->0 로 바꿔주면됨
// 최적화~~~~~~!

public class 백준_4963_섬의개수 {
	private static int W, H;
	private static int[][] m = new int[50][50];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			W = Integer.parseInt(st.nextToken()); // 너비 (열) <= 50
			H = Integer.parseInt(st.nextToken()); // 높이 (행) <= 50
			if(H == 0 && W == 0) break;
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0, index = 0; j < W; j++, index+=2) {
					m[i][j] = s.charAt(index); // 1은 땅, 0은 바다
				}
			}
			int cnt = 0; // 섬 개수 카운팅
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (m[i][j] == '1') { // 0:바다 1:섬, 섬일 때만 체크
						search(i, j);
						cnt++;
					}
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	} // end of main

	public static int[] dr = {-1,-1,-1, 0, 1, 1, 1, 0};   // 반시계방향으로 가로세로대각선
	public static int[] dc = {-1, 0, 1, 1, 1, 0,-1,-1};
	public static int[] qr = new int[2500];
	public static int[] qc = new int[2500];
	private static void search(int r, int c) { // bfs 탐색
		int top = -1;
		// 큐 생성
		qr[++top] = r;
		qc[top] = c;
		m[r][c] = 0; // 땅을 바다로 변경, 방문 체크
		while(top > -1) {
			r = qr[top];
			c = qc[top--];
			for (int k = 0; k < dc.length; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && m[nr][nc] == '1') {
					qr[++top] = nr;
					qc[top] = nc;
					m[nr][nc] = 0;
				}
			}
		}
	}
} // end of class
