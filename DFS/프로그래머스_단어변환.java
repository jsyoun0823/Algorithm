public class 프로그래머스_단어변환 {
	public static int change = 0;
	public static boolean[] visited;
	public static int answer;

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		answer = 51;
		visited = new boolean[words.length];
		dfs(begin, target, words, 0);
		System.out.println(answer);

	}

	public static void dfs(String cur, String target, String[] words, int changeCnt) {
		if (cur.equals(target)) {
			answer = Math.min(changeCnt, answer);
			return;
		}
		for (int i = 0; i < words.length; i++) {
			if (!visited[i] && isOneDiff(cur, words[i])) {
				visited[i] = true;
				dfs(words[i], target, words, changeCnt+1);
				visited[i] = false;
			}
		}
	}

	public static boolean isOneDiff(String str1, String str2) {
		int cnt = 0;
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) cnt++;
		}
		return (cnt == 1) ? true : false;
	}
}
