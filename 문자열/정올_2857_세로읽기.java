import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 정올_2857_세로읽기 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] strs = new String[5];
		for (int i = 0; i < 5; i++) {
			strs[i] = br.readLine(); // 한줄씩 읽어서 문자열 배열에 저장
		}
		
		String result = ""; // 세로로 읽은 글자들 저장할 문자열
		for (int col = 0; col < 15; col++) {	// 세로로 (열)
			for (int i = 0; i < strs.length; i++) {	// 문자열 줄 수 만큼 반복 
				if (strs[i].length() > col ) { // 현재 줄의 문자열 길이가 읽으려는 col보다 길다면
					result += strs[i].charAt(col); // 현재 줄의 문자열의 현재 col열의 문자를 저장
				}
			}
		}
		
		System.out.println(result);
	} // end of main
} // end of class