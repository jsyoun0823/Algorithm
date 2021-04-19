package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_1238_파티 {
    private static final int INF = Integer.MAX_VALUE;
    private static int N, M, X, dist[];
    private static List<int[]> [] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); 
        X = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        list = new List[N+1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[start].add(new int[] {end, d});
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int result = go(i, X) + go(X, i);
            ans = Math.max(result, ans);
        }
        System.out.println(ans);
    }

    private static int go(int start, int end) {
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        dist[start] = 0;
        pq.offer(new int[] {start, dist[start]});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int no = cur[0];
            int d = cur[1];

            for (int[] node:list[no]) {
                int next_no = node[0];
                int next_d = node[1];

                if(dist[next_no] > d + next_d) {
                    dist[next_no] = d + next_d;
                    pq.add(new int[] {next_no, dist[next_no]});
                }
            }
        }

        return dist[end];
    }
}
