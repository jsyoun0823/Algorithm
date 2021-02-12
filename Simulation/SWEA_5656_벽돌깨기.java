package 알고리즘수업;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 
 * j번째 구슬
 * 	모든 열에 반복 (w번 반복 => "선택지")
 * 한열 고정 -> 윗행 시작
 * 			모든 행 들여다보며 벽돌 찾기
 * 
 * 1. j-1 구슬까지의 상태로 초기화
 * 2. 벽돌 깨트리기 - 연대적으로 주변 벽돌 함께 깨뜨리기
 * 3. 빈공간 처리
 * 4. 다음 구슬 처리
 * 
 * *연쇄적 터뜨리기 (Boom) => BFS
 * 	시작벽돌 : 구슬에 맞은 벽돌
 * 	큐 준비
 * 	큐에 시작 벽돌 넣기
 * 	벽돌 깨뜨리기 처리 ( 비면 빈공간으로 바꿈 -> 방문처리 효과)
 * 		큐 크기만큼 반복
 * 			벽돌 크기가 1이면 다음으로
 * 			4방 탐색 반복
 * 				벽돌 크기 -1만큼 반복
 * 					경계 벗어나지 않고, 빈공간 X,
 * 						벽돌 큐에 넣고
 * 						벽돌 깨뜨리기 처리
 * 
 * *배열 내리기 (Collection 잘써라)
 * 	맨 아래부터 시작해서 
 * */
public class SWEA_5656_벽돌깨기 {

	static class Point {
		int r, c, cnt;
		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	private static int N, W, H, min;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			go(0, map);
			System.out.println("#" + t + " " + min);
		}
	}

	// i번째 구슬을 떨어뜨리기
	// count:던져진 구슬의 개수, map:이전 구슬까지의 2차원 배열
	public static void go(int count, int[][] map) {
		
		if(count == N) {
			// 남아있는 벽돌의 개수 구하여 최소값 갱신
			int result = getRemain(map);
			if(min > result) min = result;
			return;
		}
		
		
		// 모든 열에 떨어뜨리는 시도
		int[][] newMap = new int[H][W];
		for (int c = 0; c < W; c++) {
		
			int r = 0;
			while(r < H && map[r][c] == 0) ++r;
			
			if(r == H) { // 벽돌이 없음
				go(count+1, map);
			} else {
				// 이전 구슬 상태로 배열 복사하여 초기화
				copy(map, newMap);
				
				// 터뜨리기
				boom(newMap, r, c);
				
				// 벽돌 내리기
				down(newMap);
				
				// 다음 구슬 처리
				go(count+1, newMap);
			}
		}
	}

	public static void boom(int[][] map, int r, int c) {
		Queue<Point> q = new LinkedList<>();
		
		if(map[r][c] > 1) {
			q.offer(new Point(r, c, map[r][c]));
		}
		map[r][c] = 0; // 벽돌 제거 처리 (방문처리)
		
		while(!q.isEmpty()) {
			Point p = q.poll(); // 벽돌 꺼내기
			if(p.cnt == 1) continue;
			
			for (int d = 0; d < 4; d++) {
				int nr = p.r, nc = p.c;
				for (int k = 1; k < p.cnt; k++) {
					nr += dr[d];
					nc += dc[d];
					
					if(nr >=0 && nr<H && nc>=0 && nc<W && map[nr][nc] != 0) {
						if(map[nr][nc] > 1) q.offer(new Point(nr, nc, map[nr][nc]));
						map[nr][nc] = 0;
					}
				}
			}
		}
	}

	public static void down(int[][] map) {
		for (int c = 0; c < W; c++) { // 열 고정
			int r = H-1; // 제일 아래행 부터 시작
			while(r > 0) {
				if(map[r][c] == 0) {
					int nr = r-1; // 직전 행
					while(nr>0 && map[nr][c] == 0) --nr; // 처음 만나는 벽돌 찾기
					map[r][c] = map[nr][c];
					map[nr][c] = 0;
				}
				--r;
			}
		}
	}

	public static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

	public static int getRemain(int[][] map) {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] > 0) ++count;
			}
		}
		return count;
	}

}
