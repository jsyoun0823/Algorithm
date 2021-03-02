import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2056_작업 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] prevList = new ArrayList[N+1]; // 선행 관계 작업 리스트
        for (int i = 0; i <= N; i++) {
            prevList[i] = new ArrayList<>(); // ArrayList 초기화
        }

        int[] inDegree = new int[N+1]; // 진입 차수
        int[] cost = new int[N+1]; // 작업 시간
        int[] costSum = new int[N+1]; // 각 작업을 완료하는 데 걸리는 누적 시간

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            costSum[i] = cost[i] = Integer.parseInt(st.nextToken()); // 작업 시간
            int degree = Integer.parseInt(st.nextToken()); // 선행 관계 개수
            inDegree[i] = degree;

            for (int j = 0; j < degree; j++) {
                int prev = Integer.parseInt(st.nextToken());
                prevList[prev].add(i);
            }
        } // -- input end --

        // -- topology sort --
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) { // 진입차수가 0인 노드를 queue에 삽입
            if(inDegree[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) { // queue 빌 때까지
            int cur = queue.poll(); // queue에서 원소를 꺼내서
            for (int i = 0; i < prevList[cur].size(); i++) {
                int next = prevList[cur].get(i);

                // 해당 작업이 완료되는 데 걸리는 최소 시간 갱신
                costSum[next] = Math.max(costSum[next], costSum[cur] + cost[next]);

                inDegree[next]--; // 해당 원소에 부속된 모든 간선에 대해 간선 수 -1

                if(inDegree[next] == 0) queue.offer(next); // 진입차수가 0이 된 정점을 queue에 삽입
            }
        }

        // -- output --
        int ans = 0;
        for (int c : costSum) {
            ans = (ans < c) ? c : ans;
        }
        System.out.println(ans);
    }
}
