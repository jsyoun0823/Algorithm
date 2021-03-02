import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
구현, BFS 문제
   봄버맨은 격자판 모든 칸 자유롭게 이동 가능능
    1. 가장 처음에 봄버맨은 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
    2. 다음 1초 동안 봄버맨은 아무것도 하지 않는다.
    3. 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
    4. 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
    5. 3과 4를 반복한다.
*/
public class 백준_16918_봄버맨 {
    static int R, C, N, time;
    static char[][] map;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 격자판 크기 (행)
        C = Integer.parseInt(st.nextToken()); // (열)
        N = Integer.parseInt(st.nextToken()); // N초

        map = new char[R][C]; // 격자판 저장
        queue = new LinkedList<>(); // 폭탄 저장

        for (int i = 0; i < R; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp[j];
                if(temp[j] == 'O') {
                    queue.add(new int[] {i, j}); // 행, 열
                }
            }
        }

        time = 0; // 현재시간

        while(true) {
            time++;

            if(time == N) break;

            install(); // 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치
            time++; // 1초가 지난 후에

            if(time == N) break;

            explore(); // 3초 전에 설치된 폭탄이 모두 폭발
        }

        // 출력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    private static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    private static int[] dc = {0, 0, -1, 1};
    private static void explore() {

        while(!queue.isEmpty()) {

            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            map[r][c] = '.'; // 폭탄이 있던 칸이 파괴

            //  인접한 네 칸도 함께 파괴
            for (int k = 0; k < dr.length; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
                    map[nr][nc] = '.';
                }
            }
        }
    }

    // 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치
    private static void install() {

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == '.') { // 빈 칸이면
                    map[i][j] = 'O'; // 폭탄 설치
                }
                else { // 폭탄이면
                    queue.add(new int[] {i, j}); // 큐에 저장
                }
            }
        }
    }

}
