import java.util.*;
import java.io.*;

class Solution {

	static int T, N, M, K;
	static int[][] map;

	static class Cell implements Comparable<Cell> {
		int x, y, num, t;
		boolean state;

		public Cell(int x, int y, int num, int t) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.t = t;
			this.state = false;
		}

		@Override
		public int compareTo(Cell o) {
			return Integer.compare(o.num, this.num);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			PriorityQueue<Cell> que = new PriorityQueue<>();
			map = new int[N + K * 2][M + K * 2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[K + i][K + j] = num;

					if (num != 0) {
						que.add(new Cell(K + i, K + j, num, num));
					}
				}
			}

			int[] dx = { -1, 1, 0, 0 };
			int[] dy = { 0, 0, -1, 1 };
			for (int k = 0; k < K; k++) {
				PriorityQueue<Cell> tmpQue = new PriorityQueue<>();
				while (!que.isEmpty()) {
					Cell c = que.poll();
					c.t--;
					if (c.t == -1 && !c.state) {
						c.state = true;
						c.t = c.num - 2;
						for (int d = 0; d < 4; d++) {
							int nx = c.x + dx[d];
							int ny = c.y + dy[d];

							if (nx < 0 || nx >= N + K * 2 || ny < 0 || ny >= M + K * 2 || map[nx][ny] != 0)
								continue;

							map[nx][ny] = c.num;
							tmpQue.offer(new Cell(nx, ny, c.num, c.num));
						}
					}

					if (c.t >= 0) {
						tmpQue.offer(c);
					}
				}
				que = new PriorityQueue<>(tmpQue);
			}

			sb.append("#").append(t).append(" ").append(que.size()).append("\n");
		}

		System.out.println(sb);
	}

}
