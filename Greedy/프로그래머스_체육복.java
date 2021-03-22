import java.util.Arrays;

public class 프로그래머스_체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        for(int i=0; i<lost.length; i++) {
            int cur = lost[i];
            for(int j=0; j<reserve.length; j++) {
                if(cur == reserve[j]) {
                    lost[i] = -1;
                    reserve[j] = -1;
                    answer++;
                    break;
                }
            }
        }
        int[] arr = new int[5];
        Arrays.fill(arr, 3);

        for(int i=0; i<lost.length; i++) {
            int cur = lost[i];
            for(int j=0; j<reserve.length; j++) {
                if(cur-1 == reserve[j] || cur+1 == reserve[j]) {
                    reserve[j] = -1;
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}
