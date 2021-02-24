import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_17142_연구소3 {
    private static int N, M, map[][], min, space;
    private static List<Virus> virus;
    private static boolean[] selected;

    static class Virus{
        int r, c, t;
        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virus = new ArrayList<>();
        space = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 0 : 빈 칸, 1 : 벽, 2 : 바이러스
                if(map[i][j] == 2) {
                    virus.add(new Virus(i, j)); // 가장 처음에 모든 바이러스는 비활성 상태
                } else if(map[i][j] == 0) {
                    space++;
                }
            }
        }

        min = Integer.MAX_VALUE;
        selected = new boolean[10];
        comb(0, 0);
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    // 주어진 바이러스 중 M개 고르기
    public static void comb(int cnt, int start) {
        if(cnt == M) {
            int result = bfs();
            if(min > result && result >= 0) min = result;
            return;
        }
        for (int i = start; i < virus.size(); i++) {
            if(!selected[i]) {
                selected[i] = true;
                comb(cnt + 1, i + 1);
                selected[i] = false;
            }
        }
    }

    private static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    private static int[] dc = {0, 0, -1, 1};
    public static int bfs() {
        // 활성 상태인 바이러스는 인접 칸으로 동시에 복제되며, 1초 걸림
        // 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변한다.

        int result = Integer.MAX_VALUE;

        Queue<Virus> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < 10; i++) {
            if(selected[i]) {
                int tempR = virus.get(i).r;
                int tempC = virus.get(i).c;

                queue.offer(new Virus(tempR, tempC));
                visited[tempR][tempC] = true;
            }
        }

        int cnt = 0;
        int time = 0;
        while(!queue.isEmpty()) {
            if(cnt == space) {
                return (result > time) ? time : result;
            }

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Virus cur = queue.poll();

                for (int d = 0; d < dr.length; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
                            && copyMap[nr][nc] != 1) {
                        visited[nr][nc] = true;
                        queue.offer(new Virus(nr, nc));

                        if (copyMap[nr][nc] == 0) { // 빈 공간
                            cnt++;
                            copyMap[nr][nc] = 3;
                        }
                    }
                }
            }

            time++;
        }
        return result;
    }
}
