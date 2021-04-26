import java.util.*;

/** 위상정렬, 자료구조, 우선순위 큐, 그래프*/
public class 백준_1766_문제집 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 문제의 수 N(1 ≤ N ≤ 32,000)
        int M = sc.nextInt(); // 먼저 푸는 것이 좋은 문제에 대한 정보의 개수 M(1 ≤ M ≤ 100,000)

        List<Integer> [] list = new List[N + 1]; // 선행 관계 리스트
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        int[] inDegree = new int[N + 1]; // 진입 차수 리스트 ( a->b 순서로 푼다면 b의 진입차수 + 1)
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt(); // A번 문제는 B번 문제보다 먼저 푸는 것이 좋다는 의미
            int b = sc.nextInt();
            inDegree[b]++;
            list[a].add(b); // a 다음에 올 친구들을 저장
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 난이도 쉬운거부터 해야하니까 pq (오름차순)
        for (int i = 1; i <= N; i++) {
            if(inDegree[i] == 0) pq.offer(i); // 진입차수가 0인 문제들을 pq에 삽입
        }

        while(!pq.isEmpty()) {
            int cur = pq.poll();

            sb.append(cur).append(' ');

            for (int next : list[cur]) { // 현재 문제에 붙어있는 간선 수 제거
                inDegree[next]--;

                if(inDegree[next] == 0) { // 진입차수가 0이 되면 pq에 삽입
                    pq.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}
