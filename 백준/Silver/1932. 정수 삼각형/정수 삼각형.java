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
        int[][] tri = new int[N][];
        int[][] dp = new int[N][];
        for(int i = 0; i < N; i++){
            tri[i] = new int[i+1];
            dp[i] = new int[i+1];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < i+1; j++){
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = tri[0][0];

        for(int i = 1; i < N; i++){
            for(int j = 0; j < i+1; j++){
                if(j == 0){
                    dp[i][j] = dp[i-1][j] + tri[i][j];
                } else if(j == i){
                    dp[i][j] = dp[i-1][j-1] + tri[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + tri[i][j];
                }
            }
        }

        System.out.println(Arrays.stream(dp[N-1]).max().getAsInt());;
    }
}
