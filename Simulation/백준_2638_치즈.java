import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2638_치즈 {

    static int N, M, cheese, ans;
    static int[][] map;
    static List<int[]> melted;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 모눈종이 크기
        M = Integer.parseInt(st.nextToken()); // (5≤N, M≤100)
        map = new int[N][M]; // N×M의 모눈종이, 0:치즈X, 1:치즈O
        cheese = 0; // 치즈 개수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    cheese++; // 치즈 개수 카운팅
                }
            }
        }

        ans = 0; // 치즈가 다 녹는 데 걸린 시간

        while(true){
            if(cheese <= 0) break; // 치즈 다 녹으면 종료

            divideAir(); // 외부공기 내부공기 나누기

            melted = new ArrayList<>(); // 녹아야 될 치즈
            check_melt(); // 녹아야 할 치즈 체크해서 리스트에 추가

            // 녹일 치즈 리스트에서 꺼내서 치즈 녹이기
            for (int[] c: melted) {
                map[c[0]][c[1]] = 0;
                cheese--;
            }

            ans++; // 시간 카운팅
        }

        System.out.println(ans);
    }

    static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dc = {0, 0, -1, 1};

    // 외부공기, 내부공기 나누기 위해 BFS 탐색
    // 외부공기는 -1로 표시
    public static void divideAir() {
        // 외부공기 표시했던거 되돌리기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == -1) map[i][j] = 0;
            }
        }

        Queue<int[]> air = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        air.offer(new int[] {0, 0}); // 0, 0부터 탐색
        visited[0][0] = true;

        // BFS 탐색
        while(!air.isEmpty()) {
            int size = air.size();
            for (int i = 0; i < size; i++) {
                int[] cur = air.poll();
                int r = cur[0];
                int c = cur[1];
                for (int k = 0; k < dr.length; k++) {
                    int nr = r + dr[k]; int nc = c + dc[k];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                    if(visited[nr][nc]) continue;;

                    if(map[nr][nc] == 0) {
                        air.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j]) {
                    map[i][j] = -1; // 외부공기 표시
                }
            }
        }
    }

    // 치즈 4변 중 2변이 -1 이라면 녹는다
    public static void check_melt() {
        for (int i = 1; i < N-1; i++) { // 가장자리는 공기니까 한칸 띄고 완전탐색
            for (int j = 1; j < M-1; j++) {
                if(map[i][j] == 1) { // 치즈라면
                    int cnt = 0; // 인접한 외부공기 갯수 카운팅할 변수

                    for (int k = 0; k < dr.length; k++) { // 인접 칸 돌면서
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                        if(map[nr][nc] == -1) cnt++; // 인접한 외부공기 갯수 체크
                    }

                    if(cnt >= 2) { // 맞닿은 외부공기 2칸 이상이면 녹아야됨
                        melted.add(new int[] {i, j});
                    }
                }

            }
        }
    }
}
