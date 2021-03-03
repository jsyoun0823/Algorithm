import java.util.*;

public class 보석쇼핑 {
    static int gemSize, min, len;
    static String[] gems;
    public static void main(String[] args) {
        gems = new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        int[] answer = new int[2];
        len = gems.length; // 8

        Set<String> gem = new HashSet<>();
        for (String g:gems) {
            gem.add(g);
        }
        gemSize = gem.size(); // 4

        if(gemSize == 1) {
            answer[0] = answer[1] = 1;
//            return answer;
        }

        Set<String> set = new HashSet<>();
        for (int k = 0; k < gemSize; k++) {
            set.add(gems[k]);
        } // 적어도 4종류면 4개는 넣고 시작~

        min = len;
        for (int k = 0; k < len - gemSize - 1; k++) {
            select(k, k + gemSize, gemSize ,set);
        }

        System.out.println(min);
    }

    public static void select(int start, int end, int cnt, Set<String> set) {
        if(gemSize == set.size()) {
            min = Math.min(min, cnt);
            return;
        }

        if(end >= len) return;

        set.add(gems[end]);
        select(start, end + 1, cnt + 1, set);
        set.remove(gems[end]);
    }
}
