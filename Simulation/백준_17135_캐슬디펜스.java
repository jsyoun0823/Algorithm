import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/** 
 * 시뮬레이션
 * 조합 M개중 3개 고르기 (궁수의 위치)
 * 배열의 복사
 * 최대값 (제거한 적)
 * 중복의 제거 자료구조 Set
 * 블럭이동 (배열행 이동)
 * */

public class 백준_17135_캐슬디펜스 {

	private static int N, M, D;
	private static int[][] map;
	private static int sum;
	private static int[] loc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 격자판 행의 수 N,  (3<= N, M <= 15)
		M = Integer.parseInt(st.nextToken()); // 격자판 열의 수 M
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한, (1<= D <= 10)
		int[][] mapOrg = new int[N][M]; // 원본
		map = new int[N][M]; // 복제
		sum = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0, index = 0; j < M; j++, index += 2) {
				if(s.charAt(index) == '1') {
					sum++;
					mapOrg[i][j] = 1;
				}
			}
		}
		int maxCnt = 0; // 최대값
		// 궁수 3명의 위치 조합
		loc = new int[3];
		for (loc[0] = 0; loc[0] < M - 2; loc[0]++) {
			for (loc[1] = loc[0] + 1; loc[1] < M - 1; loc[1]++) {
				for (loc[2] = loc[1] + 1; loc[2] < M; loc[2]++) {
					// 원본을 복사한 배열을 준비
					for (int i = 0; i < N; i++) {
						System.arraycopy(mapOrg, 0, map, 0, M);
					}
					int cnt = shot(); // 반복(적이 있는동안) {궁수 쏘기, 한줄씩 내리기}
					if (maxCnt < cnt) maxCnt = cnt;
				}
			}
		}
		System.out.println(maxCnt);
	} // end of main
	public static int[][] dr = {{}, // 0
					            {-1}, // 1
					            {-1,-2,-1}, // 2
					            {-1,-2,-3,-2,-1}, // 3
					            {-1,-2,-3,-4,-3,-2,-1}, // 4
					            {-1,-2,-3,-4,-5,-4,-3,-2,-1}, // 5
					            {-1,-2,-3,-4,-5,-6,-5,-4,-3,-2,-1}, // 6
					            {-1,-2,-3,-4,-5,-6,-7,-6,-5,-4,-3,-2,-1}, // 7
					            {-1,-2,-3,-4,-5,-6,-7,-8,-7,-6,-5,-4,-3,-2,-1}, // 8
					            {-1,-2,-3,-4,-5,-6,-7,-8,-9,-8,-7,-6,-5,-4,-3,-2,-1}, // 9
					            {-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,-9,-8,-7,-6,-5,-4,-3,-2,-1}, // 10
								};
	public static int[][] dc = {{}, // 0
					            {0}, // 1
					            {-1, 0, 1}, // 2
					            {-2,-1, 0, 1, 2}, // 3
					            {-3,-2,-1, 0, 1, 2, 3}, // 4
					            {-4,-3,-2,-1, 0, 1, 2, 3, 4}, // 5
					            {-5,-4,-3,-2,-1, 0, 1, 2, 3, 4, 5}, // 6
					            {-6,-5,-4,-3,-2,-1, 0, 1, 2, 3, 4, 5, 6}, // 7
					            {-7,-6,-5,-4,-3,-2,-1, 0, 1, 2, 3, 4, 5, 6, 7}, // 8
					            {-8,-7,-6,-5,-4,-3,-2,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8}, // 9
					            {-9,-8,-7,-6,-5,-4,-3,-2,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, // 10
								};		
	public static HashSet<Integer> hs = new HashSet<Integer>();
	/** 궁수쏘기, 한줄씩 내리기, 죽은 인원의 수를 리턴 */
	public static int shot() {
		int cnt = 0; // 제거한 적의 수를 누적
		int total = sum; // 맵에 남아있는 적군 수
		for (int i = 0; total > 0 && i < N; i++) { // N행의 수만큼 && 적군이 있을 때까지
			hs.clear(); // 초기화
			for (int j = 0; j < loc.length; j++) { // 궁수 한명씩 쏘기
				int r = N, c = loc[j];
ex:				for (int k = 1; k <= D; k++) { // 공격거리
					for (int n = 0; n < dc[k].length; n++) {
						int nr = r + dr[k][n];
						int nc = c + dc[k][n];
						if(0 <= nr && 0 <= nc && nc < M && map[nr][nc] == 1) { // 배열 범위 내
							hs.add(nr * M + nc); // 행 열
							break ex;
						}
					}
				} // end of for 공격거리
			} // end of for 궁수
			cnt += hs.size();
			total -= hs.size(); // 잡은 적군 수 만큼 맵에서 제거
			for (Integer x : hs) {
				map[x / M][x % M] = 0; // 행 열
			}
			total -= move();
		} // end of for 한줄씩
		
		return cnt;
	}
	/** 맵을 한줄 씩 내리기, 맵 밖으로 떨어진 적군의 수를 리턴 */
	public static int move() {
		int[] temp = map[map.length-1];
		for (int i = map.length-1; i > 0; i--) {
			map[i] = map[i-1];
		}
		map[0] = temp;
		int num = 0; // 맵에서 벗어난 적의 수
		for (int i = 0; i < M; i++) {
			if(temp[i] == 1) {
				num++;
				temp[i] = 0; // 초기화
			}
		}
		return 0;
	}
} // end of class
