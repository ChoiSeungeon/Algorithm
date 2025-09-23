import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int MAX = 100001;
	static int[] dist = new int[MAX];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(dist[M] - 1);

	}

	static void bfs() {
		dist = new int[MAX];
		int tmp = 0;
		ArrayDeque<Integer> que = new ArrayDeque<>();

		dist[N] = 1;

		if (N == M) {
			return;
		}

		que.offer(N);

		while (!que.isEmpty()) {
			int curr = que.poll();
			int[] move = { curr - 1, curr + 1, curr * 2 };

			if (curr == M)
				return;

			for (int i = 0; i < 3; i++) {
				if (move[i] < 0 || move[i] >= MAX)
					continue;

				if (i == 2) {
					tmp = dist[curr];
				} else {
					tmp = dist[curr] + 1;
				}

				if ((dist[move[i]] != 0 && dist[move[i]] > tmp) || dist[move[i]] == 0) {
					dist[move[i]] = tmp;
					que.offer(move[i]);
				}

			}
		}
	}
}
