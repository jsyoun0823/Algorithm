import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA {

    // 전체 노드가 100만개 있다고 가정하면, 2^20 이면 충분하니 21로 설정
    final static int LOG = 21;

    static int N; // 최대 노드 갯수
    static int[] level; // 노드의 깊이 (레벨),
    static int[][] parent; // 2^i번째 조상
    static boolean[] visited;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            parent = new int[N+1][LOG]; // 각 노드의 몇번째 부모인지
            level = new int[N+1]; // 각 노드의 레벨 (깊이)
            visited = new boolean[N+1]; // 노드 방문 배열

            list = new ArrayList[N+1]; // 그래프 정보
            for (int i = 0; i <= N; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < N-1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }

            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            set_parent();

            sb.append(LCA(nodeA, nodeB)).append('\n');

        } // end of for Test Case

        System.out.println(sb);
    } // end of main

    // 루트 노드로부터 시작해 깊이를 구하는 함수
    public static void dfs(int cur, int depth) {
        visited[cur] = true;
        level[cur] = depth;
        for (int next: list[cur]) {
            if(!visited[next]) { // 아직 깊이를 구하지 않은 경우
                // 2^0 == 즉, 한칸 앞에 있는 부모 노드만 먼저 저장한다
                parent[next][0] = cur;
                dfs(next, depth + 1);
            }
        }
    }

    // 전체 부모 관계를 설정하는 함수
    public static void set_parent() {
        dfs(1, 0); // 루트 노드는 1번 노드

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= N; i++) {
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }
    }

    // A와 B의 최소 공통 조상을 찾는 함수
    public static int LCA(int a, int b) {
        if(level[a] > level[b]) { // b가 더 깊도록 swap
            int temp = a;
            a = b;
            b = temp;
        }

        // 깊이가 동일하도록 설정
        for (int i = LOG-1; i >= 0; i--) {
            int jump = 1 << i;

            // b의 2^i번째 조상이 a의 depth 보다 크거나 같으면 계속 조상을 타고간다.
            if(level[b] - level[a] >= jump) {
                b = parent[b][i];
            }
        }

        // 부모가 같아지도록 설정
        if(a == b) return a;

        // a와 b가 다르다면 깊이를 계속 올려 서로 다른 노드의 조상이 같아질 때까지 반복
        for (int i = LOG-1; i >= 0; i--) {
            // 조상을 향해 거슬러 올라가기
            if(parent[a][i] == parent[b][i]) continue;

            a = parent[a][i];
            b = parent[b][i];
        }

        // 부모가 찾고자 하는 조상
        return parent[a][0];
    }
}
