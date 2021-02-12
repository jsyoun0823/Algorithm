import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14500_테트로미노 {
	
	private static int maxSum = 0;
	private static int N, M;
	private static int[][] map;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로 크기, 4 <= N, M <= 500
		M = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " "); // 폭이 일정하지 않아서 쪼개야함
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 1<= 값 <= 1000
			}
		}

		// 전체 맵의 각 정점에서 출발하는 DFS를 돌려보자
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true; // 방문 체크
				dfs(i, j, 0, 0); // 행, 열, 단계, 지금까지의 숫자 합
				visited[i][j] = false; // 방문 체크 원복
				findㅗ(i, j);
			}
		}
		System.out.println(maxSum);
	} // end of main

	/** dfs로 탐색할 수 없는 도형, ㅏㅓㅗㅜ 도형 체크 */
	private static void findㅗ(int r, int c) {
		if(r+1 < N && c+2 < M) { // ㅜ
			int sum = map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+1];
			if(maxSum < sum) maxSum = sum;
		}
		
		if(r+2 < N && c+1 < M) { // ㅏ
			int sum = map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c+1];
			if(maxSum < sum) maxSum = sum;
		}

		if(r-1 >= 0 && c+2 < M) { // ㅗ
			int sum = map[r][c] + map[r][c+1] + map[r][c+2] + map[r-1][c+1];
			if(maxSum < sum) maxSum = sum;
		}

		if(0 <= r-1 && r+1 < N && c+1 < M) { // ㅓ
			int sum = map[r][c] + map[r][c+1] + map[r-1][c+1] + map[r+1][c+1];
			if(maxSum < sum) maxSum = sum;
		}
	} // end of findㅗ

	private static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	private static int[] dc = {0, 0, -1, 1};
	
	/** r,c 위치에서 깊이가 4인 테트로미노 블럭을 탐색하는 dfs, step:현재 단계, sum:지금까지 숫자 합 */
	public static void dfs(int r, int c, int step, int sum) {
		if(step == 4) { // 종료파트
			if(maxSum < sum) maxSum = sum; // 최대값 갱신
			return;
		} 

		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr>=0 && nr<N && nc>=0 && nc<M 
				&& !visited[nr][nc]) { // 배열의 범위 벗어났는지 체크, 미방문 체크
				visited[nr][nc] = true;
				dfs(nr, nc, step + 1, sum + map[r][c]);
				visited[nr][nc] = false;
			}
		}
	} // end of dfs
} // end of class
