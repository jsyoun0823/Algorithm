import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * SWEA 3308. 최장 증가 부분 수열 [D3]
 * DP - LiS
 * */
public class SWEA_3307_최장증가부분수열 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			int N = Integer.parseInt(br.readLine()); // 수열의 길이 N(1≤N≤1,000)
			int[] arr = new int[N];
			int[] lis = new int[N]; // 자신을 끝으로 하는 LIS 최장길이
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				lis[i] = 1; // 자신만으로 LIS를 구성한 경우
				
				for (int j = 0; j < i; j++) {
					if(arr[j] < arr[i] && lis[i] < lis[j] + 1) {
						lis[i] = lis[j] + 1;
					}
				}
				
				max = Math.max(max, lis[i]); // 최대값 갱신
			}
			
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		} // end of for test case
		System.out.println(sb);
	} // end of main
} // end of class