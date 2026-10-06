import java.util.*;
import java.io.*;

class Solution {

	static int T, M, A, N = 10;
	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };

	static class BC {
		int x, y;
		int c;
		int p;

		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int[][] posA = new int[M + 1][2];
			int aX = 1, aY = 1;
			posA[0] = new int[] { aX, aY };
			for (int i = 1; i <= M; i++) {
				int dir = Integer.parseInt(st.nextToken());
				aX += dx[dir];
				aY += dy[dir];
				posA[i] = new int[] { aX, aY };
			}

			st = new StringTokenizer(br.readLine());
			int[][] posB = new int[M + 1][2];
			int bX = 10, bY = 10;
			posB[0] = new int[] { bX, bY };
			for (int i = 1; i <= M; i++) {
				int dir = Integer.parseInt(st.nextToken());
				bX += dx[dir];
				bY += dy[dir];
				posB[i] = new int[] { bX, bY };
			}

			List<BC> bc = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				bc.add(new BC(x, y, c, p));
			}

			int ans = 0;
			for (int i = 0; i < M + 1; i++) {
				List<Integer> ableCA = ableCharge(posA[i][0], posA[i][1], bc);
				List<Integer> ableCB = ableCharge(posB[i][0], posB[i][1], bc);

				int charge = -1;

				for (int j : ableCA) {
					for (int k : ableCB) {
						if (j == k)
							continue;

						BC bA = bc.get(j);
						BC bB = bc.get(k);

						charge = Math.max(charge, bA.p + bB.p);
					}
				}

				if (ableCA.size() == 0 && ableCB.size() == 0) {
					charge = 0;
				} else if (ableCA.size() == 1 && ableCB.size() == 1 && (ableCA.get(0) == ableCB.get(0))) {
					charge = bc.get(ableCA.get(0)).p;
				} else if (ableCA.size() >= 1 && ableCB.size() == 0) {
					for (int j : ableCA) {
						charge = Math.max(charge, bc.get(j).p);
					}

				} else if (ableCA.size() == 0 && ableCB.size() >= 1) {
					for (int j : ableCB) {
						charge = Math.max(charge, bc.get(j).p);
					}
				}

				ans += charge;
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static List<Integer> ableCharge(int x, int y, List<BC> bc) {
		List<Integer> charger = new ArrayList<>();

		for (int i = 0; i < bc.size(); i++) {
			BC b = bc.get(i);
			if (Math.abs(b.x - x) + Math.abs(b.y - y) <= b.c) {
				charger.add(i);
			}
		}

		return charger;
	}

}
