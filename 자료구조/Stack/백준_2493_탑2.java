package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** stack을 배열로 구현하는 방법 !! 시간 더 적게 소요 */

////*********************************************************
////스택을 구현해보자 - 배열로 구현하는 것이 가장 빠르다
//int[] myStack = new int[500000]; // 50_000 자릿수 구분 50,000 랑 같음
//int top = -1;	// 스택의 마지막 입력된 데이터를 가리킬 index
//if (top == -1) { // 비어있는가?
//}
//if (top == myStack.length-1) { // 스택이 꽉찼는가?
////알고리즘에서는 물어볼 필요 없음. 부족하지 않게 크게 배열을 만들어놔야함★
//}
//// 스택에 값 넣기, (스택이 꽉찼는지 확인 후 넣어야함)
//myStack[++top] = 3;
//
//// 스택에 값 빼기, 스택이 비었는지 확인 후 빼야함
//System.out.println(myStack[top--]);


public class 백준_2493_탑2 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 50만개 이하
		int[] arr = new int[N + 1]; // 0번 안씀
		int[] stack = new int[N];   // 배열로 구현, 크기를 미리 지정해야함, 탑의 인덱스 저장

		int top = -1;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			// 스택에 나보다 작은 높이의 탑은 꺼내서 버리기
			while(top > -1 && arr[stack[top]] < arr[i]) {
				top--; // 삭제의 효과, 꺼내 버리기
			}
			
			// 나보다 큰 탑이 있으면 그 위에 내꺼 쌓기
			if(top == -1) { // 스택이 비어있으면 레이저를 수신할 탑이 없는 경우
				sb.append("0 ");
			} else {
				sb.append(stack[top]).append(' '); // 레이저 수신한 탑을 출력
			}
			stack[++top] = i; // 스택에 내 탑의 index 쌓기
		}
		System.out.println(sb);
	} // end of main
} // end of class
