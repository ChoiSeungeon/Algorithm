import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int M, N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> que = new LinkedList<int[]>();

        que.add(new int[] {0, 0, 1});
        map[0][0] = -1;

        while(!que.isEmpty()){
            int[] curr = que.poll();
            int cx = curr[0], cy = curr[1], dis = curr[2];

            if (cx == N-1 && cy == M-1)
                return dis;

            for(int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 1)
                    continue;

                map[nx][ny] = -1;
                que.add(new int[] {nx, ny, dis+1});
            }
        }

        return -1;
    }
}
