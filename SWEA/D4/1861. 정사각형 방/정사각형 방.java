import java.util.*;
import java.io.*;

class Solution {

	static int T, N;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = 0, smallNum = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited = new boolean[N][N];

					int num = map[i][j];
					int move = bfs(i, j);

					if (ans < move) {
						ans = move;
						smallNum = num;
					} else if (ans == move && smallNum > num) {
						smallNum = num;
					}
				}
			}

			sb.append("#").append(t).append(" ").append(smallNum).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

	static int bfs(int x, int y) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		int ans = 1;

		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] { x, y, map[x][y], 1 });
		visited[x][y] = true;

		while (!que.isEmpty()) {
			int[] curr = que.poll();

			for (int d = 0; d < 4; d++) {
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || curr[2] - map[nx][ny] != -1)
					continue;

				que.add(new int[] { nx, ny, map[nx][ny], curr[3] + 1 });
				visited[nx][ny] = true;

				ans = Math.max(ans, curr[3] + 1);
			}
		}

		return ans;
	}

}
