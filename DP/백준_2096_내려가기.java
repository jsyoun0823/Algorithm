import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2096_내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] num = new int[N][3];
        int[][] dp = new int[N][3];
        int[][] minDp = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                num[i][j] = dp[i][j] = minDp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] += Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] += Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
            dp[i][2] += Math.max(dp[i-1][1], dp[i-1][2]);

            minDp[i][0] += Math.min(minDp[i-1][0], minDp[i-1][1]);
            minDp[i][1] += Math.min(Math.min(minDp[i-1][0], minDp[i-1][1]), minDp[i-1][2]);
            minDp[i][2] += Math.min(minDp[i-1][1], minDp[i-1][2]);
        }

        System.out.print(Math.max(Math.max(dp[N-1][0], dp[N-1][1]), dp[N-1][2]) + " ");
        System.out.println(Math.min(Math.min(minDp[N-1][0], minDp[N-1][1]), minDp[N-1][2]));

        // 1 2 3
        // 4 5 6
        // 4 9 0

        // 1 -> 4 or 5
        // -> 4 9,
        // -> 4 9 0
        // 1 -> 4 5 [5]
        // 2 -> 4 5 6 [6]
        // 3 -> 5 6 [6];
        // dp[1][1] = 1// 첫번째 줄 첫번째까지 고려
        // dp[1][2] = 2
        // dp[1][3] = 3
        // dp[2][1] = dp[1][1] + num[2][1] or dp[1][2] + num[2][1]
        // dp[2][2] = dp[1][1] + num[2][2] or dp[1][2] + num[2][2] or dp[1][3] + num[2][2]

        // PQ size N개유지! N 줄이니까 - [1,5,0]    [3,6,9] 그때그때 dp 값 작은거..큰거..로 갱신

    }
}
