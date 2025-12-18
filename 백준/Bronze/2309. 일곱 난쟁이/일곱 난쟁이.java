import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static List<Integer> tall;
    public static boolean[] visited;
    public static boolean finish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tall = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            tall.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(tall);

        visited = new boolean[9];
        dfs(0,0,0);

        for(int i = 0; i < 9; i++){
            if(visited[i]) {
                System.out.println(tall.get(i));
            }
        }

    }

    public static void dfs(int depth, int count, int sum){
        if(finish || (sum == 100 && count == 7)) {
            finish = true;
            return;
        }

        if(sum > 100 || count >= 7 || depth > 8)
            return;

        int curr = tall.get(depth);

        visited[depth] = true;
        dfs(depth + 1, count + 1, sum + curr);

        if(finish)
            return;

        visited[depth] = false;
        dfs(depth + 1, count, sum);
    }

}