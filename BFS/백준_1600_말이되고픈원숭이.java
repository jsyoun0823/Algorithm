import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1600_말이되고픈원숭이 {

	private static int K, W, H;
	private static int[][] map;
	public static boolean[][][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		W = Integer.parseInt(st.nextToken()); // 열
		H = Integer.parseInt(st.nextToken()); // 행
		map = new int[H][W]; 
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[K+2][H][W]; // 방문 배열 [k][r][c]
		System.out.println(bfs(0, 0));
	}
	public static int dr[] = {-1, 1, 0, 0}; // 상하좌우
	public static int dc[] = {0, 0, -1, 1};
	public static int hr[] = {-2,-2,-1, 1, 2, 2, 1,-1};
	public static int hc[] = {-1, 1, 2, 2, 1,-1,-2,-2};

	public static int bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<>(); // 큐 준비
		visited[0][row][col] = true;
		q.offer(new int[] {row, col, 0, 0}); // r, c, K번 횟수, cnt(지금까지 이동 동작한 수)
		
		while(!q.isEmpty()) {
			int[] now = q.poll(); // 큐에서 꺼내기
			int r = now[0];
			int c = now[1];
			int k = now[2];
			int cnt = now[3];
			if(r == H-1 && c == W-1) { // 도착했으면
				return cnt;
			}
			
			if(k < K) {
				for (int i = 0; i < hc.length; i++) {
					int nr = r + hr[i];
					int nc = c + hc[i];
					if(nr>=0 && nr<H && nc>=0 && nc<W
							&& !visited[k+1][nr][nc]) { // 배열 범위 내, 미방문이면
						if (map[nr][nc] == 0) { // 길이면
							visited[k+1][nr][nc] = true; // 방문표시
							q.offer(new int[] {nr, nc, k + 1, cnt + 1});
						}
					}
				}
			}
			// k번 횟수 다썼으니 상하좌우 인접칸으로만 이동 가능
			for (int i = 0; i < dc.length; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr>=0 && nr<H && nc>=0 && nc<W
						&& !visited[k][nr][nc]) { // 배열 범위 내, 미방문이면
					if (map[nr][nc] == 0) { // 길이면
						visited[k][nr][nc] = true; // 방문표시
						q.offer(new int[] {nr, nc, k, cnt + 1});
					}
				}
			}
		}
		return -1;
	}
}