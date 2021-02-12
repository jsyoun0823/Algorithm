import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dfs
 * 370 ms 메모이제이션을 안 했을 때
 * 150 ms 메모이제이션을 했을 때
 */

public class SWEA_1861_정사각형방 {

	private static int[][] A;
	private static int N;
	private static int[][] memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int Tc = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= Tc; test_case++) {
			N = Integer.parseInt(br.readLine()); // N (1 <= N <= 10^3), N*N < 10^6
			A = new int[N][N];
			memo = new int[N][N];	// 갈 수 있는 칸수를 저장하기

			StringTokenizer st;
			for (int i = 0; i < A.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < A.length; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = 0;
			int num = Integer.MAX_VALUE; // 최대값이 들어있는 방의 숫자
//			모든 방에서 갈 수 있는 길을 출발해보자
			for (int row = 0; row < A.length; row++) {
				for (int col = 0; col < A.length; col++) {
					int val = dfs(row, col);
					// 이동할 수 있는 방의 개수의 최대값 구하기 : 큰 값이면 업데이트, 같은 값이면
					if (max < val || (max == val && num > A[row][col])) {
						max = val;
						num = A[row][col];
					}
				}
			}
			sb.append('#').append(test_case).append(' ').append(num).append(' ').append(max).append('\n');
		} // end of for testCase
		System.out.print(sb);
	} // end of main

	public static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	public static int[] dc = { 0, 0, -1, 1 };

	/** A[row][col] 에서 출발할 때 최대 이동할 수 있는 방의 개수 */
	public static int dfs(int row, int col) {
		if(memo[row][col]!=0) { // 연산을 이미 한 경우 리턴, 메모이제이션 - 중복호출을 제거
			return memo[row][col];
		}
		
		int result = 1; // 이동할 수 있는 칸 수, 아무 곳도 갈 수 없다면 내방 한 칸 갈 수 있으니까 초기값 1
		int num = A[row][col]; // 현재 방의 숫자
		for (int i = 0; i < dr.length; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N && num + 1 == A[nr][nc]) { // 배열범위 내 && 나보다 1큰수가 있으면
				result += dfs(nr, nc); // 고고싱
				break;
			}
		}
		return memo[row][col] = result;	// 대입하고 리턴
	}
} // end of class