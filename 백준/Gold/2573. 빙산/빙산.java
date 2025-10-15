import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int[][] map, newMap;
	static boolean[][] visited;

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

		br.close();

		int cnt = 0;
		while (true) {
			newMap = new int[N][M];
			boolean flag = false;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						if (!flag) {
							bfs(i, j);
							flag = true;
						} else {
							System.out.println(cnt);
							return;
						}
					}
				}
			}
			cnt++;
			map = newMap;
			
			if (!flag) {
				System.out.println(0);
				return;
			}
		}

	}

	static void bfs(int x, int y) {
		ArrayDeque<int[]> que = new ArrayDeque<>();

		que.offer(new int[] { x, y });

		while (!que.isEmpty()) {
			int[] curr = que.poll();
			int waterCnt = 0;

			for (int i = 0; i < 4; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] <= 0) {
					waterCnt++;
					continue;
				}

				que.offer(new int[] { nx, ny });
				visited[nx][ny] = true;
			}

			newMap[curr[0]][curr[1]] = map[curr[0]][curr[1]] - waterCnt;

		}
	}

}
