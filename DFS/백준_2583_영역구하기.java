import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2583_영역구하기 {
    static int M, N, K, size;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // N, M, K <= 100
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<int[]> point = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());
            point.add(new int[] {M - ry, lx, M - ly, rx}); // 왼쪽위 x, y 좌표 / 오른쪽아래 x, y 좌표
        }

        // 1. 모눈종이에 사각형이 차지하는 칸을 표시
        map = new int[M][N];
        paint(point);

        // 2. dfs로 영역 나눠서 넓이 구하기
        int cnt = 0;
        visited = new boolean[M][N];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0 && !visited[i][j]) {
                    size = 0;
//                    int val = bfs(i, j);
//                    if(val > 0) {
//                        cnt++;
//                        ans.add(val);
//                    }
                    dfs(i, j);
                    if(size > 0) {
                        cnt++;
                        ans.add(size);
                    }
                }
            }
        }

        Collections.sort(ans);

        System.out.println(cnt);
        for(int a : ans) {
            System.out.print(a + " ");
        }
//        int[] answer = ans.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    private static void paint(List<int[]> point) {
        for (int[] arr : point) {
            for (int i = arr[0]; i < arr[2]; i++) {
                for (int j = arr[1]; j < arr[3]; j++) {
                    map[i][j] = 1;
                }
            }
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();

        visited[i][j] = true;
        q.add(new int[] {i, j});
        int size = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int k = 0; k < dr.length; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if(nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc]) continue;

                if(map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                    size++;
                }
            }
        }

        return size;
    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;
        size++;

        for (int k = 0; k < dr.length; k++) {
            int nr = i + dr[k];
            int nc = j + dc[k];

            if(nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] != 0) continue;

            dfs(nr, nc);
        }
    }
}
