import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_2309_일곱난쟁이 {

	private static int[] d;
	private static int[] nd;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		d = new int[9];
		nd = new int[7];
		for (int i = 0; i < 9; i++) {
			d[i] = Integer.parseInt(br.readLine());
		}
		find(0, 0);
	}

	public static void find(int cnt, int idx) {
		if (cnt == 7) {
			int sum = 0;
			for (int i = 0; i < nd.length; i++) {
				sum += nd[i];
			}
			if(sum == 100) {
				Arrays.sort(nd);
				for (int n : nd) {
					System.out.println(n);
				}
			}
			return;
		}
		for (int i = idx; i < 9; i++) {
			nd[cnt] = d[i];
			find(cnt + 1, i + 1);
		}
	}

}
