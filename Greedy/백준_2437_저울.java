import java.util.Arrays;
import java.util.Scanner;

public class 백준_2437_저울 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[] weight = new long[N];
        for (int i = 0; i < N; i++) {
            weight[i] = sc.nextLong();
        }
        Arrays.sort(weight);

        if(weight[0] != 1) System.out.println(1);
        else {
            long sum = 0;
            for (int i = 0; i < N; i++) {
                if(weight[i] > sum + 1) break;
                sum += weight[i];
            }

            System.out.println(sum + 1);
        }

            // 3 1 6 2 7 30 1
            // 4 10 12 19 49 50

            // 1 1 2 3 6 7      30
            // 1 2 4 7 13 20 50

            // 이 무게까지는 주어진 무게추들로 만들 수 있는 최소값
            // 1 2
            // 1 2 3 4
            // 1 2 3 4 5 6 7
            // 1 2 3 4 5 6 7 8 9 10 11 12 13
            // 14 ~~ 20

            // 현재 올리려는 저울추의 무게가, 지금까지 올린 저울추의 총합보다 커지면 저울추의 총합은 측정할 수 없는 최솟값
            // 저울추의 무게 중 가장 가벼운것이 1보다 크면, 1은 측정할 수 없기때문에 1로 초기값을 설정


    }
}
