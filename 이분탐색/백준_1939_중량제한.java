import java.util.*;

public class 백준_1939_중량제한 {
    private static int N, start, end, max_cost;
    private static List<int[]> []  list;

    public static void main(String[] args) {
        // 1. 인접 그래프 만들고 정렬
        // 2. BFS든 DFS든 탐색 ㄱㄱ
        // 3. 어떤 다리 쓸지 이분탐색으로 최대값 찾아가면서 결정

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 섬의 수 (2 ≤ N ≤ 10,000)
        int M = sc.nextInt();

        list = new ArrayList [N+1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        max_cost = 0;
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            list[a].add(new int[] {b, c}); // 목적지, 비용
            list[b].add(new int[] {a, c});

            if(max_cost < c) max_cost = c;
        }

        // 공장이 위치해 있는 두 섬의 번호
        start = sc.nextInt();
        end = sc.nextInt();

        // cost 내림차순으로 정렬
        for (int i = 1; i < N + 1; i++) {
            list[i].sort((a, b) -> b[1] - a[1]);
        }

        binary_search();
    }

    private static void binary_search() {
        int left = 1;
        int right = max_cost;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(bfs(mid)) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(right);
    }

    private static boolean bfs(int cost) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int cur_no = queue.poll();

            if(cur_no == end) return true;

            for (int[] next : list[cur_no]) {
                int next_no = next[0];
                int next_cost = next[1];

                if(!visited[next_no] && cost <= next_cost) {
                    visited[next_no] = true;
                    queue.add(next_no);
                }
            }
        }

        return false;
    }
}
