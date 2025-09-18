import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int k;
	static boolean[] visited;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			
			if (k == 0)
				break;
			
			nums = new int[k];
			visited = new boolean[k];
			for (int i = 0; i < k; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < k; i++) {
				visited[i] = true;
				dfs(i, 1);
				visited[i] = false;
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	static void dfs(int idx, int cnt) {
		if (cnt >= 6) {
			for (int i = 0; i < k; i++) {
				if(visited[i]) {
					sb.append(nums[i]).append(" ");
				}
			}
			sb.append("\n");
			return;
		}
		
		for(int i = idx; i < k; i++) {
			if (visited[i])
				continue;
			
			visited[i] = true;
			
			dfs(i, cnt+1);
			
			visited[i] = false;
		}
		
		
	}

}
