import java.util.*;

public class 백준_9934_완전이진트리 {

    private static List<Integer>[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt(); // Tree 높이
        int N = (int)Math.pow(2, K) - 1; // 빌딩 개수 == 2%K - 1

        int[] in = new int[N+1]; // 중위순회한 결과 배열
        for (int i = 1; i <= N; i++) {
            in[i] = sc.nextInt();
        }

        tree = new ArrayList[K]; // 레벨별로 트리 저장할 리스트
        for (int i = 0; i < K; i++) {
            tree[i] = new ArrayList<>();
        }

        rebuildTree(in,0, 1, N); // 트리 재현

        for (int i = 0; i < K; i++) { // 출력
            for (int node : tree[i]) {
                System.out.print(node + " ");
            }
            System.out.println();
        }

    }

    private static void rebuildTree(int[] in, int level, int start, int end) {
        int mid = (start + end) / 2;
        tree[level].add(in[mid]); // 현재 레벨 트리에 mid값 저장

        if(start == end) return; // leaf node

        rebuildTree(in, level + 1, start, mid - 1); // 왼쪽 서브트리
        rebuildTree(in, level + 1, mid + 1, end); // 오른쪽 서브트리
    }
}
