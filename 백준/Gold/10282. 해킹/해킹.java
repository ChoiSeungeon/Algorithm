import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Dependence {
		int to, w;

		public Dependence(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}

	static class State implements Comparable<State> {
		int v, d;

		public State(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(State o) {
			return this.d - o.d;
		}
	}

	static int T, N, D, C;
	static ArrayList<Dependence>[] computer;
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			computer = new ArrayList[N + 1];
			for (int j = 1; j <= N; j++)
				computer[j] = new ArrayList<>();

			for (int j = 0; j < D; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				computer[v].add(new Dependence(u, w));
			}

			int[] dist = dijkstra(C);
			int max = Integer.MIN_VALUE;
			int cnt = 0;
			for (int j : dist) {
				if (j == INF)
					continue;
				max = Math.max(max, j);
				cnt++;
			}
			sb.append(cnt).append(" ").append(max).append("\n");
		}

		System.out.println(sb);

		br.close();
	}

	static int[] dijkstra(int c) {
		PriorityQueue<State> que = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dist[c] = 0;
		que.add(new State(c, 0));

		while (!que.isEmpty()) {
			State curr = que.poll();
			if (curr.d > dist[curr.v])
				continue;

			for (Dependence n : computer[curr.v]) {
				if (dist[n.to] > curr.d + n.w) {
					dist[n.to] = curr.d + n.w;
					que.add(new State(n.to, dist[n.to]));
				}

			}
		}

		return dist;
	}

}
