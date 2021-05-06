import java.util.Scanner;

public class 백준_5557_1학년 {

    // 중간에 나올 수 있는 수의 범위가 0-20 이므로
    // 각 i번째 나올수 있는 숫자의 개수를 계산
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }

        long[][] d = new long[N][21];
        d[0][num[0]] = 1;

        for (int i = 1; i < N-1; i++) {
            int cur = num[i];

            for (int j = 0; j < 21; j++) {
                long prev = d[i-1][j];
                if(prev != 0) {
                    if(j + cur <= 20) {
                        d[i][j + cur] = d[i][j + cur] + prev;
                    }
                    if(j - cur >= 0) {
                        d[i][j - cur] = d[i][j - cur] + prev;
                    }
                }
            }
        }

        System.out.println(d[N-2][num[N-1]]);
    }

}