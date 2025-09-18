import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static long[] dist;
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (map[u][v] > w)
				map[u][v] = w;
		}

		dist = new long[N + 1];
		Arrays.fill(dist, INF);

		if (bellford()) {
			for (int i = 2; i <= N; i++) {
				if (dist[i] == INF) {
					System.out.println(-1);
				} else {
					System.out.println(dist[i]);
				}
			}
		} else {
			System.out.println(-1);
		}

		br.close();
	}

	static boolean bellford() {
		dist[1] = 0;

		for (int t = 1; t < N; t++) {
			for (int i = 1; i <= N; i++) {
				if (dist[i] == INF)
					continue;
				for (int j = 1; j <= N; j++) {
					if (map[i][j] != INF && dist[j] > dist[i] + map[i][j]) {
						dist[j] = dist[i] + map[i][j];
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (dist[i] == INF)
				continue;
			for (int j = 1; j <= N; j++) {
				if (map[i][j] != INF && dist[j] > dist[i] + map[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

}
