import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_18500_미네랄2 {
    static int R, C;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        // 각 칸은 비어있거나, 미네랄 포함
        // 네 방향 중 하나로 인접한 미네랄 포함된 두칸은 같은 클러스터
        // 동굴 왼쪽 - 창영, 오른쪽 - 상

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int r = 0;
        for (int i = 1; i <= N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            r = R - cur;

            if(i % 2 == 1) { // 홀수번 (왼쪽)
                for (int j = 0; j < C; j++) {
                    if(map[r][j] == 'x') {
                        map[r][j] = '.';
                        checkMineral();
                        break;
                    }
                }
            } else { // 짝수번 (오른쪽)
                for (int j = C - 1; j >= 0; j++) {
                    if(map[r][j] == 'x') {
                        map[r][j] = '.';
                        checkMineral();
                        break;
                    }
                }
            }
        }

        // 1. 왼->오 먼저, 번갈아가면서 던짐
        // 2. 막대 날아가다가 미네랄 만나면, 그 칸에 있는 미네랄 모두 파괴
        // 3-1. 미네랄 파괴 후 남은 클러스터 분리
        // 3-2. 새롭게 생성된 클러스터는 바닥으로 떨어짐
            // 다른 클러스터나 땅을 만나기 전까지 계속 떨어짐
            // 다른 클러스터 위에 떨어지면 합쳐짐
    }

    // 떠있는지 안떠있는지 bfs로 판별

    // 인접 방향 미네랄 확인
    static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dc = {0, 0, -1, 1};
    private static void checkMineral() {

    }

}
