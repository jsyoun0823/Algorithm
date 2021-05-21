package kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_1197_최소스패닝트리 {

    static int V, E, parents[];
    static List<int[]> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점의 개수 V(1 ≤ V ≤ 10,000)
        E = Integer.parseInt(st.nextToken()); // 간선의 개수 E(1 ≤ E ≤ 100,000)

        edgeList = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new int[] {from, to, cost});
        }

        Collections.sort(edgeList, (a, b) -> a[2] - b[2]);

        parents = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parents[i] = i;
        }

        int sum = 0;
        for (int[] edge : edgeList) {
            if(union(edge[0], edge[1])) {
                sum += edge[2];
            }
        }

        System.out.println(sum);
    }


    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = parents[aRoot];
        return true;
    }

    private static int findSet(int a) {
        if(parents[a] == a) return a;

        return parents[a] = findSet(parents[a]);
    }
}
