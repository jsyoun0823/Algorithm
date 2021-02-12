package Queue;
import java.util.*;

public class 프로그래머스_프린터_미누버전 {
    public int solution(int[] priorities, int location) {
        int[] prior = new int[10];
        // 5 0 0 0 0 0 0 0 0 0 1
        Queue<int[]> queue = new LinkedList<>();
        
        int len = priorities.length;
        
        for (int i = 0; i < len; i++) {
            prior[priorities[i]]++;
            queue.add(new int[]{priorities[i], i});
        }
        
        int cnt = 0;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            boolean isLarge = false;
            for (int i = current[0] + 1; i < 10; i++) {
                if (prior[i] > 0) {
                    queue.add(current);
                    isLarge = true;
                    break;
                }
            }
            
            if (isLarge) continue;
            prior[current[0]]--;
            
            cnt++;
            
            if (current[1] == location) return cnt;
        }
        return cnt;
	}
}