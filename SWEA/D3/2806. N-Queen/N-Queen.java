import java.io.*;

/*
 * 비트 마스크를 썼지만 성능은 flag 배열을 쓰는 것과 유사함
 * 성능을 올리고 싶으면 canSet & -canSet으로 오른쪽 제일 끝 비트를 추출해야함
 */

class Solution {

	static int T, N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			ans = 0;
			dfs(1, 0, 0, 0);

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(int col, int leftDiago, int midDiago, int rightDiago) {
		int cantSet = leftDiago | midDiago | rightDiago;
		int canSet = (~cantSet) & ((1 << N) - 1);

		if (col == N + 1) {
			ans++;
			return;
		}

		if (canSet == 0)
			return;

		for (int i = 0; i < N; i++) {
			if ((canSet & (1 << i)) != 0) {
				int ld = ((leftDiago << 1) | (1 << (i + 1))) & ((1 << N) - 1);
				int md = (midDiago | (1 << i));
				int rd = ((rightDiago >> 1) | ((1 << i) >> 1));

				dfs(col + 1, ld, md, rd);
			}
		}
	}

}
