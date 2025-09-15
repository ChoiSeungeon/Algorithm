import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int cnt = 0;

		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("Problem ").append(++cnt).append(": ").append(dijkstra()).append("\n");

		}

		System.out.println(sb);

		br.close();
	}

	static int dijkstra() {
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1[2], o2[2]);
		});

		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}

		boolean[][] visited = new boolean[N][N];

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		dist[0][0] = 0;
		que.add(new int[] { 0, 0, map[0][0] });

		while (!que.isEmpty()) {
			int[] curr = que.poll();
			visited[curr[0]][curr[1]] = true;

			if (curr[0] == N - 1 && curr[1] == N - 1)
				return dist[curr[0]][curr[1]];

			for (int i = 0; i < 4; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;

				int nextDist = curr[2] + map[nx][ny];

				if (dist[nx][ny] > nextDist) {
					dist[nx][ny] = nextDist;
					que.add(new int[] { nx, ny, nextDist });
				}
			}
		}

		return -1;
	}

}
