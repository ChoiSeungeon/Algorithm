import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][3];
        int[] cost = new int[3];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j++)
                cost[j] = Integer.parseInt(st.nextToken());

            if(i == 0){
                System.arraycopy(cost, 0, dp[0], 0, 3);
            } else {
                dp[i][0] = cost[0] + Math.min(dp[i-1][1],dp[i-1][2]);
                dp[i][1] = cost[1] + Math.min(dp[i-1][0],dp[i-1][2]);
                dp[i][2] = cost[2] + Math.min(dp[i-1][0],dp[i-1][1]);
            }
        }

        System.out.println(Arrays.stream(dp[N-1]).min().getAsInt());
    }
}
