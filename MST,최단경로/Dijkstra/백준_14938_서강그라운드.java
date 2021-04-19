package Dijkstra;

import java.util.*;

public class 백준_14938_서강그라운드 {
    private static final int INF = Integer.MAX_VALUE;
    private static int n, m, r;
    private static List<int[]>[]  adj;
    private static int[] item;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 지역 개수 (1 ≤ n ≤ 100)
        m = sc.nextInt(); // 수색 범위 (1 ≤ m ≤ 15)
        r = sc.nextInt(); // 길의 개수 (1 ≤ r ≤ 100)

        item = new int[n+1];
        for (int i = 1; i <= n; i++) {
            item[i] = sc.nextInt(); // 각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)
        }

        adj = new List[n+1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int l = sc.nextInt();
            adj[a].add(new int[] {b, l});
            adj[b].add(new int[] {a, l});
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, go(i));
        }

        System.out.println(max);
    }

    private static int go(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n+1];
        int[] cost = new int[n+1];
        Arrays.fill(cost, INF);

        cost[start] = 0;
        pq.offer(new int[] {start, cost[start]});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int idx = cur[0];
            int curCost = cur[1];

            // 이미 방문했거나, 현재 cost가 현재까지 비용보다 크면 다음으로 넘어감
            if(visited[idx] || cost[idx] < curCost) continue;
            visited[idx] = true;

            for (int[] next: adj[idx]) {
                int nextIdx = next[0];
                int nextCost = next[1];

                if(!visited[nextIdx] && cost[nextIdx] > curCost + nextCost) {
                    cost[nextIdx] = curCost + nextCost;
                    pq.offer(new int[] {nextIdx, cost[nextIdx]});
                }
            }
        }

        int itemCnt = 0;
        for (int i = 1; i <= n; i++) {
            if(cost[i] <= m) {
                itemCnt += item[i];
            }
        }

        return itemCnt;
    }
}
