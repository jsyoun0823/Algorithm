import java.io.*;
import java.util.*;

/* 구현, 시뮬레이션 문제 */

public class 백준_20056_마법사상어와파이어볼 {
    static int N, M, K;
    static List<FireBall> [][] map, newMap;

    static class FireBall {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 격자판 크기
        M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
        K = Integer.parseInt(st.nextToken()); // 이동 횟수

        map = new ArrayList[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()); // 파이어볼 위치 행
            int c = Integer.parseInt(st.nextToken()); // 파이어볼 위치 열
            int m = Integer.parseInt(st.nextToken()); // 질량
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 방향 (인접한 8개 칸)

            map[r][c].add(new FireBall(r, c, m, s, d));
        }
        

        for (int i = 0; i < K; i++) { // K번 이동할 동안
            move(); // 파이어볼 이동
            union(); // 합치고 나누어지는 작업
        }

        int sum = 0; // 남아있는 파이어볼 질량의 합
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j].size() >= 1) {
                    for (FireBall f: map[i][j]) {
                        sum += f.m;
                    }
                }
            }
        }

        System.out.println(sum);

    }

    /*  7 0 1
        6   2
        5 4 3
    * */
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    //     모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동한다.
    //        이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
    public static void move() {
        newMap = new ArrayList[N+1][N+1]; // 이동할 배열 초기화
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j].size() >= 1) { // 1개 이상의 파이어볼 존재하면
                    for (FireBall f : map[i][j]) { // 꺼내서
                        int nr = f.r + dr[f.d] * (f.s % N); // 방향 d로 속력 s칸 만큼 이동
                        int nc = f.c + dc[f.d] * (f.s % N);

                        // 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다 -> 배열 벗어나는 것 처리
                        if(nr < 1) nr += N;
                        else if(nr > N) nr -= N;
                        // speed 990
                        if(nc < 1) nc += N;
                        else if(nc > N) nc -= N;

                        // 새 파이어볼 추가
                        newMap[nr][nc].add(new FireBall(nr, nc, f.m, f.s, f.d));
                    }
                }
            }
        }
    }

    public static void union() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int size = newMap[i][j].size();
                if(size >= 2) { // 한 칸에 2개 이상의 파이어볼이 존재하면
                    int mSum = 0; // 질량 합
                    int sSum = 0; // 속력 합
                    int evenCnt = 0; // 홀수 갯수
                    int oddCnt = 0; // 짝수 갯수

                    for (FireBall f : newMap[i][j]) {
                        mSum += f.m; // 질량 더한다
                        sSum += f.s; // 속력 더한다

                        if(f.d % 2 == 0) oddCnt++; // 짝수
                        else evenCnt++; // 홀수
                    }

                    newMap[i][j].clear(); // 현재 칸 파이어볼 하나로 합쳐서 다시 나누기 위해 클리어

                    int nm = mSum / 5; // 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋
                    int ns = sSum / size; // 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋

                    if(nm > 0) { // 질량이 0인 파이어볼은 소멸되어 없어진다.

                        if (evenCnt == size || oddCnt == size) { // 모두 홀수거나 모두 짝수면
                            for (int k = 0; k <= 6; k += 2) { // 방향은 0, 2, 4, 6
                                newMap[i][j].add(new FireBall(i, j, nm, ns, k));
                            }
                        } else { // 그렇지 않으면
                            for (int k = 1; k <= 7; k += 2) { //  1, 3, 5, 7
                                newMap[i][j].add(new FireBall(i, j, nm, ns, k));
                            }
                        }
                    }
                }
            }
        }
        map = newMap; // 다음 이동을 위해 map에 newMap 다시 넣어준다
    }

}
