package kruskal;

import java.util.*;

public class 백준_4386_별자리만들기 {
    public static class Star {
        int no;
        double x, y;

        public Star(int no, double x, double y) {
            this.no = no;
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int from, to;
        double cost;

        public Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return (int)this.cost - (int)edge.cost;
        }
    }

    static int[] parents;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 별의 개수 (1 ≤ n ≤ 100)

        List<Star> stars = new ArrayList<>(); // 별들의 좌표 리스트

        for (int i = 0; i < n; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            stars.add(new Star(i, x, y)); // 별의 x, y좌표
        }

        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double cost = getDis(stars.get(i), stars.get(j));

                edgeList.add(new Edge(i, j, cost));
            }
        }

        Collections.sort(edgeList);

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        double sum = 0;
        int cnt = 0;
        for (Edge e : edgeList) {
            if(union(e.from, e.to)) {
                sum += e.cost;
                if(cnt++ == n-1) break;
            }
        }

        System.out.printf("%.2f\n", sum);
    }

    // 선을 하나 이을 때마다 두 별 사이의 거리만큼의 비용이 든다
    private static double getDis(Star a, Star b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        else {
            parents[bRoot] = parents[aRoot];
            return true;
        }
    }

    private static int find(int a) {
        if(parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }
}
