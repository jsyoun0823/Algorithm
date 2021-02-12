/**
 * Level 3 이분탐색 - 입국심사
 * Parametric Search 
 * 
 * 시간을 기준으로 특정 시간을 주고 그 시간에 해결이 가능한지 검사할 것.
 * 
 * 추정 시간 값/각 심사별 심사시간 = 심사관당 맡을 수 있는 입국자 수
 * 
 * 추정 시간 값을 0초부터 Max초까지, Max 초기화는 심사 시간 최댓값 * 사람 수(가장 최악의 경우)
 * 
 * 30초라고 했을 때, 각 심사관당 시간이 7초 10초일 때 
 * -> 한 심사관 당 30/7 = 4명  , 30/10 = 3명, 더하면 7명
 * 30초 안에 7명 가능하니까 지금 n이랑 비교해서 시간 내에 다하는지, 못하는지를 검사해서
 * 0..29 30 31..max 중 오른쪽 갈지, 왼쪽 갈지 검사 !!!!!
 * 
 * */
public class 프로그래머스_입국심사 {

	public static void main(String[] args) {
		int n = 6;
		int[] times = {7, 10};
		long answer = Long.MAX_VALUE;

        long mid;
        long left = 0;
        long right = 0;
        for (int time : times) {
			if(time > right) right = time;
		}
 
        right *= n;
        
        while (right >= left) {
            long done = 0;
        	mid = (right + left) / 2;
 
            for (int time : times) {
				done += mid/time;
			}
 
            if (done < n) { // 해당 시간동안 심사를 다 하지 못한 경우
            	left = mid + 1;
            } else { // 시간이 여유 있거나, 딱 맞는 경우
                if(mid < answer) answer = mid;
                right = mid - 1;
            }
 
        }
    
        System.out.println(answer);
	}

}



