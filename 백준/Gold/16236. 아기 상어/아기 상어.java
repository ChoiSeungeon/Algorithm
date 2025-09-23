import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, X, Y, W, wCnt;
	static int[][] map;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int size = Integer.parseInt(st.nextToken());
				map[i][j] = size;
				if (size == 9) {
					map[i][j] = 0;
					X = i;
					Y = j;
					W = 2;
				}
			}
		}

		int ans = 0;
		while (true) {
			int[] feed = bfs(X, Y, 0);
			if (feed == null)
				break;
			map[feed[0]][feed[1]] = 0;
			X = feed[0];
			Y = feed[1];
			ans += feed[2];
			wCnt++;
			if (wCnt == W) {
				W++;
				wCnt = 0;
			}

		}

		System.out.println(ans);

		br.close();
	}

	static int[] bfs(int cx, int cy, int cnt) {
		boolean flag = false;
		int bestD = -1, bx = -1, by = -1;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		visited[cx][cy] = true;
		que.offer(new int[] { X, Y, cnt });

		while (!que.isEmpty()) {
			int[] curr = que.poll();
			int nt = curr[2];

			if (flag && bestD <= nt)
				continue;

			for (int i = 0; i < 4; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (map[nx][ny] > W || visited[nx][ny])
					continue;

				if (map[nx][ny] < W && map[nx][ny] > 0) {
					if (!flag) {
						flag = true;
						bestD = nt + 1;
						bx = nx;
						by = ny;
					}

					if (nx < bx || (nx == bx && ny < by)) {
						bx = nx;
						by = ny;
					}
				}

				if (!flag || (flag && bestD > nt)) {
					visited[nx][ny] = true;
					que.add(new int[] { nx, ny, nt + 1 });
				}
			}
		}

		if (!flag)
			return null;
		return new int[] { bx, by, bestD };

	}
}
