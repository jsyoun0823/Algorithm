import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1912_연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] d = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = d[i] = Integer.parseInt(st.nextToken());
        }

        d[0] = nums[0];
        for (int i = 1; i < N; i++) {
            if(d[i-1] < 0 || d[i] > d[i-1] + nums[i]) continue; // 이전까지 합이 음수거나, 지금까지 누적합+현재값 더해서 원값보다 작으면 넘어
            d[i] = d[i-1] + nums[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, d[i]);
        }

        System.out.println(max);
    }
}
