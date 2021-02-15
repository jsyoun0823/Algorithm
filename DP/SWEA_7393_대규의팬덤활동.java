
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DP
 * 길이가 N인 앞뒤가 1크거나 작은 (0~9 숫자가 모두 등장하는) 삐끗수(계단수) 의 개수 출력
 * 
 * 89876543210
 *  
 *  */
public class SWEA_7393_대규의팬덤활동 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int MOD = 1_000_000_000;
		final int LIMIT = 1 << 10;
		
		int[][][] memo = new int[101][10][LIMIT]; // [자리수][마지막숫자][지금까지사용한 숫자 bitmask]
		// N i
		for (int i = 1; i < 10; i++) { // 길이가 1인 수의 개수, 0으로 시작하는 숫자는 불가능하므로 0개
			memo[1][i][1 << i] = 1; // 1자리 숫자 1~9 각 1개
		}
		
		for (int i = 2; i <= 100; i++) { // 길이가 2자리~N자리까지를 작성
			for (int j = 0; j < 10; j++) { // 마지막 숫자
				for (int k = 0; k < LIMIT; k++) { // 비트마스크한 값
					int temp = k | (1 << j);
					switch (j) {
					case 0:
						memo[i][j][temp] = (memo[i][j][temp] + memo[i - 1][j + 1][k]) % MOD;
						break;
					case 9:
						memo[i][j][temp] = (memo[i][j][temp] + memo[i - 1][j - 1][k]) % MOD ;
						break;
					default:
						memo[i][j][temp] = ((memo[i][j][temp] + memo[i - 1][j - 1][k]) % MOD + memo[i - 1][j + 1][k]) % MOD;
						break;
					}
				}
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 수의 길이 N (1<=N<=100)
			
//			N자리, 마지막 자리로 0~9, 1111111111 로 채워진거 
//								1<<10 - 1
			int cnt = 0;
			for (int i = 0; i < 10; i++) {
				cnt = (cnt + memo[N][i][LIMIT-1]) % MOD;
			}
			sb.append('#').append(testCase).append(' ').append(cnt).append('\n');
		} // end of for testCase
		System.out.println(sb);
	} // end of main
} // end of class
