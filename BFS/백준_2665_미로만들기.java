import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_2665_미로만들기 {
    private static int n;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 한 줄에 들어가는 방의 수 n(1≤n≤50)

        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray(); // 0은 검은 방, 1은 흰 방
        }

        System.out.println(bfs());
    }

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();

        int[][] visited = new int[n][n]; // 검은방 -> 흰방 바꾼 횟수 저장

        queue.offer(new int[] {0, 0});
        visited[0][0] = 0;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < dr.length; i++) { // 인접 행렬 돌면서
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue; // 배열 범위 체크

                if(map[nr][nc] == '0') { // 검은방
                    if(visited[nr][nc] == 0 || visited[nr][nc] > visited[r][c] + 1) {
                        visited[nr][nc] = visited[r][c] + 1; // 횟수 늘려서 저장
                        queue.offer(new int[] {nr, nc});
                    }
                } else { // 흰 방
                    if(visited[nr][nc] == 0 || visited[nr][nc] >= visited[r][c]) {
                        visited[nr][nc] = visited[r][c]; // 횟수 늘려서 저장
                        queue.offer(new int[] {nr, nc});
                    }
                }
            }
        }

        return visited[n-1][n-1];
    }
}
