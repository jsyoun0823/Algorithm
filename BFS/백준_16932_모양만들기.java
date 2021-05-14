import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class 백준_16932_모양만들기 {
    public static void main(String[] args) throws IOException {
        // 1이 들어있는 인접칸끼리 연결했을때
        // 1개를 변경해서 만들 수 있는 모양의 최대 크기

        // 0 1 1
        // 0 0 1
        // 0 1 0

        // 각 그룹을 정해서,, 1 1 1 1 , 2 2 2 , 3 3 3 이렇게
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int[] dr = new int[] {-1, 1, 0, 0};
        int[] dc = new int[] {0, 0, -1, 1};


        // 그룹 먼저 만들기
        Map<Integer, Integer> group = new HashMap<>();
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[N][M];
        int groupNum = 1;
        q.offer(new int[] {0, 0}); // r, c
        visited[0][0] = groupNum;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < dr.length; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 0) continue;

                if(visited[nr][nc] >= 1){
                    visited[nr][nc] = visited[r][c];
                    q.offer(new int[] {nr, nc});
                    group.put(visited[r][c], group.getOrDefault(visited[r][c], 0) + 1);
                    map[nr][nc] = visited[nr][nc];
                }
            }

            groupNum++;
        }

        // 1로 하나 바꿀수있는거 해보면서 최대 크기 구해보기


    }
}