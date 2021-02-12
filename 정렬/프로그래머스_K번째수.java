import java.util.*;

public class 프로그래머스_K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int k=0; k<commands.length; k++) {
            int[] comm = commands[k];
            int[] temp = new int[comm[1] - comm[0] + 1];
            System.arraycopy(array, comm[0]-1, temp, 0, comm[1] - comm[0]+1);
            Arrays.sort(temp);
            answer[k] = temp[comm[2]-1];
        }
        return answer;
    }
}