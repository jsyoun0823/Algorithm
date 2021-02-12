import java.util.*;
public class VowelStrings {

	public String vowelString(String S, int K) {
		Queue<Character> queue = new LinkedList<>();
		Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
		
		int count = 0;
		int maxCount = 0;
		int minIndex = 0;
		
		char[] input = S.toCharArray();
		
		int index = 0;
		
		for (Character c : input) {
			queue.add(c);
			
			if (queue.size() > K) {
				char head = queue.poll();
				if (vowels.contains(head)) {
					count = Math.max(0, count - 1);
				}
			}
			
			if (vowels.contains(c)) {
				count++;
				
				if (maxCount < count) {
					maxCount = count;
					minIndex = Math.min(minIndex, index - K + 1);
				}
				
			}
			
			index++;
		}
		
		if (maxCount == 0) return "Not found!";
		else return S.substring(minIndex, minIndex + K);
	}
}
