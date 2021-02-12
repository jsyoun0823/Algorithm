import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 백준_2605_줄세우기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] s = new int[n + 1];
		LinkedList<Integer> list = new LinkedList<Integer>();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			int cur = s[i];
			if (cur == 0) {
				list.add(i);
			} else {
				list.add(i - cur - 1, i);
			}
		}

		for (int l : list) {
			System.out.print(l + " ");
		}
	}
}
