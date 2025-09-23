import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, ans;
	static Egg[] eggs;

	static class Egg {
		int d, w;

		public Egg(int d, int w) {
			this.d = d;
			this.w = w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		eggs = new Egg[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			eggs[i] = new Egg(d, w);
		}

		breakingEgg(0, 0);

		System.out.println(ans);

		br.close();
	}

	static void breakingEgg(int idx, int cnt) {
		
		if (idx >= N) {
			ans = Math.max(ans, cnt);
			return;
		}

		if (eggs[idx].d <= 0) {
			breakingEgg(idx + 1, cnt);
			return;
		}

		boolean hit = false;
		for (int i = 0; i < N; i++) {
			if (eggs[i].d <= 0 || i == idx)
				continue;
			
			hit = true;
			eggs[i].d -= eggs[idx].w;
			eggs[idx].d -= eggs[i].w;

			int breaking = 0;

			if (eggs[i].d <= 0) {
				breaking++;
			}
			if (eggs[idx].d <= 0) {
				breaking++;
			}

			breakingEgg(idx + 1, cnt + breaking);

			eggs[i].d += eggs[idx].w;
			eggs[idx].d += eggs[i].w;
		}
		
		if(!hit)
			breakingEgg(idx + 1, cnt);
		
	}

}
