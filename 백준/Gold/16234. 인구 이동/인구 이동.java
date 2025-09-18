import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R, ans;
	static int[][] map;
	static boolean[][] visited;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}

			if (flag) {
				ans++;
				flag = false;
			} else {
				break;
			}
		}

		System.out.println(ans);

		br.close();
	}

	static void bfs(int x, int y) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		ArrayList<int[]> association = new ArrayList<>();
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		int sum = 0;
		int cnt = 0;

		if (visited[x][y])
			return;

		visited[x][y] = true;
		que.add(new int[] { x, y });

		while (!que.isEmpty()) {
			int[] curr = que.poll();
			int currPop = map[curr[0]][curr[1]];

			association.add(curr);
			sum += currPop;
			cnt++;

			for (int i = 0; i < 4; i++) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (visited[nx][ny] || Math.abs(currPop - map[nx][ny]) < L || Math.abs(currPop - map[nx][ny]) > R)
					continue;

				visited[nx][ny] = true;
				que.add(new int[] { nx, ny });
			}
		}

		if (association.size() > 1) {
			flag = true;
		}

		for (int[] pos : association) {
			map[pos[0]][pos[1]] = sum / cnt;
		}
	}

}
