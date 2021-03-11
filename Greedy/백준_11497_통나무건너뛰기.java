import java.util.*;

public class 백준_11497_통나무건너뛰기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 각 통나무들의 높이가 저장될 pq
            int N = sc.nextInt(); // 통나무 개수 N(5 ≤ N ≤ 10,000)
            for (int j = 0; j < N; j++) {
                pq.add(sc.nextInt()); // 통나무의 높이 (1 ≤ Li ≤ 100,000)
            }

            int[] tree = new int[N];
            int left = 0;
            int right = N-1;
            for (int j = 0; j < N; j++) {
                if(j % 2 == 0) { // 짝수
                    tree[left] = pq.poll();
                    left++;
                } else { // 호올수
                    tree[right] = pq.poll();
                    right--;
                }
            }

            int ans = Math.abs(tree[0] - tree[N-1]);
            for (int j = 0; j < N - 1; j++) {
                ans = Math.max(ans, Math.abs(tree[j] - tree[j+1]));
            }
            System.out.println(ans);
        }
    }
}
