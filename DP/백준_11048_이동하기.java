import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*  DP 문제  */
public class 백준_11048_이동하기 {
    private static int N, M;
    private static int[][] candy;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 미로의 크기 (1 ≤ N, M ≤ 1,000)
        M = Integer.parseInt(st.nextToken());
        candy = new int[N+1][M+1]; // 0<= <=100
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                candy[i][j] = Integer.parseInt(st.nextToken()); // 각 방에 놓여져있는 사탕 개수
            }
        }
        System.out.println(go()); // (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수를 출력

    }

    public static int go() {
        int[][] D = new int[N+1][M+1]; // 얻을 수 있는 최대 사탕 수 저장
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // (r+1, c), (r, c+1), (r+1, c+1) 3가지 길만 갈 수 있음
                int max = Math.max(D[i-1][j], Math.max(D[i][j-1], D[i-1][j-1])); // 3가지 길 중에 최댓값 구하기
                D[i][j] = max + candy[i][j]; // 최대값 + 현재 사탕 수 저장
            }
        }
        return D[N][M]; // 마지막 N, M 도착 시 최대 사탕 수
    }
}
