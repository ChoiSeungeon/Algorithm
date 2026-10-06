import java.util.*;
import java.io.*;

class Solution {

	static int T, N, W, H, ans;
	static LinkedList<Integer>[] originMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			originMap = new LinkedList[W];
			for (int w = 0; w < W; w++) {
				originMap[w] = new LinkedList<>();
			}

			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					int num = Integer.parseInt(st.nextToken());
					if (num > 0)
						originMap[w].addFirst(num);
				}
			}

			ans = Integer.MAX_VALUE;
			dfs(originMap, 0);

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}
		
		System.out.println(sb);
	}

	static void blockBreak(int w, LinkedList<Integer>[] map) {
		int[] dw = { -1, 0, 1, 0 };
		int[] dh = { 0, -1, 0, 1 };

		Queue<int[]> que = new ArrayDeque<>();

		int idx = map[w].size() - 1;
		if (idx >= 0) {
			que.add(new int[] { w, idx, map[w].get(idx) });
			map[w].set(idx, 0);
		}

		while (!que.isEmpty()) {
			int[] curr = que.poll();
			int num = curr[2];

			for (int d = 0; d < 4; d++) {
				for (int n = 1; n < num; n++) {
					int nw = curr[0] + dw[d] * n;
					int nh = curr[1] + dh[d] * n;

					if (nw < 0 || nw >= W || nh < 0 || nh >= map[nw].size() || map[nw].get(nh) == 0)
						continue;

					que.add(new int[] { nw, nh, map[nw].get(nh) });
					map[nw].set(nh, 0);
				}
			}

		}

		for (int i = 0; i < W; i++) {
			map[i].removeIf(value -> value == 0);
		}
	}

	static LinkedList<Integer>[] cloneMap(LinkedList<Integer>[] map) {
		LinkedList<Integer>[] tmpMap = new LinkedList[W];

		for (int w = 0; w < W; w++) {
			tmpMap[w] = new LinkedList<>(map[w]);
		}

		return tmpMap;
	}

	static void dfs(LinkedList<Integer>[] map, int n) {
		if (n == N) {
			int sum = 0;
			for (int w = 0; w < W; w++) {
				sum += map[w].size();
			}

			ans = Math.min(ans, sum);
			return;
		}

		for (int w = 0; w < W; w++) {
			LinkedList<Integer>[] tmpMap = cloneMap(map);
			blockBreak(w, tmpMap);
			dfs(tmpMap, n + 1);
		}
	}
}
