import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_2292_벌집_ver2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans;

        if(N == 1) ans = 1;
        else {
            int cnt = 1;
            int range = 1;
            while (range < N) {
                range += (cnt * 6);
                cnt++;
            }
            ans = cnt;
        }

        System.out.println(ans);
    }
}
