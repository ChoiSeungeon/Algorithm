import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int T, N;
	static boolean[] visited1;
	static boolean[][] visited2;
	static HashMap<String, Integer> wardrobe;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());

			wardrobe = new HashMap<>();

			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();

				String clothes = st.nextToken();
				if (wardrobe.containsKey(clothes)) {
					int cnt = wardrobe.get(clothes);
					wardrobe.put(clothes, cnt + 1);
				} else {
					wardrobe.put(clothes, 1);
				}
			}

			int ans = 1;

			for (Map.Entry<String, Integer> entry : wardrobe.entrySet()) {
				ans *= (entry.getValue() + 1);
			}

			sb.append(ans - 1).append("\n");

		}

		System.out.println(sb);
		br.close();
	}

}
