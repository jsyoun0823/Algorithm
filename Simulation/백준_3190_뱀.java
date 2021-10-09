import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_3190_뱀 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 보드의 크기 (2 ≤ N ≤ 100)
        int K = Integer.parseInt(br.readLine()); // 사과 개수  (0 ≤ K ≤ 100)

        int[][] map = new int[N][N]; // 0:빈칸, 1:뱀, 2:사과
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r - 1][c - 1] = 2;
        }
        map[0][0] = 1; // 뱀 초기 위치

        int L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수 (1 ≤ L ≤ 100)
        Queue<int[]> change = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()); // 시간 X초 후
            String c = st.nextToken();
            if(c.equals("D")) {
                change.add(new int[] {x, 0}); // 0:오른쪽으로 90도, 1:왼쪽으로 90도
            } else {
                change.add(new int[] {x, 1});
            }
        }

        // 0. 뱀 초기 위치 (1, 1), 오른쪽 향함
        // 1. 다음칸에 위치
            // 2-1. 사과가 있으면, 사과 없어지고 꼬리 그대로 (몸길이 늘어나는 것)
            // 2-2. 사과가 없으면, 꼬리가 위치한 칸 비워준다. (몸길이 그대로)
        // 게임이 몇 초에 끝나는지 계산
        // 벽 또는 자기자신의 몸과 부딪히면 게임이 끝남

        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
        int time = 1;
        Deque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[] {0, 0, 3}); // 행, 열, 방향

        while(true) {
            int[] head = snake.peekFirst();
            int d = head[2];
            int nr = head[0] + dir[d][0];
            int nc = head[1] + dir[d][1];

            if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) break;

            if(map[nr][nc] == 0) { // 사과 없는 경우 꼬리 위치한 칸 비워주고, 길이는 그대로
                int[] tail = snake.pollLast();
                map[tail[0]][tail[1]] = 0; // 꼬리부분 맵에서 빈칸으로 표시
            }

            map[nr][nc] = 1;
            snake.addFirst(new int[] {nr, nc, d}); // 머리 큐에 넣기

            if(!change.isEmpty() && time == change.peek()[0]) {
                int[] t = change.poll();
                int[] h = snake.pollFirst();
                int newDir = changeDir(h[2], t[1]);
                snake.addFirst(new int[] {h[0], h[1], newDir});
            }

            time++; // 시간 증가
        }

        System.out.println(time);
    }

    private static int changeDir(int i, int d) {
        if(d == 0) {
            switch (i) {
                case 0 : return 3;
                case 1 : return 2;
                case 2 : return 0;
                default: return 1;
            }
        } else {
            switch (i) {
                case 0 : return 2;
                case 1 : return 3;
                case 2 : return 1;
                default: return 0;
            }
        }
    }
}
