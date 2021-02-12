

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** 
 * 여행자 경로문제
 * 
 * 1247. [S/W 문제해결 응용] 3일차 - 최적 경로
 * 회사 - N명의 고객 - 집 의 순열을 구해서 최단 거리를 찾는다
 * 매 단계마다 거리를 누적하고, 재귀함수의 매개변수로 넘겨준다
 * 전체 최단거리의 최소값보다 현재단계까지의 거리가 이미 크거나 같다면 가지치기
 * 
 * */
public class SWEA_1247_최적경로{
	private static int N;
	private static Loc[] customers;
	private static boolean[] visited;
	private static int hr, hc;
	private static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine()); // 고객의 수 (2 ≤ N ≤ 10)
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int cor = Integer.parseInt(st.nextToken()); // 회사 좌표 x ,  (0 ≤ x ≤ 100, 0 ≤ y ≤ 100)
			int coc = Integer.parseInt(st.nextToken()); // 회사 좌표 y
			hr = Integer.parseInt(st.nextToken()); // 집 좌표 x
			hc = Integer.parseInt(st.nextToken()); // 집 좌표 y
			customers = new Loc[N];
			for (int i = 0; i < N; i++) { // N명의 고객 좌표 x,y 객체
				customers[i] = new Loc(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			visited = new boolean[N]; // 방문 체크 배열
			min = Integer.MAX_VALUE; // 최소값 초기화
			dfs(cor, coc, 0, 0); // 회사에서부터 시작
			sb.append("#").append(test_case).append(" ").append(min).append("\n");
		} // end of for test case
		System.out.println(sb);
	} // end of main

	/** 현재 정점에서 각 고객집 까지 최단 경로 찾기 */
	public static void dfs(int x, int y, int cnt, int dis) {
		if(min <= dis) return; // 가지치기
		
		if (cnt == N) { // 모든 경로를 탐색했으면
			dis += dist(x, hr, y, hc); // 집까지의 거리 계산해서 더해주기
			min = (min < dis)? min : dis; // 최소값 갱신
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue; // 탐색했으면 다음으로 넘어가기
			
			visited[i] = true; // 방문 체크
			int d = dis + dist(x, customers[i].x, y, customers[i].y); // 거리 계산해서 더해주기
			dfs(customers[i].x, customers[i].y, cnt+1, d); // 다음으로 넘어가기
			visited[i] = false; // 돌아오면 방문 체크 해제 
		}
	} // end of dfs
	
	/** 거리 계산 |x1-x2| + |y1-y2|*/
	public static int dist(int x1, int x2, int y1, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2) ;
	}

	/** (x,y) 위치를 나타내는 객체 */
	static class Loc{
		int x;
		int y;
		public Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	} // end of class Loc
} // end of class
