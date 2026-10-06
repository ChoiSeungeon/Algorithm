import java.util.*;
import java.io.*;

class Solution {

	static int T, N, X, M;
	static int[] ans;
	static int[][] command;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			command = new int[M][];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int idx1 = Integer.parseInt(st.nextToken()) - 1;
				int idx2 = Integer.parseInt(st.nextToken()) - 1;
				int sum = Integer.parseInt(st.nextToken());

				command[i] = new int[] { idx1, idx2, sum };
			}

			ans = new int[N];
			Arrays.fill(ans, X);
			boolean c = combin(N - 1);

			sb.append("#").append(t).append(" ");
			if (c) {
				for (int i = 0; i < N; i++) {
					sb.append(ans[i]).append(" ");
				}
			} else {
				sb.append("-1").append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static boolean combin(int idx) {
		if (idx == -1) {
			return check();
		}

		for (int i = X; i >= 0; i--) {
			ans[idx] = i;
			if (combin(idx - 1)) {
				return true;
			}
		}

		return false;
	}

	static boolean check() {
		boolean flag = true;

		for (int i = 0; i < M; i++) {
			int sum = 0;
			for (int l = command[i][0]; l <= command[i][1]; l++) {
				sum += ans[l];
			}

			if (sum != command[i][2]) {
				flag = false;
				break;
			}
		}

		return flag;
	}

}
