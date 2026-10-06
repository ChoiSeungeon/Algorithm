import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

	static int T, N, ans, maxNum;
	static int[][] cheese;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			cheese = new int[N][N];
			maxNum = 1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					cheese[i][j] = num;
					maxNum = Math.max(maxNum, num);
				}
			}

			ans = 1;
			for (int day = 1; day <= maxNum; day++) {
				int cnt = 0;
				visited = new boolean[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (visited[i][j] || cheese[i][j] <= day)
							continue;

						cnt += bfs(i, j, day);
					}
				}

				if (cnt == N * N / 2) {
					ans = cnt;
					break;
				}

				ans = Math.max(ans, cnt);
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	static int bfs(int x, int y, int day) {
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] { x, y });
		visited[x][y] = true;

		while (!que.isEmpty()) {
			int[] curr = que.poll();

			for (int d = 0; d < 4; d++) {
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
					continue;

				if (cheese[nx][ny] <= day)
					continue;

				que.add(new int[] { nx, ny });
				visited[nx][ny] = true;
			}
		}

		return 1;
	}

}
