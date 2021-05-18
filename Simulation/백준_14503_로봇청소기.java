import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14503_로봇청소기 {
    static class Point {
        int r, c, d;

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public int left() {
            switch (this.d) {
                case 0:
                    return 3;
                case 1:
                    return 0;
                case 2:
                    return 1;
                default:
                    return 2;
            }
        }

        public void forward() {
            switch (this.d) {
                case 0:
                    this.r--;
                    return;
                case 1:
                    this.c++;
                    return;
                case 2:
                    this.r++;
                    return;
                default:
                    this.c--;
                    return;
            }
        }

        public void back() {
            switch (this.d) {
                case 0:
                    this.r++;
                    return;
                case 1:
                    this.c--;
                    return;
                case 2:
                    this.r--;
                    return;
                default:
                    this.c++;
                    return;
            }
        }
    }

    static int N, M, map[][];
    static int zeroCnt = 0;
    static Point robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        robot = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        // 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽

        map = new int[N][M]; // 빈 칸은 0, 벽은 1
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) zeroCnt++;
            }
        }
        System.out.println(cleaning());
    }

    private static int cleaning() {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        // 청소한 칸은 2로 바꿔준다
        map[robot.r][robot.c] = 2;
        int cleanCnt = 1;
        zeroCnt--;

        int notExistCnt = 0;

        while(true) {
            if(zeroCnt == 0) break;

            if(notExistCnt == 4) { // 네 방향 모두 청소할 공간 없으면
                robot.back(); // 바라보는 방향을 유지한 채로 한 칸 후진
                if(map[robot.r][robot.c] == 1) { // 후진했는데 벽이면
                    break; // 작동 멈춘다
                }
                notExistCnt = 0; // 초기화
                continue; // 2번으로 되돌아감
            }

            int nd = robot.left();
            int nr = robot.r + dr[nd];
            int nc = robot.c + dc[nd];
            if(map[nr][nc] == 0) { // 왼쪽 방향에 청소하지 않은 공간 존재한다면
                // 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행
                robot.d = nd;
                robot.forward();
            } else { // 왼쪽 방향에 청소할 공간이 없다면
                //  그 방향으로 회전하고 2번으로 돌아가서 진행
                robot.d = nd;
                notExistCnt++;
                continue;
            }

            if(map[robot.r][robot.c] == 0) {
                map[robot.r][robot.c] = 2;
                cleanCnt++;
                zeroCnt--;
                notExistCnt = 0;
            }
        }
        return cleanCnt;
    }

}

