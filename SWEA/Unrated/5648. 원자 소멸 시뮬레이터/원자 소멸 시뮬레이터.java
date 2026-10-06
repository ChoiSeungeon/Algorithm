import java.util.*;
import java.io.*;

class Solution {

	static int T, N, aliveAtom;
	static Atom[] atom;
	static int[] dx = { 0, 0, -1, 1 }, dy = { 1, -1, 0, 0 };
	static int[][] map;

	static class Atom {
		int x, y, dir, power;
		boolean alive;

		public Atom(int x, int y, int dir, int power) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.power = power;
			this.alive = true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			atom = new Atom[N];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());

				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int dir = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());

				atom[n] = new Atom(x, y, dir, power);

			}

			int ans = 0;
			aliveAtom = N;
			map = new int[4001][4001];
			while (aliveAtom != 0) {
				ans += move();
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	static int move() {
		int power = 0;
		List<int[]> used = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			if (!atom[n].alive)
				continue;

			int nx = atom[n].x + dx[atom[n].dir];
			int ny = atom[n].y + dy[atom[n].dir];

			if (nx < 0 || nx >= 4001 || ny < 0 || ny >= 4001) {
				atom[n].alive = false;
				aliveAtom--;
				continue;
			}

			if (map[ny][nx] == 0)
				used.add(new int[] { nx, ny });

			atom[n].x = nx;
			atom[n].y = ny;

			map[ny][nx]++;

		}

		for (int n = 0; n < N; n++) {
			if (!atom[n].alive)
				continue;

			if (map[atom[n].y][atom[n].x] > 1) {
				power += atom[n].power;
				atom[n].alive = false;
				aliveAtom--;
			}
		}

		for (int[] p : used) {
			map[p[1]][p[0]] = 0;
		}

		return power;
	}

}
