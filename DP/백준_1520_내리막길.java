import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1520_내리막길 {
    static int M, N, map[][], dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 세로 크기
        N = Integer.parseInt(st.nextToken()); // 가로 크기

        map = new int[M][N];
        dp = new int[M][N]; // 현재 위치까지 누적된 경로의 수
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1); // -1로 초기화
        }

        System.out.println(dfs(0, 0));
    }

    static int[] dr = {1, 0, 0, -1}; // 하, 우, 좌, 상
    static int[] dc = {0, 1, -1, 0};

    private static int dfs(int r, int c) {
        if(r == M - 1 && c == N - 1) { // 목적지 도착하면
            return 1; // 경로의 수 1 증가
        }

        if(dp[r][c] == -1) { // 아직 계산하지 않은 지점일때 ㄱㄱ
            dp[r][c] = 0; // 방문 체크
            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[nr][nc] < map[r][c]) { // 가려는 곳이 더 낮아야 한다
                    dp[r][c] += dfs(nr, nc); // 경로의 수 dfs 연산해서 누적
                }
            }
        }

        return dp[r][c];
    }
}
