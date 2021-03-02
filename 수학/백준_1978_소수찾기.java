import java.util.Scanner;

public class 백준_1978_소수찾기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();

            if(isPrime(num)) cnt++;
        }
        System.out.println(cnt);
    }

    public static boolean isPrime(int num) {
        if(num == 1) return false;

        for (int i = 2; i < num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
