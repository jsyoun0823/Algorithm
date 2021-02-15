import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14494_다이나믹이뭐예요 {

	public static void main(String[] args) throws IOException {
		final long mod = 1000000007;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[][] memo = new long[n+1][m+1];
		memo[1][1] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if(i==1 && j==1) continue;
				memo[i][j] = (memo[i][j-1]%mod + memo[i-1][j]%mod + memo[i-1][j-1]%mod)%mod;
			}
		}
		System.out.println(memo[n][m]%mod);
	}
}
