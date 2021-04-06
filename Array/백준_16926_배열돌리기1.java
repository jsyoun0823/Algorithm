import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    돌려야하는 그룹의 개수 == Math.min(N, M) / 2
    2x2에서는 1개, 5x5에서는 2개...
    그리고 각 시작점은 항상 r==c 인 곳에서 시작
* */
public class 백준_16926_배열돌리기1 {
    private static int N, M, R;
    private static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 배열의 크기 N, M
        M = Integer.parseInt(st.nextToken()); // 2 ≤ N, M ≤ 300
        R = Integer.parseInt(st.nextToken()); // 수행해야 하는 회전의 수, 1 ≤ R ≤ 1,000

        A = new int[N][M]; // 입력으로 주어진 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()); // 배열 A의 원소 (1 ≤ A[i][j] ≤ 10^8)
            }
        }

        int size = Math.min(N, M) / 2; // 회전할 사각형 수
        for (int i = 0; i < R; i++) {
            rotate(size); // 반시계 방향으로 배열 돌리기
        }

        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(A[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static int[] dr = {0, 1, 0, -1}; // 우,하,좌,상
    public static int[] dc = {1, 0, -1, 0};

    /** 반시계 방향으로 배열 돌리는 함수 */
    private static void rotate(int size) {
        for (int i = 0; i < size; i++) { // 그룹 개수만큼 돌리기
            int r = i; // r==c 인 곳에서 시작
            int c = i;

            int dir = 0;
            int temp = A[r][c]; // 백업
            while(dir < 4) { // 배열 원소 한칸씩 이동
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if(nr >= i && nr < N-i && nc >= i && nc < M-i) {
                    A[r][c] = A[nr][nc];
                    r = nr; c = nc;
                } else dir++; // 범위 벗어나면 다음 방향으로 전환
            }
            A[i+1][i] = temp;
        }
    }
}
