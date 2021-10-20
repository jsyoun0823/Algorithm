import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_12865_평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] weight = new int[N+1];
        int[] value = new int[N+1];
        int[][] dp = new int[N+1][K+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][w] = 물건 1~i까지 고려하고, 용량이 w일 때 최대 가치
        for (int i = 1; i <= N; i++) { // 물건 하나씩 꺼내기
            for (int j = 1; j <= K; j++) { // 용량 1부터 K 까지
                if(weight[i] <= j) { // 현재물건의 무게 <= 용량 이면 선택가능
                    dp[i][j] = Math.max(value[i] + dp[i-1][j - weight[i]], dp[i-1][j]); // 선택하냐, 안하냐 중 더 큰 값
                } else {
                    dp[i][j] = dp[i-1][j]; // 선택 불가능이니까 그냥 이전까지의 값
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
