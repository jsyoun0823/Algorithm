import java.util.*;

public class 카카오메이커스_sol3 {
    public static List<Integer> [] station;

    public static void main(String[] args) {

        int n = 6;
        int[] passenger = {1,1,1,1,1,1};
        int[][] train = {{1,2},{1,3},{1,4},{3,5},{3,6}};

        int[] answer = new int[2];

        station = new ArrayList [n+1];
        for(int i = 0; i <= n; i++) {
            station[i] = new ArrayList<>();
        }

        for(int[] t : train) {
            station[t[0]].add(t[1]);
            station[t[1]].add(t[0]);
        }

        int max = 0;
        for(int i = 2; i <= n; i++) {
            int result = bfs(i, n, passenger);
            if(max <= result) {
                answer[0] = i;
                answer[1] = result;
            }
        }

        System.out.println(Arrays.toString(answer));
    }

    public static int bfs(int end, int n, int[] passenger) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        queue.offer(1);
        visited[1] = true;
        int cnt = passenger[0]; // 이용객 수

        int[] sum = new int[n+1];
        sum[1] = cnt;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if(cur == end) break;

            for(int next : station[cur]) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    sum[next] = sum[cur] + passenger[next - 1];
                }
            }
        }
        return sum[end];
    }
}
