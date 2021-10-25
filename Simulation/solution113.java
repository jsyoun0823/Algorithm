// You may use import as below.
//import java.util.*;

public class solution113 {
    public static int solution(String pos) {
        // Write code here.
        int answer = 0;
        int r = pos.charAt(1) - '0';
        int c = pos.charAt(0) - 'A';
        int[][] dir = {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};
        for (int i = 0; i < dir.length; i++) {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];

            if(nr > 0 && nr <= 8 && nc > 0 && nc <= 8) answer++;
        }
        return answer;
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
        String pos = "A7";
        int ret = solution(pos);

        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret + " .");
    }
}