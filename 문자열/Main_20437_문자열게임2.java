import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main_20437_문자열게임2 {
    private static final int INF = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String W;
        int K;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            if (K == 1) {
                sb.append(1).append(" ").append(1).append('\n');
                continue;
            }

            sb.append(sol(W, K)).append('\n');
        }

        System.out.println(sb);
    }

    private static String sol(String W, int K) {
        Map<Character, Queue<Integer>>  map = new HashMap<>();

        for (char c = 'a'; c <= 'z'; c++) {
            map.put(c, new LinkedList<>());
        }

        int max = 0;
        int min = INF;
        for (int i = 0; i < W.length(); i++) {
            char c = W.charAt(i);

            Queue<Integer> temp = map.get(c);

            if(!temp.isEmpty() && temp.size() + 1 == K) {
                int pp = temp.poll();
                max = Math.max(max, i - pp + 1);
                min = Math.min(min, i - pp + 1);
            }

            temp.add(i);
            map.put(c, temp);
        }

        if(min == INF) return "-1";
        else return String.valueOf(min) + ' ' + max;
    }
}
