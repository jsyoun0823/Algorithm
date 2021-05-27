import java.util.Scanner;

public class 백준_10775_공항 {

    static int[] parents;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt(); // 게이트의 수 G (1 ≤ G ≤ 105)   4
        int P = sc.nextInt(); // 비행기의 수 P (1 ≤ P ≤ 105)   3
        
        parents = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parents[i] = i;
        }

        int gate, cnt = 0;
        for (int i = 0; i < P; i++) {
            gate = sc.nextInt();
            
            int gateRoot = findSet(gate);
            
            if(gateRoot == 0) break;
            
            unionSet(gateRoot, gateRoot - 1);
            cnt++;
        }

        System.out.println(cnt);
    }

    private static void unionSet(int gateA, int gateB) {
        int aRoot = findSet(gateA);
        int bRoot = findSet(gateB);

        if(aRoot != bRoot) {
            parents[aRoot] = parents[bRoot];
        }
    }

    private static int findSet(int gate) {
        if(parents[gate] == gate) return gate;

        return parents[gate] = findSet(parents[gate]);
    }
}