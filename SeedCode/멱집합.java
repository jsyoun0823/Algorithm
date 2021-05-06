/** 
 * 공집합을 포함해 모든 부분집합을 가진 집합 (중복 없음)
 * 모든 부분집합(멱집합)을 가장 쉽게 구할 수 있는 재귀함수 사용법
 * */
public class 멱집합 {
	public static void main(String[] args) {

		// 원본 집합
		String[] arr = new String[] { "a", "b", "c" };

		// 상태 체크
		boolean[] state = new boolean[arr.length];

		// 멱집합 재귀함수 호출
		powerset(arr, state, 0, arr.length);
	}

	/**
	 * 모든 부분집합 구하기
	 * 
	 * @param arr   : 원본 배열
	 * @param state : "있을 경우와 없을 경우" 상태값 체크
	 * @param index : 현재 기준이 되는 배열 인덱스
	 * @param end   : 배열의 사이즈
	 */
	public static void powerset(String[] arr, boolean[] state, int i, int end) {

		// 탈출문
		if (i >= end) {

			// 현재 true로 체크되어 있는 인덱스의 값만 출력
			for (int w = 0; w < end; w++) {

				if (state[w]) {
					System.out.print(arr[w] + " ");
				}
			}
			System.out.println();

			return;
		}

		// "내가 없을 경우"를 체크한 뒤 다른 부분집합을 구하는 재귀함수 호출 (다음 인덱스로 기준 이동)
		state[i] = false;
		powerset(arr, state, i + 1, end);

		// "내가 있을 경우"를 체크한 뒤 다른 부분집합을 구하는 재귀함수 호출 (다음 인덱스로 기준 이동)
		state[i] = true;
		powerset(arr, state, i + 1, end);
	}

}
