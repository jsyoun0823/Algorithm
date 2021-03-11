import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 백준_1038_감소하는수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 0 <= N <= 1,000,000

        if(N <= 10) { // 10보다 작으면 그대로 출력
            System.out.println(N);
            return;
        }

        Long answer = -1L; // N번째 감소하는 수

        int cnt = 0;
        Queue<Long> queue = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            queue.add((long)i);
            cnt++;
        }

        while(cnt < N && !queue.isEmpty()) {
            Long cur = queue.poll();

            for (int i = 0; i < cur % 10; i++) {
                Long temp = Long.parseLong(Long.toString(cur) + Long.toString(i));
                queue.add(temp);
                cnt++;

                if(cnt == N) {
                    answer = temp;
                    break;
                }

                if(temp == 987654321) {
                    break;
                }
            }
        }

        System.out.println(answer);

    }
}
