import java.util.*;
import java.io.*;

class Solution {

	static int T, N, M;
	static List<int[]> demons;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			demons = new ArrayList<>();
			int sX = -1, sY = -1;
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					char c = line.charAt(j);

					if (c == 'S') {
						sX = i;
						sY = j;
					} else if (c == '*') {
						demons.add(new int[] { i, j });
					}

					map[i][j] = c;
				}
			}

			String ans = bfs(sX, sY);

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	static String bfs(int x, int y) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		boolean[][] visited = new boolean[N][M];

		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] { x, y, 0 });
		visited[x][y] = true;

		int preTime = -1;
		while (!que.isEmpty()) {
			int[] curr = que.poll();

			if (preTime != curr[2]) {
				List<int[]> decay = new ArrayList<>();
				for (int[] demon : demons) {
					for (int dir = 0; dir < 4; dir++) {
						int ndx = demon[0] + dx[dir];
						int ndy = demon[1] + dy[dir];

						if (ndx < 0 || ndx >= N || ndy < 0 || ndy >= M)
							continue;

						if (map[ndx][ndy] == 'X' || map[ndx][ndy] == '*' || map[ndx][ndy] == 'D')
							continue;

						decay.add(new int[] { ndx, ndy });
						map[ndx][ndy] = '*';
					}
				}
				demons = decay;
				preTime = curr[2];
			}

			for (int d = 0; d < 4; d++) {
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny])
					continue;

				if (map[nx][ny] == 'X' || map[nx][ny] == '*')
					continue;

				if (map[nx][ny] == 'D')
					return curr[2] + 1 + "";

				que.add(new int[] { nx, ny, curr[2] + 1 });
				visited[nx][ny] = true;
			}
		}

		return "GAME OVER";
	}

}
