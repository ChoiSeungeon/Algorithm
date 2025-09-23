import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int[] dist;
	static final int MAX = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int sx = Integer.parseInt(st.nextToken());
		int bx = Integer.parseInt(st.nextToken());

		visited = new boolean[MAX + 1];
		dist = new int[MAX + 1];

		System.out.println(bfs(sx, bx));

	}

	static int bfs(int sx, int bx) {
		if (sx == bx)
			return dist[sx];

		LinkedList<Integer> queue = new LinkedList<>();

		queue.add(sx);
		visited[sx] = true;

		while (!queue.isEmpty()) {
			int currx = queue.poll();

			int[] next = { currx + 1, currx - 1, currx * 2 };
			for (int ne : next) {
				if (ne < 0 || ne > MAX)
					continue;
				if (visited[ne])
					continue;

				visited[ne] = true;
				dist[ne] = dist[currx] + 1;

				if (ne == bx)
					return dist[ne];

				queue.add(ne);
			}

		}

		return -1;

	}
}
