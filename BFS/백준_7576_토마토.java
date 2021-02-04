import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 
 * 백준 7576 토마토
 * BFS 이용
 */

public class 백준_7576_토마토 {
	private static int M;
	private static int N;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken()); // 열, 2 <= M, N <= 1000 
		N = Integer.parseInt(st.nextToken()); // 행
		map = new int[N][M];
//		정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸
		Queue<int[]> q = new LinkedList<int[]>(); // 전파할 토마토의 좌표를 저장할 큐 객체 (row, col, 경과날짜)
		int cntBaby = 0; // 익지 않은 토마토(0)의 개수
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1) { // 익은 토마토는 큐에 넣기
					q.offer(new int[] {row, col, 0}); // 행, 열, 경과날짜
				} else if (map[row][col] == 0) { // 익지 않은 토마토
					cntBaby++; // 개수 누적
				}
			}
		}
		int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
		int[] dc = { 0, 0, -1, 1 };
//		bfs 탐색
		int day = -1;
		while(!q.isEmpty()) { //		반복 큐가 빌때까지
			int[] val = q.poll(); // val = 큐에서 꺼내기
			int row = val[0]; // 행
			int col = val[1]; // 열
			day = val[2]; // 경과날짜
			for (int i = 0; i < dr.length; i++) { // val에 인접한 곳
				int nr = row + dr[i];
				int nc = col + dc[i];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) { // 익지 않은 토마토만
					map[nr][nc] = day + 1; // 기존 맵에 표시를 남김
					cntBaby--; // 익지 않은 토마토 줄이기
					q.offer(new int[] {nr, nc, day + 1}); //	큐에 넣기
				}
			}
		}
		System.out.println(cntBaby == 0 ? day : -1); //	map[][] 0이 들어있으면, -1, 날짜수
	} // end of main
} // end of class
