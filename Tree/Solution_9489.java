import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_9489 {

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
//                new Scanner(System.in);
        int[] levelCount = new int[1001];
        int[] nodes = new int[1001];

        while(true) {

            StringTokenizer st = new StringTokenizer(sc.readLine(), " ");
//            String[] str = sc.readLine().split(" ");
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(n == 0 && k == 0) break;

            for (int i = 0; i < 1001; i++) {
                levelCount[i] = 0;
                nodes[i] = 0;
            }

            int kIndex = 0;
            st = new StringTokenizer(sc.readLine(), " ");

            for (int i = 0; i < n; i++) {
                nodes[i] = Integer.parseInt(st.nextToken());

                if (nodes[i] == k) {
                    kIndex = i;
                }
            }

            int level = 0;
            int prev = nodes[0];
            levelCount[level] = 1;
            level++;

            int cnt = 0;
            int currentGroup = 0;
            int levelK = 0;
            for (int i = 1; i < n; i++) {
                int cur = nodes[i];

                if (cur == k) levelK = level;
                if (prev + 1 < cur) {
                    if (levelCount[level - 1] <= currentGroup) {
                        levelCount[level] = cnt;
                        cnt = 0;
                        level++;
                        currentGroup = 0;
                    }
                    currentGroup++;
                }

                prev = cur;
                cnt++;
            }

            levelCount[level] = cnt;

            if (levelCount[levelK] == 1) {
                System.out.println(0);
            }

            prev = k;
            int sameParent = 0;
            int idx = kIndex - 1;
            while(idx >= 0) {
                int pre = nodes[idx--];

                if (pre != prev - 1) {
                    break;
                }

                sameParent++;
                prev--;
            }

            idx = kIndex + 1;
            prev = k;

            while(idx < n) {
                int ne = nodes[idx++];

                if (ne != prev + 1) {
                    break;
                }

                sameParent++;
                prev++;
            }

            System.out.println(levelCount[levelK] - sameParent - 1);


        }
    }
}
