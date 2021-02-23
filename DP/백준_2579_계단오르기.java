import java.util.Scanner;

/*
DP문제
    계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
    연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
    마지막 도착 계단은 반드시 밟아야 한다.
*/
public class 백준_2579_계단오르기 {
    public static void main(String[] args) {
        int[] score = new int[301]; // 각 계단에 쓰여 있는 점수 (1<= <=10000)
        int[] D = new int[301]; // 점수 최대값 저장할 배열

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 계단 개수 (1<= <=300)
        for (int i = 0; i < n; i++) {
            score[i] = sc.nextInt();
        }

        D[0] = score[0]; // 1칸 간 경우
        D[1] = Math.max(score[1], score[0] + score[1]); // 1칸+1칸, 2칸 중 최대
        D[2] = Math.max(score[0] + score[2], score[1] + score[2]); // 1칸+2칸, 2칸+1칸 중 최대

        // 두 가지 중 최대값
        // 1) 전전계단 까지 최대 + 현재
        // 2) 전전전계단 까지 최대 + 전계단 + 현재
        for (int i = 3; i < n; i++) {
            D[i] = Math.max(D[i-2] + score[i], D[i-3] + score[i-1] + score[i]);
        }

        System.out.println(D[n-1]); // 마지막 계단까지의 최대값
    }
}
