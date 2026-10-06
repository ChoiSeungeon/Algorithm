import java.util.*;
import java.io.*;

class Solution {

	static int T, N, ans;
	static int[][] board;
	static boolean[] selected;
	static boolean[] bitMask;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bitMask = new boolean[(int) Math.pow(2, N)];

			ans = Integer.MAX_VALUE;
			choiceFood(0, 0, 0);

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	static void choiceFood(int cnt, int idx, int bit) {
		if (cnt == N / 2) {
			int reverse = (~bit) & ((1 << N) - 1);
			if (bitMask[reverse])
				return;

			bitMask[bit] = true;
			bitMask[reverse] = true;
			int chef1 = 0, chef2 = 0;
			for (int i = 0; i < N; i++) {
				if ((bit & (1 << i)) != 0) {
					for (int j = 0; j < N; j++) {
						if ((bit & (1 << j)) != 0) {
							chef1 += board[i][j];
						}
					}
				} else {
					for (int j = 0; j < N; j++) {
						if ((bit & (1 << j)) == 0) {
							chef2 += board[i][j];
						}
					}
				}
			}

			ans = Math.min(ans, Math.abs(chef1 - chef2));
			return;
		}

		if (idx == N)
			return;

		choiceFood(cnt + 1, idx + 1, bit | (1 << idx));

		choiceFood(cnt, idx + 1, bit);
	}

}
