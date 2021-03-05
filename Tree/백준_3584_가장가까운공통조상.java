import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_3584_가장가까운공통조상 {

    static int N; // 노드 갯수
    static int[] level, parent; // 노드의 깊이 (레벨), 부모 저장할 배열
    static boolean[] visited; // 루트 노드 찾기 위한 부모 있는지 체크 배열
    static List<Integer>[] list; // 그래프 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine()); // 노트 갯수

            parent = new int[N+1]; // 각 노드의 부모
            level = new int[N+1]; // 각 노드의 레벨 (깊이)

            list = new ArrayList[N+1]; // 자식들을 저장할 리스트
            for (int i = 0; i <= N; i++) {
                list[i] = new ArrayList<>();
            }

            visited = new boolean[N+1];
            for (int i = 0; i < N-1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                visited[b] = true; // b는 부모가 있으므로 true 설정

                parent[b] = a; // a가 b의 부모
                list[a].add(b);
            }

            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            int root = find_root();

            dfs(root, 0, -1);

            sb.append(LCA(nodeA, nodeB)).append('\n');
        } // end of for Test Case
        System.out.println(sb);
    } // end of main

    // root 찾는 함수 (부모가 없는 노드를 찾는 과정)
    private static int find_root() {
        int r = 0;
        for (int i = 1; i <= N; i++) {
            if(!visited[i]) r = i;
        }
        return r;
    }

    // 루트 노드로부터 시작해 각 노드의 깊이와 부모 노드를 찾는 함수
    public static void dfs(int cur, int depth, int p) {
        level[cur] = depth;
        parent[cur] = p;
        for (int next: list[cur]) {
            if(next != p) { // 양방향이 아니라 없애도됨
                dfs(next, depth + 1, cur);
            }
        }
    }

    // A와 B의 최소 공통 조상을 찾는 함수
    public static int LCA(int a, int b) {
        while(level[a] != level[b]) { // 깊이가 같아지도록 설정
            if(level[a] > level[b]) a = parent[a];
            else b = parent[b];
        }

        // a와 b가 같아질 때 까지 깊이를 계속 올려 서로 다른 노드의 조상이 같아질 때까지 반복
        while(a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}