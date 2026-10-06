import java.io.*;
import java.util.*;

class Solution {

	static int T, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String line = br.readLine();

			ans = 0;
			boolean[] visited = new boolean[line.length() - 1];
			for (int i = 0; i < visited.length; i++) {
				visited[i] = true;
				dfs(line, 0, 0, visited);
				visited[i] = false;
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(String line, int idx, int cnt, boolean[] visited) {

		if (idx == line.length() - 1) {
			List<Integer> choice = new ArrayList<>();
			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					choice.add(i);
				}
			}

			if (choice.size() == 0) {
				return;
			}

			int num = 1, preIdx = 0;
			for (int i : choice) {
				String sub = line.substring(preIdx, i + 1);
				preIdx = i + 1;
				num *= Integer.parseInt(sub);
			}

			num *= Integer.parseInt(line.substring(preIdx, line.length()));
			String newLine = num + "";
			if (newLine.length() <= 1) {
				ans = Math.max(ans, cnt + 1);
			} else {
				dfs(newLine, 0, cnt + 1, new boolean[newLine.length() - 1]);
			}

			return;
		}

		visited[idx] = true;
		dfs(line, idx + 1, cnt, visited);

		visited[idx] = false;
		dfs(line, idx + 1, cnt, visited);
	}

}
