import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

	static int T, N, ans;
	static char[][] map;
	static int[][] numMap;
	static boolean[][] searchMap;
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 }, dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws IOException {

		// 목표 : 최소 몇 번의 클릭을 해야지 지뢰가 없는 모든 칸에 숫자가 표시될 것인지
		// 동작 : 클릭했을 때 8방향에 지뢰가 있다면 지뢰 갯수 표시됨
		// 만약 8방향에 지뢰가 하나도 없다면 주변 모두 숫자 표시

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			ans = 0;
			map = new char[N][N];
			numMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					char c = line.charAt(j);
					map[i][j] = c;

					if (c == '*')
						numMap[i][j] = -1;
				}
			}

			// 숫자가 표기된 게임판으로 변경
			fillNum();

			// 게임판을 순회하면서 0으로 인해 한번 클릭으로 연쇄 반응이 일어나는 칸을 방문처리
			searchMap = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (numMap[i][j] == 0 && !searchMap[i][j])
						ans += chain(i, j);
				}
			}

			// 나머지 지뢰칸이 아니고 방문하지 않은 칸을 하나씩 클릭
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!searchMap[i][j] && numMap[i][j] != -1) {
						ans++;
						searchMap[i][j] = true;
					}
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	// 숫자가 표기된 게임판으로 변경
	static void fillNum() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (numMap[i][j] == -1)
					continue;

				int num = 0;
				for (int d = 0; d < 8; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;

					if (numMap[nx][ny] == -1)
						num++;
				}

				numMap[i][j] = num;
			}
		}
	}

	// 0으로 인해 한번 클릭으로 연쇄 반응이 일어나는 칸을 방문처리
	static int chain(int x, int y) {
		Queue<int[]> que = new ArrayDeque<>();
		searchMap[x][y] = true;
		que.add(new int[] { x, y });

		while (!que.isEmpty()) {
			int[] curr = que.poll();

			for (int d = 0; d < 8; d++) {
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || searchMap[nx][ny])
					continue;

				if (numMap[nx][ny] == -1) {
					continue;
				}

				searchMap[nx][ny] = true;
				// 0이 아닌 칸에서는 연쇄 반응이 끝나야함으로 큐에 넣지 않음
				if (numMap[nx][ny] == 0)
					que.add(new int[] { nx, ny });
			}
		}

		return 1;
	}

}
