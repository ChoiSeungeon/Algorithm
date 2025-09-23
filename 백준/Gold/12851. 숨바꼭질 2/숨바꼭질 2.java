import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int MAX = 100001;
	static int[] dist = new int[MAX];
	static int[] ways = new int[MAX];
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(dist[M] - 1);
		System.out.println(ways[M]);

	}

	static void bfs() {
		dist = new int[MAX];
		ways = new int[MAX];
		ArrayDeque<Integer> que = new ArrayDeque<>();
		
		if (N == M) {
			dist[N] = 1;
			ways[N] = 1;
			return;
		}

		dist[N] = 1;
		ways[N] = 1;
		que.offer(N);

		while (!que.isEmpty()) {
			int curr = que.poll();
			int[] move = { curr - 1, curr + 1, curr * 2 };

			for (int next : move) {
				if (next < 0 || next >= MAX)
					continue;

				if (dist[next] == 0) {
					dist[next] = dist[curr] + 1;
					ways[next] = ways[curr];
					que.offer(next);
				} else if (dist[next] == dist[curr] + 1) {
					ways[next] += ways[curr];
				}

			}
		}
	}
}
