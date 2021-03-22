import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DFS, 백트래킹
 * */
public class 백준_10971_외판원순회2 {
    private static int N;
    private static int[][] cost;
    private static boolean[] visited; // 방문처리용 배열
    private static int min, sum; // 최소 비용

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim()); // 도시의 수 (2 ≤ N ≤ 10)
        cost = new int[N][N]; // 도시 간 이동 비용이 저장될 배열
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                cost[i][j]= Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        for (int i = 1; i < N; i++) {
            visited = new boolean[N];
            sum = 0;
            if(cost[0][i] != 0)
                dfs(0, i, 1); // dfs 탐색 시작
        }
        System.out.println(min);
    } // end of main

    public static void dfs(int r, int c, int cnt) {
        if(sum > min) return; // 임시해보다 크면 return

        sum += cost[r][c]; // 비용 누적
        visited[r] = true; // 방문 체크

        if(cnt == N-1) { // 다 구했으면
            sum += cost[c][0]; // 다시 돌아가는 경우의 비용을 더해주기
            if(cost[c][0] == 0) return; // 이때 돌아가는 길이 이어져 있지 않으면 리턴
            min = (min > sum) ? sum : min; // 최소값 갱신
            sum -= cost[c][0]; // 다음 계산을 위해 원복해주기
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!visited[i] // 아직 방문하지 않았고
                    && cost[c][i] != 0 // 길이 이어져있고,
                    && sum < min) { // 현재까지 sum 값이 임시해보다 크지 않다면 (가지치기)
                dfs(c, i, cnt + 1); // 다음으로 재귀호출
                sum -= cost[c][i]; // 다음 경우의수를 위해 마찬가지로 다시 비용 원복해주기
                visited[i] = false; // 방문 해제
            }
        }
    } // end of dfs
} // end of class
