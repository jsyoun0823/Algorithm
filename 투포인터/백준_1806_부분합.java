import java.util.Scanner;

public class 백준_1806_부분합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int S = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        int min = N + 1;
        int left = 0;
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += A[i]; //

            while(sum >= S) {
                if(min > i - left) min = i - left + 1;

                sum -= A[left];
                left++;
            }
        }

        if(min == (N+1)) min = 0;
        System.out.println(min);
    }
}
