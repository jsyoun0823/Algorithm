import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4613_러시아국기같은깃발_DP {
	
	private static int[][] arr, memo;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 행 
			int M = Integer.parseInt(st.nextToken()); // 열 3~50
			
			arr = new int[N][3]; // 0:W, 1:B, 2:R 
			memo = new int[N][3]; // 0:W, 1:B, 2:R 
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < s.length(); j++) {
					switch (s.charAt(j)) {
					case 'W': arr[i][0]++; break;
					case 'B': arr[i][1]++; break;
					case 'R': arr[i][2]++; break;
					}
				}
			}
			
			memo[0][0] = arr[0][1] + arr[0][2]; // 첫번째 라인을 W로 칠하는 최소 변경의 개수
			memo[0][1] = 987654321; // 첫번째 라인은 B가 올수 없으므로, 아주 큰값으로 초기화
			memo[0][2] = 987654321; // 첫번째 라인은 R이 올수 없으므로, 아주 큰값으로 초기화
			for (int i = 1; i < N; i++) {
				memo[i][0] = memo[i-1][0] + arr[i][1] + arr[i][2];
				memo[i][1] = Math.min(memo[i-1][0], memo[i-1][1]) + arr[i][0] + arr[i][2];
				memo[i][2] = Math.min(memo[i-1][1], memo[i-1][2]) + arr[i][0] + arr[i][1];
			}
			sb.append('#').append(testCase).append(' ').append(memo[N-1][2]).append('\n');
		} // end of for testCase
		System.out.println(sb);
	} // end of main
} // end of class
