import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 그냥 N-1 하면 되는 일,,
// 연결 그래프에서 모든 노드를 다 방문하는 방법 : V-1

public class 백준_9372_상근이의여행 {
    static int N, M, cnt;
    static List<Integer> [] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수 T(T ≤ 100)
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 국가의 수 N(2 ≤ N ≤ 1 000)
            M = Integer.parseInt(st.nextToken()); // 비행기의 종류 M(1 ≤ M ≤ 10 000)

            list = new List[N+1]; // 인접 리스트 배열
            visited = new boolean[N+1]; // 방문 배열

            for (int i = 0; i <= N; i++) {
                list[i] = new ArrayList<>(); // 인접 리스트 초기화
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b); // 인접 리스트에 추가
                list[b].add(a); // 반대 경우도 추가
            }

            cnt = -1; // 마지막에 -1 빼야해서 -1로 초기화

            visited[1] = true; // 1부터 시작
            dfs(1);

            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
    }

    // 모든 국가를 다 방문하는 데 몇 번 걸리는지 dfs 이용해서 탐색
    private static void dfs(int cur) {
        cnt++; // 몇번 비행기 타는지
        for (int end : list[cur]) { // 현재 인접리스트에서 목적지 꺼내서
            if(!visited[end]) { // 방문했는지 체크
                visited[end] = true;
                dfs(end); // 다음 dfs 탐색
            }
        }
    }
}
