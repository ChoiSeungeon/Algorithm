import java.util.*;
import java.io.*;

/*
 * 문제: SWEA 1486 장훈이의 높은 선반
 * 메모리: 104,716 kb
 * 실행 시간: 902 ms
 */

class Solution {

	static int T, N, ans;
	static int[] mountain;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();

			mountain = new int[N];
			for (int i = 0; i < N; i++) {
				mountain[i] = sc.nextInt();
			}

			ans = 0;
			int up = 0, down = 0;
			for (int i = 1; i < N; i++) {
				if (mountain[i - 1] < mountain[i]) {	// 상승하는 경우
					
					// 하락하다가 상승하는 경우
					if (down > 0) {
						ans += down * up;
						down = 0;
						up = 0;
					}

					up++;
				} else {	// 하락하는 경우
					down++;
				}
			}

			ans += up * down;

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

}
