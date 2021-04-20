package Prim;

import java.util.*;

public class 백준_16398_행성연결 {
    static int N;
    static List<Planet> [] adj;

    static class Planet implements Comparable<Planet>{
        int next;
        long cost;

        public Planet(int next, long cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Planet planet) {
            return Long.compare(this.cost, planet.cost);
        }
    }

    public static void main(String[] args) {
        // 제국 내 모든 행성을 연결하고, 그 유지비용을 최소화
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 행성의 수 N (1 ≤ N ≤ 1000)

        adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Long cost = sc.nextLong();
                if(i == j) continue;
                adj[i].add(new Planet(j, cost)); // 각 행성간의 플로우 관리 비용
            }
        }

        System.out.println(prim());
    }

    public static long prim() {
        boolean[] visited = new boolean[N];

        long sum = 0; // 최소 신장 트리 비용
        int cnt = 0; // 처리 정점 개수

        PriorityQueue<Planet> pq = new PriorityQueue<>();
        pq.add(new Planet(0, 0)); // 임의로 0부터 시작

        while(!pq.isEmpty()) {
            // 신장트리에 포함되지 않은 행성 중 최소 간선 비용의 행성 선택
            Planet cur = pq.poll();

            if(visited[cur.next]) continue;

            visited[cur.next] = true;
            sum += cur.cost; // 간선비용 누적

            if(++cnt == N) return sum; // 모든 정점 순회한 경우

            // 다른 인접 행성 돌면서 방문하지 않았다면 pq에 넣어준다
            for (Planet p : adj[cur.next]) {
                if (!visited[p.next]) {
                    pq.add(p);
                }
            }
        }

        return sum;
    }
}
