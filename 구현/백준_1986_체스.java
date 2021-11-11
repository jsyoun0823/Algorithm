import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_1986_체스 {
    private static int n, m;
    private static int[][] map;

    public static void main(String[] args) throws IOException {

        /**
         안전한 칸 몇 칸인지 세기
         퀸 - 가로, 세로, 대각선으로 갈 수 있는 만큼 이동, 중간에 장애물 있으면 X
         나이트 - 반대쪽 꼭짓점, 장애물 있어도 가능
         pawn - 장애물의 역할

         * */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k;
        map = new int[n + 1][m + 1];
        List<int[]> queen = new LinkedList<>();
        List<int[]> knight = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        for (int j = 0; j < k; j++) {
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;
            queen.add(new int[] {r, c});
        }
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        for (int j = 0; j < k; j++) {
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            knight.add(new int[] {r, c});
            map[r][c] = 2;
        }
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        for (int j = 0; j < k; j++) {
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;
        }

        for (int[] q: queen) {
            checkQueen(q[0], q[1]);
        }

        for(int[] kn : knight) {
            checkKnight(kn[0], kn[1]);
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(map[i][j] == 0) cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void checkKnight(int r, int c) {

        int[][] dir = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
        for (int i = 0; i < dir.length; i++) {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            if(!checkRange(nr, nc) || map[nr][nc] == 2) continue;
            map[nr][nc] = 1;
        }
    }

    private static void checkQueen(int r, int c) {
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int i = 0; i < 8; i++) {
            int step = 1;
            while(true) {
                int nr = r + (step * dir[i][0]);
                int nc = c + (step * dir[i][1]);
                if (!checkRange(nr, nc) || map[nr][nc] == 2) break;
                map[nr][nc] = 1;
                step++;
            }
        }

    }

    private static boolean checkRange(int nr, int nc) {
        return (nr > 0 && nr <= n && nc > 0 && nc <= m);
    }
}
