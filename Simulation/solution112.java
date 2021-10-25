// You may use import as below.
//import java.util.*;

public class solution112 {

    private static int solution(int n) {
        // 1  2  3  4
        // 12 13 14 5
        // 11 16 15 6
        // 10 9  8  7
        int[][] map = new int[n][n];
        int row = 0, col = -1, dir = 1, num = 1;
        while(true) {
            for (int i = 0; i < n; i++) {
                col += dir;
                map[row][col] = num++;
            }
            if(n == 0) break;
            n--;
            for (int j = 0; j < n; j++) {
                row += dir;
                map[row][col] = num++;
            }
            dir *= (-1);
        }

        int sum = 0;
        for (int i = 0, j = 0; i < map.length; i++, j++) {
            sum += map[i][j];
        }
        return sum;
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
        int n1 = 3;
        int ret1 = solution(n1);

        
        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret1 + " .");
        
        int n2 = 2;
        int ret2 = solution(n2);
        
        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret2 + " .");
    }
}