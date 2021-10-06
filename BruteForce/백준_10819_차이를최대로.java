import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_10819_차이를최대로 {

    static int N, nums[], max, selectNums[];
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        max = 0;
        selectNums = new int[N];
        visited = new boolean[N];
        select(0);
        System.out.println(max);
    }

    private static void select(int cnt) {
        if(cnt == N) {
            max = Math.max(max, cal());
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selectNums[cnt] = nums[i];
                select(cnt + 1);
                visited[i] = false;
            }
        }
    }

    private static int cal() {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(selectNums[i] - selectNums[i + 1]);
        }
        return sum;
    }

}
