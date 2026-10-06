import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	static int T, N, M;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			nums = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				nums[i] = i;
			}

			sb.append("#").append(t).append(" ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int command = Integer.parseInt(st.nextToken());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());

				if (command == 0) {
					union(first, second);
				} else {
					int root1 = find(first);
					int root2 = find(second);

					if (root1 == root2) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static int find(int n) {
		if (nums[n] == n) {
			return n;
		}

		return nums[n] = find(nums[n]);
	}

	static void union(int n1, int n2) {
		int root1 = find(n1);
		int root2 = find(n2);

		if (root1 != root2) {
			nums[root2] = nums[root1];
		}
	}

}
