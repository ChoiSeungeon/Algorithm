import java.util.*;
import java.io.*;

class Solution {

	static int T, N, C;
	static int maxConnected;
	static int minLength;
	static List<int[]> mPos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			C = 0;

			mPos = new ArrayList<>();
			int[][] mxynos = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());

					if (num == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						C++;
						mPos.add(new int[] { i, j });
					}

					mxynos[i][j] = num;
				}
			}

			maxConnected = 0;
			minLength = Integer.MAX_VALUE;
			dfs(mxynos, 0, 0, 0);

			sb.append("#").append(t).append(" ").append(minLength).append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(int[][] mxynos, int idx, int connected, int len) {
		if (idx == C) {
			if (connected > maxConnected) {
				maxConnected = connected;
				minLength = len;
			} else if (connected == maxConnected) {
				minLength = Math.min(minLength, len);
			}

			return;
		}

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		int[] pos = mPos.get(idx);

		for (int d = 0; d < 4; d++) {
			int[][] copynos = copyMxynos(mxynos);

			int nx = pos[0] + dx[d];
			int ny = pos[1] + dy[d];
			int currLen = 0;
			boolean pass = true;

			while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (copynos[nx][ny] == 1 || copynos[nx][ny] == 2) {
					pass = false;
					break;
				}

				copynos[nx][ny] = 2;
				nx = nx + dx[d];
				ny = ny + dy[d];
				currLen++;
			}

			if (!pass)
				continue;

			dfs(copynos, idx + 1, connected + 1, len + currLen);
		}

		dfs(mxynos, idx + 1, connected, len);
	}

	static int[][] copyMxynos(int[][] mxynos) {
		int[][] copynos = new int[N][N];

		for (int i = 0; i < N; i++) {
			copynos[i] = mxynos[i].clone();
		}

		return copynos;
	}

}
