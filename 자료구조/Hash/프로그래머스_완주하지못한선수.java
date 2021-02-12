package Hash;

import java.util.HashMap;
import java.util.Iterator;

public class 프로그래머스_완주하지못한선수 {

	public static void main(String[] args) {
		String[] participant = {"marina", "josipa", "nikola", "filipa", "marina"};
		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		
        String answer = "";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for (String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        
        for (String name : completion) {
        	int temp = map.get(name);
            map.put(name, temp - 1);
        }
        
        Iterator<String> mapIter = map.keySet().iterator();
        while(mapIter.hasNext()){
            String key = mapIter.next();
            int value = map.get(key);
            
            if(value > 0) answer += key;
        }
        
        System.out.println(answer);
	}

}
