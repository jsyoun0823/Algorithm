package Hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class 프로그래머스_위장 {
	
    public static void solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<clothes.length; i++) {
            String key = clothes[i][1];
            String val = clothes[i][0];

            if(map.containsKey(key)) {
                map.replace(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        
        for(int val : map.values()) {
            answer *= (val + 1);
        }
        
        System.out.println(answer - 1);
    }
}