import java.util.Scanner;

public class 백준_10830_행렬제곱 {
    private static int N;
    private static int[][] ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int B = sc.nextInt();
        int[][] A = new int[N][N];
        ans = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = sc.nextInt() % 1000;
                ans[i][j] = 1;
            }
        }

        while(B > 0) {
            if(B % 2 == 1) {
                mul(ans, A);
            } else {
                mul(A, A);
            }
            B /= 2;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static int[][] mul(int[][] a, int[][] b) {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    temp[i][j] += a[i][k] * b[k][j];
                }
                temp[i][j] %= 1000;
            }
        }

        return ans = temp;
    }
}
