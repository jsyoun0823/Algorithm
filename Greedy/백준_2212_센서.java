import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/* 그리디, 정렬 문제 */
public class 백준_2212_센서 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 센서의 개수 N(1<=N<=10,000
        int K = sc.nextInt(); // 집중국의 개수 K(1<=K<=1000)

        int[] sensor = new int[N]; // 센서의 좌표
        for (int i = 0; i < N; i++) {
            sensor[i] = sc.nextInt(); // 좌표 절댓값 <=  1,000,000
        }

        Arrays.sort(sensor); // 오름차순 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 오름차순 우선순위 큐

        for (int i = 0; i < N - 1; i++) {
            pq.add(sensor[i+1] - sensor[i]); // 인접한 센서와의 간격 pq에 넣기
        }

        int ans = 0;
        // 센서 개수 - 집중국 개수 만큼 집중국이 센서를 커버 쳐야됨
        // 오름차순으로 더해서 커버 칠만큼 간격 먼거 삭제 해버리기!
        for (int i = 0; i < N - K; i++) {
            ans += pq.poll();
        }

        // [1,3] [6,9] -> 2 + 3 = 5
        // 0 + 1 + 1 + 2
        System.out.println(ans);
    }
}
