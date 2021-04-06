import java.util.*;

public class 데브매칭_sol1 {

    class Solution {

        public int[] solution(int[] lottos, int[] win_nums) {
            int[] answer = {0, 0};

            int zeroCnt = 0;
            Set<Integer> lottoSet = new HashSet<>();
            for(int num : lottos) {
                lottoSet.add(num);
                if(num == 0) zeroCnt++;
            }

            int cnt = 0;
            for(int num : win_nums) {
                if(lottoSet.contains(num)) {
                    cnt++;
                }
            }

            System.out.println(zeroCnt + " " + cnt);

            switch(cnt) {
                case 6 :
                    answer[0] = answer[1] = 1;
                    break;
                case 5 :
                    if(zeroCnt >= 1) answer[0] = 1;
                    else answer[0] = 2;
                    answer[1] = 2;
                    break;
                case 4 :
                    if(zeroCnt >= 2) answer[0] = 1;
                    else if(zeroCnt == 1) answer[0] = 2;
                    else answer[0] = 3;
                    answer[1] = 3;
                    break;
                case 3:
                    if(zeroCnt >= 3) answer[0] = 1;
                    else if(zeroCnt == 2) answer[0] = 2;
                    else if(zeroCnt == 1) answer[0] = 3;
                    else answer[0] = 4;
                    answer[1] = 4;
                    break;
                case 2:
                    if(zeroCnt >= 4) answer[0] = 1;
                    else if(zeroCnt == 3) answer[0] = 2;
                    else if(zeroCnt == 2) answer[0] = 3;
                    else if(zeroCnt == 1) answer[0] = 4;
                    else answer[0] = 5;
                    answer[1] = 5;
                    break;
                case 1 :
                    if(zeroCnt >= 5) answer[0] = 1;
                    else if(zeroCnt == 4) answer[0] = 2;
                    else if(zeroCnt == 3) answer[0] = 3;
                    else if(zeroCnt == 2) answer[0] = 4;
                    else if(zeroCnt == 1) answer[0] = 5;
                    else answer[0] = 6;
                    answer[1] = 6;
                    break;
                case 0 :
                    if(zeroCnt == 6) answer[0] = 1;
                    else if(zeroCnt == 5) answer[0] = 2;
                    else if(zeroCnt == 4) answer[0] = 3;
                    else if(zeroCnt == 3) answer[0] = 4;
                    else if(zeroCnt == 2) answer[0] = 5;
                    else answer[0] = 6;
                    answer[1] = 6;
                    break;

            }

            return answer;
        }

    }
}
