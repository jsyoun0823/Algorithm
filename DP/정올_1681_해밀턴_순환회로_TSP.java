import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 
 * TSP 알고리즘 ; 동적 계획법
 * 1. 구체적인 경로는 중요하지 않기 때문에 방문 표시는 비트마스킹 사용
 * 		a,b,c,d중에 a,b방문이면 1100 
 * 		다 방문이면 1111 ->  (1 << N) -1 하면 나옴
 * 2. 특정 start, last, V에서 이들을 활용한 find_path가 중복 호출 되는지 확인
 * 	 특정 start, last, V에 대해 아직 방문하지 않은 모든 도시를 방문하는 재귀함수를 호출하고 이중 최소값을 취한다.
 * 3. 한번 방문한 도시는 재방문하지 않는다.
 * 4. 현재 도시 -> {현재까지 방문 도시} -> 이동할수있는 도시
 * 							(마지막으로 모두 방문하면 종료)
 * 		dp[현재도시][방문한도시] = 나머지 도시들을 방문하는 데 드는 최소비용
 * 점화식 : TSP( i , visit + ( 1<< i  ) ) + w [ here ][ i ]
 * */

public class 정올_1681_해밀턴_순환회로_TSP {
	private static int N;
	private static int[][] cost;
	private static int[][] dp;
	private static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // 장소의 수 N (N(1≤N≤12)
		cost = new int[N][N];
		dp = new int[N][(1<<N)-1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				cost[i][j]= Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[i], INF);
		} // 입력 처리
		int answer = tsp(0, 1);
		if(answer == INF) System.out.println(0);
		else System.out.println(answer);
	}

	public static int tsp(int idx, int visit) {
		if(visit == ((1 << N) - 1)) { // 모든 지점을 방문했을 경우
			if(cost[idx][0] == 0) return INF;
			// 마지막 방문도시와 0번째(시작도시)가 이어져 있지 않으면 무한값 반환
			return cost[idx][0];
			// 이어져 있으면 이 경로값을 반환
		}
		
		// DP의 효율성! (가지치기)
		if(dp[idx][visit] != INF) { // 이미 계산한 경우 (중복호출)
			return dp[idx][visit]; // 재계산 하지 않고 값 바로 반환
		}
		
		// 이제 첫 계산이라면, 방문하지 않은 모든 도시 모두 방문해서 그중 최소값을 선택해야한다
		for (int i = 0; i < N; i++) {
			// i & (1 << j) 선택하지 않은 것 1로 바꿔주는 것!!
			int next = (visit | (1 << i));
			
			// i & ( 1 << j) 1인지 0인지 판단 !!!
			if(cost[idx][i] == 0 // i번 노드에 대해 길이 없거나
					|| (visit & (1 << i)) != 0) { // 이미 방문한 경우
				continue; // 다음으로 넘어가자
			}
			
			dp[idx][visit] = Math.min(dp[idx][visit], tsp(i, next) + cost[idx][i]);
//        			점화식 : TSP( i , visit + ( 1<< i  ) ) + w [ here ][ i ]
		}
		return dp[idx][visit];
	}
}
