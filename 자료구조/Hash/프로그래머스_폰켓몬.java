package Hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class 프로그래머스_폰켓몬 {
    public static int solution(int[] nums) {
        int answer = 0;

        int n = nums.length / 2;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        if(set.size() > n) answer = n;
        else answer = set.size();

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] {3, 1, 2, 3}));

        HashMap<String, Integer> map = new HashMap<>();

        while(!map.isEmpty()) {

        }
    }
}