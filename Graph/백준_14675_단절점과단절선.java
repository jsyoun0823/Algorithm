import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_14675_단절점과단절선 {
    public static void main(String[] args) throws IOException {

        // 단절선 : 모든 간선
        // 단절점 : 연결된 간선이 1개인거 제외한 나머지 모든 정점
            // 자식이 1개인 루트 노드, 리프노드 면 안됨

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 트리의 정점 개수

        List<Integer> [] adjList = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine()); // 쿼리의 개수
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(t == 1) { // k번 정점이 단절점인지
                if(adjList[k].size() <= 1) {
                    sb.append("no\n");
                } else {
                    sb.append("yes\n");
                }

            } else { // k번째 간선이 단절선인지
                // 아니근데 k번쨰 간선이란게 기준이 뭐야;;ㅅㅂ
                sb.append("yes\n");
            }
        }

        System.out.println(sb);
    }
}
