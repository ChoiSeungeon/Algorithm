import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

	static int T = 10, N = 100;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			br.readLine();

			map = new int[N][N];
			int sx = -1, sy = -1, ex = -1, ey = -1;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					int num = line.charAt(j) - '0';
					map[i][j] = num;

					if (num == 2) {
						sx = i;
						sy = j;
					} else if (num == 3) {
						ex = i;
						ey = j;
					}
				}
			}

			sb.append("#").append(t).append(" ").append(bfs(sx, sy, ex, ey)).append("\n");
		}

		System.out.println(sb);
	}

	static int bfs(int sx, int sy, int ex, int ey) {
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] { sx, sy });
		map[sx][sy] = 1;

		while (!que.isEmpty()) {
			int[] curr = que.poll();

			for (int d = 0; d < 4; d++) {
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1)
					continue;

				if (map[nx][ny] == 3)
					return 1;

				que.add(new int[] { nx, ny });
				map[nx][ny] = 1;
			}
		}

		return 0;
	}

}
