package 보충수업;

import java.util.Scanner;

public class SWEA_2117_홈방범서비스 {

	static int[][] map;
	public static void main(String[] args) {
//		map = new int[20][20];
//		int a = 4, b = 6, k = 3;
//		// a,b로부터 k거리 만큼의 마름모를 1로 변경하자
//		for (int r = a - k; r <= a + k; r++) {
//			for (int c = b - k; c <= b + k; c++) {
////				상하좌우 이동 거리가 최대 3칸인 녀석만 고르자
//				if(r>=0 && c>=0 && r<20 && c<20 && // 배열범위 내
//					(Math.abs(a-r) + Math.abs(b-c)) <= k)
//					map[r][c] = 1;
//			}
//		}
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int ans = 0;
			// 모든 위치에서 모든 크기의 마름모를 다 그려봐야 한다
			// 모든 좌표에 대해서
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 모든 마름모 크기에 대해서
					for (int k = 1; k <= N + 1; k++) {
						// 마름모가 걸치는 모든 행열 범위에 대해 검사
						int cnt = 0;
						for (int r = i - k; r <= i + k; r++) {
							for (int c = j - k; c <= j + k; c++) {
								if (r < 0 || c < 0 || r >= N || c >= N)
									continue;

								if ((Math.abs(i - r) + Math.abs(j - c)) < k) {
									if (map[r][c] == 1) {
										cnt++;
									}
								}
							}
						}
						// 집의 갯수가 cnt에 있다.
						int cost = k * k + (k - 1) * (k - 1);
						if (cost <= cnt * M) {
							// 여기 오는 cnt값 중 최댓값 찾기
							ans = Math.max(ans, cnt);
						}
					}
				}
			}
			System.out.println("#" + " " + tc + ans);
		}

	}

}