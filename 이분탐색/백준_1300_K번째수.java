import java.util.Scanner;

/**
 각 행에서의 S보다 작거나 같은 숫자의 개수 = min(S / i, N)

 왜냐하면, 각 행은
 i * 1,   i * 2,   i * 3,   i * 4,   .....   , i * j 이기 때문에,
 S를 i로 나눈 값에 나머지를 버린 값이 그 행에서 S보다 작거나 같은 개수가 된다.

 */
public class 백준_1300_K번째수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();

        int answer = 0;

        // 임의의 숫자 mid를 골라 mid보다 작은 숫자의 개수를 파악해서 K번째 숫자를 구한다
        int left = 1;
        int right = k;

        while(left <= right) {
            int mid = (left + right) / 2;

            /*
                mid보다 작은 숫자를 구하기 위해 1 ~ N까지 반복문을 돌리고
                    i * j <= mid이므로 (mid / i)가 조건을 만족하는 j의 숫자를 구한다
                -> 하지만, N이 1000보다 크면 mid / i 가 N보다 커질 수 있으므로 mid/i 와 N 중 작은 값을 더해 mid보다 작은 숫자의 개수를 파악
            */

            int cnt = 0;
            for(int i = 1; i <= N; i++) {
                cnt += Math.min((mid / i), N);
            }

            if(cnt < k) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
