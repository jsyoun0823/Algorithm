import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1194_달이차오른다가자 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 세로 크기 (행), (1 ≤ N, M ≤ 50)
		int M = Integer.parseInt(st.nextToken()); // 가로 크기 (열)
		char[][] map = new char[N][M];
		boolean[][][] visited = new boolean[N][M][1 << 7];
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					q.offer(new int[] { i, j, 0, 0 });
					visited[i][j][0] = true;
					map[i][j] = '.';
				}
			}
		}
		int minCnt = Integer.MAX_VALUE;
		boolean flag = false;
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int key = cur[2];
			int cnt = cur[3];

			if (map[r][c] == '1') {
				if(minCnt > cnt) 
					minCnt = cnt;
				flag = true;
				break; // 출구에 도착
			}

			for (int i = 0; i < dc.length; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				int nk = key;

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc][key] || map[nr][nc] == '#')
					continue;

				visited[nr][nc][key] = true;
				if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') { // 열쇠인 경우
					nk = nk | (1 << (map[nr][nc] - 'a'));
					q.offer(new int[] { nr, nc, nk, cnt + 1 });
				} else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') { // 문인 경우
					if ((key & 1 << (map[nr][nc] - 'A')) > 0) {
						q.offer(new int[] { nr, nc, nk, cnt + 1 });
					}
				} else { // 길인 경우
					q.offer(new int[] { nr, nc, nk, cnt + 1 });
				}
			}
		}
		if(flag) System.out.println(minCnt);
		else System.out.println(-1);
	}

}
