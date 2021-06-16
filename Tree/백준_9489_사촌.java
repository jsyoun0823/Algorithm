import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_9489_사촌 {
    public static int[] parents = new int[1002];

    public static void main(String[] args) throws IOException {
        // 1) 첫번째 정수는 트리의 루트 노드
        // 2) 연속된 수의 집합이 노드의 자식이 됨
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n, k;
        while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) break;

            int[] nodes = new int[n + 1];

            int kIdx = 0; // 주어진 k의 인덱스
            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 1; i <= n; i++) {
                nodes[i] = Integer.parseInt(st.nextToken());

                if (nodes[i] == k) {
                    kIdx = i;
                }
            }

            int prev = nodes[1]; // 루트 노드
            parents[1] = 0; // 루트 노드의 부모 0로 설정

            int p = 0; // 부모 노드
            for (int i = 2; i <= n; i++) {
                int cur = nodes[i];

                if (cur == k) kIdx = i;

                if (cur != prev + 1) { // 연속되지 않았으면
                    p++;
                }

                parents[i] = p;
                prev = cur;
            }

            int cnt = 0;
            if (parents[parents[kIdx]] != 0) {
                for (int i = 2; i <= n; i++) {
                    if (parents[i] != parents[kIdx] // 두 노드의 부 다르고 부모가 형제일때
                            && parents[parents[i]] == parents[parents[kIdx]]) cnt++; // 사촌 수 증
                }
            }

            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
    }
}
