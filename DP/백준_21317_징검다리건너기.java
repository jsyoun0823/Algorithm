import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_21317_징검다리건너기 {

    static int N, K, cost[];
    static boolean used = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] jumpCost = new int[N + 3][4];

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            jumpCost[i][1] = s;
            jumpCost[i][2] = b;
        }

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < N-1; i++) jumpCost[i][3] = K;
        cost = new int[N + 3];
        Arrays.fill(cost, 0);

        System.out.println(jump(jumpCost));

    }

    private static int jump(int[][]  jumpCost) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0}); // idx:위치, 0 : 점프안함, 1: 점프함
        cost[0] = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            boolean usedJump = (cur[1] != 0);
            int idx = cur[0];

            if (idx >= N) break;

            for (int i = 1; i <= 3; i++) {
                if (i == 3 && usedJump) {
                    break;
                }

                if (cost[idx + i] == 0 || (cost[idx + i] > cost[idx] + jumpCost[idx][i])) {
                    cost[idx + i] = cost[idx] + jumpCost[idx][i];

                    if (i == 3) {
                        q.offer(new int[]{idx + i ,1});
                    } else {
                        q.offer(new int[]{idx + i, 0});
                    }
                }
            }
        }

        return cost[N-1];
    }
}
