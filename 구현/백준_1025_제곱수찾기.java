import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 백준_1025_제곱수찾기 {
    public static void main(String[] args) throws IOException {

        // 행의 숫자가 등차수열
        // 열의 숫자도 등차수열
        // 서로 다른 칸의 수열의 수 모두 이어 붙임
        // 이 중 가장 큰 제곱수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] num = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                num[i][j] = line.charAt(j) - '0';
            }
        }

        int ans = -1;
        // 모든 행, 열 등차값 탐색
        // 등차값 -N ~ N, -M ~ M
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = -N; k < N; k++) {
                    for (int l = -M; l < M; l++) {
                        if(k == 0 && l == 0) continue; // 둘다 그대로인 경우는 건너뛰기

                        int ni = i, nj = j;
                        String temp = "";
                        while(ni >= 0 && ni < N && nj >= 0 && nj < M) {
                            temp += num[ni][nj];
                            int iTmp = Integer.parseInt(temp);

                            if(isSqrt(iTmp)) ans = Math.max(iTmp, ans);

                            ni += k;
                            nj += l;
                        }
                    }
                }
            }
        }

        System.out.println(ans);

        // 2, 3
        // k = -2, -1, 1, 2, 3 ~
        // l = -3, -2, -1 ~
        // 만약에 k = 0, l = -2 면
        // (2, 3) (2, 1) 이렇게 됨
        //

    }

    // 제곱수인지 판별하는 함수
    public static boolean isSqrt(int num) {
        int temp;
        switch (num & 0x0f) {
            case 0: case 1: case 4: case 9:
                temp = (int) (Math.sqrt((double) num) + 0.5);
                return temp * temp == num;
            default:
                return false;
        }
    }
}

// 12345
// 67890

// (1,1) (1,3) (1,5) -> 135
// (2,2) (2,3) (2,4) (2,5) -> 7890
// (2,4) (2,3) -> 98

// 문제에서
// 123
// 456
// (2,3) (2,1) -> 64


