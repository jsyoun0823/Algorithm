import java.util.*;

public class 카카오메이커스_sol2 {
    class Solution {
        public int[] select;
        public int answer;

        public int solution(int[][] needs, int r) {
            answer = 0;

            select = new int[15];

            int partSize = needs[0].length;

            comb(partSize, r, 0, 0, needs);
            return answer;
        }

        // 최대 15개 중에 r개 골라서
        public void comb(int partSize, int r, int start, int cnt, int[][] needs) {
            if(cnt == r) {
                answer = Math.max(answer, find_max(partSize, needs, r));
                return;
            }

            for(int i = start; i < partSize; i++) {
                select[cnt] = i;
                comb(partSize, r, i + 1, cnt + 1, needs);
            }
        }

        public int find_max(int partSize, int[][] needs, int r) {
            Set<Integer> partSet = new HashSet<>();

            for(int i = 0; i < r; i++) {
                partSet.add(select[i]);
            }

            int buildProduct = 0;
            for(int i = 0; i < needs.length; i++) {
                boolean canBuild = true;
                for(int j = 0; j < partSize; j++) {
                    if(needs[i][j] == 1 && !partSet.contains(j)) {
                        canBuild = false;
                        break;
                    }
                }

                if(canBuild) buildProduct++;
            }

            return buildProduct;
        }
    }
}
