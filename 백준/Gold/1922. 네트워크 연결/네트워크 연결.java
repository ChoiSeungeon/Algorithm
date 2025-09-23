import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt, ans;
	static PriorityQueue<Edge>[] computers;

	static class Edge implements Comparable<Edge> {
		int to, w;

		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		PriorityQueue<Edge> que = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		computers = new PriorityQueue[N + 1];
		for (int i = 1; i <= N; i++)
			computers[i] = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (from == to)
				continue;

			computers[from].add(new Edge(to, w));
			computers[to].add(new Edge(from, w));
		}

		visited[1] = true;
		cnt++;
		while (!computers[1].isEmpty()) {
			que.add(computers[1].poll());
		}

		while (!que.isEmpty()) {
			Edge edge = que.poll();
			if (visited[edge.to])
				continue;

			visited[edge.to] = true;
			cnt++;
			ans += edge.w;

			if (cnt == N)
				break;

			while (!computers[edge.to].isEmpty()) {
				que.add(computers[edge.to].poll());
			}
		}

		System.out.println(ans);

		br.close();
	}

}
