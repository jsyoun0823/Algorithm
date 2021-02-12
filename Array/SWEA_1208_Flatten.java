import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * SWEA 1208. Flatten
 * 상자 평탄화 작업 문제
 * */
public class SWEA_1208_Flatten {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int testCase = 1; testCase <= 10; testCase++) {
			int dump = Integer.parseInt(br.readLine()); // 덤프 횟수 제한, 1<= <=1000
			int[] boxes = new int[100]; // 상자의 높이값이 담길 배열, 가로 길이 항상 100
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken()); // 각 상자의 높이값, 1<= <=100
			}
			// 평탄화 작업 : 최고점과 최저점의 간격을 줄이는 작업 진행
			int max = 0, min = 101; // max:상자 높이 최고점, min:상자 높이 최저점 
			int maxIndex = 0, minIndex = 0; // 최고점과 최저점인 상자의 인덱스
			for (int j = 0; j < dump; j++) { // 주어진 덤프 횟수 만큼 반복
				for (int i = 0; i < 100; i++) { // 상자 개수만큼 반복
					if (max < boxes[i]) { // 상자 높이 최대값 찾기
						max = boxes[i]; // 높이 최대값 저장
						maxIndex = i; // 그 상자의 인덱스 저장
					}
					if (min > boxes[i]) { // 상자 높이 최소값 찾기
						min = boxes[i]; // 높이 최소값 저장
						minIndex = i; // 그 상자의 인덱스 저장
					}
				}
				boxes[maxIndex]--; // 가장 높은 상자의 높이 한칸 줄이기
				boxes[minIndex]++; // 가장 낮은 상자의 높이 한칸 늘리기
				max = 0; // 최대값 초기화
				min = 101; // 최소값 초기화
			}
			// 평탄화 작업 끝난 후 최고점과 최저점의 차이 반환
			int maxHigh = 0, minHigh = 101; // 
			for (int x : boxes) {
				if (maxHigh < x) { // 상자 높이 최고점 찾기
					maxHigh = x;
				}
				if (minHigh > x) { // 상자 높이 최저점 찾기
					minHigh = x;
				}
			}
			int answer = maxHigh - minHigh; // 최고점과 최저점의 차이
			System.out.println("#" + testCase + " " + answer);
		} // end of for test case
	} // end of main
} // end of class