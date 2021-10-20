import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_9613_GCD합 {

    static int n, nums[];
    static long sum; // int면 초과하니까 long으로 하는거 주의!!
    static boolean[] select;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            nums = new int[n];
            select = new boolean[n];
            for (int j = 0; j < n; j++) {
                nums[j] = Integer.parseInt(st.nextToken());
            }
            sum = 0;
            comb(0, 0);
            sb.append(sum).append('\n');
        }
        System.out.println(sb);
    }
    public static void comb(int start, int cnt) {
        if(cnt == 2) {
            int[] ab = new int[2];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if(select[i]) ab[idx++] = nums[i];
            }
            sum += GCD(ab[0], ab[1]);
            return;
        }
        for(int i = start; i < n; i++) {
            select[i] = true;
            comb(i + 1, cnt + 1);
            select[i] = false;
        }
    }

    public static int GCD(int a, int b) {
        if(b == 0) return a;
        else return GCD(b, a % b);
    }
}

// 4 10 => 2
// 4 20 => 4
// 4 30 => 2

// 4 10
// 4 6
// 4 2
// 4 0
//