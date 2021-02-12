public class 프로그래머스_타겟넘버 {

	static int N, target, answer;
	static int[] numbers;

	public static void main(String[] args) {
		numbers = new int[] { 1, 1, 1, 1, 1 };
		target = 3;
		N = numbers.length;
		answer = 0;

		dfs(0, 0);

		System.out.println(answer);
	}

	public static void dfs(int cnt, int sum) {
		if (cnt >= N) {
			if (sum == target) {
				answer++;
			}
			return;
		}

		dfs(cnt + 1, sum + numbers[cnt]);
		dfs(cnt + 1, sum - numbers[cnt]);

	}
}
