import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_13023_ABCDE {
    private static int N, M, ans;
    private static List<Integer>[] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a].add(b);
            map[b].add(a);
        }
        
        ans = 0;
        for(int i = 0; i < N; i++) {
            if(ans == 1) break;
            dfs(new boolean[N], 0, i);
        }

        System.out.println(ans);
    }

    private static void dfs(boolean[] visited, int cnt, int num) {
        if(ans == 1) return; // 답 구하면 return

        if(cnt >= 5) {
            ans = 1;
            return;
        }

        for(int i = 0; i < map[num].size(); i++) {
            int x = map[num].get(i);

            if(!visited[x]) {
                visited[x] = true;
                dfs(visited,cnt + 1, x);
                visited[x] = false;
            }
        }
    }
}
