
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * SWEA 7793. 오! 나의 여신님
 * BFS 퍼지는 느낌, 레벨 별로
 *  
 * */
public class SWEA_7793_오나의여신님 {
	// 악마, 수연의 위치 좌표 (상태 정보) 표현할 객체
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	// 상하좌우를 표현할 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int N, M; // 2 <= N, M <= 50
	static Queue<Point> devils;
	static Queue<Point> suyeon;
	static char[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N][M];
			devils = new LinkedList<>();
			suyeon = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				String str = sc.next();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '*') 
						devils.add(new Point(i, j));
					if(map[i][j] == 'S')
						suyeon.add(new Point(i, j));
				}
			}
			
			int cnt = 1;
//			수연이 반복문안에서, 지은이를 만나면 break 걸어야 하므로 Label 생성
out:			while(true) { // 무한 반복하면서
					int size = suyeon.size();
					if(size == 0) {
						cnt = 0;
						break;
					}
					
					// 수연이 큐가 사이즈가 0이면 게임오버이므로 반복 종료
					// 수연이의 갯수만큼 반복하면서 수연이를 꺼내서 
					// 		수연이가 지은이에게 도착하면 횟수 기억 후 종료
					//		아니라면, 각 수연이에 대해서 4방으로 갈 수 있는 곳이면 큐에 추가 .인 곳
					for (int i = 0; i < size; i++) {
						Point p = suyeon.poll();
						if(map[p.r][p.c] == '*') // 수연이가 퍼진 후 악마가 퍼지고 났더니 수연이가 죽음, 큐에서 나오자마자 죽은 것! 
							continue;
						
						for (int d = 0; d < 4; d++) {
							int nr = p.r + dr[d];
							int nc = p.c + dc[d];
							// 밖으로 나가면 아웃
							if(nr<0 || nc<0 || nr>=N || nc>=M)
								continue;
							// 지은이를 만나면 완전 종료
							if(map[nr][nc] == 'D')
								break out;
							// 평지가 아니면 못감
							if(map[nr][nc] != '.')
								continue;
							map[nr][nc] = 'S';
							suyeon.add(new Point(nr, nc));
						}
					}
					
					// 악마 큐의 사이즈만큼 반복하면서 
					//		악마도 퍼져나감 (. 이거나 수연이)
					size = devils.size();
					for (int i = 0; i < size; i++) {
						Point p = devils.poll();
						for (int d = 0; d < 4; d++) {
							int nr = p.r + dr[d];
							int nc = p.c + dc[d];
							
							if(nr<0 || nc<0 || nr>=N || nc>=M)
								continue;
							
							if(map[nr][nc] == 'S' || map[nr][nc] == '.') {
								map[nr][nc] = '*';
								devils.add(new Point(nr, nc));
							}
						}
					}
					// 1초 증가
					cnt++;
			}
			if(cnt == 0)
				System.out.println("#" + tc + " " + "GAME OVER");
			else
				System.out.println("#" + tc + " " + cnt);
		} // end of for test case
	} // end of main
} // end of class
 


