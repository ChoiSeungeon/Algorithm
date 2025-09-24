import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			ArrayDeque<Integer> que = new ArrayDeque<>();
			String command = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String nums = br.readLine();
			boolean flag = false;
			boolean reverse = false;

			st = new StringTokenizer(nums, "[,]");

			for (int j = 0; j < n; j++) {
				que.add(Integer.parseInt(st.nextToken()));
			}

			for (int j = 0; j < command.length(); j++) {
				if (command.charAt(j) == 'R') {
					reverse = reverse ? false : true;
				} else if (command.charAt(j) == 'D') {
					if (que.isEmpty()) {
						sb.append("error").append("\n");
						flag = true;
						break;
					}
					if (reverse) {
						que.pollLast();
					} else {
						que.pollFirst();
					}
					n--;

				}
			}

			if (!flag) {
				sb.append("[");
				if (n != 0) {
					if (reverse) {
						while (!que.isEmpty()) {
							sb.append(que.pollLast()).append(',');
						}
	
					} else {
						while (!que.isEmpty()) {
							sb.append(que.pollFirst()).append(',');
						}
					}
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("]").append("\n");
			}

		}

		System.out.println(sb);

		br.close();
	}

}
