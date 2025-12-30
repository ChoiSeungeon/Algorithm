import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class TimeNode {
	int s, e, len;

	public TimeNode(int s, int e) {
		this.s = s;
		this.e = e;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		LinkedList<TimeNode> time = new LinkedList<>();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			time.offer(new TimeNode(s, e));
		}

		time.sort((t1, t2) -> {
			if (t1.e == t2.e)
				return t1.s - t2.s;
			return t1.e - t2.e;
		});

		int cnt = 1;
		TimeNode t = time.poll();
		int etime = t.e;
		while (!time.isEmpty()) {
			t = time.poll();

			if (t.s < etime)
				continue;

			etime = t.e;
			cnt++;
		}

		System.out.println(cnt);

	}

}
