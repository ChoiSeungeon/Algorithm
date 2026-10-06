import java.util.*;
import java.io.*;

class Solution {

	static int T, D, W, K;
	static int[][] film;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = 0;
			for (int i = 0; i <= K; i++) {
				ans = i;
				if (dfs(film, 0, 0, i)) {
					break;
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	static boolean dfs(int[][] copyFilm, int idx, int cnt, int depth) {
		if (cnt == depth) {
			return check(copyFilm);
		}

		if (idx >= D) {
			return false;
		}

		int[][] injectAFilm = injection(copyFilm, idx, 0);
		int[][] injectBFilm = injection(copyFilm, idx, 1);

		if (dfs(injectAFilm, idx + 1, cnt + 1, depth)) {
			return true;
		}

		if (dfs(injectBFilm, idx + 1, cnt + 1, depth)) {
			return true;
		}

		if (dfs(copyFilm, idx + 1, cnt, depth)) {
			return true;
		}

		return false;
	}

	static int[][] injection(int[][] copyFilm, int d, int attr) {
		int[][] injectFilm = new int[D][W];

		for (int i = 0; i < D; i++) {
			if (i == d) {
				Arrays.fill(injectFilm[i], attr);
			} else {
				injectFilm[i] = copyFilm[i].clone();
			}
		}

		return injectFilm;
	}

	static boolean check(int[][] copyFilm) {
		if (K == 1) {
			return true;
		}

		for (int j = 0; j < W; j++) {
			boolean flag = false;
			int cnt = 1;

			for (int i = 1; i < D; i++) {
				if (copyFilm[i][j] == copyFilm[i - 1][j]) {
					cnt++;
				} else {
					cnt = 1;
				}

				if (cnt >= K) {
					flag = true;
					break;
				}
			}

			if (!flag) {
				return false;
			}
		}

		return true;
	}

}
