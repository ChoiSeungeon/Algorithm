import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		LinkedList<int[]> items = new LinkedList<>();
		int[] bag = new int[K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			items.add(new int[] { w, v });
		}

		for (int[] item : items) {
			for (int i = K; i >= 0; i--) {
				if (i - item[0] < 0)
					continue;
				bag[i] = Math.max(bag[i], bag[i-item[0]] + item[1]);
			}
		}

		System.out.println(bag[K]);

		br.close();
	}

}
