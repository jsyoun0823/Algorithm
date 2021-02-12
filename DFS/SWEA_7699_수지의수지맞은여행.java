import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 섬의개수, 단지번호붙이기, 반전영역
// 1. 나가 떨어지는 건 다음 상태로 못감
// 2. 이미 한 번 밟아본 알파벳은 안감

public class SWEA_7699_수지의수지맞은여행 {

	private static char[][] map;
	private static boolean[] visited; // 알파벳 방문 배열
	private static int ans;
	private static int R, C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R =  Integer.parseInt(st.nextToken());
			C =  Integer.parseInt(st.nextToken());
			map = new char[R][];
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
			}
			visited = new boolean[26];
			visited[map[0][0] - 65] = true;
			ans = 0;
			dfs(0, 0, 1);
			sb.append('#').append(test_case).append(' ').append(ans).append('\n');
		} // end of for test_case
		System.out.println(sb);
	} // end of main

	public static int[] dr = {-1, 1, 0, 0};
	public static int[] dc = {0, 0, -1, 1};
	public static void dfs(int r, int c, int cnt) {
		ans = Math.max(ans, cnt);
		if(cnt == 26) return; // 가지치기! 시간이 10배 차이남
		
		// 전이될 수 있는 다음 상태는 현재위치로부터 4방
		for (int k = 0; k < dc.length; k++) {
			int nr = r+ dr[k];
			int nc = c+ dc[k];
			// 배열범위 체크, 방문 체크
			if(nr>=0 && nr<R && nc>=0 && nc<C && !visited[map[nr][nc] - 65]) {
				visited[map[nr][nc] - 65] = true;
				dfs(nr, nc, cnt + 1); // 다음 호출
				visited[map[nr][nc] - 65] = false;
			}
		}
	}
}
