import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS 문제
public class 백준_16234_인구이동 {

	private static int N, L, R;
	private static int[][] m;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // NxN 땅
		L = Integer.parseInt(st.nextToken()); // 인구 차이 수 L 명이상
		R = Integer.parseInt(st.nextToken()); // 인구 차이 수 R명 이하
		m = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0; // 인구 이동이 몇차례 있었는지 카운팅할 변수
		// 연합 국가가 여러 그룹이 만들어질 수 있음
		while (true) { // 반복 변화가 없을 때까지
			boolean change = false; // 인구이동이 있었는지 체크할 플래그 변수
			visited = new boolean[N][N]; // 방문여부 체크
			// 모든 정점을 돌면서 인접한, L 이상 R 이하인 정점들에 대해 BFS 탐색
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m.length; j++) {
					if (!visited[i][j] && search(i, j)) { // 방문하지 않았으면 탐색
						change = true; // 인구 이동이 있었음
					}
				}
			}
			if (!change) {// 인구이동이 없는 경우는 반복 탈출
				break;
			}
			cnt++; // 인구이동이 있었으면 회수를 누적
		} // end of while
		System.out.println(cnt);
	} // end of main

	public static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	public static int[] dc = { 0, 0, -1, 1 };

	/**
	 * m[i][j] 국가의 인접한 국가와 인구차이가 범위내이면 인구이동후 평균값으로 저장 return 
	 * 인구이동이 있었으면 true, 없었으면 false
	 */
	public static boolean search(int i, int j) { // BFS 탐색
		ArrayList<Loc> al = new ArrayList<>(); // 연합국가의 좌표를 저장할 리스트
		Queue<Loc> q = new LinkedList<Loc>();
//		Point p = new Point(); // 객체가 위로 많음 생성하는 메모리, 시간 낭비 -> Loc 하나 만들기
		Loc l = new Loc(i, j); // 시작 정점
		al.add(l); // 연합 국가 좌표 저장
		q.offer(l);
		visited[i][j] = true;

		while (!q.isEmpty()) { // 큐가 빌때까지
			l = q.poll(); // 큐에서 꺼내기
			int r = l.r;
			int c = l.c;
			// 인접칸, 배열범위, 방문하지 않은, L~R 사이일 경우
			for (int k = 0; k < dc.length; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N // 배열 범위내
						&& !visited[nr][nc] // 방문하지 않은
						&& diff(m[nr][nc], m[r][c])) { // 차이가 L~R사이인지
					Loc nl = new Loc(nr, nc);
					al.add(nl);
					q.offer(nl); // 큐에 추가
					visited[nr][nc] = true; // 방문 체크
				}
			}
		} // end of while
		if(al.size() >= 2) { // 연합국가가 있어서 인구이동이 되면
			int total = 0;
			for (Loc loc : al) {
				total += m[loc.r][loc.c];
			}
			int avg = total / al.size(); // 평균
			for (Loc loc : al) {
				m[loc.r][loc.c] = avg;
			}
			return true;
		} else { // 연합불가
			return false;
		}
	}

	/** 두 값의 차이의 절대값이 L <= <= R 범위내에 있는지 여부를 리턴 */
	public static boolean diff(int a, int b) {
		int val = a >= b ? a - b : b - a;
		return L <= val && val <= R;
	}

	/** 행, 열 좌표를 저장할 객체 */
	static class Loc {
		int r, c;
		public Loc() {
		}
		public Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public String toString() {
			return "(" + r + "," + c + ")";
		}
	}
} // end of class
