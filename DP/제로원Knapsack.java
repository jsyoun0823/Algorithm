import java.util.Scanner;

/** 
 * 0/1 Knapsack
 * 넣거나/안넣거나
 * 
 * 이전까지 고려된 물건까지 최적해 + 지금
 * K[i,w] = 물건 1~i까지 고려하고, 용량이 w일 때 최대 가치
 * 
 * K[i,w] = 
 * 		if (i==0 or w==0) 0
 * 		if (w[i]>w) k[i-1,w]  <- 물건 무게가 가방 무게보다 크면 넣을 수 X, 기존까지 고려된 최적해 저장
 * 		if (i>0 and w[i]<=w) Max(v[i] + k[i-1, w-w[i]], k[i-1,w]) 가방에 넣을 수 있다면 자신의 가치와 넣지않았을 때의 가치중 더 큰 가치인거 선택해서 저장
 * 
 * */
public class 제로원Knapsack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int W = sc.nextInt();
		int[] weights = new int[N+1];
		int[] profits = new int[N+1];
		int[][] D = new int[N+1][W+1];
		
		// i=0은 0으로 그대로 둠.
		for (int i = 0; i <= N; i++) {
			weights[i] = sc.nextInt();
			profits[i] = sc.nextInt();
		}
		
		// 모든 아이템에 대해서 반복
		for (int i = 1; i <= N; i++) {
			
			// 현 아이템의 1부터 목표무게까지의 가치테이블을 만든다
			for (int w = 1; w <= W; w++) {
				// 현 아이템의 무게가 가치테이블을 만들기 위한 무게보다 작거나 같다면
				// 선택 가능하며, 아래 둘 중 최대 가치를 선택한다.
				// 	1) 현 아이템을 선택하지 않았을 경우의 가치는 가치 테이블에서 같은 무게의 이전아이템까지의 가치
				//	2) 현 아이템을 선택했을때의 가치와 가치테이블에서 해당 아이템의 무게만큼 뺀 무게의 이전아이템까지의 가치
				if(weights[i] <= w) {
					D[i][w] = Math.max(D[i-1][w], profits[i]+D[i-1][w-weights[i]]);
				} else {// 현 아이템의 무게가 가치테이블을 만들기 위한 무게보다 크다면 현 아이템 선택불가하므로
						// 최적의 가치는 가치테이블에서 같은 무게의 이전아이템까지의 가치
					D[i][w] = D[i-1][w];
				}
			}
		}
		System.out.println(D[N][W]); // 마지막 아이템까지 고려한 W무게를 만족하는 최대가치
	}
}
