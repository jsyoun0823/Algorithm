import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_2615_오목 {

    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        map = new int[30][30];
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            } // 검바 1, 흰바 2, 빈칸 0
        }
        // 검:1, 흰:2, 승부결정안나면 0
        // 이겼을 경우에는 가장 왼쪽 위에 있는 바둑알 r, c 출력

        boolean flag = true;
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if(map[i][j] != 0) {
                    if(vertical(i, j) || horizontal(i, j) || leftDiagonal(i, j) || rightDiagonal(i, j)) {
                        sb.append(map[i][j]).append('\n').append(i + 1).append(' ').append(j + 1);
                        flag = false;
                        break;
                    }
                }
            }
        }
        if(flag) sb.append(0);
        System.out.println(sb);
    }

    // /
    private static boolean rightDiagonal(int i, int j) {
        for (int k = 1; k < 5; k++) {
            if (!chkBound(i-k, j+k) || map[i][j] != map[i - k][j + k]) return false;
        }
        if(chkBound(i-5, j+5) && map[i-5][j+5] == map[i][j]) return false;
        if(chkBound(i+1, j-1) && map[i+1][j-1] == map[i][j]) return false;
        return true;
    }

    // \
    private static boolean leftDiagonal(int i, int j) {
        for (int k = 1; k < 5; k++) {
            if(!chkBound(i+k, j+k) || map[i][j] != map[i + k][j + k]) return false;
        }
        if(chkBound(i+5, j+5) && map[i+5][j+5] == map[i][j]) return false;
        if(chkBound(i-1, j-1) && map[i-1][j-1] == map[i][j]) return false;
        return true;
    }

    private static boolean horizontal(int i, int j) {
        for (int k = 1; k < 5; k++) {
            if(!chkBound(i+k, j) || map[i][j] != map[i + k][j]) return false;
        }
        if(chkBound(i + 5, j) && map[i + 5][j] == map[i][j]) return false;
        if(chkBound(i - 1, j) && map[i - 1][j] == map[i][j]) return false;
        return true;
    }

    private static boolean vertical(int i, int j) {
        for (int k = 1; k < 5; k++) {
            if(!chkBound(i, j+k) || map[i][j] != map[i][j + k]) return false;
        }
        if(chkBound(i, j+5) && map[i][j+5] == map[i][j]) return false;
        if(chkBound(i, j-1) && map[i][j-1] == map[i][j]) return false;
        return true;
    }

    private static boolean chkBound(int i, int j) {
        return (i >= 0 && i < 19 && j >= 0 && j < 19);
    }
}
