import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0);

		System.out.println(ans);

		br.close();
	}

	static void dfs(int x, int y, int cnt) {
		if (cnt == 3) {
			bfs();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 || map[i][j] == 2)
					continue;

				map[i][j] = 1;
				dfs(i, j, cnt + 1);
				map[i][j] = 0;
			}

		}
	}

	static void bfs() {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int[][] copyMap = new int[N][M];
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++)
			copyMap[i] = map[i].clone();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 1 || copyMap[i][j] == 0 || visited[i][j])
					continue;

				visited[i][j] = true;
				que.offer(new int[] { i, j });

				while (!que.isEmpty()) {
					int[] curr = que.poll();

					for (int k = 0; k < 4; k++) {
						int nx = curr[0] + dx[k];
						int ny = curr[1] + dy[k];

						if (nx < 0 || ny < 0 || nx >= N || ny >= M)
							continue;
						if (copyMap[nx][ny] == 1 || visited[nx][ny])
							continue;

						copyMap[nx][ny] = 2;
						visited[nx][ny] = true;
						que.offer(new int[] { nx, ny });
					}
				}

			}
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0)
					cnt++;
			}
		}

		ans = ans < cnt ? cnt : ans;

	}

}
