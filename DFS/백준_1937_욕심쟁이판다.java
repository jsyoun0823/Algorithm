import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1937_욕심쟁이판다 {

    public static int n, map[][], memo[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 대나무 숲의 크기 n(1 ≤ n ≤ 500)
        map = new int[n][n]; // 각 지역의 대나무 양 <= 1,000,000
        memo = new int[n][n]; // 메모이제이션을 위한 배열

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                memo[i][j] = -1; // -1로 초기화
            }
        }

        int ans = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                ans = Math.max(ans, dfs(r, c));
            }
        }

        System.out.println(ans);
    }

    public static int[] dr = {-1, 1, 0, 0 }; // 상 하 좌 우
    public static int[] dc = {0, 0, -1, 1};

    public static int dfs(int r, int c) {
        if(memo[r][c] != -1) return memo[r][c]; // 연산을 이미 한 경우 리턴

        int cnt = 0;
        for (int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr<0 || nr>=n || nc<0 || nc>=n) continue; // 배열 범위 체크

            if(map[nr][nc] > map[r][c]) { // 이동하려는 지역이 전 지역보다 대나무가 많아야 함
                cnt = Math.max(cnt, dfs(nr, nc)); // dfs 돌고 최대값 갱신
            }
        }
        memo[r][c] = cnt + 1; // 날짜 세준거 메모에 더한다 (첫날 1일부터 시작하므로 +1)

        return memo[r][c];
    }
}
