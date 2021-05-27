import java.util.Scanner;

public class 백준_5569_출근경로 {
    public static void main(String[] args) {
        int mod = 100000;

        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt(); // 행
        int h = sc.nextInt(); // 열 (2 ≤ w, h ≤ 100)

        // 행, 열, 경우의 수
            // 0 : 이전 위치에서 북쪽 방향으로 왔고, 방향 바꾸지 못하는 경우
            // 1 : 이전 위치에서 북쪽 방향으로 왔고, 방향 바꿀 수 있는 경우
            // 2 : 이전 위치에서 동쪽 방향으로 왔고, 방향 바꾸지 못하는 경우
            // 3 : 이전 위치에서 동쪽 방향으로 왔고, 방향 바꿀 수 있는 경우
        int[][][] dp = new int[w + 2][h + 2][4];

        // 가장 자리 1로 초기화!!
        for (int i = 1; i <= w; i++) { // 남쪽에서 북쪽으로 쭈욱
            dp[i][1][1] = 1;
        }
        for (int i = 1; i <= h; i++) { // 서쪽에서 동쪽으로 쭈욱
            dp[1][i][3] = 1;
        }


        for (int i = 2; i <= w; i++) {
            for (int j = 2; j <= h; j++) {
                dp[i][j][0] = dp[i-1][j][3]; // 이전에 <동쪽에서 왔고, 회전할 수 있는> 점에서 온 경우
                dp[i][j][1] = (dp[i-1][j][0] + dp[i-1][j][1]) % mod; // 이전에 북쪽에서 온 두 가지 경우
                dp[i][j][2] = dp[i][j-1][1]; // 이전에 <북쪽에서 왔고, 회전할 수 있는> 점에서 온 경우
                dp[i][j][3] = (dp[i][j-1][2] + dp[i][j-1][3]) % mod; // 이전에 동쪽에서 온 두 가지 경우
            }
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += dp[w][h][i] % mod;
        }

        System.out.println(sum % mod);
    }

}
