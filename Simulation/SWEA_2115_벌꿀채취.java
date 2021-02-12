import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취 {

	static int N, M, C, ans;
	static int[][] map, maxMap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력처리
			sb.append('#').append(t).append(' ').append(getMaxBenefit()).append('\n');
		} // end of for test case
		System.out.println(sb);
	} // end of main
	
	private static int getMaxBenefit() {
		makeMaxMap();
		return processCombination();
	}

	private static void makeMaxMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				makeMaxSubset(i, j, 0, 0, 0);
			}
		}
	}

	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {
		if(sum > C) return;
		
		if(cnt == M) {
			if(maxMap[i][j-M] < powSum) maxMap[i][j-M] = powSum;
			return;
		}
		
		// 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j] * map[i][j]);
		
		// 비선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powSum);
	}
	
	private static int processCombination() {
		
		int max = 0, aBenefit = 0, bBenefit = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) { // 일꾼 A 선택
				aBenefit = maxMap[i][j];
				
				// 일꾼 B 선택
				// 같은 행
				bBenefit = 0;
				for (int j2 = j+M; j2 <= N-M; j2++) {
					if(bBenefit < maxMap[i][j2]) bBenefit = maxMap[i][j2];
				}
				
				// 다른 행
				for (int i2 = i+1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N-M; j2++) {
						if(bBenefit < maxMap[i2][j2]) bBenefit = maxMap[i2][j2];
					}
				}
				if(max < aBenefit + bBenefit) max = aBenefit + bBenefit;
			}
		}
		return max;
	}
} // end of class
