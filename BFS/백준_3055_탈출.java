import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_3055_탈출 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];

        int dx = 0;
        int dy = 0;
        int sx = 0;
        int sy = 0;

        Queue<int[]> waterQ = new LinkedList<>();
        boolean[][] waterVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'D') {
                    dx = i;
                    dy = j;
                } else if(map[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if(map[i][j] == '*') {
                    waterQ.add(new int[] {i, j});
                    waterVisited[i][j] = true;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1 ,1};

        Queue<int[]> goQ = new LinkedList<>();
        int[][] visited = new int[R][C];

        int cnt = 0;
        goQ.offer(new int[] {sx, sy});
        visited[sx][sy] = 1;

out:        while(!goQ.isEmpty()) {

                int wSize = waterQ.size();
                // 1) 물 먼저 차기

                for (int i = 0; i < wSize; i++) {
                    int[] curWater = waterQ.poll();
                    int cwr = curWater[0];
                    int cwc = curWater[1];

                    for (int j = 0; j < dr.length; j++) {
                        int nwr = cwr + dr[j];
                        int nwc = cwc + dc[j];

                        if (nwr < 0 || nwr >= R || nwc < 0 || nwc >= C) continue;

                        if (map[nwr][nwc] == 'X' || map[nwr][nwc] == 'D') continue;

                        if(!waterVisited[nwr][nwc]) {
                            map[nwr][nwc] = '*';
                            waterQ.offer(new int[]{nwr, nwc});
                            waterVisited[nwr][nwc] = true;
                        }
                    }
                }

                // 2) 고슴도치 이동
                int gSize = goQ.size();

                for (int i = 0; i < gSize; i++) {
                    int[] curDo = goQ.poll();
                    int cr = curDo[0];
                    int cc = curDo[1];

                    if(cr == dx && cc == dy) {
                        cnt = visited[cr][cc] - 1;
                        break out;
                    }

                    for (int j = 0; j < dr.length; j++) {
                        int nr = cr + dr[j];
                        int nc = cc + dc[j];

                        if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

                        if (map[nr][nc] == 'X' || map[nr][nc] == '*' || visited[nr][nc] != 0) continue;

                        visited[nr][nc] = visited[cr][cc] + 1;
                        goQ.offer(new int[] {nr, nc});
                    }
                }
            }

            if(cnt == 0) System.out.println("KAKTUS");
            else System.out.println(cnt);
    }
}
