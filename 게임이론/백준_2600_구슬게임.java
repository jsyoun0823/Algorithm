import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    게임이론
    스프라그-그런디 정리
    참고 : https://casterian.net/archives/1239
          https://steady-coding.tistory.com/190
* */
public class 백준_2600_구슬게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] B = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[501];
        dp[0] = 0;

        boolean[] check = new boolean[4];
        for (int i = 1; i <= 500; i++) {
            Arrays.fill(check, false);

            // 범위에 맞는 다음 상태의 그런디 수를 true로 초기화
            for (int j = 0; j < 3; j++) {
                if (i - B[j] >= 0) {
                    check[dp[i - B[j]]] = true;
                }
            }

            // mex를 구하기
            int mex = 0;
            while(check[mex]) {
                mex++;
            }
            dp[i] = mex;
        }

        int T = 5;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int k1 = Integer.parseInt(st.nextToken());
            int k2 = Integer.parseInt(st.nextToken());

            if ((dp[k1] ^ dp[k2]) == 0) {
                sb.append("B").append('\n');
            } else {
                sb.append("A").append('\n');
            }
        }

        System.out.println(sb);
    }
}
