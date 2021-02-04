/** 
 * 두 수의 최대공약수를 구하는 알고리즘
 * 두 수가 서로 상대방 수를 나누어서 결국 원하는 수를 얻는 알고리즘
 * */
public class 유클리드호제법 {

	public static void main(String[] args) {
		System.out.println(GCD(1112, 695));
	}

	private static int GCD(int a, int b) {
		if(b == 0) return a;
		else return GCD(b, a % b);
	}
	
}
