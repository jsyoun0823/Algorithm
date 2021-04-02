import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1629_곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long answer = 1l;
        A %= C;
        while (B > 0) {
            if (B % 2 == 1) {
                answer *= A;
                answer %= C;
            }
            A *= A;
            A %= C;

            B >>= 1;
        }

        System.out.println(answer);
    }
}
