import java.util.ArrayList;

public class 프로그래머스_완탐_모의고사 {

	public static void main(String[] args) {
//		int[] answers = { 1, 2, 3, 4, 5 };
		int[] answers = { 1, 3,2,4,2 };

		int[] cnt = { 0, 0, 0 };
		int[] supo1 = { 1, 2, 3, 4, 5 };
		int[] supo2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] supo3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == supo1[i % 5])
				cnt[0]++;
			if (answers[i] == supo2[i % 8])
				cnt[1]++;
			if (answers[i] == supo3[i % 10])
				cnt[2]++;
		}

		int max = -1;
		for (int i = 0; i < cnt.length; i++) {
			if(cnt[i] > max) max = cnt[i];
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			if(max == cnt[i]) {
				list.add(i+1);
			}
		}
		
		int[] answer = new int[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}

		System.out.println(list);
//		return answer.stream().filter(i -> i != null).mapToInt(i -> i).toArray();
	}

}
