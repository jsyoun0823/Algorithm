import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14499_주사위굴리기 {
    private static int N, M, X, Y, K;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기 N (행)
        M = Integer.parseInt(st.nextToken()); // 가로 크기 M (열) (1 ≤ N, M ≤ 20)
        X = Integer.parseInt(st.nextToken()); // 주사위를 놓은 곳의 좌표 x
        Y = Integer.parseInt(st.nextToken()); // 좌표 y, (0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1)
        K = Integer.parseInt(st.nextToken()); // 명령의 개수 K (1 ≤ K ≤ 1,000)

        map = new int[N][M]; // 북쪽부터 남, 서쪽부터 동쪽, 지도에 쓰여있는 수 < 10
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 처음 - 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태 (6이 바닥면!!)
        // 6이 바닥면 일때 동쪽->3, 서쪽->4, 북쪽->2, 남쪽->5
        // 1이 바닥면일때 ->
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int cur = Integer.parseInt(st.nextToken()); // 현재 명령어
            switch (cur) {
                case 1: // 동쪽
                    // 바닥면 3
                    break;
                case 2: // 서쪽
                    break;
                case 3: // 북쪽
                    break;
                case 4: // 남쪽
                    break;

            }
            // 초기 주사위 - 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태, 모든 면에 0
            int num = 0;
            sb.append(num).append('\n');
        }
        System.out.println(sb);
    }
}
