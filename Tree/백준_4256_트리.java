import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_4256_트리 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine()); // 노드 개수

            int[] preOrder = new int[n]; // 전위 순회 결과
            int[] inOrder = new int[n]; // 중위 순회 결과

            int[] tree = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inOrder[i] = Integer.parseInt(st.nextToken());
            }

            postOrder(1);
        }

    }

    public static void postOrder(int curr) {
//        if (curr > lastIndex) return;
//        postOrder(curr * 2); // 좌측 자식
//        postOrder(curr * 2 + 1); // 우측 자식
//        System.out.print(nodes[curr] + " "); // 부모정점(현재노드 방문처리)
    }

    public static void makeTree() {
    }
}
