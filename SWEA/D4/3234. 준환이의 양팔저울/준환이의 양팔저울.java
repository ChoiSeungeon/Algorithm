import java.util.*;
import java.io.*;

class Solution {

	static int T, N, ans;
	static int[] weight;
	static boolean[] use;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			weight = new int[N];
			use = new boolean[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}

			ans = 0;
			dfs(0, 0, 0);

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);
	}

	static void dfs(int right, int left, int n) {
		if (n == N) {
			ans++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (use[i])
				continue;

			use[i] = true;

			dfs(right, left + weight[i], n + 1);

			if (right + weight[i] <= left) {
				dfs(right + weight[i], left, n + 1);
			}

			use[i] = false;
		}
	}

}
