public class 프로그래머스_큰수만들기 {

	public static void main(String[] args) {
		String number = "4177252841" ;
		int k = 4;
		String ans = "";
		
        int len = number.length() - k;
		int s = 0;
		for (int i = 0; i < len; i++) {
			char max = number.charAt(s);
			int index = s;
			for (int j = s; j <= k+i; j++) {
				char tc = number.charAt(j);
				if(max < tc) {
					max = tc;
					index = j;
				}
                if(tc == '9') break;
			}
			s = index + 1;
			ans += max;
		}
		
		System.out.println(ans);

	}

}
