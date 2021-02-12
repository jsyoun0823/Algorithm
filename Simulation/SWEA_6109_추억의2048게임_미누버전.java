import java.util.*;

public class SWEA_6109_추억의2048게임_미누버전 {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            String S = sc.next();

            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int[][] res = new int[N][N];

            if ("left".equals(S)) {

                for (int i = 0; i < N; i++){
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == 0) continue;

                        // 2 4 0 0 0
                        // j == 0
                        int index = j + 1;

                        while(index < N && map[i][index] == 0) {
                            index++;
                        }

                        if (index >= N || map[i][index] == 0) continue;

                        if (map[i][j] == map[i][index]) {
                            map[i][j] *= 2;
                            map[i][index] = 0;
                            j = index;
                        }
                    }

                    int index = 0;
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] != 0)
                            res[i][index++] = map[i][j];
                    }
                }
            } else if ("right".equals(S)) {
                for (int i = 0; i < N; i++){
                    for (int j = N - 1; j >= 0; j--) {
                        if (map[i][j] == 0) continue;

                        int index = j - 1;

                        while(index >= 0 && map[i][index] == 0) {
                            index--;
                        }

                        if (index < 0 || map[i][index] == 0) continue;

                        if (map[i][j] == map[i][index]) {
                            map[i][j] *= 2;
                            map[i][index] = 0;
                            j = index;
                        }
                    }

                    int index = N - 1;
                    for (int j = N - 1; j >= 0; j--) {
                        if (map[i][j] != 0)
                            res[i][index--] = map[i][j];
                    }
                }

            } else if ("up".equals(S)) {
                for (int i = 0; i < N; i++){
                    for (int j = 0; j < N; j++) {
                        if (map[j][i] == 0) continue;

                        int index = j + 1;

                        while(index < N && map[index][i] == 0) {
                            index++;
                        }

                        if (index >= N || map[index][i] == 0) continue;

                        if (map[j][i] == map[index][i]) {
                            map[j][i] *= 2;
                            map[index][i] = 0;
                            j = index;
                        }
                    }

                    int index = 0;
                    for (int j = 0; j < N; j++) {
                        if (map[j][i] != 0)
                            res[index++][i] = map[j][i];
                    }
                }
            } else {
                for (int i = 0; i < N; i++){
                    for (int j = N - 1; j >= 0; j--) {
                        if (map[j][i] == 0) continue;

                        int index = j - 1;

                        while(index >= 0 && map[index][i] == 0) {
                            index--;
                        }

                        if (index < 0 || map[index][i] == 0) continue;

                        if (map[j][i] == map[index][i]) {
                            map[j][i] *= 2;
                            map[index][i] = 0;
                            j = index;
                        }
                    }

                    int index = N - 1;
                    for (int j = N - 1; j >= 0; j--) {
                        if (map[j][i] != 0)
                            res[index--][i] = map[j][i];
                    }
                }

            }

            System.out.printf("#%d\n", test_case);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++){
                    System.out.printf("%d ", res[i][j]);
                }
                System.out.println();
            }

        }
    }
}