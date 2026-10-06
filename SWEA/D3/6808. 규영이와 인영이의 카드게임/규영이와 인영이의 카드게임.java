import java.util.*;
import java.io.*;

class Solution {

	static int T, winGue, winIn;
	static int[] deck;
	static boolean[] has;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			deck = new int[9];
			has = new boolean[19];
			for (int i = 0; i < 9; i++) {
				int num = Integer.parseInt(st.nextToken());

				deck[i] = num;
				has[num] = true;
			}

			winGue = 0;
			winIn = 0;
			dfs(0, 0, 0);

			sb.append("#").append(t).append(" ").append(winGue).append(" ").append(winIn).append("\n");
		}

		System.out.println(sb);

	}

	static void dfs(int idx, int pointGue, int pointIn) {
		if (idx == 9) {
			if (pointGue > pointIn) {
				winGue++;
			} else if (pointGue < pointIn) {
				winIn++;
			}

			return;
		}

		for (int i = 1; i < 19; i++) {
			if (has[i])
				continue;

			has[i] = true;

			if (deck[idx] > i) {
				dfs(idx + 1, pointGue + deck[idx] + i, pointIn);
			} else {
				dfs(idx + 1, pointGue, pointIn + deck[idx] + i);
			}

			has[i] = false;
		}
	}

}
