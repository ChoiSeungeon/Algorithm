import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] row = new boolean[9][10];
	static boolean[][] col = new boolean[9][10];
	static boolean[][] box = new boolean[9][10];
	static int[][] board = new int[9][9];
	static boolean flag = false;
	static ArrayList<int[]> blank = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num != 0) {
					row[i][num] = true;
					col[j][num] = true;
					box[(i / 3) * 3 + (j / 3)][num] = true;
				} else {
					blank.add(new int[] { i, j });
				}
				board[i][j] = num;
			}
		}

		dfs(0);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);

		br.close();
	}

	static void dfs(int idx) {
		if (idx == blank.size()) {
			flag = true;
			return;
		}

		int x = blank.get(idx)[0];
		int y = blank.get(idx)[1];

		for (int i = 1; i <= 9; i++) {
			if (row[x][i] || col[y][i] || box[(x / 3) * 3 + (y / 3)][i]) {
				continue;
			}

			row[x][i] = true;
			col[y][i] = true;
			box[(x / 3) * 3 + (y / 3)][i] = true;
			board[x][y] = i;

			dfs(idx + 1);

			if (flag)
				return;

			row[x][i] = false;
			col[y][i] = false;
			box[(x / 3) * 3 + (y / 3)][i] = false;
			board[x][y] = 0;

		}
	}

}
