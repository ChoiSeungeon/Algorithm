import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int[] inDp = new int[N];
		int[] deDp = new int[N];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					inDp[i] = Math.max(inDp[j] + 1, inDp[i]);
				}
			}
		}

		for (int i = N - 2; i >= 0; i--) {
			for (int j = N - 1; j > i; j--) {
				if (nums[i] > nums[j]) {
					deDp[i] = Math.max(deDp[j] + 1, deDp[i]);
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, inDp[i] + deDp[i] +	 1);
		}

		System.out.println(ans);

		br.close();
	}

}
