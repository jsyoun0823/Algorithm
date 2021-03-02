package FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1956_운동 {
    public static void main(String[] args) throws IOException {
        final int INF = 100000;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] d = new int[V+1][V+1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                d[i][j] = INF;
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            d[start][end] = dist;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if(d[i][k] == INF || d[k][j] == INF) continue;
                    if(d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }

        int min = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if(i != j && d[i][j] != INF && d[j][i] != INF) {
                    min = Math.min(min, d[i][j] + d[j][i]);
                }
            }
        }

        if(min == INF) System.out.println(-1);
        else System.out.println(min);

    }
}
