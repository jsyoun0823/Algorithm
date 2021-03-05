import java.util.*;

public class 보석쇼핑 {

    public static void main(String[] args) {
        String[] gems = new String[] {"XYZ", "XYZ", "XYZ"};

        int[] answer = new int[2];
        int len = gems.length;

        Set<String> gem = new HashSet<>();
        for (String g:gems) {
            gem.add(g);
        }
        int gemSize = gem.size();

        Set<String> select = new HashSet<>();
        Map<String, Integer> count = new HashMap<>();

        int left = 0;
        int min = len;

        // gem을 넣을때 count도 세서 같이 넣는다!
        for (int i = 0; i < len; i++) {
            String cur = gems[i];
            select.add(cur);
            count.put(cur, count.getOrDefault(cur, 0) + 1);

            while(select.size() == gemSize) {
                if(min > i-left) {
                    min = i - left;
                    answer[0] = left + 1;
                    answer[1] = i + 1;
                }

                if(count.get(gems[left]) <= 1){
                    select.remove(gems[left]);
                    count.remove(gems[left]);
                } else {
                    count.put(gems[left], count.get(gems[left]) - 1);
                }
                left++;
            }
        }
        System.out.println(Arrays.toString(answer));
    }
}
