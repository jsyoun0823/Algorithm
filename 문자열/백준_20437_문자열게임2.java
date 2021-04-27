import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_20437_문자열게임2 {

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
            int sol1 = sol1(W, K);

            if(sol1 == -1) {
                sb.append(-1).append('\n');
                continue;
            }

            sb.append(sol1).append(' ');
            sb.append(sol2(W, K)).append('\n');
        }

        System.out.println(sb);
    }
//
//    private static int sol1(String W, int K) {
//        Map<Character, Queue<Integer>>  map = new HashMap<>();
//
//        for (char c = 'a'; c <= 'z'; c++) {
//            map.put(c, new LinkedList<>());
//        }
//
//        int min = INF;
//
//        for (int i = 0; i < W.length(); i++) {
//            char c = W.charAt(i);
//            Queue<Integer> temp = map.get(c);
//
//            if(!temp.isEmpty() && temp.size() + 1 == K) {
//                min = Math.min(min, i - temp.poll() + 1);
//            }
//
//            temp.add(i);
//            map.put(c, temp);
//        }
//        return min == INF? -1 : min;
//    }
//     어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이
    private static int sol1(String W, int K) {
        Map<Character, Integer> map = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();

        int min = INF;
        for (int i = 0; i < W.length(); i++) {
            char c = W.charAt(i);

            while(map.containsKey(c) && map.get(c) + 1 == K) {
                min = Math.min(min, queue.size() + 1);

                char r = queue.poll();
                int cnt = map.get(r);
                if(cnt >= 2) {
                    map.put(r, cnt - 1);
                } else {
                    map.remove(r);
                }
            }

            map.put(c, map.getOrDefault(c, 0) + 1);
            queue.offer(c);
        }

        // 만족하는 연속 문자열이 없을 시 -1을 출력
        return min == INF? -1 : min;
    }

    // 어떤 문자를 정확히 K개를 포함하고,
    // 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이
    private static int sol2(String W, int K) {
        Map<Character, Queue<Integer>>  map = new HashMap<>();

        for (char c = 'a'; c <= 'z'; c++) {
            map.put(c, new LinkedList<>());
        }

        int max = 0;
        for (int i = 0; i < W.length(); i++) {
            char c = W.charAt(i);
            //  queue<integer> idx, count
            // s -> 0  // 1
            // u -> 7, 8, 9  // 3
            // p -> 1 1      // 3

            // count == K
            // Math.max(max, i - left)

            // K == 3
            // s u p e r a q u u u
            // s u p e r a q u
            // 1 1 1 1 1 1 1 1

            Queue<Integer> temp = map.get(c);

            if(!temp.isEmpty() && temp.size() + 1 == K) {
                max = Math.max(max, i - temp.poll() + 1);
            }
            temp.add(i);
            map.put(c, temp);
        }
        return max;
    }
}
