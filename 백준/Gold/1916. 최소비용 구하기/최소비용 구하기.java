import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, S, E;
	static int[][] graph;
	static int[] dist;
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (graph[u][v] > w)
				graph[u][v] = w;
		}

		dist = new int[N + 1];
		Arrays.fill(dist, INF);

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		dijkstra(s, e);

		System.out.println(dist[e]);

		br.close();
	}

	static void dijkstra(int s, int e) {
		PriorityQueue<int[]> que = new PriorityQueue<>((a1, a2) -> {
			return Integer.compare(a1[1], a2[1]);
		});
		boolean[] visited = new boolean[N + 1];

		dist[s] = 0;
		que.add(new int[] { s, 0 });

		while (!que.isEmpty()) {
			int[] curr = que.poll();
			if (visited[curr[0]])
				continue;

			if (curr[0] == e)
				return;

			visited[curr[0]] = true;

			for (int i = 1; i <= N; i++) {
				if (visited[i] || graph[curr[0]][i] == INF)
					continue;

				if (dist[i] > curr[1] + graph[curr[0]][i]) {
					dist[i] = curr[1] + graph[curr[0]][i];
					que.add(new int[] { i, dist[i] });
				}
			}
		}

	}
}
