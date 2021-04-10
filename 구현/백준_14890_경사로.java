import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14890_경사로 {
    public static int[][] map;
    public static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (go(i, 0, 0)) {
                count++;
            }

            if (go(0, i, 1)) {
                count++;
            }
        }
        System.out.println(count);
    }

    // 해당 행이나 열 지나갈 수 있는지 체크하는 함수
    public static boolean go(int x, int y, int dir) {
        boolean[] visited = new boolean[N];
        int[] height = new int[N]; // 행 높이 or 열 높이

        for (int i = 0; i < N; i++) {
            height[i] = (dir == 0) ? map[x][y + i] : map[x + i][y];
        }

        for (int i = 0; i < N - 1; i++) {

            // 높이 똑같은 경우
            if (height[i] == height[i + 1]) continue;;

            // 높이차이 1보다 크면 못지나간다
            if (Math.abs(height[i] - height[i + 1]) > 1) return false;

            if (height[i] - 1 == height[i + 1]) { // 현재 칸 높이 > 다음 칸 높이
                for (int j = i + 1; j <= i + L; j++) {
                    // 범위체크, 방문체크, 높이 체크
                    if (j >= N || visited[j] || height[j] != height[i + 1]) {
                        return false;
                    }
                    visited[j] = true;
                }
            } else if (height[i] + 1 == height[i + 1]) { // 현재 칸 높이 < 다음 칸 높이
                for (int j = i; j > i - L; j--) {
                    // 범위체크, 방문체크, 높이 체크
                    if (j < 0 || visited[j] || height[j] != height[i]) {
                        return false;
                    }
                    visited[j] = true;
                }
            }
        }

        return true;
    }
}
