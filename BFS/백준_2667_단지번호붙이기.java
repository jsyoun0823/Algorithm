import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_2667_단지번호붙이기 {

	static int N;
	static char[][] map;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 지도의 크기, 5≤N≤25
		map = new char[N][N]; // 정사각형 모양의 지도
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray(); // 1은 집이 있는 곳, 0은 집이 없는 곳
		}
		
		int cnt = 0;
		ArrayList<Integer> sumApart = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] == '1') {
					sumApart.add(bfs(i, j));
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		Collections.sort(sumApart);
		for (int sum : sumApart) {
			System.out.println(sum);
		}
	}
	
	public static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	public static int[] dc = {0, 0, -1, 1};
	public static int bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<>();
		int cnt = 1;
		q.offer(new int[] {row, col});
		visited[row][col] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int j = 0; j < dc.length; j++) {
				int nr = cur[0] + dr[j];
				int nc = cur[1] + dc[j];
				
				if(nr>=0 && nr<N && nc>=0 && nc<N
						&& map[nr][nc] == '1' 
						&& !visited[nr][nc]) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}

}
