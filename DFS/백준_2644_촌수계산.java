import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2644_촌수계산 {

	private static boolean[][] map;
	private static boolean[] visited;
	private static int n, e, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new boolean[n+1][n+1];
		visited = new boolean[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map[from][to] = map[to][from] = true;
		}
		
		ans = -1;
		dfs(s, 0);
		System.out.println(ans);
	}
	
	public static void dfs(int cur, int cnt) {
		visited[cur] = true;
		
		if(cur == e) ans = cnt;

		for (int i = 1; i <= n; i++) {
			if(map[cur][i] && !visited[i]) {
				dfs(i, cnt + 1);
			}
		}
	}

}
