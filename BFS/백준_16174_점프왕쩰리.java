import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_16174_점프왕쩰리 {
    static int N, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(bfs()) System.out.println("HaruHaru");
        else System.out.println("Hing");
    }

    private static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.offer(new int[] {0, 0});
        visited[0][0] = true;

        int[] dr = {0, 1}; // 오른쪽, 아래
        int[] dc = {1, 0};

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            if(r == N - 1 && c == N - 1) { // 도착하면 성공
                return true;
            }

            for (int i = 0; i < 2; i++) {
                int nr = r + (dr[i] * map[r][c]); // 현재 칸에 써져있는 수만큼 이동
                int nc = c + (dc[i] * map[r][c]);

                if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;

                queue.offer(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

        return false;
    }

}
