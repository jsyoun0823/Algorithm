import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_14002_가장긴증가하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] LIS = new int[N];
        int max = 0;
        int maxIdx = 0;
        for (int i = 0; i < N; i++) {
            LIS[i] = 1; // 나 까지..
            for (int j = 0; j < i; j++) {
                if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                }
            }
            if(max < LIS[i]) {
                max = LIS[i];
                maxIdx = i;
            }
        }

        Stack<Integer> stack = new Stack<>();
        int cur = max;
        for (int i = maxIdx; i >= 0; i--) {
            if(LIS[i] == cur) {
                stack.push(arr[i]);
                cur--;
            }
        }

        System.out.println(max);
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
