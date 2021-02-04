
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4613_러시아국기같은깃발 {

	private static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 
			int M = Integer.parseInt(st.nextToken()); // 
			
			arr = new int[N][3]; // 0:W, 1:B, 2:R 
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < s.length(); j++) {
					switch (s.charAt(j)) {
					case 'W':
						arr[i][0]++;
						break;
					case 'B':
						arr[i][1]++;
						break;
					case 'R':
						arr[i][2]++;
						break;
					}
				}
			}
			
			// W B R 순서로 칠하는 경우의 수, 조합, 2중 for문으로 구현, 각 경우의 최소값 갱신해서 출력, i,j 결정
//			1행 <= W <= i행
//			i행 <= B <= j행
//			j행 <= R < N행
			int minCnt = Integer.MAX_VALUE;
			for (int i = 1; i <= N - 2; i++) {
				int w = cntChar(0, i, 'W');
				for (int j = i + 1; j <= N - 1; j++) {
					int b = cntChar(i, j, 'B');
					int r = cntChar(j, N, 'R');
					int sum = w + b + r;
					if(minCnt > sum) minCnt = sum;
				}
			}
			sb.append('#').append(testCase).append(' ').append(minCnt).append('\n');
		} // end of for testCase
		System.out.println(sb);
	} // end of main

	/** s행 이상 e행 미만의 범위에서 c글자로 변경 시 바꿔야되는 글자의 개수 리턴 */
	public static int cntChar(int s, int e, char c) {
		int cnt = 0; // 바꿔야되는 글자의 개수
		for (int i = s; i < e; i++) { // 0:W, 1:B, 2:R 
			switch (c) {
			case 'W': cnt += arr[i][1] + arr[i][2]; break; // B, R 갯수 누적
			case 'B': cnt += arr[i][0] + arr[i][2]; break;
			case 'R': cnt += arr[i][0] + arr[i][1]; break;
			}
		}
		return cnt;
	}
} // end of class
